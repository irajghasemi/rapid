package se.rapid.config;

import org.springframework.stereotype.Service;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * MorpiahMongoDBConnection providing the database connection.
 */
@Service
public class MorphiaMongoDBConnection
{
	private static final MorphiaMongoDBConnection INSTANCE = new MorphiaMongoDBConnection();

	private final Datastore datastore;
	public static final String DB_NAME = "booking";

	private MorphiaMongoDBConnection()
	{
		try
		{
			MongoClient mongo= new MongoClient( new ServerAddress("lockalhost",27017));
			datastore = new Morphia().mapPackage("se.rapid").createDatastore(mongo, DB_NAME);
			datastore.ensureIndexes();
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error initializing MorphiaMongoDBConnection", e);
		}
	}

	public static MorphiaMongoDBConnection instance()
	{
		return INSTANCE;
	}

	public Datastore getDatabase()
	{
		return datastore;
	}
}