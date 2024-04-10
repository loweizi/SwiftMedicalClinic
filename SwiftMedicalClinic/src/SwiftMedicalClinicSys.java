import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SwiftMedicalClinicSys extends Application {

	private Patient currentPatient;
	private Associates currentAssociate;
	private Appointments currentApp;
	private ArrayList<Patient> patientList = new ArrayList<>();
	private ArrayList<Associates> AssList = new ArrayList<>();
	private ArrayList<Appointments> AppList = new ArrayList<>();
	private ArrayList<Appointments> PatientAppList = new ArrayList<>();
    private Stage primaryStage;
    private Scene loginScenePL;
    private Scene loginSceneAsso;
    private Scene signUpScenePL;
    private Scene signUpSceneAsso;
    private Scene forgotPasswordScene;
    private Scene PatientPortalScene;
    private Scene AssociateHPScene;
    private Scene AppointmentsScene;
    private Scene PastAppointmentsScene;
    private Scene PLPastAppointmentsScene;
    private Scene TestScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Swift Medical Clinic System");

        //create each page screen
        BorderPane loginPanePL = LoginPanePL();
        loginScenePL = new Scene(loginPanePL, 900, 600);
        
        BorderPane loginPaneAsso = LoginPaneAsso();
        loginSceneAsso = new Scene(loginPaneAsso, 900, 600);
        
        BorderPane signUpPanePL = SignUpPanePL();
        signUpScenePL = new Scene(signUpPanePL, 900, 600);
        
        BorderPane signUpPaneAsso = SignUpPaneAsso();
        signUpSceneAsso = new Scene(signUpPaneAsso, 900, 600);

        BorderPane forgotPasswordPane = ForgotPasswordPane();
        forgotPasswordScene = new Scene(forgotPasswordPane, 900, 600);

        // Set initial scene
        primaryStage.setScene(loginScenePL);
        primaryStage.show();
    }

    private BorderPane LoginPanePL() {
    	
    	loadPatientFiles();
    	loadAssociatesFiles();
    	loadAppointmentFiles();
    	
    	//pane holding all the other panes of patient login screen
        BorderPane PatientLoginPane = new BorderPane();
        PatientLoginPane.setPadding(new Insets(100));
        
        //-----------------------------------------------------------------
        //creating and adding title onto main PL pane
        Text PLtitle = new Text("Patient Login Portal");
        PLtitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        PatientLoginPane.setTop(PLtitle);
        BorderPane.setAlignment(PLtitle, javafx.geometry.Pos.CENTER);

        //-----------------------------------------------------------------
        //container for the text fields
        VBox PLtextfieldPane = new VBox();
        PLtextfieldPane.setSpacing(30);
        PLtextfieldPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        //text fields and their adjustments
        TextField usernameField = new TextField();
        usernameField.setPromptText("Email");
        usernameField.setMaxWidth(300);
        usernameField.setPadding(new Insets(10, 10, 10, 10));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setPadding(new Insets(10, 10, 10, 10));
        
        PLtextfieldPane.getChildren().addAll(usernameField, passwordField);
        
        //-----------------------------------------------------------------
        //container for the buttons
        HBox PLbuttonPane = new HBox();
        PLbuttonPane.setSpacing(100);
        PLbuttonPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        //(e -> primaryStage.setScene(PatientPortalScene));
        //buttons and their adjustments
        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(400);
        loginButton.setOnAction(e -> {
        	
        	String email = usernameField.getText();
        	String password = passwordField.getText();
        	Patient user = findPatient(email, password);
        	
        	if(user == null || 
        			usernameField.getText().isEmpty() || 
        			passwordField.getText().isEmpty()) {
        		
        		usernameField.setStyle("-fx-control-inner-background: #E34234"); 
        		passwordField.setStyle("-fx-control-inner-background: #E34234");
        	}
        	
        	//else if everything goes smoothly...
        	else if(user != null){
        		
        		BorderPane PatientPortalPane = PatientPortalPane(user);
                PatientPortalScene = new Scene(PatientPortalPane, 900, 600);
        		primaryStage.setScene(PatientPortalScene);
        		
        		usernameField.setStyle("-fx-control-inner-background: white"); 
        		passwordField.setStyle("-fx-control-inner-background: white"); 
        		
        		//clear the textfields
        		usernameField.clear();
        		passwordField.clear();
        	}
        });
        loginButton.setPadding(new Insets(10, 10, 10, 10));
        Button associateLoginButton = new Button("Associate Login");
        associateLoginButton.setOnAction(e -> {
        	usernameField.clear();
    		passwordField.clear();
    		
    		usernameField.setStyle("-fx-control-inner-background: white"); 
    		passwordField.setStyle("-fx-control-inner-background: white"); 
    		
        	primaryStage.setScene(loginSceneAsso);
        	
        });
        associateLoginButton.setMaxWidth(400);
        associateLoginButton.setPadding(new Insets(10, 10, 10, 10));
        
        PLbuttonPane.getChildren().addAll(associateLoginButton, loginButton);

        //-----------------------------------------------------------------
        //container for the hyper links
        VBox PLlinks = new VBox();
        PLlinks.setPadding(new Insets(20));
        PLlinks.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT); 
        
        //hyper links and their adjustments
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setOnAction(e -> {
        	usernameField.clear();
    		passwordField.clear();
    		
    		usernameField.setStyle("-fx-control-inner-background: white"); 
    		passwordField.setStyle("-fx-control-inner-background: white"); 
    		
    		primaryStage.setScene(signUpScenePL);
        });
        signUpLink.setPadding(new Insets(10, 170, 10, 10));
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setOnAction(e -> {
        	usernameField.clear();
    		passwordField.clear();
    		
    		usernameField.setStyle("-fx-control-inner-background: white"); 
    		passwordField.setStyle("-fx-control-inner-background: white"); 
    		
    		primaryStage.setScene(forgotPasswordScene);
        });
        forgotPasswordLink.setPadding(new Insets(10, 170, 10, 10));
        
        PLlinks.getChildren().addAll(forgotPasswordLink, signUpLink);
        
        //-----------------------------------------------------------------
        //setting them all into one pane
        VBox PLsettingPane = new VBox();
        PLsettingPane.setSpacing(20);
        PLsettingPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PLsettingPane.getChildren().addAll(PLtextfieldPane, PLlinks, PLbuttonPane);

        //-----------------------------------------------------------------
        //add the setting into the main pane
        PatientLoginPane.setCenter(PLsettingPane);
        BorderPane.setAlignment(PLsettingPane, javafx.geometry.Pos.CENTER);

        return PatientLoginPane;
    }
    
    private BorderPane LoginPaneAsso() {
    	
    	//pane holding all the other panes of associate login screen
        BorderPane AssociateLoginPane = new BorderPane();
        AssociateLoginPane.setPadding(new Insets(100));
        
        //-----------------------------------------------------------------
        //creating and adding title onto main PL pane
        Text AssoTitle = new Text("Associates Login Portal");
        AssoTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        AssociateLoginPane.setTop(AssoTitle);
        BorderPane.setAlignment(AssoTitle, javafx.geometry.Pos.CENTER);

        //-----------------------------------------------------------------
        //container for the text fields
        VBox AssoTextfieldPane = new VBox();
        AssoTextfieldPane.setSpacing(30);
        AssoTextfieldPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        //text fields and their adjustments
        TextField usernameField = new TextField();
        usernameField.setPromptText("Email");
        usernameField.setMaxWidth(300);
        usernameField.setPadding(new Insets(10, 10, 10, 10));
        TextField associateIDField = new TextField();
        associateIDField.setPromptText("Associate ID");
        associateIDField.setMaxWidth(300);
        associateIDField.setPadding(new Insets(10, 10, 10, 10));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(300);
        passwordField.setPadding(new Insets(10, 10, 10, 10));
        
        AssoTextfieldPane.getChildren().addAll(usernameField, associateIDField, passwordField);
        
        //-----------------------------------------------------------------
        //container for the buttons
        HBox AssoButtonPane = new HBox();
        AssoButtonPane.setSpacing(100);
        AssoButtonPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        //buttons and their adjustments
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
        	
        	String email = usernameField.getText();
        	String associateID = associateIDField.getText();
        	String password = passwordField.getText();
        	Associates user = findAssociate(email, associateID, password);
        	
        	System.out.println(user.getFirstName());
        	
        	if(user == null ||
        			usernameField.getText().isEmpty() ||
        			associateIDField.getText().isEmpty() ||
        			passwordField.getText().isEmpty()) {
        		
        		usernameField.setStyle("-fx-control-inner-background: #E34234"); 
        		associateIDField.setStyle("-fx-control-inner-background: #E34234");
        		passwordField.setStyle("-fx-control-inner-background: #E34234");
        	}
        	
        	//else if everything goes smoothly...
        	else if(user != null){
        		
        		BorderPane AssociateHPPane = AssociateHPPane(user);
                AssociateHPScene = new Scene(AssociateHPPane, 900, 600);
        		primaryStage.setScene(AssociateHPScene);
        		
        		usernameField.setStyle("-fx-control-inner-background: white"); 
        		associateIDField.setStyle("-fx-control-inner-background: white");
        		passwordField.setStyle("-fx-control-inner-background: white"); 
        		
        		//clear the textfields
        		usernameField.clear();
        		associateIDField.clear();
        		passwordField.clear();
        	}
        });
        loginButton.setMaxWidth(400);
        loginButton.setPadding(new Insets(10, 10, 10, 10));
        Button patientLoginButton = new Button("Patient Login");
        patientLoginButton.setOnAction(e -> {
        	usernameField.clear();
    		associateIDField.clear();
    		passwordField.clear();
    		
    		usernameField.setStyle("-fx-control-inner-background: white"); 
    		associateIDField.setStyle("-fx-control-inner-background: white");
    		passwordField.setStyle("-fx-control-inner-background: white"); 
    		
    		primaryStage.setScene(loginScenePL);
        });
        patientLoginButton.setMaxWidth(400);
        patientLoginButton.setPadding(new Insets(10, 10, 10, 10));
        
        AssoButtonPane.getChildren().addAll(patientLoginButton, loginButton);

        //-----------------------------------------------------------------
        //container for the hyper links
        VBox AssoLinks = new VBox();
        AssoLinks.setPadding(new Insets(20));
        AssoLinks.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT); 
        
        //hyper links and their adjustments
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setOnAction(e -> {
        	usernameField.clear();
    		associateIDField.clear();
    		passwordField.clear();
    		
    		usernameField.setStyle("-fx-control-inner-background: white"); 
    		associateIDField.setStyle("-fx-control-inner-background: white");
    		passwordField.setStyle("-fx-control-inner-background: white"); 
    		
    		primaryStage.setScene(signUpSceneAsso);
        });
        signUpLink.setPadding(new Insets(10, 170, 10, 10));
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setOnAction(e -> {
        	usernameField.clear();
    		associateIDField.clear();
    		passwordField.clear();

    		usernameField.setStyle("-fx-control-inner-background: white"); 
    		associateIDField.setStyle("-fx-control-inner-background: white");
    		passwordField.setStyle("-fx-control-inner-background: white"); 
    		
    		primaryStage.setScene(forgotPasswordScene);
        });
        forgotPasswordLink.setPadding(new Insets(10, 170, 10, 10));
        
        AssoLinks.getChildren().addAll(forgotPasswordLink, signUpLink);
        
        //-----------------------------------------------------------------
        //setting them all into one pane
        VBox AssoSettingPane = new VBox();
        AssoSettingPane.setSpacing(20);
        AssoSettingPane.setAlignment(javafx.geometry.Pos.CENTER); 
        AssoSettingPane.getChildren().addAll(AssoTextfieldPane, AssoLinks, AssoButtonPane);

        //-----------------------------------------------------------------
        //add the setting into the main pane
        AssociateLoginPane.setCenter(AssoSettingPane);
        BorderPane.setAlignment(AssoSettingPane, javafx.geometry.Pos.CENTER);

        return AssociateLoginPane;
    }
    
    private BorderPane SignUpPanePL() {
        BorderPane CreateAccPanePL = new BorderPane();
        CreateAccPanePL.setPadding(new Insets(100));

        //-----------------------------------------------------------------
        //creating title 
        Text PCreateAcc = new Text("Account Creation");
        PCreateAcc.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(PCreateAcc, javafx.geometry.Pos.CENTER);

        //-----------------------------------------------------------------
        //container for the left third label and text fields
        VBox PCAfirst = new VBox();
        PCAfirst.setSpacing(10);
        
        //labels and their adjustment
        Label fNameLabel = new Label("First Name:");
        fNameLabel.setFont(Font.font("Tahoma", 10));
        Label lNameLabel = new Label("Last Name:");
        lNameLabel.setFont(Font.font("Tahoma", 10));
        Label DOBLabel = new Label("Date of Birth:");
        DOBLabel.setFont(Font.font("Tahoma", 10));
        
        //text field and their adjustments
        TextField fNameField = new TextField();
        fNameField.setPromptText("First Name");
        fNameField.setMaxWidth(150);
        fNameField.setPadding(new Insets(10, 10, 10, 10));
        TextField lNameField = new TextField();
        lNameField.setPromptText("Last Name");
        lNameField.setMaxWidth(150);
        lNameField.setPadding(new Insets(10, 10, 10, 10));
        TextField DOBpicker = new TextField();
        DOBpicker.setPromptText("Date of Birth");
        DOBpicker.setMaxWidth(150);
        DOBpicker.setPadding(new Insets(10, 10, 10, 10));
        
        
        PCAfirst.getChildren().addAll(fNameLabel, fNameField, lNameLabel, lNameField, DOBLabel, DOBpicker);
        
        //-----------------------------------------------------------------
        //container for the middle third label and text fields
        VBox PCAsecond = new VBox();
        PCAsecond.setSpacing(10);
        
        //labels and their adjustment
        Label SSNLabel = new Label("Social Security Number:");
        SSNLabel.setFont(Font.font("Tahoma", 10));
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Tahoma", 10));
        Label phoneNumLabel = new Label("Phone Number:");
        phoneNumLabel.setFont(Font.font("Tahoma", 10));
        
        //text field and their adjustments
        TextField SSNField = new TextField();
        SSNField.setPromptText("SSN");
        SSNField.setMaxWidth(150);
        SSNField.setPadding(new Insets(10, 10, 10, 10));
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(150);
        emailField.setPadding(new Insets(10, 10, 10, 10));
        TextField phoneNumField = new TextField();
        phoneNumField.setPromptText("Phone Number");
        phoneNumField.setMaxWidth(150);
        phoneNumField.setPadding(new Insets(10, 10, 10, 10));
        
        
        PCAsecond.getChildren().addAll(SSNLabel, SSNField, emailLabel, emailField, phoneNumLabel, phoneNumField);
        
        //-----------------------------------------------------------------
        //container for the right third label and text fields
        VBox PCAthird = new VBox();
        PCAthird.setSpacing(10);
        
        //labels and their adjustment
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Tahoma", 10));
        Label CpasswordLabel = new Label("Confirm Password:");
        CpasswordLabel.setFont(Font.font("Tahoma", 10));
        
        //text field and their adjustments
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(150);
        passwordField.setPadding(new Insets(10, 10, 10, 10));
        TextField CpasswordField = new TextField();
        CpasswordField.setPromptText("Confirm Password");
        CpasswordField.setMaxWidth(150);
        CpasswordField.setPadding(new Insets(10, 10, 10, 10));
        
        PCAthird.getChildren().addAll(passwordLabel, passwordField, CpasswordLabel, CpasswordField);
        
        //-----------------------------------------------------------------
        //container to put all those labels and fields tgt in one pane
        HBox PCAField = new HBox();
        PCAField.setSpacing(5);
        PCAField.setAlignment(javafx.geometry.Pos.CENTER); 
        
        PCAField.getChildren().addAll(PCAfirst, PCAsecond, PCAthird);
        
        //-----------------------------------------------------------------
        //container for the buttons
        HBox FPbuttonPane = new HBox();
        FPbuttonPane.setSpacing(100);
        FPbuttonPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        //buttons and their adjustments
        Button backToLogin = new Button("Back to Login");
        backToLogin.setOnAction(e -> {
        	fNameField.clear();
        	lNameField.clear();
        	DOBpicker.clear();
        	SSNField.clear();
        	emailField.clear();
        	phoneNumField.clear();
        	passwordField.clear();
        	CpasswordField.clear();
        	
        	passwordField.setStyle("-fx-control-inner-background: white"); 
        	CpasswordField.setStyle("-fx-control-inner-background: white");
    		
    		primaryStage.setScene(loginScenePL);
        });
        backToLogin.setMaxWidth(400);
        backToLogin.setPadding(new Insets(10, 10, 10, 10));
        Button createAccButton = new Button("Create Account");
        createAccButton.setOnAction(e -> {
        	
        	if(passwordField.getText().compareTo(CpasswordField.getText()) != 0) {
        		passwordField.setStyle("-fx-control-inner-background: #E34234"); 
        		CpasswordField.setStyle("-fx-control-inner-background: #E34234");
        	}
        	
        	if(passwordField.getText().compareTo(CpasswordField.getText()) == 0 && 
        			!fNameField.getText().isEmpty() &&
        			!lNameField.getText().isEmpty() &&
        			!DOBpicker.getText().isEmpty() &&
        			!SSNField.getText().isEmpty() &&
        			!emailField.getText().isEmpty() &&
        			!phoneNumField.getText().isEmpty() &&
        			!passwordField.getText().isEmpty()) {
        		
        		currentPatient = new Patient();
            	currentPatient.setFirstName(fNameField.getText());
            	currentPatient.setLastName(lNameField.getText());
            	currentPatient.setDOB(DOBpicker.getText());
            	currentPatient.setSSN(SSNField.getText());
            	currentPatient.setEmail(emailField.getText());
            	currentPatient.setPhoneNum(phoneNumField.getText());
            	currentPatient.setPassword(passwordField.getText());
            	
            	passwordField.setStyle("-fx-control-inner-background: white"); 
            	CpasswordField.setStyle("-fx-control-inner-background: white");
            	
            	savePatientFile(currentPatient);
                patientList.add(currentPatient);
            	
            	fNameField.clear();
            	lNameField.clear();
            	DOBpicker.clear();
            	SSNField.clear();
            	emailField.clear();
            	phoneNumField.clear();
            	passwordField.clear();
            	CpasswordField.clear();
            	
            	primaryStage.setScene(loginScenePL);
        	}
        });
        createAccButton.setMaxWidth(400);
        createAccButton.setPadding(new Insets(10, 10, 10, 10));
        
        FPbuttonPane.getChildren().addAll(backToLogin, createAccButton);
        
        //-----------------------------------------------------------------
        //setting them all into one pane
        VBox FPsettingPane = new VBox();
        FPsettingPane.setSpacing(40);
        FPsettingPane.setAlignment(javafx.geometry.Pos.CENTER); 
        FPsettingPane.getChildren().addAll(PCreateAcc, PCAField, FPbuttonPane);

        //-----------------------------------------------------------------
        //add the setting into the main pane
        CreateAccPanePL.setCenter(FPsettingPane);
        BorderPane.setAlignment(FPsettingPane, javafx.geometry.Pos.CENTER);


        return CreateAccPanePL;
    }
    
    private BorderPane SignUpPaneAsso() {
        BorderPane CreateAccPaneAsso = new BorderPane();
        CreateAccPaneAsso.setPadding(new Insets(100));

        //-----------------------------------------------------------------
        //creating title 
        Text AssoCreateAcc = new Text("Account Creation");
        AssoCreateAcc.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(AssoCreateAcc, javafx.geometry.Pos.CENTER);

        //-----------------------------------------------------------------
        //container for the left third label and text fields
        VBox AssoCAfirst = new VBox();
        AssoCAfirst.setSpacing(10);
        
        //labels and their adjustment
        Label fNameLabel = new Label("First Name:");
        fNameLabel.setFont(Font.font("Tahoma", 10));
        Label lNameLabel = new Label("Last Name:");
        lNameLabel.setFont(Font.font("Tahoma", 10));
        Label associateIDLabel = new Label("Associate ID:");
        associateIDLabel.setFont(Font.font("Tahoma", 10));
        
        //text field and their adjustments
        TextField fNameField = new TextField();
        fNameField.setPromptText("First Name");
        fNameField.setMaxWidth(150);
        fNameField.setPadding(new Insets(10, 10, 10, 10));
        TextField lNameField = new TextField();
        lNameField.setPromptText("Last Name");
        lNameField.setMaxWidth(150);
        lNameField.setPadding(new Insets(10, 10, 10, 10));
        TextField associateIDField = new TextField();
        associateIDField.setPromptText("Associate ID");
        associateIDField.setMaxWidth(150);
        associateIDField.setPadding(new Insets(10, 10, 10, 10));
        
        
        AssoCAfirst.getChildren().addAll(fNameLabel, fNameField, lNameLabel, lNameField, associateIDLabel, associateIDField);
        
        //-----------------------------------------------------------------
        //container for the middle third label and text fields
        VBox AssoCAsecond = new VBox();
        AssoCAsecond.setSpacing(10);
        
        //labels and their adjustment
        Label DOBLabel = new Label("Date of Birth:");
        DOBLabel.setFont(Font.font("Tahoma", 10));
        Label SSNLabel = new Label("Social Security Number:");
        SSNLabel.setFont(Font.font("Tahoma", 10));
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Tahoma", 10));
      
        
        //text field and their adjustments
        TextField DOBpicker = new TextField();
        DOBpicker.setPromptText("Date of Birth");
        DOBpicker.setMaxWidth(150);
        DOBpicker.setPadding(new Insets(10, 10, 10, 10));
        TextField SSNField = new TextField();
        SSNField.setPromptText("SSN");
        SSNField.setMaxWidth(150);
        SSNField.setPadding(new Insets(10, 10, 10, 10));
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setMaxWidth(150);
        emailField.setPadding(new Insets(10, 10, 10, 10));
        
        
        AssoCAsecond.getChildren().addAll(DOBLabel, DOBpicker, SSNLabel, SSNField, emailLabel, emailField);
        
        //-----------------------------------------------------------------
        //container for the right third label and text fields
        VBox AssoCAthird = new VBox();
        AssoCAthird.setSpacing(10);
        
        //labels and their adjustment
        Label phoneNumLabel = new Label("Phone Number:");
        phoneNumLabel.setFont(Font.font("Tahoma", 10));
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Tahoma", 10));
        Label CpasswordLabel = new Label("Confirm Password:");
        CpasswordLabel.setFont(Font.font("Tahoma", 10));
        
        //text field and their adjustments
        TextField phoneNumField = new TextField();
        phoneNumField.setPromptText("Phone Number");
        phoneNumField.setMaxWidth(150);
        phoneNumField.setPadding(new Insets(10, 10, 10, 10));
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(150);
        passwordField.setPadding(new Insets(10, 10, 10, 10));
        TextField CpasswordField = new TextField();
        CpasswordField.setPromptText("Confirm Password");
        CpasswordField.setMaxWidth(150);
        CpasswordField.setPadding(new Insets(10, 10, 10, 10));
        
        AssoCAthird.getChildren().addAll(phoneNumLabel, phoneNumField, passwordLabel, passwordField, CpasswordLabel, CpasswordField);
        
        //-----------------------------------------------------------------
        //container to put all those labels and fields tgt in one pane
        HBox AssoCAField = new HBox();
        AssoCAField.setSpacing(5);
        AssoCAField.setAlignment(javafx.geometry.Pos.CENTER); 
        
        AssoCAField.getChildren().addAll(AssoCAfirst, AssoCAsecond, AssoCAthird);
        
        //-----------------------------------------------------------------
        //container for the buttons
        HBox AssoButtonPane = new HBox();
        AssoButtonPane.setSpacing(100);
        AssoButtonPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        //buttons and their adjustments
        Button backToLogin = new Button("Back to Login");
        backToLogin.setOnAction(e -> {
        	fNameField.clear();
        	lNameField.clear();
        	associateIDField.clear();
        	DOBpicker.clear();
        	SSNField.clear();
        	emailField.clear();
        	phoneNumField.clear();
        	passwordField.clear();
        	CpasswordField.clear();
        	
        	passwordField.setStyle("-fx-control-inner-background: white"); 
        	CpasswordField.setStyle("-fx-control-inner-background: white");
    		
    		primaryStage.setScene(loginSceneAsso);
        });
        backToLogin.setMaxWidth(400);
        backToLogin.setPadding(new Insets(10, 10, 10, 10));
        Button AssoCreateAccButton = new Button("Create Account");
        AssoCreateAccButton.setOnAction(e -> {
        	
        	if(passwordField.getText().compareTo(CpasswordField.getText()) != 0) {
        		passwordField.setStyle("-fx-control-inner-background: #E34234"); 
        		CpasswordField.setStyle("-fx-control-inner-background: #E34234");
        	}
        	
        	if(passwordField.getText().compareTo(CpasswordField.getText()) == 0 && 
        			!fNameField.getText().isEmpty() &&
        			!lNameField.getText().isEmpty() &&
        			!associateIDField.getText().isEmpty() &&
        			!DOBpicker.getText().isEmpty() &&
        			!SSNField.getText().isEmpty() &&
        			!emailField.getText().isEmpty() &&
        			!phoneNumField.getText().isEmpty() &&
        			!passwordField.getText().isEmpty()) {
        		
        		currentAssociate = new Associates();
        		currentAssociate.setFirstName(fNameField.getText());
        		currentAssociate.setLastName(lNameField.getText());
        		currentAssociate.setID(associateIDField.getText());
        		currentAssociate.setDOB(DOBpicker.getText());
        		currentAssociate.setSSN(SSNField.getText());
        		currentAssociate.setEmail(emailField.getText());
        		currentAssociate.setPhoneNum(phoneNumField.getText());
        		currentAssociate.setPassword(passwordField.getText());
            	
            	saveAssociateFile(currentAssociate);
                AssList.add(currentAssociate);
            	
                passwordField.setStyle("-fx-control-inner-background: white"); 
            	CpasswordField.setStyle("-fx-control-inner-background: white");
                
            	fNameField.clear();
            	lNameField.clear();
            	associateIDField.clear();
            	DOBpicker.clear();
            	SSNField.clear();
            	emailField.clear();
            	phoneNumField.clear();
            	passwordField.clear();
            	CpasswordField.clear();
            	
            	primaryStage.setScene(loginSceneAsso);
        	}
        });
        AssoCreateAccButton.setMaxWidth(400);
        AssoCreateAccButton.setPadding(new Insets(10, 10, 10, 10));
        
        AssoButtonPane.getChildren().addAll(backToLogin, AssoCreateAccButton);
        
        //-----------------------------------------------------------------
        //setting them all into one pane
        VBox FPsettingPane = new VBox();
        FPsettingPane.setSpacing(40);
        FPsettingPane.setAlignment(javafx.geometry.Pos.CENTER); 
        FPsettingPane.getChildren().addAll(AssoCreateAcc, AssoCAField, AssoButtonPane);

        //-----------------------------------------------------------------
        //add the setting into the main pane
        CreateAccPaneAsso.setCenter(FPsettingPane);
        BorderPane.setAlignment(FPsettingPane, javafx.geometry.Pos.CENTER);


        return CreateAccPaneAsso;
    }

    private BorderPane ForgotPasswordPane() {

    	//pane holding all the other panes of forgot password screen
        BorderPane ForgotPasswordPane = new BorderPane();
        ForgotPasswordPane.setPadding(new Insets(100));
        
        //-----------------------------------------------------------------
        //creating title 
        Text FPtitle = new Text("Password Recovery");
        FPtitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(FPtitle, javafx.geometry.Pos.CENTER);

        //-----------------------------------------------------------------
        //container for the label and text fields
        VBox FPtextfieldPane = new VBox();
        FPtextfieldPane.setSpacing(30);
        FPtextfieldPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        //label and their adjustment
        Label FPinstruction = new Label("Enter your email address to reset your password:");
        FPinstruction.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        //text field and their adjustments
        TextField FPtextfield = new TextField();
        FPtextfield.setPromptText("Email");
        FPtextfield.setMaxWidth(300);
        FPtextfield.setPadding(new Insets(10, 10, 10, 10));
        
        FPtextfieldPane.getChildren().addAll(FPinstruction, FPtextfield);
        
        //-----------------------------------------------------------------
        //container for the buttons
        HBox FPbuttonPane = new HBox();
        FPbuttonPane.setSpacing(100);
        FPbuttonPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        //buttons and their adjustments
        Button backToLogin = new Button("Back to Login");
        backToLogin.setOnAction(e -> primaryStage.setScene(loginScenePL));
        	//make method to make it go back to prev screen
        backToLogin.setMaxWidth(400);
        backToLogin.setPadding(new Insets(10, 10, 10, 10));
        Button sendEmail = new Button("Send Email");
        	//make method to send email
        sendEmail.setMaxWidth(400);
        sendEmail.setPadding(new Insets(10, 10, 10, 10));
        
        FPbuttonPane.getChildren().addAll(backToLogin, sendEmail);
        
        //-----------------------------------------------------------------
        //setting them all into one pane
        VBox FPsettingPane = new VBox();
        FPsettingPane.setSpacing(40);
        FPsettingPane.setAlignment(javafx.geometry.Pos.CENTER); 
        FPsettingPane.getChildren().addAll(FPtitle, FPtextfieldPane, FPbuttonPane);

        //-----------------------------------------------------------------
        //add the setting into the main pane
        ForgotPasswordPane.setCenter(FPsettingPane);
        BorderPane.setAlignment(FPsettingPane, javafx.geometry.Pos.CENTER);

        return ForgotPasswordPane;
    }

    private BorderPane PatientPortalPane(Patient user) {

    	//pane holding all the other panes of patient portal screen
        BorderPane PatientPortalPane = new BorderPane();
        PatientPortalPane.setPadding(new Insets(30));
        
        System.out.println(user.getFirstName());
        
        if(user != null) {
        	//-----------------------------------------------------------------
            //creating and adding title onto main PL pane
            Text Welcome = new Text("Hello " + user.getFirstName() + " " + user.getLastName());
            Welcome.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            PatientPortalPane.setTop(Welcome);
            BorderPane.setAlignment(Welcome, javafx.geometry.Pos.CENTER);
            
            //-----------------------------------------------------------------
            //creating left pane (previous visits) 
            
            BorderPane PrevVisitsPane = new BorderPane();
            PrevVisitsPane.setPrefSize(400, 250);
            PrevVisitsPane.setPadding(new Insets(30));
            PrevVisitsPane.setStyle("-fx-border-color: black");
            
            VBox Apps = new VBox();
            Apps.setSpacing(15);
            
            Text PrevVisitsTitle = new Text("Previous Visits");
            PrevVisitsTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            BorderPane.setAlignment(PrevVisitsTitle, javafx.geometry.Pos.CENTER);
            
            if (findAppointment(user.getEmail())) {
            	for(int i = 0; i < PatientAppList.size(); i++) {
                    
                	Appointments currentApp = PatientAppList.get(i);
                	
                	Hyperlink App = new Hyperlink("Appointment " + (i+1));
                	App.setPadding(new Insets(10, 10, 10, 10));
                	App.setOnAction(e -> {
                		BorderPane PLPastAppointmentsPane = PLPastAppointmentsPane(currentApp);
                        PLPastAppointmentsScene = new Scene(PLPastAppointmentsPane, 900, 600);
                    	primaryStage.setScene(PLPastAppointmentsScene);
                    	
                    	PatientAppList.clear();
                    		
                	});
                	
                	Apps.getChildren().addAll(App);
                }
            }
            
            
            PrevVisitsPane.setTop(PrevVisitsTitle);
            PrevVisitsPane.setCenter(Apps);
            
            //-----------------------------------------------------------------
            //creating right pane (messages) 
            BorderPane ChangeInfo = new BorderPane();
            ChangeInfo.setPrefSize(400, 250);
            ChangeInfo.setPadding(new Insets(30));
            ChangeInfo.setStyle("-fx-border-color: black");
            
            VBox questions = new VBox();
            questions.setPadding(new Insets(15));
            questions.setSpacing(15);
            
            Text InfoTitle = new Text("Account Information");
            InfoTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            BorderPane.setAlignment(InfoTitle, javafx.geometry.Pos.CENTER);
            
            
            Label Question = new Label("Enter New Email:");
            TextField question = new TextField();
            question.setMaxWidth(300);
            question.setPadding(new Insets(10, 10, 10, 10));
            Label Question2 = new Label("Enter New Phone Number:");
            TextField question2 = new TextField();
            question2.setMaxWidth(300);
            question2.setPadding(new Insets(10, 10, 10, 10));
            
            Button save = new Button("Save");
            save.setOnAction(e -> {
            	user.setEmail(question.getText());
            	user.setPhoneNum(question2.getText());
            });
            save.setMaxWidth(400);
            save.setPadding(new Insets(10, 10, 10, 10));
            
            questions.getChildren().addAll(Question, question, Question2, question2, save);
            
            ChangeInfo.setTop(InfoTitle);
            ChangeInfo.setCenter(questions);

            //-----------------------------------------------------------------
            //container for the bottom pane under the two main panes
            HBox PPortalBottPane = new HBox();
            PPortalBottPane.setSpacing(15);
            PPortalBottPane.setPadding(new Insets(15));
            PPortalBottPane.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT); 
            
            //text field and their adjustment
            TextField Qs = new TextField("Questions to Clinic Staff?");
            Qs.setPrefWidth(700);
            
            Button PlogoutButton = new Button("Logout");
            PlogoutButton.setOnAction(e -> primaryStage.setScene(loginScenePL));
            PlogoutButton.setMaxWidth(400);
            PlogoutButton.setPadding(new Insets(10, 10, 10, 10));
            
            PPortalBottPane.getChildren().addAll(Qs, PlogoutButton);
                      
            //-----------------------------------------------------------------
            //setting main panes into one pane
            HBox PPortalMainPane = new HBox();
            PPortalMainPane.setSpacing(40);
            PPortalMainPane.setAlignment(javafx.geometry.Pos.CENTER); 
            PPortalMainPane.getChildren().addAll(PrevVisitsPane, ChangeInfo);

            //-----------------------------------------------------------------
            //add the setting/bottom into the main pane
            PatientPortalPane.setCenter(PPortalMainPane);
            PatientPortalPane.setBottom(PPortalBottPane);
        }

        return PatientPortalPane;
    }
    
    private BorderPane AssociateHPPane(Associates user) {
    	//pane holding all the other panes of associate home page screen
        BorderPane AssociateHPPane = new BorderPane();
        AssociateHPPane.setPadding(new Insets(30));
        
        if(user != null) {
        	
        	//creating and adding title onto main PL pane
            Text Welcome = new Text("Hello " + user.getFirstName() + " " + user.getLastName());
            Welcome.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            AssociateHPPane.setTop(Welcome);
            BorderPane.setAlignment(Welcome, javafx.geometry.Pos.CENTER);
        	
        	//-----------------------------------------------------------------
            //creating left pane (upcoming appointments) 
            
            BorderPane UpAppPane = new BorderPane();
            UpAppPane.setPrefSize(400, 250);
            UpAppPane.setPadding(new Insets(20));
            UpAppPane.setStyle("-fx-border-color: black");
            
            VBox Apps = new VBox();
            Apps.setSpacing(15);
            
            Text UpAppTitle = new Text("Upcoming Appointments");
            UpAppTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            BorderPane.setAlignment(UpAppTitle, javafx.geometry.Pos.CENTER);
            
            UpAppPane.setTop(UpAppTitle);
            
            for(int i = 0; i < patientList.size(); i++) {
            	Hyperlink patient = new Hyperlink(patientList.get(i).getFirstName() + patientList.get(i).getLastName());
            	patient.setPadding(new Insets(10, 10, 10, 10));
            	
            	BorderPane AppointmentsPane = AppointmentsPane(patientList.get(i), user);
                AppointmentsScene = new Scene(AppointmentsPane, 900, 600);
            	
            	patient.setOnAction(e -> {
            		primaryStage.setScene(AppointmentsScene);
                });
            	
            	Apps.getChildren().addAll(patient);
            }
            
            UpAppPane.setCenter(Apps);
            
            //-----------------------------------------------------------------
            //creating right pane (messages) 
            BorderPane ChangeInfo = new BorderPane();
            ChangeInfo.setPrefSize(400, 250);
            ChangeInfo.setPadding(new Insets(30));
            ChangeInfo.setStyle("-fx-border-color: black");
            
            VBox questions = new VBox();
            questions.setPadding(new Insets(15));
            questions.setSpacing(15);
            
            Text InfoTitle = new Text("Account Information");
            InfoTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            BorderPane.setAlignment(InfoTitle, javafx.geometry.Pos.CENTER);
            
            
            Label Question = new Label("Enter New Email:");
            TextField question = new TextField();
            question.setMaxWidth(300);
            question.setPadding(new Insets(10, 10, 10, 10));
            Label Question2 = new Label("Enter New Phone Number:");
            TextField question2 = new TextField();
            question2.setMaxWidth(300);
            question2.setPadding(new Insets(10, 10, 10, 10));
            
            Button save = new Button("Save");
            save.setOnAction(e -> {
            	user.setEmail(question.getText());
            	user.setPhoneNum(question2.getText());
            });
            save.setMaxWidth(400);
            save.setPadding(new Insets(10, 10, 10, 10));
            
            questions.getChildren().addAll(Question, question, Question2, question2, save);
            
            ChangeInfo.setTop(InfoTitle);
            ChangeInfo.setCenter(questions);

            //-----------------------------------------------------------------
            //container for the bottom pane under the two main panes
            VBox AssoHPBottPane = new VBox();
            AssoHPBottPane.setSpacing(10);
            AssoHPBottPane.setPadding(new Insets(15));
            AssoHPBottPane.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT); 
            
            //label
            Label PastAppLabel = new Label("Past Appointments:");
            
            //dropdown list
            ComboBox<Appointments> PastAppList = new ComboBox<Appointments>();
            
            for(int i = 0; i < AppList.size(); i++) {
            	PastAppList.getItems().add(AppList.get(i));
            	
            	PastAppList.setCellFactory(param -> new ListCell<>() {
                    @Override
                    protected void updateItem(Appointments appointment, boolean empty) {
                        super.updateItem(appointment, empty);

                        if (empty || appointment == null) {
                            setText("Select"); // Display "Select" for the default item
                        } else {
                            // Display the patient's name for each appointment
                            setText(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
                        }
                    }
                });
            }
            
            //past appointments button
            Button PastAppButton = new Button("Open");
            PastAppButton.setOnAction(e -> {
            	Appointments pastAppointment = PastAppList.getValue();
            	
            	BorderPane PastAppointmentsPane = PastAppointmentsPane(pastAppointment);
                PastAppointmentsScene = new Scene(PastAppointmentsPane, 900, 600);
            	primaryStage.setScene(PastAppointmentsScene);
            });
            PastAppButton.setMaxWidth(400);
            PastAppButton.setPadding(new Insets(10, 10, 10, 10));

            AssoHPBottPane.getChildren().addAll(PastAppLabel, PastAppList, PastAppButton);
            
            //-----------------------------------------------------------------
            //container for the logout button
            HBox AssoLogoutButtonPane = new HBox();
            AssoLogoutButtonPane.setSpacing(100);
            AssoLogoutButtonPane.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
            
            //buttons and their adjustments
            Button AssologoutButton = new Button("Logout");
            AssologoutButton.setOnAction(e -> primaryStage.setScene(loginSceneAsso));
            AssologoutButton.setMaxWidth(400);
            AssologoutButton.setPadding(new Insets(10, 10, 10, 10));
                    
            AssoLogoutButtonPane.getChildren().addAll(AssologoutButton);    
                
            
            //-----------------------------------------------------------------
            //setting main panes into one pane
            HBox AssoHPMainPane = new HBox();
            AssoHPMainPane.setSpacing(40);
            AssoHPMainPane.setAlignment(javafx.geometry.Pos.CENTER); 
            AssoHPMainPane.getChildren().addAll(UpAppPane, ChangeInfo);
            
                    
            //-----------------------------------------------------------------
            //setting bottom panes into one pane
            HBox AssoPortalBottomPane = new HBox();
            AssoPortalBottomPane.setSpacing(600);
            AssoPortalBottomPane.setAlignment(javafx.geometry.Pos.CENTER); 
            AssoPortalBottomPane.getChildren().addAll(AssoHPBottPane, AssoLogoutButtonPane);


            //-----------------------------------------------------------------
            //add the setting/bottom into the main pane
            AssociateHPPane.setCenter(AssoHPMainPane);
            AssociateHPPane.setBottom(AssoPortalBottomPane);
        }

        return AssociateHPPane;
    }

    private BorderPane AppointmentsPane(Patient patient, Associates user) {
    	//pane holding all the other panes of associate home page screen
        BorderPane AppointmentsPane = new BorderPane();
        AppointmentsPane.setPadding(new Insets(30));
        
        HBox MainPane = new HBox();
        VBox LeftPane = new VBox();
        
        Label patientName = new Label(patient.getFirstName() + " " + patient.getLastName());
        patientName.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        Label weight = new Label("Weight:");
        weight.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label height = new Label("Height:");
        height.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label bodyTemp = new Label("Body Temperature:");
        bodyTemp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label bloodPressure = new Label("Blood Pressure:");
        bloodPressure.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        TextField weightText = new TextField();
        TextField heightText = new TextField();
        TextField bodyTempText = new TextField();
        TextField bloodPressureText = new TextField();
        
        LeftPane.setSpacing(20);
        LeftPane.setAlignment(javafx.geometry.Pos.TOP_LEFT); 
        LeftPane.getChildren().addAll(patientName, weight, weightText, height, 
        		heightText, bodyTemp, bodyTempText, bloodPressure, bloodPressureText);
        
        HBox HistoryPane = new HBox();
        
        Label medPrescribed = new Label("Medicine Prescribed:");
        medPrescribed.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label PrevHealth = new Label("Previous Health Issues:");
        PrevHealth.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label Immunization = new Label("Immunization History:");
        Immunization.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        TextArea medPrescribedText = new TextArea();
        medPrescribedText.setPrefWidth(110);
        medPrescribedText.setFont(Font.font(15));
        medPrescribedText.setWrapText(true);
        medPrescribedText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        TextArea PrevHealthText = new TextArea();
        PrevHealthText.setPrefWidth(110);
        PrevHealthText.setFont(Font.font(15));
        PrevHealthText.setWrapText(true);
        PrevHealthText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        TextArea ImmunizationText = new TextArea();
        ImmunizationText.setPrefWidth(110);
        ImmunizationText.setFont(Font.font(15));
        ImmunizationText.setWrapText(true);
        ImmunizationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        Button TakeTest = new Button("Take Physical Test");
        TakeTest.setOnAction(e -> {
        	
        	if(!weightText.getText().isEmpty() &&
        			!heightText.getText().isEmpty() &&
        			!bodyTempText.getText().isEmpty() &&
        			!bloodPressureText.getText().isEmpty() &&
        			!medPrescribedText.getText().isEmpty() &&
        			!PrevHealthText.getText().isEmpty() &&
        			!ImmunizationText.getText().isEmpty()) {
        		
        		Appointments currentApp = new Appointments();
        		currentApp.setPatient(patient);
        		currentApp.setAssociate(user);
        		currentApp.setWeight(weightText.getText());
        		currentApp.setHeight(heightText.getText());
        		currentApp.setBodyTemp(bodyTempText.getText());
        		currentApp.setBloodPressure(bloodPressureText.getText());
        		currentApp.setMedication(medPrescribedText.getText());
        		currentApp.setPrevHealth(PrevHealthText.getText());
        		currentApp.setImmunization(ImmunizationText.getText());
                
                weightText.clear();
                heightText.clear();
                bodyTempText.clear();
                bloodPressureText.clear();
                medPrescribedText.clear();
                PrevHealthText.clear();
                ImmunizationText.clear();
                
                GridPane TestPane = TestPane(currentApp);
                TestScene = new Scene(TestPane, 900, 600);
            	
            	primaryStage.setScene(TestScene);
        	}
        	
        	else {
        		System.out.println("Empty Boxes");
        	}
        	
        });
        TakeTest.setMaxWidth(150);
        TakeTest.setPadding(new Insets(10, 10, 10, 10));
        
        VBox history1 = new VBox();
        VBox history2 = new VBox();
        VBox history3 = new VBox();
        VBox historyall = new VBox();
        historyall.setStyle("-fx-border-color: black");
        historyall.setPadding(new Insets(30));
        
        history1.setSpacing(15);
        history1.setAlignment(javafx.geometry.Pos.CENTER); 
        history1.getChildren().addAll(medPrescribed, medPrescribedText);
        
        history2.setSpacing(15);
        history2.setAlignment(javafx.geometry.Pos.CENTER); 
        history2.getChildren().addAll(PrevHealth, PrevHealthText);
        
        history3.setSpacing(15);
        history3.setAlignment(javafx.geometry.Pos.CENTER); 
        history3.getChildren().addAll(Immunization, ImmunizationText);
        
        HistoryPane.setSpacing(15);
        HistoryPane.setAlignment(javafx.geometry.Pos.CENTER); 
        HistoryPane.getChildren().addAll(history1, history2, history3);
        
        historyall.setSpacing(15);
        historyall.setAlignment(javafx.geometry.Pos.CENTER); 
        historyall.getChildren().addAll(HistoryPane, TakeTest);
   
        MainPane.setSpacing(40);
        MainPane.setAlignment(javafx.geometry.Pos.CENTER); 
        MainPane.getChildren().addAll(LeftPane, historyall);
        
        AppointmentsPane.setCenter(MainPane);
        
        
        return AppointmentsPane;

    }
    
    private BorderPane PLPastAppointmentsPane(Appointments pastAppointment) {
    	//pane holding all the other panes of associate home page screen
        BorderPane PastAppointmentsPane = new BorderPane();
        PastAppointmentsPane.setPadding(new Insets(30));
        
        Text Title = new Text(pastAppointment.getPatient().getFirstName() + " " + 
        pastAppointment.getPatient().getLastName() + "'s Appointment");
        Title.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        BorderPane.setAlignment(Title, javafx.geometry.Pos.CENTER);
        PastAppointmentsPane.setTop(Title);
        
      //-----------------------------------------------------------------
        //creating upper left pane (vitals, allergies, and health concerns) 

        BorderPane UpLeftPane = new BorderPane();
        UpLeftPane.setPadding(new Insets(10));
        UpLeftPane.setPrefSize(400, 250);
        UpLeftPane.setStyle("-fx-border-color: black");
        
        VBox VitalsPane = new VBox();
        VitalsPane.setSpacing(10);

        Text UpLeftTitle = new Text("Vitals");
        UpLeftTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(UpLeftTitle, javafx.geometry.Pos.CENTER);
        
        Label weight = new Label("Weight:");
        weight.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label height = new Label("Height:");
        height.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label bodyTemp = new Label("Body Temperature:");
        bodyTemp.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label bloodPressure = new Label("Blood Pressure:");
        bloodPressure.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text Weight = new Text(pastAppointment.getWeight());
        Weight.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text Height = new Text(pastAppointment.getHeight());
        Height.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text BodyTemp = new Text(pastAppointment.getBodyTemp());
        BodyTemp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text BloodPressure = new Text(pastAppointment.getBloodPressure());
        BloodPressure.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        VitalsPane.getChildren().addAll(weight, Weight, height, Height, bodyTemp, BodyTemp, bloodPressure, BloodPressure);
        UpLeftPane.setTop(UpLeftTitle);
        UpLeftPane.setCenter(VitalsPane);

        //-----------------------------------------------------------------
        //creating bottom left pane (Newly Prescribed Medications) 
        BorderPane MedsPane = new BorderPane();
        MedsPane.setPadding(new Insets(10));
        MedsPane.setPrefSize(400, 250);
        MedsPane.setStyle("-fx-border-color: black");
        
        VBox MedsPane2 = new VBox();
        MedsPane2.setSpacing(10);

        Text MedsTitle = new Text("Newly Prescribed Medications");
        MedsTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(MedsTitle, javafx.geometry.Pos.CENTER);
        
        Label TBMeds = new Label("Newly Prescribed Medication:");
        TBMeds.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text TBMed = new Text(pastAppointment.getTBMedication());
        TBMed.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        MedsPane2.getChildren().addAll(TBMeds, TBMed);

        MedsPane.setTop(MedsTitle);
        MedsPane.setCenter(MedsPane2);

        //-----------------------------------------------------------------
        //creating upper right pane (Doctors Findings) 
        BorderPane FindingsPane = new BorderPane();
        FindingsPane.setPadding(new Insets(10));
        FindingsPane.setPrefSize(400, 250);
        FindingsPane.setStyle("-fx-border-color: black");
        
        VBox FindingsPane2 = new VBox();
        FindingsPane2.setSpacing(10);

        Text FindingsTitle = new Text("Doctor's Findings");
        FindingsTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(FindingsTitle, javafx.geometry.Pos.CENTER);
        
        Label findings = new Label("Findings:");
        findings.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text Findings = new Text(pastAppointment.getFindings());
        Findings.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        FindingsPane2.getChildren().addAll(findings, Findings);

        FindingsPane.setTop(FindingsTitle);
        FindingsPane.setCenter(FindingsPane2);

        //-----------------------------------------------------------------
        //creating bottom right pane (health issues, prescriptions, immunizations) 
        BorderPane BottRightPane = new BorderPane();
        BottRightPane.setPadding(new Insets(10));
        BottRightPane.setPrefSize(400, 250);
        BottRightPane.setStyle("-fx-border-color: black");
        
        VBox BottRightPane2 = new VBox();
        BottRightPane2.setSpacing(10);

        Text BottRightTitle = new Text("Existing Health Issues | Prescriptions | Immunizations");
        BottRightTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        BorderPane.setAlignment(BottRightTitle, javafx.geometry.Pos.CENTER);
        
        Label prevHealth = new Label("Exhisting Health Issues:");
        prevHealth.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label meds = new Label("Prescriptions:");
        meds.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label immunization = new Label("Immunization:");
        immunization.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text PrevHealth = new Text(pastAppointment.getPrevHealth());
        PrevHealth.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text Meds = new Text(pastAppointment.getMedication());
        Meds.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text Immunization = new Text(pastAppointment.getImmunization());
        Immunization.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        BottRightPane2.getChildren().addAll(prevHealth, PrevHealth, meds, Meds, immunization, Immunization);   

        BottRightPane.setTop(BottRightTitle);
        BottRightPane.setCenter(BottRightPane2); 

        //-----------------------------------------------------------------
        //container for the back to home button
        HBox AssoHomeButtonPane = new HBox();
        AssoHomeButtonPane.setSpacing(100);
        AssoHomeButtonPane.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);

        //buttons and their adjustments
        Button AssoHomeButton = new Button("Back To Home Page");
        AssoHomeButton.setOnAction(e -> {
        	BorderPane PatientPortalPane = PatientPortalPane(pastAppointment.getPatient());
            PatientPortalScene = new Scene(PatientPortalPane, 900, 600);
    		primaryStage.setScene(PatientPortalScene);
        });
        AssoHomeButton.setMaxWidth(400);
        AssoHomeButton.setPadding(new Insets(10, 10, 10, 10));

        AssoHomeButtonPane.getChildren().addAll(AssoHomeButton);     

        //-----------------------------------------------------------------
        //setting left panes into one pane
        VBox PastAppLeftPane = new VBox();
        PastAppLeftPane.setSpacing(40);
        PastAppLeftPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PastAppLeftPane.getChildren().addAll(UpLeftPane, MedsPane);


        //-----------------------------------------------------------------
        //setting right panes into one pane
        VBox PastAppRightPane = new VBox();
        PastAppRightPane.setSpacing(40);
        PastAppRightPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PastAppRightPane.getChildren().addAll(FindingsPane, BottRightPane);

        //-----------------------------------------------------------------
        //setting bottom pane
        VBox PastAppBotttPane = new VBox();
        PastAppBotttPane.setSpacing(40);
        PastAppBotttPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PastAppBotttPane.getChildren().addAll(AssoHomeButtonPane);


        //-----------------------------------------------------------------
        //add the setting/bottom into the main pane
        PastAppointmentsPane.setLeft(PastAppLeftPane);
        PastAppointmentsPane.setRight(PastAppRightPane);
        PastAppointmentsPane.setBottom(PastAppBotttPane);
        
        return PastAppointmentsPane;
    }
    
    private BorderPane PastAppointmentsPane(Appointments pastAppointment) {
    	//pane holding all the other panes of associate home page screen
        BorderPane PastAppointmentsPane = new BorderPane();
        PastAppointmentsPane.setPadding(new Insets(30));
        
        Text Title = new Text(pastAppointment.getPatient().getFirstName() + " " + 
        pastAppointment.getPatient().getLastName() + "'s Appointment");
        Title.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        BorderPane.setAlignment(Title, javafx.geometry.Pos.CENTER);
        PastAppointmentsPane.setTop(Title);
        
      //-----------------------------------------------------------------
        //creating upper left pane (vitals, allergies, and health concerns) 

        BorderPane UpLeftPane = new BorderPane();
        UpLeftPane.setPadding(new Insets(10));
        UpLeftPane.setPrefSize(400, 250);
        UpLeftPane.setStyle("-fx-border-color: black");
        
        VBox VitalsPane = new VBox();
        VitalsPane.setSpacing(10);

        Text UpLeftTitle = new Text("Vitals");
        UpLeftTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(UpLeftTitle, javafx.geometry.Pos.CENTER);
        
        Label weight = new Label("Weight:");
        weight.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label height = new Label("Height:");
        height.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label bodyTemp = new Label("Body Temperature:");
        bodyTemp.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label bloodPressure = new Label("Blood Pressure:");
        bloodPressure.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text Weight = new Text(pastAppointment.getWeight());
        Weight.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text Height = new Text(pastAppointment.getHeight());
        Height.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text BodyTemp = new Text(pastAppointment.getBodyTemp());
        BodyTemp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text BloodPressure = new Text(pastAppointment.getBloodPressure());
        BloodPressure.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        VitalsPane.getChildren().addAll(weight, Weight, height, Height, bodyTemp, BodyTemp, bloodPressure, BloodPressure);
        UpLeftPane.setTop(UpLeftTitle);
        UpLeftPane.setCenter(VitalsPane);

        //-----------------------------------------------------------------
        //creating bottom left pane (Newly Prescribed Medications) 
        BorderPane MedsPane = new BorderPane();
        MedsPane.setPadding(new Insets(10));
        MedsPane.setPrefSize(400, 250);
        MedsPane.setStyle("-fx-border-color: black");
        
        VBox MedsPane2 = new VBox();
        MedsPane2.setSpacing(10);

        Text MedsTitle = new Text("Newly Prescribed Medications");
        MedsTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(MedsTitle, javafx.geometry.Pos.CENTER);
        
        Label TBMeds = new Label("Newly Prescribed Medication:");
        TBMeds.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text TBMed = new Text(pastAppointment.getTBMedication());
        TBMed.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        MedsPane2.getChildren().addAll(TBMeds, TBMed);

        MedsPane.setTop(MedsTitle);
        MedsPane.setCenter(MedsPane2);

        //-----------------------------------------------------------------
        //creating upper right pane (Doctors Findings) 
        BorderPane FindingsPane = new BorderPane();
        FindingsPane.setPadding(new Insets(10));
        FindingsPane.setPrefSize(400, 250);
        FindingsPane.setStyle("-fx-border-color: black");
        
        VBox FindingsPane2 = new VBox();
        FindingsPane2.setSpacing(10);

        Text FindingsTitle = new Text("Doctor's Findings");
        FindingsTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(FindingsTitle, javafx.geometry.Pos.CENTER);
        
        Label findings = new Label("Findings:");
        findings.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text Findings = new Text(pastAppointment.getFindings());
        Findings.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        FindingsPane2.getChildren().addAll(findings, Findings);

        FindingsPane.setTop(FindingsTitle);
        FindingsPane.setCenter(FindingsPane2);

        //-----------------------------------------------------------------
        //creating bottom right pane (health issues, prescriptions, immunizations) 
        BorderPane BottRightPane = new BorderPane();
        BottRightPane.setPadding(new Insets(10));
        BottRightPane.setPrefSize(400, 250);
        BottRightPane.setStyle("-fx-border-color: black");
        
        VBox BottRightPane2 = new VBox();
        BottRightPane2.setSpacing(10);

        Text BottRightTitle = new Text("Existing Health Issues | Prescriptions | Immunizations");
        BottRightTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        BorderPane.setAlignment(BottRightTitle, javafx.geometry.Pos.CENTER);
        
        Label prevHealth = new Label("Exhisting Health Issues:");
        prevHealth.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label meds = new Label("Prescriptions:");
        meds.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        Label immunization = new Label("Immunization:");
        immunization.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        
        Text PrevHealth = new Text(pastAppointment.getPrevHealth());
        PrevHealth.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text Meds = new Text(pastAppointment.getMedication());
        Meds.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text Immunization = new Text(pastAppointment.getImmunization());
        Immunization.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        BottRightPane2.getChildren().addAll(prevHealth, PrevHealth, meds, Meds, immunization, Immunization);   

        BottRightPane.setTop(BottRightTitle);
        BottRightPane.setCenter(BottRightPane2); 

        //-----------------------------------------------------------------
        //container for the back to home button
        HBox AssoHomeButtonPane = new HBox();
        AssoHomeButtonPane.setSpacing(100);
        AssoHomeButtonPane.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);

        //buttons and their adjustments
        Button AssoHomeButton = new Button("Back To Home Page");
        AssoHomeButton.setOnAction(e -> {
        	BorderPane AssociateHPPane = AssociateHPPane(pastAppointment.getAssociate());
            AssociateHPScene = new Scene(AssociateHPPane, 900, 600);
    		primaryStage.setScene(AssociateHPScene);
        });
        AssoHomeButton.setMaxWidth(400);
        AssoHomeButton.setPadding(new Insets(10, 10, 10, 10));

        AssoHomeButtonPane.getChildren().addAll(AssoHomeButton);     

        //-----------------------------------------------------------------
        //setting left panes into one pane
        VBox PastAppLeftPane = new VBox();
        PastAppLeftPane.setSpacing(40);
        PastAppLeftPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PastAppLeftPane.getChildren().addAll(UpLeftPane, MedsPane);


        //-----------------------------------------------------------------
        //setting right panes into one pane
        VBox PastAppRightPane = new VBox();
        PastAppRightPane.setSpacing(40);
        PastAppRightPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PastAppRightPane.getChildren().addAll(FindingsPane, BottRightPane);

        //-----------------------------------------------------------------
        //setting bottom pane
        VBox PastAppBotttPane = new VBox();
        PastAppBotttPane.setSpacing(40);
        PastAppBotttPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PastAppBotttPane.getChildren().addAll(AssoHomeButtonPane);


        //-----------------------------------------------------------------
        //add the setting/bottom into the main pane
        PastAppointmentsPane.setLeft(PastAppLeftPane);
        PastAppointmentsPane.setRight(PastAppRightPane);
        PastAppointmentsPane.setBottom(PastAppBotttPane);
        
        return PastAppointmentsPane;
    }

    private GridPane TestPane(Appointments currentApp) {
        	GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(20);
            grid.setVgap(10); 
            grid.setPadding(new Insets(25, 25, 25, 25)); 

            // Title 
            Label titleLabel = new Label("Physical Test Assessment");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
            GridPane.setHalignment(titleLabel, HPos.CENTER);
            
            titleLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
            GridPane.setConstraints(titleLabel, 0, 0, 3, 1); 

            // Findings Text Area
            Label findingsLabel = new Label("Enter Findings:");
            TextArea findingsInput = new TextArea();
            findingsLabel.setFont(Font.font("Tahoma",15));
            findingsInput.setPrefHeight(200);
            GridPane.setConstraints(findingsLabel, 0, 1); 
            GridPane.setConstraints(findingsInput, 0, 2); 

            //Medicine Text Label
            Label medicineLabel = new Label("Medicine to Prescribe:");
            TextArea medicineInput = new TextArea();
            medicineLabel.setFont(Font.font("Tahoma",15));
            medicineInput.setPrefHeight(200);
            GridPane.setConstraints(medicineLabel, 1, 1); 
            GridPane.setConstraints(medicineInput, 1, 2); 

            //Rec Text Area
            Label recommendationsLabel = new Label("Recommendations:");
            TextArea recommendationsInput = new TextArea();
            recommendationsLabel.setFont(Font.font("Tahoma",15));
            recommendationsInput.setPrefHeight(200);
            GridPane.setConstraints(recommendationsLabel, 2, 1); 
            GridPane.setConstraints(recommendationsInput, 2, 2); 

            //Submit Button
            Button submitButton = new Button("Save");
            submitButton.setOnAction(e -> {
            	
            	if(!findingsInput.getText().isEmpty() &&
            			!medicineInput.getText().isEmpty() &&
            			!recommendationsInput.getText().isEmpty()) {
            		
            		currentApp.setFindings(findingsInput.getText());
            		currentApp.setTBMedication(medicineInput.getText());
            		currentApp.setReccomendations(recommendationsInput.getText());
            		
            		currentApp.getPatient().setCount(currentApp.getPatient().getCount() + 1);
                	
                	saveAppointmentFile(currentApp);
                    AppList.add(currentApp);
                    
                    findingsInput.clear();
                    medicineInput.clear();
                    recommendationsInput.clear();
                    
                    BorderPane AssociateHPPane = AssociateHPPane(currentApp.getAssociate());
                    AssociateHPScene = new Scene(AssociateHPPane, 900, 600);
            		primaryStage.setScene(AssociateHPScene);
            	}
            	
            	else {
            		System.out.println("Empty Boxes");
            	}
            });
            submitButton.setStyle("-fx-font-size: 16px;");
            submitButton.setFont(Font.font("Tahoma",0));
            GridPane.setHalignment(submitButton, HPos.CENTER);
            GridPane.setConstraints(submitButton, 0, 3, 3, 1); 

            
            grid.getChildren().addAll(titleLabel, findingsLabel, findingsInput, medicineLabel, medicineInput, recommendationsLabel, recommendationsInput, submitButton);

            return grid;
        }
    
    private Patient findPatient(String email, String password) {
    	//if the list isn't empty, then search until the ID matches 
    	//and return that patient object
    	if(!patientList.isEmpty()) {
    		for(int i = 0; i < patientList.size(); i++) { 
    	    	if (email.compareTo(patientList.get(i).getEmail()) == 0 
    	    			&& password.compareTo(patientList.get(i).getPassword()) == 0) {
    	    		return patientList.get(i);
    	    	}
    	    }
    	}
        return null;
    }
    
    
    private Associates findAssociate(String email, String associateID, String password) {
    	//if the list isn't empty, then search until the ID matches 
    	//and return that patient object
    	if(!AssList.isEmpty()) {
    		for(int i = 0; i < AssList.size(); i++) { 
    	    	if (email.compareTo(AssList.get(i).getEmail()) == 0 && 
    	    			associateID.compareTo(AssList.get(i).getID()) == 0&& 
    	    			password.compareTo(AssList.get(i).getPassword()) == 0) {
    	    		return AssList.get(i);
    	    	}
    	    }
    	}
        return null;
    }
    
    private void loadPatientFiles() {
    	//folder holding all the files
        File folder = new File("src/PatientRecords");
        File[] patientFiles = folder.listFiles((dir, name) -> name.endsWith("_PatientInfo.txt"));
        //while there are files found, read that patient information
        //get the patient object, and add it to the patient list
        if (patientFiles != null) {
            for (File file : patientFiles) {
                Patient patient = readPatientInfo(file);
                if (patient != null) {
                    patientList.add(patient);
                }
            }
        }
    }
    
    private void loadAssociatesFiles() {
    	//folder holding all the files
        File folder = new File("src/AssociateRecords");
        File[] associateFiles = folder.listFiles((dir, name) -> name.endsWith("_AssociateInfo.txt"));
        //while there are files found, read that patient information
        //get the patient object, and add it to the patient list
        if (associateFiles != null) {
            for (File file : associateFiles) {
                Associates associate = readAssociateInfo(file);
                if (associate != null) {
                    AssList.add(associate);
                }
            }
        }
    }
    
    private void loadAppointmentFiles() {
    	//folder holding all the files
        File folder = new File("src/AppointmentRecords");
        File[] appointmentFiles = folder.listFiles((dir, name) -> name.endsWith("_AppointmentInfo.txt"));
        //while there are files found, read that patient information
        //get the patient object, and add it to the patient list
        if (appointmentFiles != null) {
            for (File file : appointmentFiles) {
                Appointments appointment = readAppointmentInfo(file);
                if (appointment != null) {
                    AppList.add(appointment);
                }
            }
        }
    }
    
    private void savePatientFile(Patient currentPatient) {
    	//create the file path and the name of the file it will be saved as
        String fileName = currentPatient.getFirstName() + "_" + currentPatient.getLastName() + "_PatientInfo.txt";
        String filePath = "src/PatientRecords/" + fileName;
        
        //then try to write all the patient information into the txt file 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("First Name: " + currentPatient.getFirstName() + "\n");
            writer.write("Last Name: " + currentPatient.getLastName() + "\n");
            writer.write("DOB: " + currentPatient.getDOB() + "\n");
            writer.write("SSN: " + currentPatient.getSSN() + "\n");
            writer.write("Email: " + currentPatient.getEmail() + "\n");
            writer.write("Phone Number: " + currentPatient.getPhoneNum() + "\n");
            writer.write("Password: " + currentPatient.getPassword() + "\n");
            writer.write("Count: " + currentPatient.getCount() + "\n");

            
            System.out.println("Patient information saved to file: " + fileName);
        } 
        //if it was unable to, then it will send an error message to the console
        catch (IOException e) {
            System.out.println("Error occurred while saving patient information to file.");
        }
    }
    
    private void saveAssociateFile(Associates currentAssociate) {
    	//create the file path and the name of the file it will be saved as
        String fileName = currentAssociate.getFirstName() + "_" + currentAssociate.getLastName() + "_AssociateInfo.txt";
        String filePath = "src/AssociateRecords/" + fileName;
        
        //then try to write all the patient information into the txt file 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("First Name: " + currentAssociate.getFirstName() + "\n");
            writer.write("Last Name: " + currentAssociate.getLastName() + "\n");
            writer.write("ID: " + currentAssociate.getID() + "\n");
            writer.write("DOB: " + currentAssociate.getDOB() + "\n");
            writer.write("SSN: " + currentAssociate.getSSN() + "\n");
            writer.write("Email: " + currentAssociate.getEmail() + "\n");
            writer.write("Phone Number: " + currentAssociate.getPhoneNum() + "\n");
            writer.write("Password: " + currentAssociate.getPassword() + "\n");

            
            System.out.println("Associate information saved to file: " + fileName);
        } 
        //if it was unable to, then it will send an error message to the console
        catch (IOException e) {
            System.out.println("Error occurred while saving associate information to file.");
        }
    }
    
    private boolean findAppointment(String email) {
    	//if the list isn't empty, then search until the ID matches 
    	//and return that patient object
    	if(!AppList.isEmpty()) {
    		for(int i = 0; i < AppList.size(); i++) { 
    	    	if (email.compareTo(AppList.get(i).getPatient().getEmail()) == 0) {
    	    		PatientAppList.add(AppList.get(i));
    	    	}
    	    }
    		return true;
    	}
    	return false;
    }
    
    private void saveAppointmentFile(Appointments currentApp) {
    	//create the file path and the name of the file it will be saved as
        String fileName = currentApp.getPatient().getFirstName() + "_" + currentApp.getPatient().getLastName() 
        		+ "_" + currentApp.getPatient().getCount()+ "_AppointmentInfo.txt";
        String filePath = "src/AppointmentRecords/" + fileName;
        
        //then try to write all the patient information into the txt file 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        	writer.write("Email: " + currentApp.getPatient().getEmail() + "\n");
        	writer.write("Weight: " + currentApp.getWeight() + "\n");
        	writer.write("Height: " + currentApp.getHeight() + "\n");
        	writer.write("Body Temperature: " + currentApp.getBodyTemp() + "\n");
        	writer.write("Blood Pressure: " + currentApp.getBloodPressure() + "\n");
            writer.write("Medication: " + currentApp.getMedication() + "\n");
            writer.write("Previous Health: " + currentApp.getPrevHealth() + "\n");
            writer.write("Immunization: " + currentApp.getImmunization() + "\n");
            writer.write("Findings: " + currentApp.getFindings() + "\n");
            writer.write("To Be Medicine: " + currentApp.getTBMedication() + "\n");
            writer.write("Reccomendations: " + currentApp.getReccomendations() + "\n");
            writer.write("Doctor: " + currentApp.getAssociate().getID() + "\n");

            
            System.out.println("Appointment information saved to file: " + fileName);
        } 
        //if it was unable to, then it will send an error message to the console
        catch (IOException e) {
            System.out.println("Error occurred while saving appointment information to file.");
        }
    }
    
    private Patient readPatientInfo(File file) {
    	//create patient object to store the information we are extracting
        Patient patient = new Patient();
        //try to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            //if it can, and while there is information to be extracted
            while ((line = reader.readLine()) != null) {
                //split each line between label and value in the txt file
                String[] info = line.split(": ");
                if (info.length >= 2) {
                    String label = info[0].trim();
                    String value = info[1].trim();

                    //set the corresponding value in the patient object
                    switch (label) {
                        case "First Name":
                            patient.setFirstName(value);
                            break;
                        case "Last Name":
                            patient.setLastName(value);
                            break;
                        case "DOB":
                        	patient.setDOB(value);
                            break;
                        case "SSN":
                        	patient.setSSN(value);
                            break;
                        case "Email":
                        	patient.setEmail(value);
                            break;
                        case "Phone Number":
                        	patient.setPhoneNum(value);
                            break;
                        case "Password":
                        	patient.setPassword(value);
                            break;
                        case "Count":
                        	patient.setCount(Integer.parseInt(value));
                            break;
                        default:
                            break;
                    }
                }
            }
            //return patient object created
            return patient;
        } 
        //else it'll send an error message saying it can't read the file to the console
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading patient information from file.");
            return null;
        }
    }
    
    private Associates readAssociateInfo(File file) {
    	//create patient object to store the information we are extracting
        Associates associate = new Associates();
        //try to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            //if it can, and while there is information to be extracted
            while ((line = reader.readLine()) != null) {
                //split each line between label and value in the txt file
                String[] info = line.split(": ");
                if (info.length >= 2) {
                    String label = info[0].trim();
                    String value = info[1].trim();

                    //set the corresponding value in the patient object
                    switch (label) {
                        case "First Name":
                        	associate.setFirstName(value);
                            break;
                        case "Last Name":
                        	associate.setLastName(value);
                            break;
                        case "ID":
                        	associate.setID(value);
                            break;
                        case "DOB":
                        	associate.setDOB(value);
                            break;
                        case "SSN":
                        	associate.setSSN(value);
                            break;
                        case "Email":
                        	associate.setEmail(value);
                            break;
                        case "Phone Number":
                        	associate.setPhoneNum(value);
                            break;
                        case "Password":
                        	associate.setPassword(value);
                            break;
                        default:
                            break;
                    }
                }
            }
            //return patient object created
            return associate;
        } 
        //else it'll send an error message saying it can't read the file to the console
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading patient information from file.");
            return null;
        }
    }
    
    private Appointments readAppointmentInfo(File file) {
    	//create patient object to store the information we are extracting
    	Appointments appointment = new Appointments();
        //try to read the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            //if it can, and while there is information to be extracted
            while ((line = reader.readLine()) != null) {
                //split each line between label and value in the txt file
                String[] info = line.split(": ");
                if (info.length >= 2) {
                    String label = info[0].trim();
                    String value = info[1].trim();

                    //set the corresponding value in the patient object
                    switch (label) {
                    	case "Email":
                    		if(!patientList.isEmpty()) {
                        		for(int i = 0; i < patientList.size(); i++) { 
                        	    	if (value.compareTo(patientList.get(i).getEmail()) == 0) {
                        	    		appointment.setPatient(patientList.get(i));
                        	    	}
                        	    }
                        	}
                    		break;
                    	case "Weight":
                    		appointment.setWeight(value);
                    		break;
                    	case "Height":
                    		appointment.setHeight(value);
                    		break;
                    	case "Body Temperature":
                    		appointment.setBodyTemp(value);
                    		break;
                    	case "Blood Pressure":
                    		appointment.setBloodPressure(value);
                    		break;
                        case "Medication":
                        	appointment.setMedication(value);
                            break;
                        case "Previous Health":
                        	appointment.setPrevHealth(value);
                            break;
                        case "Immunization":
                        	appointment.setImmunization(value);
                            break;
                        case "Findings":
                        	appointment.setFindings(value);
                            break;
                        case "To Be Medicine":
                        	appointment.setTBMedication(value);
                            break;
                        case "Reccomendations":
                        	appointment.setReccomendations(value);
                            break;
                        case "Doctir":
                    		if(!AssList.isEmpty()) {
                        		for(int i = 0; i < AssList.size(); i++) { 
                        	    	if (value.compareTo(AssList.get(i).getID()) == 0) {
                        	    		appointment.setAssociate(AssList.get(i));
                        	    	}
                        	    }
                        	}
                        default:
                            break;
                    }
                }
            }
            //return patient object created
            return appointment;
        } 
        //else it'll send an error message saying it can't read the file to the console
        catch (IOException | NumberFormatException e) {
            System.out.println("Error reading patient information from file.");
            return null;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}