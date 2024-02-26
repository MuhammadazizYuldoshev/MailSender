package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    public static boolean sendText(String from,String to,String token, String text, String subject) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, token);
                }
            };

            Session session = Session.getInstance(props, auth);


            Message message = new MimeMessage(session);

            message.setSubject(subject);
            message.setText(text);
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(from));

            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            Transport.send(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

