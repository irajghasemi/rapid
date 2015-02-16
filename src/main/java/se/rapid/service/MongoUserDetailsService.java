package se.rapid.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import se.rapid.domain.Customer;

@Component
public class MongoUserDetailsService implements UserDetailsService
{
	
	@Autowired
	private MongoOperations mongoOperation;
	
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(MongoUserDetailsService.class);
	private User userdetails;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Customer user= getUserDetail(username);
		userdetails = new User(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(user.getRoleAsInt()));
		return userdetails;
	}
	
	public List<GrantedAuthority> getAuthorities(Integer role)
	{
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		if (role.intValue() == 2)
		{
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		else if (role.intValue() == 1)
		{
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authList;
	}

	public Customer getUserDetail(String username)
	{
		Customer user = mongoOperation.findOne(new Query(Criteria.where("username").is(username)), Customer.class);
		return user;
	}
}