package se.rapid.test;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.text.DefaultEditorKit.CutAction;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import se.rapid.domain.Customer;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class NewMainTest
{

	public static void main(String[] args) throws UnknownHostException
	{
		DBCollection collection = createConnection();
		// printCollection(collection);
		// DBObject user=collection.findOne(new BasicDBObject("username",
		// "iraj"), new BasicDBObject("password","golestan"));
		DBObject userByUsername = collection.findOne(new BasicDBObject("username", "iraj"));
		DBObject userByPassword = collection.findOne(new BasicDBObject("password", userByUsername.get("password")));
		// System.out.println("found user by username: "+userByUsername);
		// System.out.println("found user by password: "+userByPassword);

		final String separator = ",";
		Arrays.asList("1", "2", "3").forEach((String e) -> System.out.println(e + separator));

		Optional<String> firstName = Optional.of("Iraj");
		System.out.println("First Name is set? " + firstName.isPresent());
		System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
		System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
		System.out.println();
		
		// Find using the text index
		BasicDBObject search = new BasicDBObject("$search", "iraj@zas.se");
		BasicDBObject textSearch = new BasicDBObject("$text", search);
		//		BasicDBObject textSearch1 = new BasicDBObject("$text", new BasicDBObject("$search", "iraj@zas.se"));
		DBCursor cursor=collection.find(textSearch);
		
		try {
			   while(cursor.hasNext()) {
			       System.out.println("from search: "+cursor.next());
//			       System.out.println("from search: "+textSearch.toString());
			   }
			} finally {
			   cursor.close();
			}

		
		int matchCount = collection.find(textSearch).count();
		System.out.println("Text search matches: "+ matchCount);
		
//		System.out.println("find by email: "+collection.findOne(new BasicDBObject("email", "iraj@zas.se")));
		
	}
	
	
	
	

	private static DBCollection createConnection() throws UnknownHostException
	{
		MongoClient client = new MongoClient();
		DB db = client.getDB("booking");
		DBCollection collection = db.getCollection("customer");
		collection.createIndex(new BasicDBObject("email","text"));   // for creating an text index to use full text search
																	// and can only one ensourdIndex be added to a collection
																	//  ensourdIndex is deprecated and we use createIndex
		
		return collection;
	}

	private static void printCollection(final DBCollection collection)
	{
		DBCursor cursor = collection.find().sort(new BasicDBObject("_id", 1));
		try
		{
			while (cursor.hasNext())
			{
				System.out.println(cursor.next());
			}
		}
		finally
		{
			cursor.close();
		}

	}

}
