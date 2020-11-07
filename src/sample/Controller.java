package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
//import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import sample.domain.admin.Admin;
import sample.domain.user.Party;
import sample.domain.vote.Voter;
import sample.factory.VoterFactory;
import sample.io.admin.AdminIO;
import sample.io.user.PartyIO;
import sample.io.vote.NewVoteIo;
import sample.io.vote.VoteResult;
import sample.io.vote.Voter_io;
import sample.util.ImageService;
import sample.util.MyNotification;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    private Pane resultTable;

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
    private Pane viewParty;

    @FXML
    private Pane homePanel;
    @FXML
    private Button notificationBTN;
    @FXML
    private Label notificationText;

    @FXML
    private Pane partyEditPanel;
    @FXML
    private Pane updatePanel;
    @FXML
    private TextField partyEditName;

    private ImageService imageService;

    @FXML
    private TextField updatePartyName;

    @FXML
    private TextField updateAbbviation;

    @FXML
    private TextField partyAbreviation;
    @FXML
    private Button deleteParty;
    @FXML
    private Button updatePartyBtn;
    @FXML
    private ListView myListView;
    @FXML
    private ListView resultListView;
    @FXML
    private ListView adminListView;
    @FXML
    private Pane loginPane;
    @FXML
    private Pane sidebarPane;
    @FXML
    private Button superAdminPower;
    @FXML
    private Pane adminPane;

    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private  TextField adminName;
    @FXML
    private TextField adminemail;
    @FXML
    private TextField adminpassword;
    @FXML
    private TextField adminRole;
    @FXML
    private Pane adminTable;

    private byte[] resized;

    private byte[] partyImageReservior;

    @FXML
    private PasswordField passwordPassword;

    PictureClass myThread = new PictureClass();
    AdminIO adminIO = AdminIO.getAdminIO();

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
    public void logIn(){
        String emailString = email.getText();
        String passwordString = passwordPassword.getText();

        System.out.println("The password: "+passwordString);
        if(emailString.equals("")||passwordString.equals("")){
            showNotification("Login details missing!","Login Error","error");
        }else {
            Boolean result = adminIO.logIn(emailString,passwordString);
            if(result){

                if(adminIO.superLogIn(emailString,passwordString)){
                    superAdminPower.setVisible(true);
                }
                sidebarPane.setVisible(true);
                panelVisibility("home");
            }else {
                showNotification("Password or Email wrong Please verify!","Login Error","error");
            }
        }

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

    private void showNotification(String notificationMassage, String content,String notificationType){
        notificationPanel.setVisible(true);
        if(notificationType.equals("error"))notificationPanel.setStyle("-fx-background-color: #FFB6C1");
        else notificationPanel.setStyle("-fx-background-color: #90EE90");


        if (!notificationMassage.equals("")) notificationText.setText(notificationMassage);
        if(!content.equals("")) notificationContent.setText(content);
        else notificationContent.setVisible(false);
    }

    /***
     * This part deals with methods that calls panels
     */
    @FXML
    private void adminList() throws IOException {

        List<String> partyString = new ArrayList<>();

        for(Admin party:  adminIO.readAllX()){
            partyString.add(party.toString());
        }
        //ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (partyString);

        //list.setItems(items);

        adminListView.setItems(items);
        adminListView.setOrientation(Orientation.VERTICAL);

        panelVisibility("admin-list");
    }
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

        NewVoteIo newVoteIo = new NewVoteIo();

        List<String> partyString = new ArrayList<>();

        for(VoteResult party:  newVoteIo.readResult()){
            partyString.add(party.toString());
        }
        //ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (partyString);

        //list.setItems(items);

        resultListView.setItems(items);
        resultListView.setOrientation(Orientation.VERTICAL);

        panelVisibility("result");
    }

    @FXML
    private void location() throws IOException {
        panelVisibility("location");
    }
    @FXML
    public void editParty(){
        panelVisibility("edit-party");
    }

    @FXML
    private void adminPanel(){
        adminName.setText("");
        adminemail.setText("");
        adminpassword.setText("");
        adminRole.setText("");
        panelVisibility("admin");
    }



    /***
     * This part deals with methods that send data to the Api
     */


    @FXML
    public void createAdmin() {

        String adminNameString = adminName.getText();
        String adminEmailString = adminemail.getText();
        String adminPassword = adminpassword.getText();
        String adminRoleString = adminRole.getText();

        if(!adminEmailString.equals("")&&!adminNameString.equals("")&&!adminPassword.equals("")&&!adminRoleString.equals("")){
            Admin admin = new Admin(adminEmailString,adminNameString,adminPassword,adminRoleString);
            try {
                int statusCode = adminIO.create(admin);
                if (statusCode == 200) {
                    showNotification("Admin Created Successfully", "", "success");
                } else {
                    showNotification("Unknown Error", "Network Problem", "error");
                }
            }catch (IOException io){
                showNotification("Unknown Error", "Network Problem", "error");
            }
        }else {
            showNotification("Requested Field may be Empty!","Please fill the all requested.","error");
        }
        adminName.setText("");
        adminemail.setText("");
        adminpassword.setText("");
        adminRole.setText("");
    }

    @FXML
    private void fetchParty(){
        //partyComponent(false);
        partyEditName.setText("");
        partyEditName.setVisible(false);
        System.out.println(" we are fetching the party");
        String pAbreviation = partyAbreviation.getText();
        if (pAbreviation!=null){
            try{
                Party party = partyIO.readWithAbbreviation(pAbreviation);
                partyEditName.setText(party.getName());
                partyComponent(true);
            }catch (NullPointerException n){
                showNotification("Party Not Found","Please fill the Party Abbreviation with a correct Abbreviation","error");
            }
        } else {
            showNotification("Request Field Empty!","Please fill the Party Abbreviation.","error");
        }
        partyAbreviation.setText("");
    }

    public void partyComponent(Boolean stat){
        partyEditName.setVisible(stat);
        deleteParty.setVisible(stat);
        updatePartyBtn.setVisible(stat);
    }

    @FXML
    private void deleteParty(){
        String pAbreviation = partyAbreviation.getText();
        System.out.println("Party Abbreviation: "+pAbreviation);
        if (pAbreviation!=null){
            try{
                Boolean result = partyIO.deleteWithAbbreviation(pAbreviation);
                if (result) {
                    showNotification("Delete Successful",pAbreviation+" Was deleted","success");
                    partyComponent(false);
                    panelVisibility("party");
                }

            }catch (NullPointerException n){
                showNotification("Party Not Found","Please fill the Party Abbreviation with a correct Abbreviation","error");
            }
        }
        else {
            showNotification("Request Field Empty!","Please fill the Party Abbreviation.","error");
        }
        partyAbreviation.setText("");
    }
    @FXML
    private void requestPartyUpdate(){

        String pAbreviation = partyAbreviation.getText();
        partyComponent(false);


        //We first disable the editParty Panel and call the update panel
        panelVisibility("update-party");

        if (pAbreviation!=null){
            try{
                Party party = partyIO.readWithAbbreviation(pAbreviation);
                partyImageReservior = party.getFlag();

                updatePartyName.setText(party.getName());

                updateAbbviation.setText(party.getAbbreviation());

            }catch (NullPointerException n){
                showNotification("Party Not Found","Please fill the Party Abbreviation with a correct Abbreviation","error");
            }
        }
        else {
            showNotification("Request Field Empty!","Please fill the Party Abbreviation.","error");
        }
        partyAbreviation.setText("");
    }
    @FXML
    public void viewParties() throws IOException {
        List<String> partyString = new ArrayList<>();

        for(Party party:  partyIO.readAllX()){
            partyString.add(party.toString());
        }
        //ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (partyString);

        myListView.setItems(items);
        myListView.setOrientation(Orientation.VERTICAL);
        panelVisibility("view-parties");
    }

   // resultListView
    @FXML
    private void updateParty() throws IOException {
        String pName = partyName.getText();
        String pabbviation = abbviation.getText();
        if(!pName.equals("") && !pabbviation.equals("")){

            Party party = new Party("",pName,pabbviation,resized);
            resized=null;
            int result = partyIO.update(party);
            if (result !=200) {
                showNotification("error creating party","Network or server error","error");
            }else {
                showNotification("You have successfully updated a party","Party: "+pName,"success");
                partyName.setText("");
                abbviation.setText("");
                panelVisibility("party");
            }
        }
        partyName.setText("");
        partyName.setVisible(false);
        abbviation.setText("");
    }
    @FXML
    private void createNewVoter() throws IOException {
        int resultCode = 0;
        String name = voterName.getText();
        String last = lastName.getText();
        String id = idNumber.getText();
        String phone = phoneNumber.getText();

        try{
           byte[] fileContent= myThread.getBytes();
            System.out.println("fileContent: "+fileContent);

//            File file = new File("glory.jpg");
//            byte[] fileContent = FileUtils.readFileToByteArray(file);

            if(fileContent!=null){
                Voter voter = VoterFactory.getVoter(id,name,last,phone,fileContent);
                //System.out.println("File sent: "+file.getName());
                resultCode = voter_io.create(voter);
                resized=null;
                if (resultCode !=200) {
                    showNotification("error creating voter","Network or server error","error");
                }else {
                    showNotification("You have successfully created new Voter","Name: "+name,"success");
                }
            }else {
                showNotification("error reading and writing the file"," FILE!","error");
            }

        }catch (IOException ex){
            showNotification("error reading and writing the file","","error");
        }
        voterName.setText("");
        lastName.setText("");
        idNumber.setText("");
        phoneNumber.setText("");
    }
