package se.rapid.web.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
public class AdminController
{
	@Autowired
	MongoOperations op;

	public static Integer adminRole = 2;

	@RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
	public String adminIndex(ModelMap model)
	{
		Collection<Customer> users = op.findAll(Customer.class);
		model.addAttribute("users", users);
		model.addAttribute("currentUser", getCurrentUsername());
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/editUser", method = RequestMethod.GET)
	public String getSingelUserForUpdate(HttpServletRequest request, ModelMap model)
	{
		model.addAttribute("currentUser", getCurrentUsername());

		String ssn = request.getParameter("ssn");

		Customer user = op.findOne(new Query(Criteria.where("ssn").is(ssn)), Customer.class);

		model.addAttribute("getuserforupdate", user);

		return "/admin/updateUser";
	}

	@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") Customer user, ModelMap model, BindingResult result, RedirectAttributes redirect, HttpServletRequest request)
	{
		if (result.hasErrors())
		{
			return "/admin/admin";
		}

		String firstname = user.getFirstname();
		String surname = user.getSurname();
		String username = user.getUsername();
		String email = user.getEmail();

		Query query = Query.query(Criteria.where("email").is(user.getEmail()));

		Customer customer = new Customer();

		customer.setFirstname(user.getFirstname());
		customer.setSurname(user.getSurname());
		customer.setEmail(user.getEmail());

		Update update = new Update();
		update.set("firstname", firstname).set("surname", surname).set("username", username).set("email", email);

		// BasicDBObject updateUser = new BasicDBObject().append("$set", new
		// BasicDBObject().append("firstname", firstname).append("surname",
		// surname).append("email", email));
		// op.getCollection("customer").update(new
		// BasicDBObject(),updateUser,false,true);

		op.updateMulti(query, update, Customer.class);
		return "redirect:/admin/admin";
	}

	@RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request, ModelMap model)
	{
		model.addAttribute("currentUser", getCurrentUsername());
		String ssn = request.getParameter("ssn");
		Customer user = op.findOne(new Query(Criteria.where("ssn").is(ssn)), Customer.class);
		op.remove(user);
		return "redirect:/admin/admin/";
	}

	@RequestMapping(value = "/admin/addAdmin", method = RequestMethod.GET)
	public String addNewAdminIndex(ModelMap model)
	{
		
		return "/admin/addAdmin";
	}

	@RequestMapping(value = "/admin/addAdmin", method = RequestMethod.POST)
	public String addNewAdmin(@ModelAttribute("user") Customer user, ModelMap model, BindingResult result, RedirectAttributes redirect, HttpServletRequest request)
	{
		if (result.hasErrors())
		{
			return "/admin/addAdmin";
		}

		String password = encodePasswordWithBCrypt(user.getPassword());

		Customer newAdmin = new Customer(user.getSsn(), user.getFirstname(), user.getSurname(), user.getUsername(), password, user.getEmail(), adminRole);
		op.save(newAdmin);
		redirect.addFlashAttribute("globalMessage", "Successfully signed up new admin");

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
		SecurityContextHolder.getContext().setAuthentication(auth);

		model.addAttribute("addAdmin", newAdmin);

		return "/admin/adminAdded";
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
