import java.util.*;  

import javax.mail.*;  

import javax.mail.internet.*;  

import javax.activation.*; 

import javax.mail.*;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import java.util.Properties; 
public class Email {
	public static void sendEmail(String email) 

	{



		final String username = "rescipes@outlook.com";

		final String password = "CSCI201FinalProject";

		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.outlook.com");

		prop.put("mail.smtp.port", "587");

		prop.put("mail.smtp.auth", "true");

		prop.put("mail.smtp.starttls.enable", "true"); //TLS

		Session session = Session.getInstance(prop,new javax.mail.Authenticator() 

		{

			protected PasswordAuthentication getPasswordAuthentication() 

			{

				return new PasswordAuthentication(username, password);

			}

		});



		try {



			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(username));

			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));

			message.setSubject("Welcome to reSCipes");

			message.setText("Welcome Chef,\n\nThank you for signing up!\n\nOur Web Application allows you to find and share reSCipes with your friends and other users. We strive to provide you with the best so that your meals can be as delicious as possible! Now don't just wait there, get on the application and start sharing...\n\nWarm Regards,\nTeam reSCipes");

			Transport.send(message);

			System.out.println("Email sent!");

		} catch (MessagingException e) 

		{

			e.printStackTrace();

		}

	}
}
