package com.curable.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.curable.config.EmailService;

@RestController
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping("/send-email/{email}")
	public String sendEmail(@PathVariable String email) {
		emailService.sendSESEmail(email, "Test Email", "This is a test email sent via AWS SES.");
		return "Email sent!";
	}
}
