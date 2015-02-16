package se.rapid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import se.rapid.security.AuthFailureHandler;
import se.rapid.security.HttpLogoutSuccessHandler;
import se.rapid.service.MongoUserDetailsService;
import se.rapid.service.MySimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private AuthFailureHandler authFailureHandler;
	
	@Autowired
	private HttpLogoutSuccessHandler logoutSuccessHandler;
	
	@Bean
	public MongoUserDetailsService mongoUserDetailsService()
	{

		return new MongoUserDetailsService();
	}

	@Bean
	public MySimpleUrlAuthenticationSuccessHandler myAuthenticationSuccessHandler()
	{
		return new MySimpleUrlAuthenticationSuccessHandler();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(mongoUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());

	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler()
	{
		SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint()
	{
		LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login");
		return loginUrlAuthenticationEntryPoint;
	}

	
	// .csrf() is optional, enabled by default, if using
	// WebSecurityConfigurerAdapter constructor
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.csrf().disable()
//		.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//		.authenticationProvider(authenticationProvider())
//        .exceptionHandling()
//        .authenticationEntryPoint(authenticationEntryPoint)
		.authorizeRequests()
		.antMatchers("/shared/**").permitAll()
		.antMatchers("/lostPassword/**").permitAll()
		.antMatchers("/emailSended/**").permitAll()
		.antMatchers("/signup**").permitAll()
		.antMatchers("/upload**").permitAll()
		.antMatchers("/signedup/**").permitAll()
		.antMatchers("/change-password/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/hello/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/admin/**").hasRole("ADMIN").and()
		.formLogin()
		.successHandler(myAuthenticationSuccessHandler())
		.failureHandler(authFailureHandler)
		.loginPage("/login")
		.failureUrl("/login?error")
		.usernameParameter("username")
		.passwordParameter("password")
		.and()
		.portMapper()
		.http(80).mapsTo(443)
		.http(8080).mapsTo(8443)
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.logoutSuccessHandler(logoutSuccessHandler)
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
		.exceptionHandling()
		.accessDeniedPage("/shared/accessDenied")
		.and()
		.sessionManagement()
		.invalidSessionUrl("/login")
//		.invalidSessionUrl("/login?time=100000")
		.maximumSessions(1);
		
//		http.authorizeRequests().anyRequest().authenticated();
	}

	@SuppressWarnings("unused")
	private CsrfTokenRepository csrfTokenRepository()
	{
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

//	@Configuration
//	@EnableGlobalMethodSecurity(prePostEnabled = true)
//	static class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration
//	{
//		@Autowired
//		private PermissionEvaluator permissionEvaluator;
//
//		@Override
//		protected MethodSecurityExpressionHandler createExpressionHandler()
//		{
//			DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
//			handler.setPermissionEvaluator(permissionEvaluator);
//			return handler;
//		}
//	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
	auth
	.userDetailsService(userDetailsService)
	.passwordEncoder(new BCryptPasswordEncoder());
	}
//	@Bean
//	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//	return new SecurityEvaluationContextExtension();
//	}

}