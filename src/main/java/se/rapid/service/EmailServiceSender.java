package se.rapid.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

import se.rapid.domain.Customer;
import se.rapid.repository.VelocityEmailRepository;

@Service("emailServiceSender")
@PropertySource(value = { "classpath:/velocity/gmail.properties" })
public class EmailServiceSender implements VelocityEmailRepository
{
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceSender.class);

	@Autowired(required = true)
	public VelocityEngine velocityEngine;

	@Autowired(required = true)
	public VelocityEngineFactoryBean velocityEngineFactoryBean;

	@Autowired(required = true)
	public JavaMailSender mailSender;
	

	String velocity_WEB_INF_Path = "/velocity/lost-password.vm";
	String Image_WEB_INF_Path = "/velocity/RESET-PASSWORD1.png";

	public void sendLostPasswordEmail(final Customer user, final String action)
	{
		logger.debug("Sending lost password email to: {}", user.getUsername());

		MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare(MimeMessage mimeMessage) throws Exception
			{

				velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
				velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
				velocityEngine.init();

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				message.setTo(user.getEmail());
				message.setFrom("no-reply@gmail.com");
				message.setSubject("Your Password");
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				model.put("url", action);
//				String urlLogo ="/velocity/RESET-PASSWORD1.png";
//				model.put("urlLogo", urlLogo);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocity_WEB_INF_Path,"UTF-8", model);
				FileSystemResource res = new FileSystemResource(new File(Image_WEB_INF_Path).getCanonicalPath());
//				message.setText("<html><body><img src='cid:image'></body></html>", true);
//				FileSystemResource res = new FileSystemResource(new File("/velocity/RESET-PASSWORD1.png").getAbsolutePath());
				message.addInline("image", res);
				System.out.println(res.contentLength());
				System.out.println(res.exists());
				System.out.println(res.getFilename());
				System.out.println(res.getFile());
				message.setText(text, true);

			}
		};
		this.mailSender.send(preparator);
	}

}