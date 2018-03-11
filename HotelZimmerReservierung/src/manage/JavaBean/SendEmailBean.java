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
	
	String rechnungsnummer;
	
	String toEmail;
	String emailSubject;
	String emailBody;
	
	class SMTPAuthenticator extends Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication("greatforother@gmail.com", "Tity@013890!");
		}
	}
	
	public SendEmailBean(String toEmail, String emailSubject, String emailBody, String rechnungsnummer) {
		super();
		this.toEmail = toEmail;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.rechnungsnummer = rechnungsnummer;
		
		//System properties
		Properties properties = new Properties();
		properties.put("mail.smtp.user", "greatforother@gmail.com"); //fromEmail
		properties.put("mail.smtp.host", "smtp.gmail.com"); //emailSMTPserver
		properties.put("mail.smtp.port", "465"); //emailServerPort
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", "465");
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
			msg.setFrom(new InternetAddress("greatforother@gmail.com"));
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
//	         String filename = "Rechnung.pdf";
	         String filename = rechnungsnummer+"_"+"Rechnung.pdf";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart );
			
			//send message
			Transport.send(msg);
			System.out.println("E-MAIL successfully send...");
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
	
	public String getRechnungsnummer() {
		return rechnungsnummer;
	}
	
	public void setRechnungsnummer(String rechnungsnummer) {
		this.rechnungsnummer = rechnungsnummer;
	}

}
