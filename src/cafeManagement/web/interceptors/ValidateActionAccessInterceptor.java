package cafeManagement.web.interceptors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;

import cafeManagement.security.accesspolicy.AccessPolicyHelper;

/**
 * Check if user can access an action class. The interceptor assumes the user is
 * already logged in, for not logged in users it will do nothing!
 * 
 * @author a_mgr
 */
public class ValidateActionAccessInterceptor extends BaseInterceptor {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ValidateActionAccessInterceptor.class);
	@Inject
	public AccessPolicyHelper accessPolicyhelper;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = getActionName();
		HttpServletRequest request = getHttpServletRequest();
		HttpSession session = request.getSession(false);

		// If user has no session, let the action proceed, the task of this
		// interceptor is to prevent
		// logged in users to certain actions
		if (session == null) {
			return invocation.invoke();
		}
		// User user = (User)
		// session.getAttribute(WebConstants.USER_SESSION_KEY);
		// if (user == null) {
		// return invocation.invoke();
		// }

		// The spring security has custom implementation of
		// HttpServletRequestWrapper named
		// SecurityContextHolderAwareRequestWrapper
		// which overrides the isUserInRole and search SPRING authorities
		if (request.isUserInRole("ROLE_NOT_ACCESS_TO_ACTION_" + actionName)) {
			LOG.debug("The user can not access action {}", actionName);
			return Action.NONE;
		}

		return invocation.invoke();
	}

	public void init() {
		LOG.info("Initializing ValidateActionAccessInterceptor");
	}

}
