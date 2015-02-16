package se.rapid.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import se.rapid.domain.Customer;
import se.rapid.initializer.MongoConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class TestMain
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{

		// // For Annotation
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		Customer user = new Customer();
		
		String prnr = "6311228313";
		String firstname = "iraj";
		String surname = "ghasemi";
		String username = "iraj";
		String password = "golestan";
		String email = "iraj@zas.se";
		int roleAsInt = 1;
		// int roleAdmin=2;
		String pass = BCrypt.hashpw(password, BCrypt.gensalt());

		user.setSsn(prnr);
		user.setFirstname(firstname);
		user.setSurname(surname);
		user.setUsername(username);
		user.setPassword(pass);
		user.setEmail(email);
		// user.setRole(roleUser);
		user.setRoleAsInt(roleAsInt);

		// mongoOperation.save(user);
		Query query = new Query();
		 query.addCriteria(Criteria.where("firstname").is("iraj").and("email").is("iraj@zas.se"));
		query.addCriteria(Criteria.where("firstname").is("mastane"));

		Customer userTest2 = mongoOperation.findOne(query, Customer.class);
		 System.out.println("query - " + query.toString());
		 System.out.println("userTest - " + userTest2);

		 


		// DateTimeUtils date=new DateTimeUtils();

		// System.out.println("time right now: "+date.getOnlyTime());
		// System.out.println(date.getNameOfTheMonth()+"\n"+ date.getOnlyDate()+
		// "\n"+date.getDayOfTheWeek().getAsText()+
		// "\n"+date.getDayOfTheWeek().getAsShortText());
		// System.out.println(date.getDayOfYear());
		// System.out.println(date.getShortTermOfTheMonth());
		// System.out.println(date.getDayOfTheWeekAsShortTerm());
		// System.out.println(date.getYearAsShortTerm());

		// mongoOperation.save(cus);
		// Customer cus = new Customer("6311228313", "iraj", "ghasemi", "iraj",
		// encodePasswordWithBCrypt("golestan"), "6311228313", "iraj@zas.se",
		// 1);

		// System.out.println(userTest2);
		// String nepass=encodePasswordWithBCrypt("masti");
		// userTest2.setPassword(nepass);
		// mongoOperation.save(userTest2);
		// System.out.println(userTest2);

		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String existingPassword = "masti";
		// String dbPassword = userTest2.getPassword();
		//
		// if (passwordEncoder.matches(existingPassword, dbPassword)) {
		// System.out.println(true);
		// } else {
		// System.out.println(false);
		// }
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			System.out.println(mapper.writeValueAsString(userTest2));
			System.out.println(userTest2);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}

		givenListContainsNulls_whenFilteringParallel_thenCorrect();
		givenListContainsNulls_whenFilteringSerial_thenCorrect();
		givenListContainsDuplicates_whenRemovingDuplicatesWithJava8_thenCorrect();
		sortingListWithNumbers();

	}

	public static String encodePasswordWithBCrypt(String plainPassword)
	{

		return new BCryptPasswordEncoder().encode(plainPassword);
	}

	public static void givenListContainsNulls_whenFilteringParallel_thenCorrect()
	{
		List<Integer> list = Lists.newArrayList(null, 1, 2, null, 3, null);
		List<Integer> listWithoutNulls = list.parallelStream().filter(i -> i != null).collect(Collectors.toList());
		System.out.println(listWithoutNulls);
	}

	public static void givenListContainsNulls_whenFilteringSerial_thenCorrect()
	{
		List<Integer> list = Lists.newArrayList(null, 1, 2, null, 3, null, 4, 5, 6, null);
		List<Integer> listWithoutNulls = list.stream().filter(i -> i != null).collect(Collectors.toList());
		System.out.println(listWithoutNulls);
	}

	public static void givenListContainsDuplicates_whenRemovingDuplicatesWithJava8_thenCorrect()
	{
		List<Integer> listWithDuplicates = Lists.newArrayList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 9);
		List<Integer> listWithoutDuplicates = listWithDuplicates.parallelStream().distinct().collect(Collectors.toList());
		System.out.println(listWithoutDuplicates);
	}

	public static void sortingListWithNumbers()
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(33);
		list.add(44);
		list.add(0);
		list.add(123);
		list.add(19);
		list.add(1899);
		Collections.sort(list);

		System.out.println(list);
		
		try
		{
			readFileAsStream();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	
	
	public static void readFileAsStream() throws IOException
	{
		String path = new File("/Users/irajghasemi/myfiles/Files&Programs/nyc/addajar.text").getCanonicalPath();
//		System.getProperty(path);
		System.out.println(System.getProperty(path));
//		String p="/test/addajar.text";
		
		File theFile = new File(path);
//		File theFile = new File(p);
		LineIterator it = FileUtils.lineIterator(theFile, "UTF-8");
		
		try
		{
			while(it.hasNext()){
				String line = it.nextLine();
				System.out.println(line);
			}
		}
		finally
		{
			LineIterator.closeQuietly(it);
		}

	}

}