package MyMail.MyMailClient.FX;

import java.io.IOException;

import javax.mail.MessagingException;

import MyMail.MyMailClient.Mail;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SendMail extends Application {
	private String user;
	public SendMail(String user) {
		this.user = user;
	}
	@Override
	public void start(Stage sendMailStage) throws Exception {
		sendMailStage.setWidth(800);
		sendMailStage.setHeight(600);
		sendMailStage.setMinHeight(480);
		sendMailStage.setMinWidth(640);
		
		HBox hBox = new HBox();
		VBox vBox = new VBox();
		vBox.getChildren().add(hBox);
		
		vBox.setSpacing(10);
		vBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		
		Scene sendMailScene = new Scene(vBox);
		
		sendMailStage.centerOnScreen();
		sendMailStage.setTitle("Nowa wiadomosc");
		sendMailStage.setScene(sendMailScene);
		sendMailStage.show();
		
		final TextField to = new TextField();
		vBox.getChildren().add(to);
		
		final TextField title = new TextField();
		vBox.getChildren().add(title);		
		
		final TextArea text = new TextArea();
		text.setPrefSize(800,600);
		vBox.getChildren().add(text);
		VBox.setVgrow(text, Priority.ALWAYS);
		
		Button send = new Button("Wyslij");
		send.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				try {
					Mail mail = new Mail(user);
					mail.sendMail(text.getText(), title.getText(), to.getText());
		            Alert mailSendetAlert = new Alert(AlertType.INFORMATION);
		            mailSendetAlert.setTitle("MailClient");
		            mailSendetAlert.setHeaderText(null);
		            mailSendetAlert.setContentText("Wiadomosc wyslana pomyslnie!");
		            mailSendetAlert.showAndWait();
			
				} catch (MessagingException e) {
		            Alert missingAlert = new Alert(AlertType.INFORMATION);
		            missingAlert.setTitle("MailClient");
		            missingAlert.setHeaderText(null);
		            missingAlert.setContentText("Wyslanie wiadomosci do "+to.getText()+ " nie powiodło sie.");
		            missingAlert.showAndWait();
					e.printStackTrace();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		hBox.getChildren().add(send);
        
        
	}
}
