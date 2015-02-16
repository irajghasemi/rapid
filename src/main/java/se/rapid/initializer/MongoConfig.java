package se.rapid.initializer;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoConfig
{

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception
	{
		return new SimpleMongoDbFactory(new MongoClient(), "booking");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception
	{

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		return mongoTemplate;

	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception
	{
		MongoDbFactory dbFactory = mongoDbFactory();
		MongoConverter converter = mappingMongoConverter();
		GridFsTemplate gridFsTemplate = new GridFsTemplate(dbFactory, converter);
		return gridFsTemplate;
	}

	@Bean
	public MappingMongoConverter mappingMongoConverter() throws Exception
	{
		MongoMappingContext mappingContext = new MongoMappingContext();
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
		converter.setCustomConversions(customConversions());
		return converter;
	}

	/**
	 * Register custom {@link Converter}s in a {@link CustomConversions} object
	 * if required. These {@link CustomConversions} will be registered with the
	 * {@link #mappingMongoConverter()} and {@link #mongoMappingContext()}.
	 * Returns an empty {@link CustomConversions} instance by default.
	 *
	 * @return must not be {@literal null}.
	 */
	@Bean
	public CustomConversions customConversions()
	{
		return new CustomConversions(Collections.emptyList());
	}

}