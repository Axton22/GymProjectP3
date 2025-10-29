package com.gym.gymclient.Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GYMAPLICATIONController implements Initializable {

    @FXML
    private Button btnclientes;
    @FXML
    private Button btnreservaciones;
    @FXML
    private Button btnrutinas;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    }    
    
    @FXML
    private void BtnClientes(){
       try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Clientes.fxml"));
            Parent root = loader.load();

            // Crear nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setTitle("Clientes");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }  

    @FXML
    private void BtnReserva(ActionEvent event) {
               try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Reservas.fxml"));
            Parent root = loader.load();

            // Crear nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setTitle("Reservas");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BtnRutinas(ActionEvent event) {
               try {
            // Cargar el FXML de la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vistas/Rutinas.fxml"));
            Parent root = loader.load();

            // Crear nueva ventana (Stage)
            Stage stage = new Stage();
            stage.setTitle("Rutinas");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
    

