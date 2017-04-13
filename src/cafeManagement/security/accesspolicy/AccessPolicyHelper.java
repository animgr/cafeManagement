package cafeManagement.security.accesspolicy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import utils.PropertyIgnoreCase;

/**
 * Find the list of actions a user can access. This list is build by adding all
 * the actions which user can not visit plus other access policies
 * 
 * @author a_mgr
 * 
 */
@Named
public class AccessPolicyHelper {
	private static final Logger LOG = LoggerFactory.getLogger(AccessPolicyHelper.class);

	private static final String NOT_ALLOW_ACTIONS = "not_allowed_actions";
	private static final String NOT_ALLOW_ACTIONS_FOR_ADMIN = "not_allowed_actions_for_admin";
	private static final String NOT_ALLOW_ACTIONS_FOR_NON_ADMIN = "not_allowed_actions_for_none_admin";

	// Special users must be case insensitive when looked up from property files
	@Resource(name = "accessPolicy")
	public PropertyIgnoreCase accessPolicy;

	/**
	 * A comma list of actions which a saba user is not allowed to access
	 * 
	 * @param user
	 * @return
	 */
	public List<String> getListOfNotAllowed(String userRole) {
		String notAllowdActions = (String) accessPolicy.get(NOT_ALLOW_ACTIONS);
		LOG.debug("The {} are not allowed for {}", notAllowdActions, userRole);

		String notAllowedActionsBaseOnUserType;
		// There might be access policy for admin and non-admin users
		if (userRole.contains("ADMIN")) {
			notAllowedActionsBaseOnUserType = (String) accessPolicy.get(NOT_ALLOW_ACTIONS_FOR_ADMIN);
			LOG.debug("The user is admin, not allowed links for admin is {}", notAllowedActionsBaseOnUserType);
		} else {
			notAllowedActionsBaseOnUserType = (String) accessPolicy.get(NOT_ALLOW_ACTIONS_FOR_NON_ADMIN);
			LOG.debug("The user is not persona, not allowed links for not persoanl links is {}", notAllowedActionsBaseOnUserType);
		}
		// Convert not allowed to list, it must be a linked list as we may want
		// to remove from it.
		String allNotAllowed = notAllowdActions + "," + notAllowedActionsBaseOnUserType;
		List<String> notAllowedActionsList = new LinkedList<>(Arrays.asList(allNotAllowed.split(",")));

		return notAllowedActionsList;
	}
}
