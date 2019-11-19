package com.sportsclub.mailer;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

import com.sportsclub.domain.Mail;

public class Mailer {

	public synchronized static boolean sendMail(Mail mail, String recipientmailaddress, String message,
			String mailSubject) {
		try {
			String host = "smtp.gmail.com";
			String from = mail.getHostmailaddress();
			String pass = mail.getHostpassword();
			String to = recipientmailaddress;
			String user = mail.getHostmailaddress();
			String subject = mailSubject;
			String BODY = String.join(System.getProperty("line.separator"), "<h3>Sports club System</h3>",
					" <br> " + message + " <br>", "<h3>Thank you Team Sports Club </h3>");
			String messageText = "Hyyy boss u have a message!!!!!!!!!! ";
			System.out.println("Sending the mail please wait --------------------");

			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(BODY, "Text/html");

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Message sent successfully!!!!!!!!!!!!");
			return true;
		} catch (Exception ex) {
			System.out.println("Error occured while sending mail");
			System.out.println(ex);
			return false;
		}

	}

}
