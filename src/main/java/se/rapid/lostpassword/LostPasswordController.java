package se.rapid.lostpassword;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.rapid.domain.Customer;
import se.rapid.security.BCrypt;
import se.rapid.service.EmailServiceSender;

@Controller
public class LostPasswordController
{
	private static final Logger logger = LoggerFactory.getLogger(LostPasswordController.class);

	@Autowired
	MongoOperations op;

	@Autowired(required = false)
	public EmailServiceSender sender;

	public static String URL = "http://localhost:8080/rapid/password/reset?frob=";

	@Autowired(required = false)
	public void setSender(EmailServiceSender sender)
	{
		this.sender = sender;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.setAllowedFields(new String[] { "captcha", "username" });
	}

	@ModelAttribute("form")
	public LostPasswordForm populateForm()
	{
		return new LostPasswordForm();
	}

	@RequestMapping(value = "/lostPassword", method = RequestMethod.GET)
	public String lostPassword()
	{
		logger.debug("Rendering lost password form.");
		return "/lostPassword";
	}

	@RequestMapping(value = "/lostPassword", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("form") LostPasswordForm form, Model model, HttpServletResponse response, HttpServletRequest request,
			BindingResult result, ModelAndView modelView)
	{

		logger.debug("Processing lost password form.");
		new LostPasswordFormValidator().validate(form, result);

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(form.getUsername()));

		if (!result.hasErrors())
		{

			Customer user = op.findOne(query, Customer.class);
			if (user != null)
			{
				String frob = BCrypt.hashpw(user.getUsername() + "3m4il", BCrypt.gensalt());
				String link = createLostPasswordLink(user, frob);
				response.addCookie(persistFrob(frob));
				// model for the *.vm
				modelView.addObject("user", user);

				// model for the *.jsp
				model.addAttribute("userModel", user);
				model.addAttribute("userEmail", user);

				sender.sendLostPasswordEmail(user, link);
				request.getSession().setMaxInactiveInterval(15);
				return "emailSended";
			}
			result.rejectValue("username", "error.username.invalid");
		}
		return "redirect:/emailSended";
	}

	@RequestMapping(value = "/emailSended", method = RequestMethod.GET)
	public String emailSended()
	{
		return "emailSended";
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
