package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("Preparing to send message...");
        String message = " Hello !  this message is for security check";
        String subject = "codersArea : confirmation";
        String to = "kmadhur07@gmail.com";
        String from = "kmadhur07@gmail.com";

        sendEmail(message, subject, to, from);

    }

    //this method is responsible for sending a message
    private static void sendEmail(String message, String subject, String to, String from)  {
        //variable for gmail host
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties = System.getProperties();

        //setting important information to properties object
        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step1.to get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new  PasswordAuthentication("kmadhur07@gmail.com", "ghdldcqxrsglhapn");
            }
        });
        session.setDebug(true);
        //step2. compose the message
        MimeMessage  mimeMessage = new MimeMessage(session);
        try {
            //from email
            mimeMessage.setFrom(from);
            //adding recipients to mes
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set subject
            mimeMessage.setSubject(subject);
            //adding text to message
            mimeMessage.setText(message);
            //send
            //step3 send the message using transport class
            Transport.send(mimeMessage);
            System.out.println("Message sent successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}