package com.gym.gymclient.Controlador;


import com.gym.gymclient.Entity.GymClientEntity;
import com.gym.gymclient.Service.GymClientServiceFX;
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

public class ClientesController implements Initializable {

    @FXML
    private Button btnBcliente;
    @FXML
    private Button btnbuscartodos;
    @FXML
    private Button btneditcliente;
    @FXML
    private Button btneliminar;
    @FXML
    private Button btnguardarnuevo;
    @FXML
    private TextField txtidB;
    @FXML
    private TableView<GymClientEntity> Tablacliente;
    @FXML
    private TableColumn<GymClientEntity, Long> colid;
    @FXML
    private TableColumn<GymClientEntity, String> colNombre;
    @FXML
    private TableColumn<GymClientEntity, String> colCorreo;
    @FXML
    private TableColumn<GymClientEntity, String> colTelefono;
    @FXML
   private TableColumn<GymClientEntity, String> colSuscripcion;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txttelefono;
    @FXML
    private TextField txtcorreo;
    @FXML
    private TextField txtidEdit;
    @FXML
    private TextField txtsus;
    @FXML
    private TextField txtiddelete;
    private boolean clienteCargado = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    colid.setCellValueFactory(new PropertyValueFactory<>("clientID"));
    colNombre.setCellValueFactory(new PropertyValueFactory<>("clientName"));
    colCorreo.setCellValueFactory(new PropertyValueFactory<>("mail"));
    colTelefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
    colSuscripcion.setCellValueFactory(new PropertyValueFactory<>("subscriptionType"));
    } 
    private void mostrarAlerta(String titulo, String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(titulo);
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
}
    @FXML
    private void BtnBUSCAR_CLIENTE(ActionEvent event) {
    try {
        Long id = Long.parseLong(txtidB.getText().trim());
        GymClientEntity cliente = GymClientServiceFX.getClientById(id);

        if (cliente != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente encontrado");
            alert.setHeaderText("Información del cliente");
            alert.setContentText(
                "ID: " + cliente.getClientID() + "\n" +
                "Nombre: " + cliente.getClientName() + "\n" +
                "Correo: " + cliente.getMail() + "\n" +
                "Teléfono: " + cliente.getPhone() + "\n" +
                "Suscripción: " + cliente.getSubscriptionType()
            );
            alert.showAndWait();
        } else {
            mostrarAlerta("No encontrado", "No se encontró ningún cliente con el ID " + id);
        }
    } catch (NumberFormatException e) {
  mostrarAlerta("Error", "Ingrese un id valido.");
    }
}

    @FXML
    private void BtnBUSCAR_TODOS(ActionEvent event) {
    List<GymClientEntity> clientes = GymClientServiceFX.getAllClients();

    if (clientes != null) {
        ObservableList<GymClientEntity> datosTabla = FXCollections.observableArrayList(clientes);
        Tablacliente.setItems(datosTabla);
    } else {
        mostrarAlerta("Error", "No se pudieron obtener los clientes del servidor.");
    }
}

@FXML
private void BtnEDITAR_CLIENTE(ActionEvent event) {
    String idText = txtidEdit.getText().trim();

    if (idText.isEmpty()) {
        mostrarAlerta("Error", "Debe ingresar un ID");
        return;
    }

    try {
        Long id = Long.parseLong(idText);

        if (!clienteCargado) {
            GymClientEntity cliente = GymClientServiceFX.getClientById(id);

            if (cliente != null) {
                txtNombre.setText(cliente.getClientName());
                txtcorreo.setText(cliente.getMail());
                txttelefono.setText(cliente.getPhone());
                txtsus.setText(cliente.getSubscriptionType());

                clienteCargado = true; // ahora estamos en modo edición
                mostrarAlerta("Cliente cargado", "Modifica los datos y presiona nuevamente 'Editar'");
            } else {
                mostrarAlerta("No encontrado", "No existe un cliente con ese ID");
            }
        } else {
            GymClientEntity clienteEditado = new GymClientEntity(
                txtNombre.getText(),
                txtcorreo.getText(),
                txttelefono.getText(),
                txtsus.getText()
            );

            boolean exito = GymClientServiceFX.updateClient(id, clienteEditado);

            if (exito) {
                mostrarAlerta("Éxito", "Cliente actualizado correctamente");
                clienteCargado = false; // reseteamos
                limpiarCampos();
                BtnBUSCAR_TODOS(null); //actualizar la tabla
            } else {
                mostrarAlerta("Error", "No se pudo actualizar el cliente");
            }
        }

    } catch (NumberFormatException e) {
        mostrarAlerta("Error", "El ID debe ser un número");
    }
}

private void limpiarCampos() {
    txtNombre.clear();
    txtcorreo.clear();
    txttelefono.clear();
    txtsus.clear();
    txtidEdit.clear();
}

@FXML
private void BtnELIMINAR(ActionEvent event) {
    try {
        int id = Integer.parseInt(txtiddelete.getText());
        boolean eliminado = GymClientServiceFX.deleteClientById(id);

        if (eliminado) {
            mostrarAlerta("Eliminado", "El cliente con ID " + id + " fue eliminado correctamente.");
            Tablacliente.getItems().clear();
        } else {
            mostrarAlerta("Error", "No se pudo eliminar el cliente con ID: " + id);
        }
    } catch (NumberFormatException e) {
        mostrarAlerta("Error de formato", "Por favor ingresa un número válido de ID.");
    }
}

    @FXML
    private void BtnGUARDA_CLIENTE(ActionEvent event) {
    String nombre = txtNombre.getText();
    String correo = txtcorreo.getText();
    String telefono = txttelefono.getText();
    String suscripcion = txtsus.getText();

    GymClientEntity client = new GymClientEntity(nombre, correo, telefono, suscripcion);
    boolean resultado = GymClientServiceFX.saveClient(client);

    if (resultado) {
        mostrarAlerta("ECHO", "Cliente guardado correctamente");
        limpiarCampos();
    } else {
   
        mostrarAlerta("ERROR", "Error al guardar cliente");
    }

    }
    
}
