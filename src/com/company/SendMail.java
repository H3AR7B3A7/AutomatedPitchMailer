package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class SendMail {

    private static final String username = "steven.d.hondt.sdh@gmail.com";
    private static String password;
    private static String receivers;
    private static String pitch;

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        getInfo(args);
        readGeneratedPitch();
        sendMail();
    }

    /**
     * Check args
     */
    private static void checkArgs(String[] args) {
        int i = args.length;
        if (i > 0) {
            password = args[0];
        }
        if (i > 1) {
            receivers = args[1];
        }
    }

    /**
     * Get required information if not all args are present
     */
    private static void getInfo(String[] args) throws IOException {
        if (args.length < 4) {
            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Password:");
            password = cin.readLine();
            System.out.println("Receivers seperated by ',':");
            receivers = cin.readLine();
        }
    }

    /**
     * Read pitch from file: pitch.txt
     */
    private static void readGeneratedPitch() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("pitch.txt"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        pitch = stringBuilder.toString();
    }

    /**
     * Send mail through Gmail-account
     * Use of Gmail account requires access for less secure applications from:
     * https://myaccount.google.com/lesssecureapps
     */
    private static void sendMail() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("steven.d.hondt.sdh@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(receivers)
            );
            message.setSubject("Job Application");
            message.setText(pitch);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}