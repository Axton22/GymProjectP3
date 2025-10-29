package com.gym.gymclient.Controlador;

import com.gym.gymclient.Entity.GymRutinaEntity;
import com.gym.gymclient.Service.GymRutinaServiceFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RutinasController implements Initializable {

    @FXML
    private Button btnBRutina;
    @FXML
    private Button btnbuscartodos;
    @FXML
    private Button btneditrutina;
    @FXML
    private Button btneliminar;
    @FXML
    private Button btnguardarnuevaRU;
    @FXML
    private TextField txtidRU;
    @FXML
    private TableView<GymRutinaEntity> TablaRutina;
    @FXML
    private TableColumn<GymRutinaEntity,Long> colidR;

    @FXML
    private TableColumn<GymRutinaEntity,String > colRutina;
    @FXML
    private TableColumn<GymRutinaEntity, String> colNombre;
    @FXML
    private TableColumn<GymRutinaEntity, String> colTelefono;
    @FXML
    private TableColumn<GymRutinaEntity, String> colCorreo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txttelefono;
    @FXML
    private TextField txtcorreo;
    @FXML
    private TextField txtidEditRU;
    @FXML
    private TextField txtRutina;
    @FXML
    private TextField txtiddelete;
    private boolean rutinaCargada = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colidR.setCellValueFactory(new PropertyValueFactory<>("RutinaID"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colRutina.setCellValueFactory(new PropertyValueFactory<>("RutinaType"));
    }  
        private void mostrarAlerta(String titulo, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
}
    @FXML
    private void BtnBUSCAR_RUTINA(ActionEvent event) {
         try {
        Long id = Long.parseLong(txtidRU.getText().trim());
        GymRutinaEntity rutina = GymRutinaServiceFX.getRutinaById(id);

        if (rutina != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rutina encontrada");
            alert.setHeaderText("Información del cliente y rutina");
            alert.setContentText(
                "ID: " + rutina.getClientID() + "\n" +
                "Nombre: " + rutina.getClientName() + "\n" +
                "Correo: " + rutina.getMail() + "\n" +
                "Teléfono: " + rutina.getPhone() + "\n" +
                "Rutina: " + rutina.getRutinaType()
            );
            alert.showAndWait();
        } else {
            mostrarAlerta("No encontrado", "No se encontró ninguna rutina con el ID " + id);
        }
    } catch (NumberFormatException e) {
  mostrarAlerta("Error", "Ingrese un id valido.");
    }   
    }

    @FXML
    private void BtnBUSCAR_TODOS(ActionEvent event) {
      List<GymRutinaEntity> reservas = GymRutinaServiceFX.getAllRutinas();

        if (reservas != null) {
            ObservableList<GymRutinaEntity> datosTabla = FXCollections.observableArrayList(reservas);
            TablaRutina.setItems(datosTabla);
        } else {
            mostrarAlerta("Error", "No se pudieron obtener las reservas del servidor.");
        } 
    }
    @FXML
    private void BtnEDITAR_Rutina(ActionEvent event) {
   String idText = txtidEditRU.getText().trim();

        if (idText.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar un ID");
            return;
        }

        try {
            Long id = Long.parseLong(idText);

            if (!rutinaCargada) {
                GymRutinaEntity rutina = GymRutinaServiceFX.getRutinaById(id);

                if (rutina != null) {
                    txtNombre.setText(rutina.getClientName());
                    txtcorreo.setText(rutina.getMail());
                    txttelefono.setText(rutina.getPhone());
                    txtRutina.setText(rutina.getRutinaType());

                    rutinaCargada = true;
                    mostrarAlerta("Reserva cargada", "Modifica los datos y presiona nuevamente 'Editar'");
                } else {
                    mostrarAlerta("No encontrada", "No existe una reserva con ese ID");
                }
            } else {
                GymRutinaEntity reservaEditada = new GymRutinaEntity(
                    txtNombre.getText(),
                    txtcorreo.getText(),
                    txttelefono.getText(),
                    txtRutina.getText()
                );

                boolean exito = GymRutinaServiceFX.updateReserva(id, reservaEditada);

                if (exito) {
                    mostrarAlerta("Éxito", "Reserva actualizada correctamente");
                    rutinaCargada = false;
                    limpiarCampos();
                    BtnBUSCAR_TODOS(null);
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar la reserva");
                }
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número válido.");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtcorreo.clear();
        txttelefono.clear();
        txtRutina.clear();
        txtidEditRU.clear();
    }
    
    @FXML
    private void BtnELIMINAR(ActionEvent event) {
                try {
            int id = Integer.parseInt(txtiddelete.getText());
            boolean eliminado = GymRutinaServiceFX.deleteRutinaById(id);

            if (eliminado) {
                mostrarAlerta("Eliminado", "La reserva con ID " + id + " fue eliminada correctamente.");
                TablaRutina.getItems().clear();
            } else {
                mostrarAlerta("Error", "No se pudo eliminar la reserva con ID: " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Por favor ingresa un número válido de ID.");
        }
    }
    @FXML
    private void BtnGUARDA_RUTINA(ActionEvent event) {
        String nombre = txtNombre.getText();
        String correo = txtcorreo.getText();
        String telefono = txttelefono.getText();
        String tipoRutina = txtRutina.getText();

        GymRutinaEntity reserva = new GymRutinaEntity(nombre, correo, telefono, tipoRutina);
        boolean resultado = GymRutinaServiceFX.saveRutina(reserva);

        if (resultado) {
            mostrarAlerta("Éxito", "Reserva guardada correctamente");
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Error al guardar reserva");
        }
    }
    
    
}
