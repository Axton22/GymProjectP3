package com.gym.gymclient.Controlador;

import com.gym.gymclient.Entity.GymReservasEntity;
import com.gym.gymclient.Service.GymReservasServiceFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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

    @FXML private Button btnBreserva;
    @FXML private Button btnbuscartodos;
    @FXML private Button btneditreserva;
    @FXML private Button btneliminar;
    @FXML private Button btnguardarreserva;

    @FXML private TableView<GymReservasEntity> TablaReserva;
    @FXML private TableColumn<GymReservasEntity, Long> colidReserva;
    @FXML private TableColumn<GymReservasEntity, String> colFecha;
    @FXML private TableColumn<GymReservasEntity, String> colHora;
    @FXML private TableColumn<GymReservasEntity, String> colEstado;

    @FXML private TextField txtidR;
    @FXML private TextField txtidEditR;
    @FXML private TextField txtiddeleteR;
    @FXML private TextField txtfecha;
    @FXML private TextField txthora;
    @FXML private TextField txtestado;

    private boolean reservaCargada = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar columnas
        colidReserva.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("reservationHour"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("state"));

        // Cargar tabla al inicio
        refreshTable();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void refreshTable() {
        List<GymReservasEntity> reservas = GymReservasServiceFX.getAllReservas();
        if (reservas != null) {
            TablaReserva.setItems(FXCollections.observableArrayList(reservas));
        }
    }

    @FXML
    private void BtnBUSCAR_RESERVA(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtidR.getText().trim());
            GymReservasEntity reserva = GymReservasServiceFX.getReservaById(id);

            if (reserva != null) {
                mostrarAlerta("Reserva encontrada",
                    "ID: " + reserva.getReservationID() + "\n" +
                    "Fecha: " + reserva.getReservationDate() + "\n" +
                    "Hora: " + reserva.getReservationHour() + "\n" +
                    "Estado: " + reserva.getState());
            } else {
                mostrarAlerta("No encontrada", "No se encontró ninguna reserva con el ID " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese un ID válido.");
        }
    }

    @FXML
    private void BtnBUSCAR_TODOS(ActionEvent event) {
        refreshTable();
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
                    txtfecha.setText(reserva.getReservationDate());
                    txthora.setText(reserva.getReservationHour());
                    txtestado.setText(reserva.getState());
                    reservaCargada = true;
                    mostrarAlerta("Reserva cargada", "Modifica los datos y presiona nuevamente 'Editar'");
                } else {
                    mostrarAlerta("No encontrada", "No existe una reserva con ese ID");
                }
            } else {
                GymReservasEntity reservaEditada = new GymReservasEntity(
                    txtfecha.getText(),
                    txthora.getText(),
                    txtestado.getText()
                );

                boolean exito = GymReservasServiceFX.updateReserva(id, reservaEditada);

                if (exito) {
                    mostrarAlerta("Éxito", "Reserva actualizada correctamente");
                    reservaCargada = false;
                    limpiarCampos();
                    refreshTable();
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar la reserva");
                }
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número válido.");
        }
    }

    @FXML
    private void BtnELIMINAR(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtiddeleteR.getText());
            boolean eliminado = GymReservasServiceFX.deleteReservaById(id);
            if (eliminado) {
                mostrarAlerta("Eliminado", "Reserva eliminada correctamente.");
                refreshTable();
            } else {
                mostrarAlerta("Error", "No se pudo eliminar la reserva.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Ingrese un ID válido.");
        }
    }

    @FXML
    private void BtnGUARDA_RESERVA(ActionEvent event) {
        String fecha = txtfecha.getText().trim();
        String hora = txthora.getText().trim();
        String estado = txtestado.getText().trim();

        if (fecha.isEmpty() || hora.isEmpty() || estado.isEmpty()) {
            mostrarAlerta("Error", "Debe completar todos los campos.");
            return;
        }

        GymReservasEntity reserva = new GymReservasEntity(fecha, hora, estado);
        boolean resultado = GymReservasServiceFX.saveReserva(reserva);

        if (resultado) {
            mostrarAlerta("Éxito", "Reserva guardada correctamente");
            limpiarCampos();
            refreshTable();
        } else {
            mostrarAlerta("Error", "Error al guardar la reserva");
        }
    }

    private void limpiarCampos() {
        txtfecha.clear();
        txthora.clear();
        txtestado.clear();
        txtidEditR.clear();
        txtidR.clear();
        txtiddeleteR.clear();
    }
}
