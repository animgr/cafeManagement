package cafeManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import cafeManagement.security.SimpleUrlAuthenticationSuccessHandler;

/**
 * A replacement for spring security config.
 * All spring security frame work ( http and user authentication management) are
 * put in this class
 * 
 * @author a_mgr
 *
 */
@Configuration
@EnableWebSecurity
@PropertySource("classpath://configs.properties")
public class SecurityContextConfig extends WebSecurityConfigurerAdapter {

	private static final String LOGOUT_URL = "/j_spring_security_logout";
	private static final String LOGIN_PAGE = "/login.jsp";
	private static final String LOGIN_PROCESS_URL = "/j_spring_security_check";
	
	private static final String PASSWORD_PARAMETER = "j_password";
	private static final String USERNAME_PARAMETER = "j_username";

	@Autowired
	private SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler;
	

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	/**
	 * Two hard coded users are added
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	/**
	 * The http configurations will be set in this method. see public_page.jsp to find more about login page 
	 */
	@Override	
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated() 
				.and()
				.formLogin()
					.loginPage(LOGIN_PAGE)
					.permitAll()
					.loginProcessingUrl(LOGIN_PROCESS_URL)
					.usernameParameter(USERNAME_PARAMETER)
					.passwordParameter(PASSWORD_PARAMETER)
					.successHandler(simpleUrlAuthenticationSuccessHandler)
				.and()
					.logout()
					.invalidateHttpSession(true)
					.logoutUrl(LOGOUT_URL)
				.and()
					.csrf().disable();
				
					

	}

	/**
	 * configure which patterns the spring security should not be applied
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/index.jsp", "/images/**", "/css/**", "/js/**");
	}


	/**
	 * The database password encoder
	 * To get the encoded password use: encoder.encode("123456")
	 * @return
	 */
	@Bean(name = "passwordEncoder")
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();		
	}

}

