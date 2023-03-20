package com.noname.books_exchange.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.util.ResourceUtils;

public final class EmailUtils {

    private static final String HOST = "smtp.gmail.com";
    private static final String USER_NAME = "loveyiffalways@gmail.com"; //Спасибо, Виталя!
    private static final String PASSWORD = System.getenv("PAPO_GMAIL_PWD");
    //https://support.google.com/mail/answer/7104828?visit_id=638148291518798237-268929434&rd=3
    private static final String PORT = "587";
    private static String verificationEmailTemplate;

    static {
        String content = "WIP";
        try {
            File templateFile = ResourceUtils.getFile("classpath:templates/validationEmail.html");
            content = Files.readString(templateFile.toPath());
        } catch (IOException ioe) {

        }
        verificationEmailTemplate = content;
    }

    public static final boolean sendVerificationEmail(String email) {
        Properties props = System.getProperties();
        String subject = "Hello world";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", HOST);
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress sender = new InternetAddress(USER_NAME);
            InternetAddress recipient = new InternetAddress(email);
            Transport transport = session.getTransport("smtp");
            message.setFrom(sender);
            message.addRecipient(Message.RecipientType.TO, recipient);
            message.setSubject(subject);
            message.setContent(verificationEmailTemplate, "text/html");
            transport.connect(HOST, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return true;
    }
}
