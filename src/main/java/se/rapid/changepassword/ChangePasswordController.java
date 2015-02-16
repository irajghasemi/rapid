package se.rapid.changepassword;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import se.rapid.domain.Customer;

@Controller
public class ChangePasswordController
{

	private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);

	@Autowired(required = false)
	MongoOperations op;


	@ModelAttribute("form")
	public ChangePasswordForm populateForm(HttpServletRequest request)
	{
		return new ChangePasswordForm();
	}

	@RequestMapping(value = "/changePassword", method = GET)
	public String changePassword()
	{
		logger.debug("Rendering change password form.");
		return "change-password";
	}

	@RequestMapping(value = "/changePassword", method = POST)
	public String processSubmit(HttpServletRequest request, @ModelAttribute("form") ChangePasswordForm form, BindingResult result, HttpServletResponse response)
	{
		logger.debug("Processing change password form.");

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(getCurrentUsername()));
		Customer user = op.findOne(query, Customer.class);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		
		new ChangePasswordFormValidator(user).validate(form, result);
		
		if (!result.hasErrors())
		{
			String password= encodePasswordWithBCrypt(form.getPassword());
			user.setPassword(password);
			op.save(user);
			request.getSession().setAttribute("message", "You have successfully changed your password.");
			securityContextLogoutHandler.logout(request, response, null);
			
			return "redirect:/logout";
		}
		return "change-password";
	}

	private String getCurrentUsername()
	{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return obj instanceof UserDetails ? ((UserDetails) obj).getUsername() : obj.toString();
	}

	public static String encodePasswordWithBCrypt(String plainPassword)
	{

		return new BCryptPasswordEncoder().encode(plainPassword);
	}

}
