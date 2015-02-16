package se.rapid.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.rapid.domain.Customer;

@Controller
public class CustomerController
{
	@Autowired
	MongoOperations op;

	@RequestMapping(value = "/user/users", method = RequestMethod.GET)
	public String getAllUsers(ModelMap model)
	{
		Collection<Customer> users = op.findAll(Customer.class);
		model.addAttribute("users", users);
		model.addAttribute("currentUser", getCurrentUsername());
		return "/user/users";
	}
	
	
	private String getCurrentUsername()
	{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return obj instanceof UserDetails ? ((UserDetails) obj).getUsername() : obj.toString();
	}
	

}
