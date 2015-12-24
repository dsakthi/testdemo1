package com.sme.tst;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestSendMail
{

	public static void main(String[] args)
	{

		try
		{
			String smtp_host = "";
			String smtp_port = "";
			if (args.length > 0)
				smtp_port = args[0];

			String smtp_user = "";
			String smtp_password = "";
			String from_email = "";
			String to_email = "@.com";
			if (args.length > 1)
				to_email = args[1];

			System.out.println("#################### Sending Mail using Below Configuration ####################");
			System.out.println("smtp_host:" + smtp_host);
			System.out.println("smtp_port:" + smtp_port);
			System.out.println("smtp_user:" + smtp_user);
			System.out.println("smtp_password:" + smtp_password);
			System.out.println("from_email:" + from_email);
			System.out.println("to_email:" + to_email);
			System.out.println("################################################################################");
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtp_host);
			properties.put("mail.smtp.port", smtp_port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.localhost","..com");
			
			// creates a new session with an authenticator
			Authenticator auth = new Authenticator()
			{
				public PasswordAuthentication getPasswordAuthentication()
				{
					return new PasswordAuthentication(smtp_user, smtp_password);
				}
			};

			Session session = Session.getInstance(properties, auth);
			session.setDebug(true);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(from_email));
			InternetAddress[] toAddresses =
			{new InternetAddress(to_email)};
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject("This is Test Mail");
			msg.setSentDate(new Date());
			msg.setText("This is A test mail to test SES from Dev App");

			// sends the e-mail
			Transport.send(msg);

			System.out.println(">>>> Mail Sent SUCCESS");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(">>>> ERROR in sending Mail");
		}
	}

}
