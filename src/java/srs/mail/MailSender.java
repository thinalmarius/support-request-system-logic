
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srs.mail;

import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Thinal
 */
public class MailSender {
    static Logger logger = Logger.getLogger("srsmail");
    static FileHandler fh;
    static String username;
    static String password;
    static String host;
    static String port;
    static String ccEmail;
    
        
    public static void send(String recipientEmail, int ticketid, String line, String category) {
        try {
            fh = new FileHandler("maillog.txt");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        SimpleFormatter formatterTxt = new SimpleFormatter();
        fh.setFormatter(formatterTxt);
        logger.addHandler(fh);
        
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.xml");
            org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(input));
            XPath xpath = XPathFactory.newInstance().newXPath();
            host = (String) xpath.compile("//send//authentication//host").evaluate(document, XPathConstants.STRING);
            username = (String) xpath.compile("//send//authentication//username").evaluate(document, XPathConstants.STRING);
            password = (String) xpath.compile("//send//authentication//password").evaluate(document, XPathConstants.STRING);
            port = (String) xpath.compile("//send//authentication//port").evaluate(document, XPathConstants.STRING);
            if(category=="software")
                ccEmail = (String) xpath.compile("//send//software//mailid").evaluate(document, XPathConstants.STRING);
            else if(category=="hardware")
                ccEmail = (String) xpath.compile("//send//hardware//mailid").evaluate(document, XPathConstants.STRING);
        } catch (IOException e) {
        } catch (SAXException ex) {
             logger.log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        // Get a Properties object
        System.out.println(username + ccEmail + recipientEmail);
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", host);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
            
        System.out.println(host);
        
        //props.put("mail.smtps.quitwait", "false");
           
        Session session = Session.getInstance(props, null); 
        
        String title="SR#"+String.format("%05d",ticketid)+" - "+line+"...";           
        String msg="Dear Customer \n\nYour request has been accepted and our technician will contact "
                    + "you soon. \n \nThank You \n"
                    + "SRS Team";
           
        try {
            System.out.println("Done");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(title);
            System.out.println("Done");
            message.setText(msg);
            System.out.println("Done");
            SMTPTransport t = (SMTPTransport)session.getTransport("smtps");

            t.connect(host, username, password);
            t.sendMessage(message, message.getAllRecipients());      
            t.close();
            System.out.println("Done");

	} catch (MessagingException e) {
        	
	}
    }

        
}
