package manage.JavaBean;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmailBean {
	
	final String fromEmail = "wimaster.hotel@gmail.com";
	final String fromPasswort = "init12345";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String toEmail;
	String emailSubject;
	String emailBody;
	
	public static void main(String[] args) {
		String toEmail =  "greatforother@gmail.com";
		String emailSubject = "Hotelzimmer Reservierung";
		String emailBody = "Hallo Mr. Mandfred Müller, \n"
				+ "Wenn du diese MAil liest, bedeutet dass du die Rechnung (im Anhang) bezahlt hast"
				+ " und dass Wir morgen zusammen frühstücken werden :) . \n"
				+ "\n"
				+ "Kind Regards, \n"
				+ "Tity Zozur a.k.a Fresh Montana \n"
				+ "\n"
				+ "ps: Die Rechnung sieht scheisse aus, ich weiß...LOL";
		SendEmailBean sendEmailBean = new SendEmailBean(toEmail, emailSubject, emailBody);
	}
	
	class SMTPAuthenticator extends Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(fromEmail, fromPasswort);
		}
	}
	
	public SendEmailBean(String toEmail, String emailSubject, String emailBody) {
		super();
		this.toEmail = toEmail;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		
		//System properties
		Properties properties = new Properties();
		properties.put("mail.smtp.user", fromEmail);
		properties.put("mail.smtp.host", emailSMTPserver);
		properties.put("mail.smtp.port", emailServerPort);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", emailServerPort);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		SecurityManager security = System.getSecurityManager();
		
		
		try {
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(properties, auth);
			
			//create message
			MimeMessage msg = new MimeMessage(session);

			//add Subject and send
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			
			// Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(emailBody);
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "Rechnung.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart );
			
			//send message
			Transport.send(msg);
			System.out.println("Message successfully send...");
		} 
		catch (MessagingException me) {
			me.printStackTrace();
		}	
	}
	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	
}
