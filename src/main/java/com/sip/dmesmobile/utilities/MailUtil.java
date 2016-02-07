/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sip.dmesmobile.utilities;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author gchavarro88
 */
public class MailUtil {  
  
    private String SMTP_HOST = "smtp.gmail.com";  
  
    public boolean sendMail(String[] recipients, String[] bccRecipients, String subject, 
            String message, final String userName, final String password, String namePersonal )throws Exception
    {  
        try {  
            Properties props = new Properties();  
            props.put("mail.smtp.host", SMTP_HOST);  
            props.put("mail.smtp.auth", "true");  
            props.put("mail.debug", "false");  
            props.put("mail.smtp.ssl.enable", "true");  
            props.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                @Override  
                    protected PasswordAuthentication getPasswordAuthentication() 
                    {  
                        return new PasswordAuthentication(userName, password);  
                    }
            });  
            Message msg = new MimeMessage(session);  
            
            InternetAddress from = new InternetAddress(userName, namePersonal);  
            msg.setFrom(from);  
  
            InternetAddress[] toAddresses = new InternetAddress[recipients.length];  
            if(recipients != null && recipients.length > 0)
            {
                for (int i = 0; i < recipients.length; i++) 
                {  
                    toAddresses[i] = new InternetAddress(recipients[i]);  
                }  
            }
            msg.setRecipients(Message.RecipientType.TO, toAddresses);  
  
  
            InternetAddress[] bccAddresses = null;  
            if(bccRecipients != null && bccRecipients.length > 0)
            {
                bccAddresses = new InternetAddress[bccRecipients.length];  
                for (int j = 0; j < bccRecipients.length; j++)
                {
                    bccAddresses[j] = new InternetAddress(bccRecipients[j]);
                }
            }  
            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);  
  
            msg.setSubject(subject);  
            msg.setContent(message, "text/plain");  
            Transport.send(msg);  
                return true;  
        } catch (UnsupportedEncodingException ex) {  
            
            throw ex;  
  
        } catch (MessagingException ex) {  
            
            throw ex;  
        }  
    }  
    
    
}