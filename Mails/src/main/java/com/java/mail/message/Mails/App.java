package com.java.mail.message.Mails;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
        System.out.println( "Start email sending " );
        String message="you are getting this email because you are selected for world tour";
        String subject="World Tour: Confirmation";
        String to="syed.shahrukh33@gmail.com";//here we provide to email address
        String from="shahrukhoffiziell@gmail.com";//here we provide sender email id
        
        sendEmail(message, subject, to, from);
    }

    //this is responsible to sending email.
	private static void sendEmail(String message, String subject, String to, String from) {
		
		//variable for gmail host
		String host="smtp.gmail.com"; // simple mail transfer protocol 
		
		//get the system properties
		Properties properties = System.getProperties();
		
		//setting important information to properties object
		
		//setting host
		properties.put("mail.smtp.host", host); // first we set host 
		properties.put("mail.smtp.port","465"); // then its port
		properties.put("mail.smtp.ssl.enable","true"); //its enable ssl true, security true
		properties.put("mail.smtp.auth","true");
		
		
		// new we need session object. above all are we set  the information
	//step 1: to get the session object.
		   Session session = Session.getInstance(properties, new Authenticator() {
			

			// inside this we have to override the method
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
			
				return new PasswordAuthentication("shahrukhoffiziell@gmail.com","yourPassword"); // now password steps are changed in google account. you will find the steps  in notes
			}
		
			
		});
		   session.setDebug(true);
	//step 2: compose the message[text, multi media]
		 MimeMessage m   = new MimeMessage(session);
		 
		 // now we are setting the value
		 try {
			 //from email
			 m.setFrom(from);
			 
			 //adding receipient
			 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			 
			 //adding subject to message
			 m.setSubject(subject);
			 
			 //adding text to message
			 m.setText(message);
			 
			 //send message
			 //step 3: send the message using transport class
			 Transport.send(m);
			 
			 System.out.println("message sent successfully....");
			 
		 }catch(Exception e) {
			 //handle exception
			 e.printStackTrace();
		 }
		
	}
}
