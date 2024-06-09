package com.yashdevs.optiTracker.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.stereotype.Component;

import com.yashdevs.optiTracker.resource.EmailVerificationHtmlProvider;
import com.yashdevs.optiTracker.resource.OptiTrackerConstants;

@Component
public class EmailVerificationService {

	public EmailVerificationService() {
		// TODO document why this constructor is empty
	}

	public boolean validateEmail(String email, String token) {
		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
			props.put("mail.smtp.port", "587"); // TLS Port
			props.put("mail.smtp.auth", "true"); // enable authentication
			props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
			props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // jdk supports > TLS v1

			// create Authenticator object to pass in Session.getInstance argument
			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(OptiTrackerConstants.EMAIL_FROM,
							OptiTrackerConstants.GOOGLE_APP_TOKEN_OPTITRACKER);
				}
			};
			Session session = Session.getInstance(props, auth);

			String location = "http://localhost:5000/auth/verify/" + token;

			StringBuilder message = EmailVerificationHtmlProvider.provide(location);

			return EmailUtilService.sendEmail(session, email, "Opti Tracker Email Verification", message.toString());

		} catch (Exception e) {
			return false;
		}
	}

	public String generateToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}
