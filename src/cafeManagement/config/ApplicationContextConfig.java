package cafeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configure CM managed beans 
 * 
 * @author a_mgr
 *
 */
@Configuration
@ComponentScan("cafeManagement")
@PropertySource("classpath://configs.properties")
public class ApplicationContextConfig {


	@Bean(name = "validator")
	public LocalValidatorFactoryBean getValidator() {
		return new LocalValidatorFactoryBean();
	}
}
