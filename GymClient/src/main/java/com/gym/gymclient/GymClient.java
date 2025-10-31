package com.gym.gymclient;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Axton Urbina
 */
public class GymClient extends Application{

 private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("GYMAPLICATION"));
        stage.setScene(scene);
        stage.show();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(GymClient.class.getResource("/Vistas/" + fxml + ".fxml"));
    return fxmlLoader.load();
}



    public static void main(String[] args) {
        launch(args);
    }
}
