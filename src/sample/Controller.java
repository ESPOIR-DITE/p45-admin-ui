package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import sample.domain.user.Party;
import sample.domain.vote.Voter;
import sample.factory.VoterFactory;
import sample.io.user.PartyIO;
import sample.io.vote.Voter_io;
import sample.util.ImageResizer;
import sample.util.ImageService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
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

    @FXML
    private TextField partyName;
    @FXML
    private TextField abbviation;
    @FXML
    private TextField partyPicture;
    @FXML
    private Button addPartyBtn;
    @FXML
    private Label newParty;

    @FXML
    private TextField locationName;
    @FXML
    private TextField locationType;
    @FXML
    private TextField parentLocation;
    @FXML
    private Button addLocationBtn;
    @FXML
    private Label newLocation;
    @FXML
    private TableView resultTable;

    @FXML
    private TextField voterName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField idNumber;
    @FXML
    private TextField phoneNumber;
    @FXML
    private Button addVoterBtn;
    @FXML
    private Label newVoter;
    @FXML
    private Pane partyPanel;
    @FXML
    private Pane locationPanel;

    @FXML
    private Pane notificationPanel;
    @FXML
    private Label notificationContent;
    @FXML
    private Pane voterPanel;

    @FXML
    private Pane homePanel;
    @FXML
    private Button notificationBTN;
    @FXML
    private Label notificationText;

    private ImageService imageService;

    private byte[] resized;

    @FXML
     private Pane webCamPanel;
    @FXML
     private MediaView mediaPanel;

    private String fileName = Paths.get("").toAbsolutePath().toString()+"output.jpg";
    private File file_save_path = new File(fileName);


    Pane rootPane1 = FXMLLoader.load(getClass().getResource("view/partyform.fxml"));

    Voter_io voter_io = Voter_io.getVoter_io();
    PartyIO partyIO = PartyIO.getPartyIO();

    public Controller() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void closeButtonAction(){
            System.exit(0);
    }

    /***
     * This part deals with methods that calls Notification
     */
    @FXML
    private void removeNotificationPanel(){
        notificationPanel.setVisible(false);
    }
    private void showNotification(String notificationMassage, String content){

        notificationPanel.setVisible(true);

        if (!notificationMassage.equals("")) notificationText.setText(notificationMassage);
        if(!content.equals("")) notificationContent.setText(content);
        else notificationContent.setVisible(false);
    }

    /***
     * This part deals with methods that calls panels
     */
    @FXML
    private void home() throws IOException {
        panelVisibility("home");
    }

    @FXML
    private void party() throws IOException {
        panelVisibility("party");
    }
    @FXML
    public void voterPanel(){
        panelVisibility("voter");
    }
    @FXML
    private void result() throws IOException {
        panelVisibility("result");
    }
    @FXML
    private void location() throws IOException {
        panelVisibility("location");
    }



    /***
     * This part deals with methods that send data to the Api
     */
    @FXML
    private void createNewVoter() throws IOException {
        int resultCode = 0;
        String name = voterName.getText();
        String last = lastName.getText();
        String id = idNumber.getText();
        String phone = phoneNumber.getText();


        // Voter

        try{
//            imageService.readAndWriteFile("glory.jpg");
//            ImageResizer.getResizedImage();
            //resized = imageService.convertToBytes();
            byte[] bytes = new byte[001];

            Voter voter = VoterFactory.getVoter(id,name,last,phone,bytes);
            resultCode = voter_io.create(voter);
            resized=null;
            if (resultCode !=200) {
                showNotification("error creating voter","Network or server error");
            }else {
                showNotification("You have successfully created new Voter","Name: "+name);
                voterName.setText("");
                lastName.setText("");
                idNumber.setText("");
                phoneNumber.setText("");
            }
        }catch (IOException ex){
            showNotification("error reading and writing the file","");
        }



    }
    @FXML
    private void openFileExplore() {
        resized = null;
        imageService = new ImageService();
        FileChooser fileChooser = new FileChooser();
        // fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(null);
        //int result = fileChooser.showOpenDialog(null);
        if (selectedFile.exists()) {
            //File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            try{
                imageService.readAndWriteFile(selectedFile.getAbsolutePath());
                ImageResizer.getResizedImage();
                resized = imageService.convertToBytes();
            }catch (IOException ex){
                showNotification("error reading and writing the file","");
            }
        }
    }
    @FXML
    private void addParty() throws IOException {
        String pName = partyName.getText();
        String pabbviation = abbviation.getText();
        if(!pName.equals("") && !pabbviation.equals("")){
            Party party = new Party("",pName,pabbviation,resized);
            //Clearing the resized.
            resized=null;
            int result = partyIO.create(party);
            if (result !=200) {
                showNotification("error creating party","Network or server error");
            }else {
                showNotification("You have successfully created a party","Party: "+pName);
                partyName.setText("");
                abbviation.setText("");
            }
        }
    }

    /***
     * This part deals with methods that makes panels visible and invisible
     */

    public void panelVisibility(String paneName){
        removeAllPane();
        switch (paneName){
            case "location": locationPanel.setVisible(true);
                System.out.println("in location");
                break;
            case "party": partyPanel.setVisible(true);
                System.out.println("in party");
                break;
            case "voter": voterPanel.setVisible(true);
                System.out.println("in voter");
                break;
            case "result": resultTable.setVisible(true);
                System.out.println("in result");
                break;
            case "home": homePanel.setVisible(true);
                System.out.println("in home");
                break;
        }
    }

    //This method makes all the Panes invisible.
    public void removeAllPane(){
        locationPanel.setVisible(false);
        partyPanel.setVisible(false);
        voterPanel.setVisible(false);
        resultTable.setVisible(false);
        homePanel.setVisible(false);
    }

    @FXML
    public void openWebCamCapture(){
        PictureClass myThread = new PictureClass();
        myThread.start();
        //webCamPanel.getChildren().add(window,n)
    }




}
