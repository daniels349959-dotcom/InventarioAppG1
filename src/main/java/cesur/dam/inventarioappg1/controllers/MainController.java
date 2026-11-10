package cesur.dam.inventarioappg1.controllers;

import cesur.dam.inventarioappg1.SceneNavigator;
import cesur.dam.inventarioappg1.data.DataSourceFactory;
import cesur.dam.inventarioappg1.data.EquipoJdbcDAO;
import cesur.dam.inventarioappg1.models.Equipo;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
    @FXML
    private TableView<Equipo> table;
    @FXML
    private TableColumn<Equipo, String> cId;
    @FXML
    private TableColumn<Equipo, String> cName;
    @FXML
    private TableColumn<Equipo, String> cCategory;
    @FXML
    private TableColumn<Equipo, String> cAvailability;

    @FXML
    public void initialize() {
        // Cada columna muestra un dato del equipo de esa fila.
        cId.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getId().toString()));
        cName.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getNombre()));
        cCategory.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getCategoria()));
        cAvailability.setCellValueFactory(row -> {
            if (row.getValue().getDisponible()) {
                return new SimpleStringProperty("disponible");
            } else {
                return new SimpleStringProperty("no disponible");
            }
        });

        try {
            EquipoJdbcDAO dao = new EquipoJdbcDAO(DataSourceFactory.getDataSource());
            for (Equipo equipo : dao.findAll()) {
                table.getItems().add(equipo);
            }
        } catch (RuntimeException e) {
            table.setPlaceholder(new Label("No se han podido cargar los datos"));
            new Alert(Alert.AlertType.ERROR, "No se ha podido conectar con la base de datos.").showAndWait();
        }
    }

    @FXML
    public void create() {
        SceneNavigator.navigateTo("create-view.fxml", "Crear equipo");
    }

    @FXML
    public void logout() {
        SceneNavigator.navigateTo("login-view.fxml", "Login");
    }
}
