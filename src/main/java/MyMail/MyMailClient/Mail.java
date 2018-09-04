package MyMail.MyMailClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;



public class Mail {
	private MyData existProfile;

	public Mail(String user) throws FileNotFoundException, IOException {
		existProfile = new MyData();
		existProfile.loadProfile(user);
	}
	
	public void sendMail(String text, String title, String reciver) throws MessagingException {
		
	    Properties props = new Properties();
	    props.put("mail.smtp.host", existProfile.getSmtp());
	    props.put("mail.smtp.port", existProfile.getPort());
	    Session session = Session.getInstance(props, null);
       	MimeMessage msg = new MimeMessage(session);
        msg.setFrom(existProfile.getMyMail());
        msg.setRecipients(Message.RecipientType.TO, reciver);
        msg.setSubject(title);
        msg.setText(text);
        Transport.send(msg, existProfile.getMyMail(), existProfile.getMyPass());
	}
}