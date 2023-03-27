package com.noname.books_exchange.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public final class EmailUtils {

    private static final String HOST = "smtp.gmail.com";
    private static final String USER_NAME = "loveyiffalways@gmail.com"; //Спасибо, Виталя!
    private static final String PASSWORD = System.getenv("PAPO_GMAIL_PWD");
    //https://support.google.com/mail/answer/7104828?visit_id=638148291518798237-268929434&rd=3
    private static final String PORT = "587";
    private static Properties sendProperties;
    private static String verificationEmailTemplate;

    static {
        String content = "Привет, %UwU%. Перейди по ссылке, чтобы завершить регистрацию: http://localhost:8080/verification/%OwO%";
        try {
            File templateFile = GeneralUtils.getResourceFile("validationEmail.html");
            content = Files.readString(templateFile.toPath());
        } catch (IOException ioe) {

        }
        verificationEmailTemplate = content;
        sendProperties = System.getProperties();
        sendProperties.put("mail.smtp.starttls.enable", "true");
        sendProperties.put("mail.smtp.host", HOST);
        sendProperties.put("mail.smtp.user", USER_NAME);
        sendProperties.put("mail.smtp.password", PASSWORD);
        sendProperties.put("mail.smtp.port", PORT);
        sendProperties.put("mail.smtp.auth", "true");
        sendProperties.put("mail.smtp.ssl.trust", HOST);
        sendProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
    }

    public static final boolean sendVerificationEmail(String email, String nickname, String randomStr) {
        String subject = "Добро пожаловать!";
        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("OwO", randomStr);
        tokens.put("UwU", nickname);
        Session session = Session.getInstance(sendProperties, new javax.mail.Authenticator() {
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
            String patternString = "%(" + String.join("|", tokens.keySet()) + ")%";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(verificationEmailTemplate);
            StringBuffer sb = new StringBuffer();
            while(matcher.find()) {
                matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
            }
            matcher.appendTail(sb);
            String sendContent = sb.toString();
            message.setContent(sendContent, "text/html; charset=utf-8");
            transport.connect(HOST, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            //TODO
            ae.printStackTrace();
        } catch (MessagingException me) {
            //TODO
            me.printStackTrace();
        }
        return true;
    }
}
