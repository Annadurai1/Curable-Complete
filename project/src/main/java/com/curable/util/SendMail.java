/*
 * package com.curable.util;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import java.util.Properties;
 * 
 * import javax.mail.Message; import javax.mail.internet.InternetAddress; import
 * javax.mail.internet.MimeMessage; import java.io.File;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.io.FileSystemResource; import
 * org.springframework.mail.MailException; import
 * org.springframework.mail.SimpleMailMessage; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.mail.javamail.JavaMailSenderImpl; import
 * org.springframework.mail.javamail.MimeMessageHelper; import
 * org.springframework.mail.javamail.MimeMessagePreparator;
 * 
 * public class SendMail {
 * 
 * @Autowired private JavaMailSender mailSender;
 * 
 * public void sendMailWithAttachment(String to, String subject, String body,
 * String fileToAttach) { MimeMessagePreparator preparator = new
 * MimeMessagePreparator() { public void prepare(MimeMessage mimeMessage) throws
 * Exception { mimeMessage.setRecipient(Message.RecipientType.TO, new
 * InternetAddress(to)); mimeMessage.setFrom(new
 * InternetAddress("admin@gmail.com")); mimeMessage.setSubject(subject);
 * mimeMessage.setText(body);
 * 
 * FileSystemResource file = new FileSystemResource(new File(fileToAttach));
 * MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
 * helper.addAttachment("logo.jpg", file); } };
 * 
 * try { mailSender.send(preparator); } catch (MailException ex) { // simply log
 * it and go on... System.err.println(ex.getMessage()); } } }
 */