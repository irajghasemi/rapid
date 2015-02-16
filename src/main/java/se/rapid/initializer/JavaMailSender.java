//package se.rapid.initializer;
//
//import java.util.Properties;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//@ComponentScan(basePackages = { "se.rapid.*" })
//@PropertySource(value = { "classpath:/velocity/gmail.properties" })
//public class JavaMailSender
//{
//	
//	@Autowired(required=false)
//	private Environment env;
//
//	@Autowired(required=false)
//	void setEnvironment(Environment env) {
//		System.out.println("setting environment javamailsender: " + 
//                      env.getProperty("mail.host"));
//	}
//
//	@Bean
//	public  JavaMailSenderImpl mailSender()
//	{
//		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//		javaMailSender.setProtocol(env.getProperty("mail.protocol"));
//		javaMailSender.setHost(env.getProperty("mail.host"));
//		
//		String portAsString=env.getProperty("mail.post");
//		int portAsInteger=Integer.parseInt(portAsString);
//		System.out.println(portAsInteger);
//		
//		javaMailSender.setPort(portAsInteger);
//		
//		javaMailSender.setUsername(env.getProperty("mail.username"));
//		javaMailSender.setPassword(env.getProperty("mail.password"));
//
//		Properties props = new Properties();
//
//		props.setProperty(env.getProperty("mail.smtps.host"), "true");
//		props.setProperty(env.getProperty("mail.smtp.starttls.enable"), "true");
//		props.setProperty(env.getProperty("mail.smtps.host"), "true");
//		props.setProperty(env.getProperty("mail.smtp.isSecure"), "true");
//		props.setProperty(env.getProperty("mail.transport.protocol"), "true");
//		props.setProperty(env.getProperty("mail.smtps.auth"), "true");
//		javaMailSender.setJavaMailProperties(props);
//
//		return javaMailSender;
//	}
////	@Bean
////	public  JavaMailSenderImpl mailSender()
////	{
////		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
////		javaMailSender.setProtocol("mail.protocol");
////		javaMailSender.setHost("mail.host");
////		javaMailSender.setHost(env.getProperty("mail.host"));
////		javaMailSender.setPort(465);
////		javaMailSender.setUsername("mail.username");
////		javaMailSender.setPassword("mail.password");
////		
////		Properties props = new Properties();
////		
////		props.setProperty("mail.smtps.host", "true");
////		props.setProperty("mail.smtp.starttls.enable", "true");
////		props.setProperty("mail.smtps.host", "true");
////		props.setProperty("mail.smtp.isSecure", "true");
////		props.setProperty("mail.transport.protocol", "true");
////		props.setProperty("mail.smtps.auth", "true");
////		javaMailSender.setJavaMailProperties(props);
////		
////		return javaMailSender;
////	}
//	
////	@Bean
////	public JavaMailSenderImpl mailSender()
////	{
////		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
////		javaMailSenderImpl.setDefaultEncoding("UTF-8");
////		javaMailSenderImpl.setHost("smtp.gmail.com");
////		javaMailSenderImpl.setPort(587);
////		javaMailSenderImpl.setUsername("*************");
////		javaMailSenderImpl.setPassword("*************");
////
////		Properties properties = new Properties();
////		properties.put("mail.smtp.auth", true);
////		properties.put("mail.smtp.starttls.enable", true);
////		properties.put("mail.smtp.auth", true);
////		properties.put("mail.smtps.host", true);
////		properties.put("mail.smtp.isSecure", true);
////		javaMailSenderImpl.setJavaMailProperties(properties);
////		return javaMailSenderImpl;
////	}
//
//
//}
