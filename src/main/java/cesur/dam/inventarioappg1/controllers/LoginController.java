package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.SceneNavigator;
import javafx.fxml.FXML;

public class LoginController {
    @FXML
    public void login() {
        SceneNavigator.navigateTo("main-view.fxml", "Vista principal");
    }
}
