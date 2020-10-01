/**
 * 
 */
package com.ito.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
/**
 * @author Mohan Kappadi
 *
 */
@SuppressWarnings("unused")
public class SendFileEmail {

	/**
	 * 
	 */
	public SendFileEmail() {
		// TODO Auto-generated constructor stub
	}
	
	/*private static FileReader reader;
	private static Properties pr=new Properties();
	
	static {
		try {
			reader = new FileReader("PASS.properties");
			pr.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		sendEmail(pr);
	}*/
	
	public static void sendEmail(Properties pr) throws MessagingException {     

		String to = pr.getProperty("mail.to.address");
		String from = pr.getProperty("mail.from.address");
		String host = pr.getProperty("mail.smtp.host");
		pr.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(pr);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			
			String tos [] = to.split(";");
			for(String t:tos) {
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(t));
			}
			message.setSubject(pr.getProperty("mail.subject.line")+getTodaysDate(false));
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(pr.getProperty("mail.body.line"));
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			String filename = pr.getProperty("pass.file.path")+pr.getProperty("pass.file.name")+getTodaysDate(true)+pr.getProperty("pass.file.extension");
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart );

			Transport.send(message);
			System.out.println("Mail sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw mex;
		}
	}
	
	private static String getTodaysDate(boolean b) {
		if(!b) {
			DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			Date date = new Date();
			return dateFormat.format(date);
		}
		else {
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        Date date = new Date();
			return dateFormat.format(date);
		}
	}
}
