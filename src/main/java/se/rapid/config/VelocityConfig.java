package se.rapid.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

@Configuration
@ComponentScan(basePackages = { "se.rapid.*" })
@PropertySource(value = { "classpath:/velocity/gmail.properties", "classpath:/velocity/lost-password.vm", "classpath:/velocity/velocity.properties" })
@PropertySources(value = { @PropertySource("classpath:/velocity/gmail.properties") })
public class VelocityConfig
{

	@Autowired
	public VelocityEngine velocityEngine;

	@Autowired
	public VelocityEngineFactoryBean velocityEngineFactoryBean;

	@Autowired(required = false)
	public JavaMailSender mailSender;

	@Autowired
	private Environment environment;
	
	@SuppressWarnings("static-access")
	public void setEnvironment(Environment environment) {
//        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_SPRING_MAIL);
		this.propertyPlaceHolderConfigurer();
    }

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException
	{
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}

	@Bean
	public VelocityEngineFactoryBean velocityEngineFactoryBean()
	{
		VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
		Properties velocityProperties = new Properties();
		velocityProperties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityProperties.setProperty("resource.loader", "class");
		velocityProperties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityProperties.setProperty("file.resource.loader.modificationCheckInterval", "3600");
		velocityEngineFactoryBean.setVelocityProperties(velocityProperties);
		return velocityEngineFactoryBean;
	}

	@Bean
	public VelocityConfigurer velocityConfig()
	{
		VelocityConfigurer configurer = new VelocityConfigurer();
		configurer.setResourceLoaderPath("/velocity");
		Properties props = new Properties();
		props.put("output.encoding", "UTF-8");
		props.put("input.encoding", "UTF-8");
		configurer.setVelocityProperties(props);
		return configurer;
	}

	@Bean
	public JavaMailSender mailSender()
	{
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setDefaultEncoding("UTF-8");
		javaMailSenderImpl.setHost("smtp.gmail.com");
		javaMailSenderImpl.setPort(587);
		javaMailSenderImpl.setUsername("j10mehdig@gmail.com");
		javaMailSenderImpl.setPassword("Kermanshah1342");
		
//		javaMailSenderImpl.setHost(env.getProperty("smtp.host"));
//		javaMailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
//		javaMailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
//		javaMailSenderImpl.setUsername(env.getProperty("smtp.username"));
//		javaMailSenderImpl.setPassword(env.getProperty("smtp.password"));

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtps.host", true);
		properties.put("mail.smtp.isSecure", true);
		javaMailSenderImpl.setJavaMailProperties(properties);
		return javaMailSenderImpl;
	}

}
