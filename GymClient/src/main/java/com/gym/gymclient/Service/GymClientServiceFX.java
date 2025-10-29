
package com.gym.gymclient.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.gymclient.Entity.GymClientEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GymClientServiceFX {

    private static final String BASE_URL = "http://localhost:8080/clientes"; // tu endpoint

    public static boolean saveClient(GymClientEntity client) {
        try {
  
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(client);

            URL url = new URL(BASE_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setDoOutput(true);

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }


            int code = con.getResponseCode();
            return code == 200 || code == 201;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<GymClientEntity> getAllClients() {
    List<GymClientEntity> clients = new ArrayList<>();
    try {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        int code = con.getResponseCode();
        if (code == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                ObjectMapper mapper = new ObjectMapper();
                clients = Arrays.asList(mapper.readValue(response.toString(), GymClientEntity[].class));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return clients;
}
    public static GymClientEntity getClientById(Long id) {
    try {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        int code = con.getResponseCode();
        if (code == 200) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(con.getInputStream(), GymClientEntity.class);
        } else {
            System.out.println("Error al obtener cliente. Código: " + code);
            return null;
        }

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
public static boolean deleteClientById(int id) {
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
public static boolean updateClient(Long id, GymClientEntity client) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(client);

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
        return code == 200;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


}
