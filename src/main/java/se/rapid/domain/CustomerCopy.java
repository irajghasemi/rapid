package se.rapid.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@XmlType(name = "customerType")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerCopy
{
	@Id
	@XmlElement
	private String id;
	@XmlElement
	private String firstname;
	@XmlElement
	private String surname;
	@XmlElement
	private String username;
	@XmlElement
	private String password;
	@XmlElement
	private String civicNumber;
	@XmlElement
	private String email;
	@XmlElement
	private Integer roleAsInt;
	
	
	

	public Integer getRoleAsInt()
	{
		return roleAsInt;
	}

	public void setRoleAsInt(Integer roleAsInt)
	{
		this.roleAsInt = roleAsInt;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCivicNumber()
	{
		return civicNumber;
	}

	public void setCivicNumber(String civicNumber)
	{
		this.civicNumber = civicNumber;
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

	public int hashCode()
	{
		return new HashCodeBuilder()
		.append("id")
		.append("email")
		.append(civicNumber)
		.toHashCode();
	}

	@Override
	public String toString()
	{
		return new ToStringBuilder(this)
		.append("id", this.id)
		.append("firstname", this.firstname)
		.append("surname", this.surname)
		.append("username", this.username)
		.append("password", this.password)
		.append("civic reg number", this.civicNumber)
		.append("email", this.email)
		.append("role", this.roleAsInt)
		.toString();
	}

}
