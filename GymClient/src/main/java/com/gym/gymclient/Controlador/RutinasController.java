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
    private TableColumn<GymRutinaEntity, Long> colidR;
    @FXML
    private TableColumn<GymRutinaEntity, String> colNameR;
    @FXML
    private TableColumn<GymRutinaEntity, String> coldia;
    @FXML
    private TableColumn<GymRutinaEntity, String> colinicio;
    @FXML
    private TableColumn<GymRutinaEntity, String> colfin;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtidEditRU;
    @FXML
    private TextField txtiddelete;
    @FXML
    private TextField txtdia;
    @FXML
    private TextField txtincio;
    @FXML
    private TextField txtfin;

    private boolean rutinaCargada = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Los nombres deben coincidir exactamente con los getters de GymRutinaEntity
        colidR.setCellValueFactory(new PropertyValueFactory<>("routineID"));
        colNameR.setCellValueFactory(new PropertyValueFactory<>("routineName"));
        coldia.setCellValueFactory(new PropertyValueFactory<>("dayWeek"));
        colinicio.setCellValueFactory(new PropertyValueFactory<>("starHour"));
        colfin.setCellValueFactory(new PropertyValueFactory<>("endHour"));
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // 🔍 Buscar una rutina por ID
    @FXML
    private void BtnBUSCAR_RUTINA(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtidRU.getText().trim());
            GymRutinaEntity rutina = GymRutinaServiceFX.getRutinaById(id);

            if (rutina != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rutina encontrada");
                alert.setHeaderText("Información de la rutina");
                alert.setContentText(
                        "ID: " + rutina.getRoutineID()+ "\n" +
                        "Nombre: " + rutina.getRoutineName()+ "\n" +
                        "Día: " + rutina.getDayWeek()+ "\n" +
                        "Hora inicio: " + rutina.getEndHour()+ "\n" +
                        "Hora fin: " + rutina.getEndHour()
                );
                alert.showAndWait();
            } else {
                mostrarAlerta("No encontrado", "No se encontró ninguna rutina con el ID " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingrese un ID válido.");
        }
    }

    // 📋 Mostrar todas las rutinas
    @FXML
    private void BtnBUSCAR_TODOS(ActionEvent event) {
        List<GymRutinaEntity> rutinas = GymRutinaServiceFX.getAllRutinas();

        if (rutinas != null) {
            ObservableList<GymRutinaEntity> datosTabla = FXCollections.observableArrayList(rutinas);
            TablaRutina.setItems(datosTabla);
        } else {
            mostrarAlerta("Error", "No se pudieron obtener las rutinas del servidor.");
        }
    }

    // ✏️ Editar una rutina
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
                    txtNombre.setText(rutina.getRoutineName());
                    txtincio.setText(rutina.getStarHour());
                    txtdia.setText(rutina.getDayWeek());
                    txtfin.setText(rutina.getEndHour());

                    rutinaCargada = true;
                    mostrarAlerta("Rutina cargada", "Modifica los datos y presiona nuevamente 'Editar'");
                } else {
                    mostrarAlerta("No encontrada", "No existe una rutina con ese ID");
                }
            } else {
                GymRutinaEntity rutinaEditada = new GymRutinaEntity(
                        txtNombre.getText(),
                        txtdia.getText(),
                        txtincio.getText(),
                        txtfin.getText()
                );

                boolean exito = GymRutinaServiceFX.updateRutina(id, rutinaEditada);

                if (exito) {
                    mostrarAlerta("Éxito", "Rutina actualizada correctamente");
                    rutinaCargada = false;
                    limpiarCampos();
                    BtnBUSCAR_TODOS(null);
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar la rutina");
                }
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número válido.");
        }
    }

    // 🗑️ Eliminar una rutina
    @FXML
    private void BtnELIMINAR(ActionEvent event) {
        try {
            Long id = Long.parseLong(txtiddelete.getText());
            boolean eliminado = GymRutinaServiceFX.deleteRutinaById(id);

            if (eliminado) {
                mostrarAlerta("Eliminado", "La rutina con ID " + id + " fue eliminada correctamente.");
                TablaRutina.getItems().clear();
            } else {
                mostrarAlerta("Error", "No se pudo eliminar la rutina con ID: " + id);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Por favor ingresa un número válido de ID.");
        }
    }

    // 💾 Guardar una nueva rutina
    @FXML
    private void BtnGUARDA_RUTINA(ActionEvent event) {
        String nombrerutina = txtNombre.getText().trim();
        String dia = txtdia.getText().trim();
        String horaI = txtincio.getText().trim();
        String horaF = txtfin.getText().trim();

        if (nombrerutina.isEmpty() || dia.isEmpty() || horaI.isEmpty() || horaF.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return;
        }

        GymRutinaEntity nuevaRutina = new GymRutinaEntity(nombrerutina, dia, horaI, horaF);
        boolean resultado = GymRutinaServiceFX.saveRutina(nuevaRutina);

        if (resultado) {
            mostrarAlerta("Éxito", "Rutina guardada correctamente");
            limpiarCampos();
            BtnBUSCAR_TODOS(null);
        } else {
            mostrarAlerta("Error", "Error al guardar rutina");
        }
    }

    // 🔄 Limpieza general de campos
    private void limpiarCampos() {
        txtNombre.clear();
        txtdia.clear();
        txtincio.clear();
        txtfin.clear();
        txtidEditRU.clear();
        txtidRU.clear();
        txtiddelete.clear();
    }
}