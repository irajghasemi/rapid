package se.rapid.email;

import java.util.Collection;

import javax.mail.internet.AddressException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.rapid.domain.Customer;
import se.rapid.domain.EmailMessage;
import se.rapid.lostpassword.LostPasswordForm;
import se.rapid.security.BCrypt;
import se.rapid.service.EmailServiceSender;

@Controller
public class EmailController
{
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	MongoOperations op;

	@Autowired
	public EmailServiceSender sender;

	private final String dbName = "booking";
	
	public static String URL = "http://localhost:8080/rapid/password/reset?frob=";

	@RequestMapping(value = "/recover", method = RequestMethod.GET)
	public String getIndex(ModelMap model)
	{
		model.addAttribute("emailAddress", new EmailServiceSender());
		return "index";
	}


	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String sendEmailByVelocity(@ModelAttribute("form") LostPasswordForm form, ModelMap model, HttpServletRequest request, HttpServletResponse response,
			ModelAndView modelView) throws AddressException
	{

		logger.debug("Processing lost password form.");
		Collection<Customer> getUser = op.findAll(Customer.class, dbName);
		System.out.println("user from emailcontoller: "+getUser);
		for (Customer user : getUser)
		{
			System.out.println(user);

			if (user != null)
			{
				String frob = BCrypt.hashpw(user.getUsername() + "3m4il", BCrypt.gensalt());
				String link = createLostPasswordLink(user, frob);
				response.addCookie(persistFrob(frob));
				// model for the *.vm
				modelView.addObject("user", user);
				// model for the *.jsp
				model.addAttribute("userModel", user);
				sender.sendLostPasswordEmail(user, link);
				return "/emailSended";
			}
		}
		return "redirect:/emailSended";
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public String getPassword(ModelMap model)
	{
		model.addAttribute("sended", new EmailMessage());
		return "sendEmail";
	}

	private String createLostPasswordLink(final Customer user, final String frob)
	{
		StringBuilder link = new StringBuilder();
		link.append(URL);
		link.append(frob);
		link.append("&username=");
		link.append(user.getUsername());
		return link.toString();
	}

	private Cookie persistFrob(final String frob)
	{

		Cookie cookie = new Cookie("frob", frob);
		cookie.setMaxAge(60 * 60); // 1 hour
		return cookie;
	}

}
