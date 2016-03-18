package Assignment_4;

import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		Properties props = new Properties();
		props.put("mail.smtp.com", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		String to = "tejapruthvivarma@gmail.com";
		String from = "tejapruthvivarma@gmail.com";
		String subject = arg.toString();
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"tejapruthvivarma@gmail.com", "***********");
					}
				});
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(arg.toString() + "Web Page Updated");
			Transport.send(msg);
		} catch (MessagingException exc) {

		}
	}

}
