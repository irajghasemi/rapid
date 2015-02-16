package se.rapid.mail.test;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// Public Class
public class EmailSMS {

    // Global Variables
    String              TO;
    String              FROM;
    String              SUBJECT;
    String              TEXT;
    String              MAILHOST;
    String              LASTERROR;

    // Main function executed
    public static void main(String[] args) throws Exception {
        EmailSMS SMS = new EmailSMS();

        SMS.setMailHost("smtprelay1.telia.com");
        SMS.setTo("*******");
        SMS.setFrom("iraj@zas.se");
        SMS.setSubject("SMS FROM SMTP");
        SMS.setText("Hello World!");
        boolean ret = SMS.send();

        if (ret) {
            System.out.println("SMS was sent!");
        } else {
            System.out.println("SMS was not sent - " + SMS.getLastError());
        }
    }

    // Public Constructor
    public EmailSMS() {
        TO = null;
        FROM = null;
        SUBJECT = null;
        TEXT = null;
        MAILHOST = null;
        LASTERROR = "No method called.";
    }

    public void setTo(String to) {
        TO = to;
    }

    public String getTo() {
        return TO;
    }

    public void setFrom(String from) {
        FROM = from;
    }

    public String getFrom() {
        return FROM;
    }

    public void setSubject(String subject) {
        SUBJECT = subject;
    }

    public String getSubject() {
        return SUBJECT;
    }

    public void setText(String text) {
        TEXT = text;
    }

    public String getText() {
        return TEXT;
    }

    public void setMailHost(String host) {
        MAILHOST = host;
    }

    public String getMailHost() {
        return MAILHOST;
    }

    public String getLastError() {
        return LASTERROR;
    }

    // Will attempt to send the Email SMS and return a boolean meaning it
    // either failed or succeeded.
    public boolean send() {

        // Variables to check message length.
        int maxLength;
        int msgLength;

        // Check to make sure that the parameters are correct
        if (TO.indexOf("mobile.att.net") > 0) {
            maxLength = 140;
        } else if (TO.indexOf("messaging.nextel.com") > 0) {
            maxLength = 280;
        } else if (TO.indexOf("messaging.sprintpcs.com") > 0) {
            maxLength = 100;
        } else {
            maxLength = 160;
        }

        // Calculate message length
        msgLength = FROM.length() + 1 + SUBJECT.length() + 1 + TEXT.length();

        // Typically, there are at least two characters of delimiter
        // between the from, subject, and text. This is here to make
        // sure the message isn't longer than the device supports.
        if (msgLength > maxLength) {
            LASTERROR = "SMS length too long.";
            return false;
        }

        // Set Email Properties
        Properties props = System.getProperties();

        if (MAILHOST != null) {
            props.put("mail.smtp.host", MAILHOST);
        }

        // Get a Session object
		Session session = Session.getDefaultInstance(props, null);

        try {

            // Construct the email
            Message msg = new MimeMessage(session);

            // Set From
            if (FROM != null) {
                msg.setFrom(new InternetAddress(FROM));
            } else {
                msg.setFrom();
            }

            // Set Subject
             msg.setSubject(SUBJECT);

            // Set Text
            msg.setText(TEXT);

            // Add Recipient
            msg.setRecipients(Message.RecipientType.TO,
                              InternetAddress.parse(TO, false));

            // Sent Date
            msg.setSentDate(new Date());

			// Send Email SMS
			Transport.send(msg);

            LASTERROR = "Success.";
            return true;
        } catch (MessagingException mex) {
            LASTERROR = mex.getMessage();
	    return false;
        }
    }
}
