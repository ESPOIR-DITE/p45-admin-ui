package sample.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartyController implements Initializable {
    @FXML
    private AnchorPane anchorPanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(getCurrentStage());
        if (selectedFile != null) {
            //mainStage.display(selectedFile);
        }
    }
    private final ObjectProperty<Stage> currentStage = new SimpleObjectProperty<>(null);
    public final ObjectProperty<Stage> currentStageProperty() {
        return this.currentStage;
    }
    public final javafx.stage.Stage getCurrentStage() {
        return this.currentStageProperty().get();
    }

    @FXML
    private void cancelParty() throws IOException {
        Pane rootPane1 = FXMLLoader.load(getClass().getResource("view/partyform.fxml"));
        rootPane1.getChildren().remove(rootPane1);

    }
}
