package com.curable.config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.curable.service.dto.custom.MailCustomDTO;
import com.curable.util.CommonUtil;
import com.curable.util.Constant;

@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Value("${spring.mail.from}")
	private String from;

	@Autowired
	CommonUtil commonUtil;

	@Value("${spring.mail.username}")
	private String userName;

	@Value("${spring.mail.password}")
	private String password;

	// Method for sending a simple email
	public void sendSimpleEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("support@curable.care"); // Replace with a verified email in SES
		message.setTo("sada@curable.care");
		message.setSubject(subject);
		message.setText(body);

		try {
			javaMailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	// Method for sending an HTML email
	public void sendHtmlEmail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("support@curable.care"); // Replace with a verified email in SES
		helper.setTo("sada@curable.care");
		helper.setSubject(subject);
		helper.setText(body, true); // true for HTML content

		try {
			javaMailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	public void sendSESEmail(String to, String subject, String body) {
		try {
			BasicAWSCredentials awsCredentials = new BasicAWSCredentials(userName, password);

			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					// Replace US_WEST_2 with the AWS Region you're using for
					// Amazon SES.
					.withRegion(Regions.AP_SOUTH_1).build();
			SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(to))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(body))
									.withText(new Content().withCharset("UTF-8").withData(body)))
							.withSubject(new Content().withCharset("UTF-8").withData(subject)))
					.withSource(from)
					// Comment or remove the next line if you are not using a
					// configuration set
					.withConfigurationSetName("my-first-configuration-set");
			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
		}
	}

	public void sendMail(String to, String subject, MailCustomDTO mailDTO) {
		try {

			String body = commonUtil.processHtml(Constant.USER_MAIL, Constant.LIST_USER, mailDTO);

			BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIAXWMA6HM6EDC3YZMT",
					"dS1mwkd2tGijrTm38aw0xFwtTKqxlBeUGuMcjpyu");

			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					// Replace US_WEST_2 with the AWS Region you're using for
					// Amazon SES.
					.withRegion(Regions.AP_SOUTH_1).build();
			SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(to))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(body))
									.withText(new Content().withCharset("UTF-8").withData(body)))
							.withSubject(new Content().withCharset("UTF-8").withData(subject)))
					.withSource(from)
					// Comment or remove the next line if you are not using a
					// configuration set
					.withConfigurationSetName("my-first-configuration-set");
			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
		}
	}
}
