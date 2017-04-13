package cafeManagement.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * A web.xml replacement. Manage filters which are needed plus their mappings.
 * 
 * @author a_mgr
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

	@SuppressWarnings("resource")
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.register(ApplicationContextConfig.class);
		servletContext.addListener(new RequestContextListener());

		// Add Spring security filter
		FilterRegistration.Dynamic springSecurityFilterChain = servletContext
				.addFilter(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, DelegatingFilterProxy.class);
		springSecurityFilterChain.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");

		// Add Spring open session filter
		FilterRegistration.Dynamic openEntityManagerInViewFilter = servletContext
				.addFilter("openEntityManagerInViewFilter", new OpenEntityManagerInViewFilter());
		openEntityManagerInViewFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

		// Add Struts MVC filter
		FilterRegistration.Dynamic strutsDispatcher = servletContext.addFilter("strutsDispatcher",
				new StrutsPrepareAndExecuteFilter());
		strutsDispatcher.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

	}

}
