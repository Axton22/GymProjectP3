package com.gym.gymclient.Controlador;

import com.gym.gymclient.Entity.GymReservasEntity;
import com.gym.gymclient.Service.GymReservasServiceFX;
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

public class ReservasController implements Initializable {

    @FXML
    private Button btnBreserva;
    @FXML
    private Button btnbuscartodos;
    @FXML
    private Button btneditreserva;
    @FXML
    private Button btneliminar;
    @FXML
    private Button btnguardarreserva;
    @FXML
    private TableView<GymReservasEntity> TablaReserva;
    @FXML
    private TableColumn<GymReservasEntity, Long> colid;
    @FXML
    private TableColumn<GymReservasEntity, String> colNombre;
    @FXML
    private TableColumn<GymReservasEntity, String> colTelefono;
    @FXML
    private TableColumn<GymReservasEntity, String> colCorreo;
    @FXML
    private TableColumn<GymReservasEntity, String> colReserva;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txttelefono;
    @FXML
    private TextField txtcorreo;
    @FXML
    private TextField txtReserva;
    @FXML
    private TextField txtidR;
    @FXML
    private TextField txtidEditR;
    @FXML
    private TextField txtiddeleteR;

    private boolean reservaCargada = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colid.setCellValueFactory(new PropertyValueFactory<>("ReservaID"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colReserva.setCellValueFactory(new PropertyValueFactory<>("ReservaType"));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void BtnBUSCAR_RESERVA(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtidR.getText().trim());
            GymReservasEntity reserva = GymReservasServiceFX.getReservaById(id);

            if (reserva != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reserva encontrada");
                alert.setHeaderText("Información de la reserva y el cliente");
                alert.setContentText(
                    "ID: " + reserva.getReservaID() + "\n" +
                    "Nombre: " + reserva.getClientName() + "\n" +
                    "Correo: " + reserva.getMail() + "\n" +
                    "Teléfono: " + reserva.getPhone() + "\n" +
                    "Tipo de reserva: " + reserva.getReservaType()
                );
                alert.showAndWait();
            } else {
                mostrarAlerta("No encontrada", "No se encontró ninguna reserva con el ID " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese un ID válido.");
        }
    }

    @FXML
    private void BtnBUSCAR_TODOS(ActionEvent event) {
        List<GymReservasEntity> reservas = GymReservasServiceFX.getAllReservas();

        if (reservas != null) {
            ObservableList<GymReservasEntity> datosTabla = FXCollections.observableArrayList(reservas);
            TablaReserva.setItems(datosTabla);
        } else {
            mostrarAlerta("Error", "No se pudieron obtener las reservas del servidor.");
        }
    }

    @FXML
    private void BtnEDITAR_RESERVA(ActionEvent event) {
        String idText = txtidEditR.getText().trim();

        if (idText.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar un ID");
            return;
        }

        try {
            Long id = Long.parseLong(idText);

            if (!reservaCargada) {
                GymReservasEntity reserva = GymReservasServiceFX.getReservaById(id);

                if (reserva != null) {
                    txtNombre.setText(reserva.getClientName());
                    txtcorreo.setText(reserva.getMail());
                    txttelefono.setText(reserva.getPhone());
                    txtReserva.setText(reserva.getReservaType());

                    reservaCargada = true;
                    mostrarAlerta("Reserva cargada", "Modifica los datos y presiona nuevamente 'Editar'");
                } else {
                    mostrarAlerta("No encontrada", "No existe una reserva con ese ID");
                }
            } else {
                GymReservasEntity reservaEditada = new GymReservasEntity(
                    txtNombre.getText(),
                    txtcorreo.getText(),
                    txttelefono.getText(),
                    txtReserva.getText()
                );

                boolean exito = GymReservasServiceFX.updateReserva(id, reservaEditada);

                if (exito) {
                    mostrarAlerta("Éxito", "Reserva actualizada correctamente");
                    reservaCargada = false;
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
        txtReserva.clear();
        txtidEditR.clear();
    }
    @FXML
    private void BtnELIMINAR(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtiddeleteR.getText());
            boolean eliminado = GymReservasServiceFX.deleteReservaById(id);

            if (eliminado) {
                mostrarAlerta("Eliminado", "La reserva con ID " + id + " fue eliminada correctamente.");
                TablaReserva.getItems().clear();
            } else {
                mostrarAlerta("Error", "No se pudo eliminar la reserva con ID: " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Por favor ingresa un número válido de ID.");
        }
    }

    @FXML
    private void BtnGUARDA_RESERVA(ActionEvent event) {
        String nombre = txtNombre.getText();
        String correo = txtcorreo.getText();
        String telefono = txttelefono.getText();
        String tipoReserva = txtReserva.getText();

        GymReservasEntity reserva = new GymReservasEntity(nombre, correo, telefono, tipoReserva);
        boolean resultado = GymReservasServiceFX.saveReserva(reserva);

        if (resultado) {
            mostrarAlerta("Éxito", "Reserva guardada correctamente");
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Error al guardar reserva");
        }
    }
}
