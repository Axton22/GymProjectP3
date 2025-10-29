package com.gym.gymclient.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gymclient.Entity.GymRutinaEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GymRutinaServiceFX {
        private static final String BASE_URL = "http://localhost:8080/rutinas";
        
        public static boolean saveRutina(GymRutinaEntity rutina){       
      try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(rutina);

            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            return code == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
            
        }

        public static List<GymRutinaEntity> getAllRutinas(){
          List<GymRutinaEntity> rutina = new ArrayList<>();
          
           try {
            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int code = con.getResponseCode();
            if (code == 200) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    ObjectMapper mapper = new ObjectMapper();
                    rutina = Arrays.asList(mapper.readValue(response.toString(), GymRutinaEntity[].class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rutina;
            
        }
        public static GymRutinaEntity getRutinaById(Long id) {
            
            try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int code = con.getResponseCode();
            if (code == 200) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(con.getInputStream(), GymRutinaEntity.class);
            } else {
                System.out.println("Error al obtener reserva. Código: " + code);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }
        
        public static boolean deleteRutinaById(int id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            int responseCode = con.getResponseCode();
            return responseCode == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        
       public static boolean updateReserva(Long id, GymRutinaEntity rutina) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(rutina);

            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = con.getResponseCode();
            return code == 200 || code == 204;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        
        
}
