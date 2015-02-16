package se.rapid.rest;

import java.util.Collection;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.rapid.domain.Customer;
import se.rapid.utils.AuthoritiesConstants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/rest")
//@Secured("ROLE_ADMIN,ROLE_USER")
@RolesAllowed(AuthoritiesConstants.ADMIN)
public class UserRestController
{

	@Autowired
	private MongoOperations op;

	private final ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getAllUsers() throws JsonProcessingException
	{

		Collection<Customer> users = op.findAll(Customer.class);

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);

	}

	@RequestMapping(value = "/{ssn}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getUserByCivicNumber(@PathVariable("ssn") String civicNumber) throws JsonProcessingException
	{
		Customer user = op.findOne(new Query(Criteria.where("ssn").is(civicNumber)), Customer.class, "customer");

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);

	}

	@RequestMapping(value = "/firstname/{firstname}/surname/{surname}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String findByFirstnameAndLastname(@PathVariable("firstname") String firstname,@PathVariable("surname") String surname) throws JsonProcessingException{
		
		Collection<Customer> list= op.find(new Query(Criteria.where("firstname").is(firstname).andOperator(Criteria.where("surname").is(surname))), Customer.class, "customer");
		
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String getUserByEmail(@PathVariable("email") String email) throws JsonProcessingException
	{
		
		Customer user = op.findOne(new Query(Criteria.where("email").is(email)), Customer.class, "customer");
		
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		
	}

	@RequestMapping(value = "/allusers", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String allUser(@QueryParam("callback") String callback) throws JsonProcessingException
	{

		Collection<Customer> users = op.findAll(Customer.class);

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);

	}
	
	
	public List<Customer> searchForNames(String searchValue){
		  searchValue=".*" + searchValue + ".*";
		  Criteria firstNameCriterion=new Criteria("firstname").regex(searchValue,"i");
		  Criteria lastNameCriterion=new Criteria("surname").regex(searchValue,"i");
		  Criteria criteria=new Criteria().orOperator(firstNameCriterion,lastNameCriterion);
		  Query query=Query.query(criteria);
		  return op.find(query,Customer.class);
		}

}