//    @FXML
//    private void openFileExplore() throws IOException {
//        resized = null;
//        imageService = new ImageService();
//        FileChooser fileChooser = new FileChooser();
//        // fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        File selectedFile = fileChooser.showOpenDialog(null);
//        //int result = fileChooser.showOpenDialog(null);
//        if (selectedFile.exists()) {
//            byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
//            //File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//            try{
//                imageService.readAndWriteFile(selectedFile.getAbsolutePath());
//                imageService.pictureWriter(fileContent);
//                ImageResizer.getResizedImage();
//                resized = imageService.convertToBytes();
//            }catch (IOException ex){
//                showNotification("error reading and writing the file","");
//            }
//        }
//    }

    @FXML
    private void openFileExplore() throws IOException {
        resized = null;
        imageService = new ImageService();
        FileChooser fileChooser = new FileChooser();
        // fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(null);
        //int result = fileChooser.showOpenDialog(null);
        if (imageService.checkFileExtension(selectedFile)) {
            //File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            //resized = new byte[(int) selectedFile.getCanonicalFile().length()];
           // resized = selectedFile;
            System.out.println(resized);
            try{
                //byte[] fileContent = Files.readAllBytes(selectedFile.toPath());
                //imageService.pictureWriter(fileContent);

                byte[] bytes = FileUtils.readFileToByteArray(selectedFile.getAbsoluteFile());
                //BufferedImage bufferedImage = ImageResizer.getResizedImage(selectedFile.getAbsoluteFile());
                if(bytes != null) {
                    resized= bytes;
                    //resized = imageService.convertToBytes();
                }

            }catch (IOException ex){
                showNotification("error reading and writing the file","","error");
            }
        }else {
            System.out.println(" Error has occured");
            showNotification("error reading and writing the file"," Please select a jpg or jpeg","error");
        }
    }
    @FXML
    private void addParty() throws IOException {
        String pName = partyName.getText();
        String pabbviation = abbviation.getText();
        if(!pName.equals("") && !pabbviation.equals("")){
            System.out.println("image: "+resized);
            Party party = new Party("",pName,pabbviation,resized);
            //Clearing the resized.
            resized=null;

            int result = partyIO.create(party);
            if (result !=200) {
                showNotification("error creating party","Network or server error","error");
            }else {
                System.out.println(" Error has occured");
                showNotification("You have successfully created a party","Party: "+pName,"success");

            }
        }
        partyName.setText("");
        abbviation.setText("");
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
            case "edit-party": partyEditPanel.setVisible(true);
                System.out.println("in party edit");
                break;
            case "update-party": updatePanel.setVisible(true);
                System.out.println("in party update");
                break;
            case "view-parties": viewParty.setVisible(true);
                System.out.println("in party view");
                break;
            case "log-in": loginPane.setVisible(true);
                System.out.println("in login");
                break;
            case "admin": adminPane.setVisible(true);
                System.out.println("in admin");
                break;
            case "admin-list": adminTable.setVisible(true);
                System.out.println("in admin-list");
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
        partyEditPanel.setVisible(false);
        updatePanel.setVisible(false);
        viewParty.setVisible(false);
        loginPane.setVisible(false);
        adminPane.setVisible(false);
        adminTable.setVisible(false);
    }

    @FXML
    public void openWebCamCapture(){
        myThread.run();
        //webCamPanel.getChildren().add(window,n)
    }






}
