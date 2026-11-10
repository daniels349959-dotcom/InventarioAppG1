package cesur.dam.inventarioappg1;

import javafx.application.Application;
import javafx.stage.Stage;

public class AppG1 extends Application {
    @Override
    public void start(Stage stage) {
        SceneNavigator.setStage(stage);
        SceneNavigator.navigateTo("login-view.fxml","Login");
        stage.show();
    }
}
