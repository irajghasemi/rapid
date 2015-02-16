package se.rapid.domain;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mongodb.morphia.annotations.Property;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.code.morphia.annotations.Entity;

@Document(collection = "customer")
@Entity(value="customer",noClassnameStored=true)
public class Customer
{
	@Id
	@XmlElement
	@Property("ssn")
	private String ssn;
	@XmlElement
	private String firstname;
	@XmlElement
	private String surname;
	@XmlElement
	private String username;
	@XmlElement
	private String password;
	@XmlElement
	private String email;
	@XmlElement
	private Integer roleAsInt;
	@XmlElement
	private Collection<String> blockedId;
	
	@NotNull
    @Pattern(regexp = "^[a-z0-9]*$")
    @Size(min = 1, max = 50)
    private String login;

	public Customer()
	{
		super();
	}

	
	
	public Customer(String ssn, String firstname, String surname, String username, String password, String email, Integer roleAsInt, Collection<String> blockedId)
	{
		super();
		this.ssn = ssn;
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleAsInt = roleAsInt;
		this.blockedId = blockedId;
	}



	public Customer(String ssn, String firstname, String surname, String username, String password, String email, Integer roleAsInt)
	{
		super();
		this.ssn = ssn;
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleAsInt = roleAsInt;
	}

	public String getSsn()
	{
		return ssn;
	}

	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	public Integer getRoleAsInt()
	{
		return roleAsInt;
	}

	public void setRoleAsInt(Integer roleAsInt)
	{
		this.roleAsInt = roleAsInt;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	

	public Collection<String> getBlockedId()
	{
		return blockedId;
	}

	public void setBlockedId(Collection<String> blockedId)
	{
		this.blockedId = blockedId;
	}
	
	
	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Customer)
		{
			Customer other = (Customer) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(getSsn(), other.getSsn());
			builder.append(getFirstname(), other.getFirstname());
			builder.append(getSurname(), other.getSurname());
			builder.append(getUsername(), other.getUsername());
			builder.append(getPassword(), other.getPassword());
			builder.append(getBlockedId(), this.getBlockedId());
			builder.append(getEmail(), this.getEmail());
			return builder.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getSsn());
		builder.append(getFirstname());
		builder.append(getSurname());
		builder.append(getUsername());
		builder.append(getPassword());
		builder.append(getEmail());
		builder.append(getBlockedId());
		builder.append(getRoleAsInt());
		return builder.toHashCode();
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this)
		.append("ssn", this.ssn)
		.append("firstname", this.firstname)
		.append("surname", this.surname)
		.append("username", this.username)
		.append("password", this.password)
		.append("email", this.email)
		.append("role", this.roleAsInt)
		.append("blocked", this.blockedId)
		.toString();
	}

}
