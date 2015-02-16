package se.rapid.config;

import java.security.Permission;
import java.util.Properties;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "se.rapid.*" })
@Import({ SecurityConfig.class })
@EnableSpringDataWebSupport
@PropertySource({ "classpath:/velocity/gmail.properties", "classpath:/velocity/velocity.properties" })
public class AppConfig extends WebMvcConfigurerAdapter
{

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	public void addViewController(ViewResolverRegistry reg)
	{
		reg.enableContentNegotiation(new MappingJackson2JsonView());
		reg.jsp();

	}


	@Bean
	public InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public VelocityViewResolver velocityViewiewResolver()
	{
		VelocityViewResolver resolver = new VelocityLayoutViewResolver();
		resolver.setExposeSpringMacroHelpers(true);
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setSuffix(".vm");
		return resolver;
	}

	// Enable serving static public even when DispatcherServlet is mapped to
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		logger.debug("configureDefaultServletHandling");
		configurer.enable();
	}

	@Bean
	public AnnotationMBeanExporter annotationMBeanExporter()
	{
		return new AnnotationMBeanExporter();
	}

	@Bean
	public LocaleResolver localeResolver()
	{
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
		cookieLocaleResolver.setCookieName("LOCALE");
		return cookieLocaleResolver;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		logger.debug("setting up resource handlers");
		// registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		super.addResourceHandlers(registry);
		if (!registry.hasMappingForPattern("/resources/**"))
		{
			// you have to define a cache period
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
			registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

		}
	}

	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry)
	{
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	{
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.web.servlet.PageNotFound", "/shared/pageNotFound");
		mappings.put("org.springframework.dao.DataAccessException", "dataAccessFailure");
		mappings.put("org.springframework.transaction.TransactionException", "dataAccessFailure");
		// Alternative to other lines
		mappings.put(NoResultException.class.getName(), "/shared/pageNotFound");
		mappings.put(Exception.class.getName(), "/shared/pageNotFound");
		mappings.put(Permission.class.getName(), "/shared/accessDenied");
		mappings.put(ResourceNotFoundException.class.getName(), "/shared/accessDenied");
		mappings.put(TypeMismatchException.class.getName(), "/shared/badRequest");
		mappings.put(NoSuchRequestHandlingMethodException.class.getName(), "/shared/pageNotFound ");
		mappings.put(HttpRequestMethodNotSupportedException.class.getName(), "/shared/requestNoSupported");
		mappings.put(HttpMediaTypeNotSupportedException.class.getName(), "/shared/mediaTypeNotSupport ");
		mappings.put(HttpMediaTypeNotAcceptableException.class.getName(), "/shared/badMediaType");
		mappings.put(MissingServletRequestParameterException.class.getName(), "/shared/badRequest ");
		mappings.put(ServletRequestBindingException.class.getName(), "/shared/badRequest ");
		mappings.put(MethodArgumentNotValidException.class.getName(), "/shared/badRequest ");
		mappings.put(MissingServletRequestPartException.class.getName(), "/shared/badRequest ");
		mappings.put(HttpMessageNotReadableException.class.getName(), "/shared/badRequest ");
		mappings.put(HttpMessageNotWritableException.class.getName(), "/shared/serverError ");
		mappings.put(ConversionNotSupportedException.class.getName(), "/shared/serverError ");
		mappings.put(DataAccessException.class.getName(), "/shared/serverError");
		mappings.put(ClassNotFoundException.class.getName(), "/shared/serverError");
		mappings.put(RuntimeException.class.getName(), "/shared/serverError");

		b.setExceptionMappings(mappings);
		return b;
	}

	// Only needed if we are using @Value and ${...} when referencing properties
	// Otherwise @PropertySource is enough by itself

	@Bean
	public static PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
	{
		PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
		placeholderConfigurer.setLocation(new ClassPathResource("/messages/messages.properties"));
		placeholderConfigurer.setLocation(new ClassPathResource("/messages/messages_fr.properties"));
		placeholderConfigurer.setLocation(new ClassPathResource("/messages/messages_es.properties"));
		// placeholderConfigurer.setLocation(new
		// ClassPathResource("velocity/gmail.properties"));
		// placeholderConfigurer.setLocation(new
		// ClassPathResource("messages/gmail.properties"));
		// placeholderConfigurer.setLocation(new
		// ClassPathResource("velocity/velocity.properties"));
		// placeholderConfigurer.setLocation(new
		// ClassPathResource("velocity/lost-password.vm"));
		placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return placeholderConfigurer;
	}

	@Bean
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/messages");
		// if true, the key of the message will be displayed if the key is not
		// found, instead of throwing a NoSuchMessageException
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		// # -1 : never reload, 0 always reload
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public HandlerExceptionResolver handlerExceptionResolver()
	{
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		exceptionResolver.setDefaultErrorView("uncaughtException");
		Properties mappings = new Properties();
		mappings.put(".DataAccessException", "/shared/accessDenied");
		mappings.put(".NoSuchRequestHandlingMethodException", "/shared/pageNotFound ");
		mappings.put(".TypeMismatchException", "/shared/pageNotFound ");
		mappings.put(".MissingServletRequestParameterException", "/shared/pageNotFound ");
		exceptionResolver.setExceptionMappings(mappings);
		return exceptionResolver;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(5 * 1024 * 1024);
		return resolver;
	}

}