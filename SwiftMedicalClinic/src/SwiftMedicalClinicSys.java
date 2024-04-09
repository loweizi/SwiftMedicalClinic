import javafx.application.Application;
import javafx.geometry.Insets;
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
        
        BorderPane PatientPortalPane = PatientPortalPane();
        PatientPortalScene = new Scene(PatientPortalPane, 900, 600);
        
        BorderPane AssociateHPPane = AssociateHPPane();
        AssociateHPScene = new Scene(AssociateHPPane, 900, 600);
        
        BorderPane AppointmentsPane = AppointmentsPane();
        AppointmentsScene = new Scene(AppointmentsPane, 900, 600);
        
        BorderPane PastAppointmentsPane = PastAppointmentsPane();
        PastAppointmentsScene = new Scene(PastAppointmentsPane, 900, 600);

        // Set initial scene
        primaryStage.setScene(loginScenePL);
        primaryStage.show();
    }

    private BorderPane LoginPanePL() {
    	
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
        
        //buttons and their adjustments
        Button loginButton = new Button("Login");
        loginButton.setMaxWidth(400);
        loginButton.setOnAction(e -> primaryStage.setScene(PatientPortalScene));
        loginButton.setPadding(new Insets(10, 10, 10, 10));
        Button associateLoginButton = new Button("Associate Login");
        associateLoginButton.setOnAction(e -> primaryStage.setScene(loginSceneAsso));
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
        signUpLink.setOnAction(e -> primaryStage.setScene(signUpScenePL));
        signUpLink.setPadding(new Insets(10, 170, 10, 10));
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setOnAction(e -> primaryStage.setScene(forgotPasswordScene));
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
        loginButton.setOnAction(e -> primaryStage.setScene(AssociateHPScene));
        loginButton.setMaxWidth(400);
        loginButton.setPadding(new Insets(10, 10, 10, 10));
        Button patientLoginButton = new Button("Patient Login");
        patientLoginButton.setOnAction(e -> primaryStage.setScene(loginSceneAsso));
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
        signUpLink.setOnAction(e -> primaryStage.setScene(signUpSceneAsso));
        signUpLink.setPadding(new Insets(10, 170, 10, 10));
        Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
        forgotPasswordLink.setOnAction(e -> primaryStage.setScene(forgotPasswordScene));
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
        DatePicker DOBpicker = new DatePicker();
        DOBpicker.setMaxWidth(150);
        
        
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
        backToLogin.setOnAction(e -> primaryStage.setScene(loginScenePL));
        backToLogin.setMaxWidth(400);
        backToLogin.setPadding(new Insets(10, 10, 10, 10));
        Button createAccButton = new Button("Create Account");
        	//make method to create account
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
        DatePicker DOBpicker = new DatePicker();
        DOBpicker.setMaxWidth(150);
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
        backToLogin.setOnAction(e -> primaryStage.setScene(loginSceneAsso));
        backToLogin.setMaxWidth(400);
        backToLogin.setPadding(new Insets(10, 10, 10, 10));
        Button AssoCreateAccButton = new Button("Create Account");
        	//make method to create account
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

    private BorderPane PatientPortalPane() {

    	//pane holding all the other panes of patient portal screen
        BorderPane PatientPortalPane = new BorderPane();
        PatientPortalPane.setPadding(new Insets(30));
        
        //-----------------------------------------------------------------
        //creating left pane (previous visits) 
        
        VBox PrevVisitsPane = new VBox();
        PrevVisitsPane.setPrefSize(400, 250);
        PrevVisitsPane.setSpacing(30);
        PrevVisitsPane.setStyle("-fx-border-color: black");
        PrevVisitsPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        Text PrevVisitsTitle = new Text("Previous Visits");
        PrevVisitsTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(PrevVisitsTitle, javafx.geometry.Pos.CENTER);
        
        PrevVisitsPane.getChildren().addAll(PrevVisitsTitle);
        
        //-----------------------------------------------------------------
        //creating right pane (messages) 
        VBox MsgPane = new VBox();
        MsgPane.setPrefSize(400, 250);
        MsgPane.setSpacing(30);
        MsgPane.setStyle("-fx-border-color: black");
        MsgPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        Text MsgTitle = new Text("Messages");
        MsgTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(MsgTitle, javafx.geometry.Pos.CENTER);
        
        MsgPane.getChildren().addAll(MsgTitle);

        //-----------------------------------------------------------------
        //container for the bottom pane under the two main panes
        VBox PPortalBottPane = new VBox();
        PPortalBottPane.setSpacing(15);
        PPortalBottPane.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT); 
        
        //text field and their adjustment
        TextField Qs = new TextField("Questions to Clinic Staff?");
        
        //labels and their adjustments
        Label PInsurance = new Label("Patient's Insurance");
        Label PPharmacy = new Label("Patient's Pharmacy");
        
        PPortalBottPane.getChildren().addAll(Qs, PInsurance, PPharmacy);
        
        //-----------------------------------------------------------------
        //setting main panes into one pane
        HBox PPortalMainPane = new HBox();
        PPortalMainPane.setSpacing(40);
        PPortalMainPane.setAlignment(javafx.geometry.Pos.CENTER); 
        PPortalMainPane.getChildren().addAll(PrevVisitsPane, MsgPane);

        //-----------------------------------------------------------------
        //add the setting/bottom into the main pane
        PatientPortalPane.setCenter(PPortalMainPane);
        PatientPortalPane.setBottom(PPortalBottPane);

        return PatientPortalPane;
    }
    
    private BorderPane AssociateHPPane() {
    	//pane holding all the other panes of associate home page screen
        BorderPane AssociateHPPane = new BorderPane();
        AssociateHPPane.setPadding(new Insets(30));
        
        //-----------------------------------------------------------------
        //creating left pane (upcoming appointments) 
        
        VBox UpAppPane = new VBox();
        UpAppPane.setPrefSize(400, 250);
        UpAppPane.setSpacing(30);
        UpAppPane.setStyle("-fx-border-color: black");
        UpAppPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        Text UpAppTitle = new Text("Upcoming Appointments");
        UpAppTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(UpAppTitle, javafx.geometry.Pos.CENTER);
        
        UpAppPane.getChildren().addAll(UpAppTitle);
        
        //-----------------------------------------------------------------
        //creating right pane (messages) 
        VBox MsgPane = new VBox();
        MsgPane.setPrefSize(400, 250);
        MsgPane.setSpacing(30);
        MsgPane.setStyle("-fx-border-color: black");
        MsgPane.setAlignment(javafx.geometry.Pos.CENTER); 
        
        Text MsgTitle = new Text("Messages");
        MsgTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        BorderPane.setAlignment(MsgTitle, javafx.geometry.Pos.CENTER);
        
        MsgPane.getChildren().addAll(MsgTitle);

        //-----------------------------------------------------------------
        //container for the bottom pane under the two main panes
        VBox AssoHPBottPane = new VBox();
        AssoHPBottPane.setSpacing(15);
        AssoHPBottPane.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT); 
        
        //label
        Label PastAppLabel = new Label("Past Appointments:");
        
        //dropdown list
        ComboBox PastAppList = new ComboBox();
        PastAppList.getItems().add("Test1");
        PastAppList.getItems().add("Test2");
        PastAppList.getItems().add("Test3");
        
        AssoHPBottPane.getChildren().addAll(PastAppLabel, PastAppList);
        
        //-----------------------------------------------------------------
        //setting main panes into one pane
        HBox AssoHPMainPane = new HBox();
        AssoHPMainPane.setSpacing(40);
        AssoHPMainPane.setAlignment(javafx.geometry.Pos.CENTER); 
        AssoHPMainPane.getChildren().addAll(UpAppPane, MsgPane);

        //-----------------------------------------------------------------
        //add the setting/bottom into the main pane
        AssociateHPPane.setCenter(AssoHPMainPane);
        AssociateHPPane.setBottom(AssoHPBottPane);

        return AssociateHPPane;
    }

    private BorderPane AppointmentsPane() {
    	//pane holding all the other panes of associate home page screen
        BorderPane AppointmentsPane = new BorderPane();
        AppointmentsPane.setPadding(new Insets(30));
        
        HBox MainPane = new HBox();
        VBox LeftPane = new VBox();
        
        Label patientName = new Label("(Patient's Name");
        patientName.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        Label weight = new Label("Weight:");
        weight.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label height = new Label("Height:");
        height.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label bodyTemp = new Label("Body Temperature:");
        bodyTemp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label bloodPressure = new Label("Blood Pressure:");
        bloodPressure.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        TextField weightText = new TextField("Weight");
        TextField heightText = new TextField("Height");
        TextField bodyTempText = new TextField("Body Temperature");
        TextField bloodPressureText = new TextField("Blood Pressure");
        
        LeftPane.setSpacing(40);
        LeftPane.setAlignment(javafx.geometry.Pos.CENTER); 
        LeftPane.getChildren().addAll(patientName, weight, weightText, height, 
        		heightText, bodyTemp, bodyTempText, bloodPressure, bloodPressureText);
        
        HBox HistoryPane = new HBox();
        HistoryPane.setStyle("-fx-border-color: black");
        
        Label medPrescribed = new Label("Medicine Prescribed:");
        medPrescribed.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label PrevHealth = new Label("Previous Health Issues:");
        PrevHealth.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Label Immunization = new Label("Immunization History:");
        Immunization.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        Text medPrescribedText = new Text();
        medPrescribedText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text PrevHealthText = new Text();
        PrevHealthText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        Text ImmunizationText = new Text();
        ImmunizationText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        
        Button TakeTest = new Button("Take Physical Test");
        TakeTest.setMaxWidth(80);
        TakeTest.setPadding(new Insets(10, 10, 10, 10));
        
        VBox history1 = new VBox();
        VBox history2 = new VBox();
        VBox history3 = new VBox();
        
        history1.setSpacing(15);
        history1.setAlignment(javafx.geometry.Pos.CENTER); 
        history1.getChildren().addAll(medPrescribed, medPrescribedText, TakeTest);
        
        history2.setSpacing(15);
        history2.setAlignment(javafx.geometry.Pos.CENTER); 
        history2.getChildren().addAll(PrevHealth, PrevHealthText);
        
        history3.setSpacing(15);
        history3.setAlignment(javafx.geometry.Pos.CENTER); 
        history3.getChildren().addAll(Immunization, ImmunizationText);
        
        HistoryPane.setSpacing(40);
        HistoryPane.setAlignment(javafx.geometry.Pos.CENTER); 
        HistoryPane.getChildren().addAll(history1, history2, history3);
   
        MainPane.setSpacing(40);
        MainPane.setAlignment(javafx.geometry.Pos.CENTER); 
        MainPane.getChildren().addAll(LeftPane, TakeTest);
        
        AppointmentsPane.setCenter(MainPane);
        
        
        return AppointmentsPane;
    }
    
    private BorderPane PastAppointmentsPane() {
    	//pane holding all the other panes of associate home page screen
        BorderPane PastAppointmentsPane = new BorderPane();
        PastAppointmentsPane.setPadding(new Insets(30));
        
        
        return PastAppointmentsPane;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
