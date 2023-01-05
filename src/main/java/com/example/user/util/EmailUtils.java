package com.example.user.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;

	 public boolean sendHtmlEmail(String to, String subject, String body) {
		 boolean isSent=false;
		 try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	        helper.setTo(to);
	        helper.setSubject(subject);
	        helper.setText(body, true);
	        javaMailSender.send(message);
	        isSent=true;
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	    }
}
