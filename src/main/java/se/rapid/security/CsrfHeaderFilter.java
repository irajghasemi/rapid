package se.rapid.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

public class CsrfHeaderFilter extends OncePerRequestFilter
{
	
	@Override
	  protected void doFilterInternal(HttpServletRequest request,
	      HttpServletResponse response, FilterChain filterChain)
	      throws ServletException, IOException {
	    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
	        .getName());
	    if (csrf != null) {
	      Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
	      String token = csrf.getToken();
	      if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
	        cookie = new Cookie("XSRF-TOKEN", token);
	        cookie.setPath("/");
	        response.addCookie(cookie);
	      }
	    }
	    filterChain.doFilter(request, response);
	  }
//	private static final String CSRF_TOKEN = "CSRF-TOKEN";
//	private static final String X_CSRF_TOKEN = "X-CSRF-TOKEN";
//	private final RequestMatcher requireCsrfProtectionMatcher = new DefaultRequiresCsrfMatcher();
//	private final AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
//	{
//		if (requireCsrfProtectionMatcher.matches(request))
//		{
//			final String csrfTokenValue = request.getHeader(X_CSRF_TOKEN);
//			final Cookie[] cookies = request.getCookies();
//
//			String csrfCookieValue = null;
//			if (cookies != null)
//			{
//				for (Cookie cookie : cookies)
//				{
//					if (cookie.getName().equals(CSRF_TOKEN))
//					{
//						csrfCookieValue = cookie.getValue();
//					}
//				}
//			}
//
//			if (csrfTokenValue == null || !csrfTokenValue.equals(csrfCookieValue))
//			{
//				accessDeniedHandler.handle(request, response, new AccessDeniedException("Missing or non-matching CSRF-token"));
//				return;
//			}
//		}
//		filterChain.doFilter(request, response);
//	}
//
//	public static final class DefaultRequiresCsrfMatcher implements RequestMatcher
//	{
//		private final Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
//
//		@Override
//		public boolean matches(HttpServletRequest request)
//		{
//			return !allowedMethods.matcher(request.getMethod()).matches();
//		}
//	}
}