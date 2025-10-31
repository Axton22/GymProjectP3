package com.gym.gymclient.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gymclient.Entity.GymRutinaEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GymRutinaServiceFX {

    private static final String BASE_URL = "http://localhost:8080/rutinas";
    private static final ObjectMapper mapper = new ObjectMapper();

 
    public static boolean saveRutina(GymRutinaEntity rutina) {
        try {
            String jsonString = mapper.writeValueAsString(rutina);

            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonString.getBytes(StandardCharsets.UTF_8));
            }

            int code = con.getResponseCode();
            return code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public static List<GymRutinaEntity> getAllRutinas() {
        List<GymRutinaEntity> rutinas = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    rutinas = Arrays.asList(mapper.readValue(response.toString(), GymRutinaEntity[].class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rutinas;
    }

    public static GymRutinaEntity getRutinaById(Long id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return mapper.readValue(con.getInputStream(), GymRutinaEntity.class);
            } else {
                System.out.println("⚠️ Error al obtener rutina. Código: " + con.getResponseCode());
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean deleteRutinaById(Long id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            int code = con.getResponseCode();
            return code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_NO_CONTENT;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRutina(Long id, GymRutinaEntity rutina) {
        try {
            String jsonString = mapper.writeValueAsString(rutina);

            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonString.getBytes(StandardCharsets.UTF_8));
            }

            int code = con.getResponseCode();
            return code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_NO_CONTENT;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
