package com.traulko.project.util.mail;

import com.traulko.project.exception.SendMailException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code MailSender} class represents mail sender.
 *
 * @author Anastasiya Golubeva
 * @version 1.0
 */
public class MailSender {
    private static final Logger LOGGER = LogManager.getLogger(MailSender.class);
    private static final String PROPERTY_NAME = "property/mail.properties";
    private static final String MAIL_SUBJECT = "Online store";
    private static final String MAIL_MESSAGE_LINK = "%s?commandName=account_access_command&accessCode=";

    private MimeMessage message;
    private final String sendToEmail;
    private final String mailSubject;
    private final String mailText;
    private final Properties properties;

    /**
     * Instantiates a new MailSender.
     *
     * @param email the email
     * @param code the code
     * @param url the url
     */
    public MailSender(String email, String url, String code) {
        this.sendToEmail = email;
        this.mailSubject = MAIL_SUBJECT;
        this.mailText = String.format(MAIL_MESSAGE_LINK, url) + code;
        properties = getProperties();
    }

    /**
     * Send message.
     */
    public void send() throws SendMailException {
        try {
            initMessage();
            Transport.send(message);
        } catch (MessagingException e) {
            throw new SendMailException(e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        ClassLoader classLoader = MailSender.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(PROPERTY_NAME);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error while reading properties");
        }
        return properties;
    }
}
