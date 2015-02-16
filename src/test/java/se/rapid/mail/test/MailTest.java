package se.rapid.mail.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import se.rapid.domain.Customer;
import se.rapid.service.EmailServiceSender;

public class MailTest
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new AnnotationConfigApplicationContext(EmailServiceSender.class);
		EmailServiceSender sender= (EmailServiceSender) ctx.getBean("emailServiceSender");
		Customer user= new Customer();
		user.setEmail("iraj@zas.se");
		sender.sendLostPasswordEmail(user, "text");
		
	}
	
}
