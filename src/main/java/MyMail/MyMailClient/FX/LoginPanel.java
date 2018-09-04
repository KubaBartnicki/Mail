package MyMail.MyMailClient.FX;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginPanel extends Application {
	
    @Override
    public void start(final Stage primaryStage) throws Exception {
    	primaryStage.setResizable(false);
    	primaryStage.setWidth(300);
    	primaryStage.setHeight(200);
        GridPane grid = new GridPane();
        grid.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(10); //vertical gap in pixels
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        Scene login = new Scene(grid, 400,300);
        primaryStage.setScene(login);
       
       
        Label nazwa = new Label("Zaloguj");
        nazwa.setFont(new Font("Arial", 20));
        grid.add(nazwa, 0, 0);
        
        final TextField user = new TextField("podaj nazwe");
        grid.add(user, 0, 1);
       
        Button loginButton = new Button("Zaloguj");
        grid.add(loginButton, 2, 1);
        loginButton.setPrefWidth(100);
        loginButton.setOnMouseClicked(new EventHandler<Event>() {

            public void handle(Event event) {
	
				try {
            		MailBox mb = new MailBox(user.getText());
					mb.start(new Stage());
					primaryStage.close();
				} catch (IOException e) {
		            Alert alert = new Alert(AlertType.INFORMATION);
		            alert.setTitle("MailClient");
		            alert.setHeaderText(null);
		            alert.setContentText("Nie ma uzytkownika o nazwie "+user.getText());
		            alert.showAndWait();
				}
			

                } 
            }
        );
       
        Button create = new Button("Stworz profil");
        create.setPrefWidth(100);
        grid.add(create, 2, 2);
        create.setOnMouseClicked(new EventHandler<Event>() {

            public void handle(Event event) {
            	CreateProfile newProfile = new CreateProfile();
            	try {
					newProfile.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        
        primaryStage.centerOnScreen();
        primaryStage.setTitle("MailClient");
        primaryStage.show();
    }

    public static void main(String[] args) {
        LoginPanel.launch(args);
    }

}