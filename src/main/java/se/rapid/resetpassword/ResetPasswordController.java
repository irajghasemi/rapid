package se.rapid.resetpassword;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.rapid.domain.Customer;

@Controller
public class ResetPasswordController
{

	private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

	@Autowired(required = false)
	MongoOperations op;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.setAllowedFields(new String[] { "confirm", "password", "username" });
	}

	@ModelAttribute("form")
	public ResetPasswordForm populateForm(@RequestParam("username") String username)
	{
		return new ResetPasswordForm(username);
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, @RequestParam("frob") String frob)
	{
		logger.debug("Rendering reset password form.");
		Cookie cookie = null;
		for (Cookie c : request.getCookies())
		{
			if (c.getName().equals("frob"))
			{
				cookie = c;
			}
		}
		return isValidCookie(cookie, frob) ? "reset-password" : "reset-expired";
	}

	@RequestMapping(value = "/password/reset", method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("form") ResetPasswordForm form, HttpServletRequest request, BindingResult result, HttpServletResponse response)
	{
		logger.debug("Processing reset password form.");

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(form.getUsername()));
		Customer user = op.findOne(query, Customer.class);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		new ResetPasswordFormValidator().validate(form, result);

		if (!result.hasErrors())
		{
			String password= encodePasswordWithBCrypt(form.getPassword());
			user.setPassword(password);
			
			op.save(user);
			request.getSession().setAttribute("message", "You have successfully changed your password.");
			securityContextLogoutHandler.logout(request, response, null);
			return "redirect:/index";
		}
		return "reset-password";
	}

	private boolean isValidCookie(Cookie cookie, final String frob)
	{
		return cookie != null && cookie.getValue().equals(frob);
	}

	public static String encodePasswordWithBCrypt(String plainPassword)
	{

		return new BCryptPasswordEncoder().encode(plainPassword);
	}
	
	
}
