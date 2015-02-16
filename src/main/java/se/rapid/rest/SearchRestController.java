package se.rapid.rest;

import java.net.UnknownHostException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.rapid.domain.Customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/search")
@Secured("ROLE_ADMIN,ROLE_USER")
public class SearchRestController
{
	@Autowired
	MongoOperations op;

	private final ObjectMapper mapper = new ObjectMapper();


	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCustomerByEmail(@PathVariable("email") String email) throws JsonProcessingException, UnknownHostException
	{

		Customer user = op.findOne(new Query(Criteria.where("email").is(email)), Customer.class);
		System.out.println("find by email: " + user);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAllUsers() throws JsonProcessingException
	{
		Collection<Customer> list = op.findAll(Customer.class);
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
	}

}
