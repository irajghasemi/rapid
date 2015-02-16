package se.rapid.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import se.rapid.config.AppConfig;
import se.rapid.config.SecurityConfig;
import se.rapid.config.SessionListener;
import se.rapid.config.VelocityConfig;
import se.rapid.execption.ErrorHandling;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		// return new Class[] { SecurityConfig.class,
		// MongoConfig.class,JavaMailSender.class,VelocityConfig.class,ErrorHandling.class
		// };
		return new Class[] { SecurityConfig.class, MongoConfig.class, VelocityConfig.class, ErrorHandling.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters()
	{

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
		MultipartFilter multipartFilter = new MultipartFilter();
		return new Filter[] { characterEncodingFilter, springSecurityFilterChain, new HiddenHttpMethodFilter(), multipartFilter };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		super.onStartup(servletContext);
		servletContext.addListener(new SessionListener());
	}

}