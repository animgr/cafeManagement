package cafeManagement.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Configure database which includes database connection management and hibernate
 * 
 * @author a_mgr
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseContextConfig {

	private static final String MODEL_PACKAGE = "cafeManagement.model";
	private static final String HIBERNATE_PROPERTIES = "hibernate.properties";
	
	@Autowired
	private Environment env;

	@Bean(name = "validator")
	public LocalValidatorFactoryBean getValidator() {
		return new LocalValidatorFactoryBean();
	}

	/**
	 * The database connection parameters are read from applicaiton property file
	 * @return database connection
	 */
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("dbClassName"));
		dataSource.setUrl(env.getProperty("dbUrl"));
		dataSource.setUsername(env.getProperty("dbUserName"));
		dataSource.setPassword(env.getProperty("dbPassword"));

		return dataSource;
	}

	/**
	 * get transaction manager
	 * @param entityManagerFactory
	 * @return
	 */
	@Autowired
	@Bean(name = "transactionManager")
	public JpaTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}


	/**
	 * initial JPA entity manager factory, which relays on Hibernate
	 * @param dataSource
	 * @return
	 * @throws IOException
	 */
	@Autowired
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) throws IOException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		entityManagerFactoryBean.setPackagesToScan(MODEL_PACKAGE);
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

		return entityManagerFactoryBean;
	}
	
	@Bean(name = "hibernateProperties")
	public Properties getHibernateProperties() throws IOException {
	    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
	    propertiesFactoryBean.setLocation(new ClassPathResource(HIBERNATE_PROPERTIES));
	    propertiesFactoryBean.afterPropertiesSet();
	    return propertiesFactoryBean.getObject();
	}

}
