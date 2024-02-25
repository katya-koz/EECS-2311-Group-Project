package guilayout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import backend.database.Database;
import backend.dog.Dog;
import backend.user.User;
import guicontrol.AppData;


public class LoginScene extends Application{
	private User userInfo = new User("","");
	private static LoginScene instance;
	ArrayList<Dog> posterDogs = AppData.getInstance().getDogProfiles();//TEMP
	User user = AppData.getInstance().getUser();
	
	ArrayList<User> userlist = new ArrayList<User>();
	
	public static LoginScene getInstance() {
		if (instance == null) {
			instance = new LoginScene();		
		}
		return instance;
	}
	

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	DogProfileScene dogProfileScene = DogProfileScene.getInstance();
        primaryStage.setTitle("Login UI");
        int width = 900;
		int height = 900;

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userLabel = new Label("Username:");
        TextField userTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button signUpButton = new Button("Sign Up");
        Button loginButton = new Button("Login");

        grid.add(userLabel, 0, 0);
        grid.add(userTextField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(signUpButton, 0, 2);
        grid.add(loginButton, 1, 2);
        signUpButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();
            user.setUsername(username);
            user.setPassword(password);
            userlist.add(user);
            if (Database.addUser(username, password) == false|| password == "" && username == "")
            	showAlert("Sign up Failed", "Please enter a valid username or password.");
            clearFields(userTextField, passwordField);
        });
        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = passwordField.getText();

            
            if (/*Database.getUser(username, password)!= null*/ userlist.contains(user) && username != "" && password != "") {
                AppData appData = AppData.getInstance();
                appData.setDogProfiles();
                appData.setUser(username, password);
                dogProfileScene.start(primaryStage);
            } else {
                // Display an alert for unsuccessful login
                showAlert("Login Failed", "Invalid username or password.");
            }

            clearFields(userTextField, passwordField);
        });

        Scene scene = new Scene(grid, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
//        primaryStage.setOnCloseRequest(event -> {
//    	    System.out.println("Window is closing. Perform cleanup if needed.");
//    	    
//    	    Database.onApplicationClose(user, posterDogs);
//    	});
    }

    private void clearFields(TextField usernameField, PasswordField passwordField) {
        usernameField.clear();
        passwordField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public User sendUserInfo() {
    return this.userInfo;
    }

}
