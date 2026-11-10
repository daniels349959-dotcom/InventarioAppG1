package cesur.dam.inventarioappg1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneNavigator {
    private static Stage stage;

    public static void setStage(Stage stage) {
        SceneNavigator.stage = stage;
    }

    // Carga el FXML y cambia la escena de la ventana principal.
    public static void navigateTo(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(AppG1.class.getResource("views/" + fxml));
            Scene scene = new Scene(loader.load());
            stage.setTitle(title);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido abrir la vista " + fxml, e);
        }
    }
}
