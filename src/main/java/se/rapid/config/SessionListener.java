package se.rapid.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionListener extends HandlerInterceptorAdapter implements HttpSessionListener
{

	@Override
	public void sessionCreated(HttpSessionEvent event)
	{
		// System.out.println("==== Session is created ====");
		event.getSession().setMaxInactiveInterval(15 * 60);
	}

	// System.out.println("==== Session is created ====");
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		request.getSession().setMaxInactiveInterval(15 * 60);
		return true;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event)
	{
		// System.out.println("==== Session is destroyed ====");
	}

}