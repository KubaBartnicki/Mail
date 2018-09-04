package MyMail.MyMailClient.FX;

import MyMail.MyMailClient.MyData;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class CreateProfile extends Application {

	@Override
	public void start(final Stage createprofileStage) throws Exception {
		
		createprofileStage.setTitle("MailClient");
		createprofileStage.setResizable(false);
        GridPane gridProfile = new GridPane();
        gridProfile.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        gridProfile.setVgap(10); //vertical gap in pixels
        gridProfile.setPadding(new Insets(10, 10, 10, 10));
        
        Scene sceneProfile = new Scene(gridProfile, 200, 270);
        
        
        Label nazwa = new Label("Stworz profil");
        nazwa.setFont(new Font("Arial", 20));
        gridProfile.add(nazwa, 0, 0);
        
        final TextField user = new TextField("nazwa");
        gridProfile.add(user, 0, 1);
       
        final TextField myMail = new TextField("mail adres");
        gridProfile.add(myMail, 0, 2);
       
        final TextField myPass = new TextField("haslo");
        gridProfile.add(myPass, 0, 3);
                       
        final TextField smtp = new TextField("smtp");
        gridProfile.add(smtp, 0, 4);
       
        final TextField port = new TextField("port");
        gridProfile.add(port, 0, 5);
        
        final Label emptyTextFields = new Label("wypelnij wszystkie pola");
        gridProfile.add(emptyTextFields, 0, 6);
        emptyTextFields.setVisible(false);
       
        createprofileStage.setScene(sceneProfile);
        createprofileStage.show();
       
        Button createProfil = new Button("Create");
        gridProfile.add(createProfil, 0, 6);
        createProfil.setOnMouseClicked(new EventHandler<Event>() {

            public void handle(Event event) {
                Alert profileCreateAlert = new Alert(AlertType.INFORMATION);
                if ((user.getText() == "" || myMail.getText() == "" || myPass.getText() == "" || smtp.getText() == "" || port.getText() == "") || (user.getText() == "nazwa" || myMail.getText() == "mail adres" || myPass.getText() == "haslo" || smtp.getText() == "smtp" || port.getText() == "port")) {
                    emptyTextFields.setVisible(true);
                    
                    profileCreateAlert.setTitle("wypelnij pola");


                    profileCreateAlert.showAndWait();
                }


                else {
                	MyData newProfile = new MyData();
                    newProfile.createProfile(user.getText(), myMail.getText(), myPass.getText(), smtp.getText(), port.getText());
                    createprofileStage.close();

                    profileCreateAlert.setTitle("Utworzono profil uzytkownika " + user.getText());
                    profileCreateAlert.setHeaderText("Konto o nazwie "+ user.getText()+" zostalo utworzone");
                    profileCreateAlert.setContentText(null);

                    profileCreateAlert.showAndWait();
                }
            }

        });
    }
}
