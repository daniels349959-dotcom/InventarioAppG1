package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.SceneNavigator;
import cesur.dam.inventarioappg1.data.DataSourceFactory;
import cesur.dam.inventarioappg1.data.EquipoJdbcDAO;
import cesur.dam.inventarioappg1.models.Equipo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class DetailsController {
    @FXML
    private TextField txtName;
    @FXML
    private ChoiceBox<String> choiceCategory;
    @FXML
    private CheckBox chkAvailable;

    @FXML
    public void initialize() {
        choiceCategory.getItems().addAll("Keyboard", "Laptop", "Gamepad",
                "Monitor", "Computer", "Printer");
        choiceCategory.getSelectionModel().selectFirst();
    }

    @FXML
    public void save() {
        String name = txtName.getText().trim();
        String category = choiceCategory.getValue();
        if (name.isEmpty() || category == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Introduce el nombre y selecciona una categoría.").showAndWait();
            return;
        }

        Equipo equipment = new Equipo();
        equipment.setNombre(name);
        equipment.setCategoria(category);
        equipment.setDisponible(chkAvailable.isSelected());

        try {
            EquipoJdbcDAO dao = new EquipoJdbcDAO(DataSourceFactory.getDataSource());
            dao.save(equipment);
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR,
                    "No se ha guardado el equipo. Comprueba la conexión y la base de datos.")
                    .showAndWait();
            return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Equipo guardado correctamente.").showAndWait();
        SceneNavigator.navigateTo("main-view.fxml", "Vista principal");
    }

    @FXML
    public void goBack() {
        SceneNavigator.navigateTo("main-view.fxml", "Vista principal");
    }
}
