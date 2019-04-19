package com.sportsclub.mailer;

import com.sportsclub.admindao.AdminDao;
import com.sportsclub.admindao.AdminDaoImpl;
import com.sportsclub.domain.Mail;

public class MailService {

	private static AdminDao adminDao = new AdminDaoImpl();

	public static boolean sendMailService(String recipientmailaddress, String message, String mailSubject) {
		Mail mail = adminDao.getMailAuthentication();
		int msgid = (int) (Math.random() * 1234 + 599); //Random Message ID
		if (mail != null) {
			return Mailer.sendMail(mail, recipientmailaddress, message, mailSubject + (" Mail id :- " + msgid));
		} else {
			return false;
		}
	}

}
