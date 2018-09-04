package MyMail.MyMailClient.FX;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Multipart;

import MyMail.MyMailClient.MyData;
import MyMail.MyMailClient.receivedMessages;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MailBox extends Application {
	private MyMail.MyMailClient.MyData existProfile;
	private String user;
	
	public MailBox(String user) throws FileNotFoundException, IOException {
		this.user = user;
		existProfile = new MyData();
		existProfile.loadProfile(user);
	}
	
	
	@Override
	public void start(Stage mailBoxStage)  {
     
        	try {
				existProfile.loadProfile(user);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

            HBox hbox = new HBox();
            VBox vbox = new VBox();
            vbox.getChildren().add(hbox);
            hbox.setSpacing(10);
            hbox.setPadding(new Insets(10, 10, 10, 10));
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(10, 10, 10, 10));
            
            Scene sceneMailBox = new Scene(vbox, 900, 800);
            
            final ComboBox<Integer> cb = new ComboBox<>(FXCollections.observableArrayList(5, 10 , 15, 20));
            cb.setValue(5);
            
            final TableView<receivedMessages> messagesTable = new TableView<receivedMessages>();
            final ObservableList<receivedMessages> titleList = FXCollections.observableArrayList();
            final ObservableList<receivedMessages> toList = FXCollections.observableArrayList();
            final ObservableList<receivedMessages> dateList = FXCollections.observableArrayList();
            final ObservableList<receivedMessages> fromList = FXCollections.observableArrayList();
            
            TableColumn from = new TableColumn("From");
            from.setPrefWidth(100);
            from.setCellValueFactory(new PropertyValueFactory<receivedMessages, String>("From"));
            
            TableColumn date = new TableColumn("Date");
            date.setPrefWidth(100);
            date.setCellValueFactory(new PropertyValueFactory<receivedMessages, String>("Date"));
            
            TableColumn to = new TableColumn("To");
            to.setPrefWidth(100);
            to.setCellValueFactory(new PropertyValueFactory<receivedMessages, String>("To"));
            
            TableColumn title = new TableColumn("Title");
            title.setPrefWidth(500);
            title.setCellValueFactory(new PropertyValueFactory<receivedMessages, String>("Title"));
            
            messagesTable.setPrefSize(600, 300);
            final TextArea mesText = new TextArea();
            VBox.setVgrow(mesText, Priority.ALWAYS);
            mesText.setPrefSize(600, 300);
            
            
            messagesTable.setItems(toList);
            messagesTable.setItems(fromList);
            messagesTable.setItems(dateList);
            messagesTable.setItems(titleList);
            messagesTable.getColumns().addAll(from, to, date,  title);
            
            messagesTable.setOnMouseClicked(new javafx.event.EventHandler<Event>() {
     
				@Override
				public void handle(Event event) {
					messagesTable.getSelectionModel().getSelectedIndex();
					mesText.clear();
					
					if(new receivedMessages(existProfile, messagesTable.getSelectionModel().getSelectedIndex()).getTextOfMessage() instanceof String) {
						mesText.setText((String) new receivedMessages(existProfile, messagesTable.getSelectionModel().getSelectedIndex()).getTextOfMessage());
					}
					else if (new receivedMessages(existProfile, messagesTable.getSelectionModel().getSelectedIndex()).getTextOfMessage() instanceof Multipart) {
						try {
				        Multipart multipart = (Multipart) new receivedMessages(existProfile, messagesTable.getSelectionModel().getSelectedIndex()).getTextOfMessage();
				        BodyPart part = multipart.getBodyPart(0);
				        mesText.setText(part.getContent().toString());
						}catch(Exception e) {
							System.out.println("blad");
						}
						
				        
					}
				}

			
			});
            
            for(int i = 0;i<cb.getValue();i++) {
            	dateList.add(new receivedMessages(existProfile, i));
            	fromList.add(new receivedMessages(existProfile, i));
            	titleList.add(new receivedMessages(existProfile, i));
            } 

            final Button addButton = new Button("Odswiez");
            
            addButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                	dateList.clear();
                	fromList.clear();
                	titleList.clear();
                    for(int i = 0;i<cb.getValue();i++) {
	                    dateList.add(new receivedMessages(existProfile, i));
	                	fromList.add(new receivedMessages(existProfile, i));
	                	titleList.add(new receivedMessages(existProfile, i));
                    }

                }
            });
                
         
            final Button sendButton = new Button("Wyslij wiadomosc");
            sendButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                	SendMail sm = new SendMail(user);
                	try {
						sm.start(new Stage());
					} catch (Exception e1) {
		
						e1.printStackTrace();
					}

                }
            });
            
            hbox.getChildren().add(sendButton);
            hbox.getChildren().add(addButton);
            vbox.getChildren().add(messagesTable);
            vbox.getChildren().add(mesText);
            hbox.getChildren().add(cb);
            
            
            
            mailBoxStage.centerOnScreen();
            mailBoxStage.setTitle("Zalogowany użytkownik " + user);
            mailBoxStage.setScene(sceneMailBox);
            mailBoxStage.show();

	}

}
