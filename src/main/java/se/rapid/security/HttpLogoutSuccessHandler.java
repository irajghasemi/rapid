package se.rapid.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class HttpLogoutSuccessHandler implements LogoutSuccessHandler
{
	public final String url="http://localhost:8080/rapid/";
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException
	{
		response.setStatus(HttpServletResponse.SC_OK);
		response.encodeURL(url);
		response.encodeRedirectURL(url);
		response.sendRedirect(url);
		response.getWriter().flush();
	}
}