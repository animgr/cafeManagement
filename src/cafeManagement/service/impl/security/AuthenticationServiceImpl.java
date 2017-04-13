package cafeManagement.service.impl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cafeManagement.service.security.AuthenticationService;
import cafeManagement	.web.CurrentUser;

/**
 * Authentication service implementation which lays on Spring Security
 * @author a_mgr
 *
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {
 
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

	@Override
	public CurrentUser getPrincipal() {
		return (CurrentUser)getAuthentication().getPrincipal();
	}
    
}