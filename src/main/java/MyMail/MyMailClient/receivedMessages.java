package MyMail.MyMailClient;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import javafx.beans.property.SimpleStringProperty;

public class ReceivedMessages {
	private MyData profile;
	private SimpleStringProperty title;
	private SimpleStringProperty date;
	private SimpleStringProperty from;
	private SimpleStringProperty to;
	private Object textOfMessage;
	
	@SuppressWarnings("deprecation")
	public ReceivedMessages (MyData existProfile, int i) {
        try {
        	profile = existProfile;            
            Properties props = new Properties();
			Session session = Session.getInstance(props, null);
        	Store store = session.getStore("pop3");
            store.connect(profile.getSmtp(), profile.getMyMail(), profile.getMyPass());
            Folder folder = store.getFolder("INBOX");                
            folder.open(Folder.READ_ONLY);
            int mesNumber = folder.getMessageCount();
            Message messageTab[] = folder.getMessages(); //tablica obiektu Message  dodac haslo uzytkownika ,  dodac 2 plik z haslami , kodowanie hasel, opcje + gui, otwieranie wiadomosci event handler 
            Message mes = folder.getMessage(mesNumber-i);
            textOfMessage = mes.getContent();
            Address[] from = mes.getFrom();
            Address[] to = mes.getAllRecipients();
            this.to = new SimpleStringProperty(to[0].toString());
            this.from = new SimpleStringProperty(from[0].toString());
            this.date = new SimpleStringProperty(new Date(mes.getSentDate().getTime()).toGMTString());
            this.title = new SimpleStringProperty(mes.getSubject());

        }
        catch(Exception e) {
        	
        }
	}
	public String getFrom() {
		return from.get();
	}
	
	public String getDate() {
		return date.get();
	}
	
	public String getTitle() {
		return title.get();
	}

	public String getTo() {
		return to.get();
	}
	
	public Object getTextOfMessage() {
		return textOfMessage;
	}
	
	
	public void refresh() {
        //watek
        Thread refresher = new Thread(new Runnable() {
			
			public void run() {
				while(true) {
					try {
						Thread.sleep(2000);
						System.out.println("watek");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
         refresher.start();
	}
	
}
