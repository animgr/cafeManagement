package cafeManagement.service.security;

import org.springframework.security.core.Authentication;

import cafeManagement.web.CurrentUser;

/**
 * The interface for all authentication services
 * @author a_mgr
 *
 */
public interface AuthenticationService {	
	  Authentication getAuthentication();
	  CurrentUser getPrincipal();
}

