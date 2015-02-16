package se.rapid.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import se.rapid.domain.Customer;
import se.rapid.security.BCryptPasswordEncoder;

@Controller
@RequestMapping
public class SignUpController
{

	public static Integer userRole = 1;

	@Autowired
	MongoOperations op;

	BCryptPasswordEncoder encoder;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupIndex()
	{

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") Customer user, ModelMap model, BindingResult result, RedirectAttributes redirect)
	{

		if (result.hasErrors())
		{
			return "/signup";
		}

		String password = encodePasswordWithBCrypt(user.getPassword());

		Customer signup = new Customer(user.getSsn(), user.getFirstname(), user.getSurname(), user.getUsername(), password, user.getEmail(), userRole);
		op.save(signup);
		redirect.addFlashAttribute("globalMessage", "Successfully signed up");

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		model.addAttribute("added", signup);

		return "signedup";
	}

	@RequestMapping(value = "/signedup", method = RequestMethod.GET)
	public String signedup()
	{

		return "signedup";
	}

	public static String encodePasswordWithBCrypt(String plainPassword)
	{

		return new BCryptPasswordEncoder().encode(plainPassword);
	}

}
