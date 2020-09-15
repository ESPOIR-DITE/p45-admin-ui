package sample;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private AnchorPane anchorPanel;
    @FXML
    private PieChart chart3;

    @FXML
    private LineChart chart2;

    @FXML
    private BarChart chart1;
    Pane rootPane1 = FXMLLoader.load(getClass().getResource("view/partyform.fxml"));

    public Controller() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeButtonAction(){
            System.exit(0);
    }
    @FXML
    private void removePanel() throws IOException {
        if(rootPane1!=null){
            anchorPanel.getChildren().remove(rootPane1);
        }
        anchorPanel.getChildren().remove(chart1);
        anchorPanel.getChildren().remove(chart2);
        anchorPanel.getChildren().remove(chart3);
        //anchorPanel.getChildren().clear();
    }
    @FXML
    private void home() throws IOException {
        removePanel();
        anchorPanel.getChildren().add(chart1);
        anchorPanel.getChildren().add(chart2);
        anchorPanel.getChildren().add(chart3);
    }

    @FXML
    private void party() throws IOException {
        removePanel();
        anchorPanel.getChildren().addAll(rootPane1);
    }
}
