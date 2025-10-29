package com.gym.gymclient.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gymclient.Entity.GymReservasEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GymReservasServiceFX {

    private static final String BASE_URL = "http://localhost:8080/reservas"; // tu endpoint

    public static boolean saveReserva(GymReservasEntity reserva) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(reserva);

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
            return code == 200 ;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<GymReservasEntity> getAllReservas() {
        List<GymReservasEntity> reservas = new ArrayList<>();
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
                    reservas = Arrays.asList(mapper.readValue(response.toString(), GymReservasEntity[].class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public static GymReservasEntity getReservaById(Long id) {
        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            int code = con.getResponseCode();
            if (code == 200) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(con.getInputStream(), GymReservasEntity.class);
            } else {
                System.out.println("Error al obtener reserva. Código: " + code);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteReservaById(int id) {
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

    public static boolean updateReserva(Long id, GymReservasEntity reserva) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(reserva);

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
