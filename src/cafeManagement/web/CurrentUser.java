package cafeManagement.web;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import cafeManagement.enums.Role;


/**
 * Hold extra user information. The class extends the Spring User class too be managed in session.
 * There is no need to annotate this class as session bean or whatever.
 * 
 * @author a_mgr
 *
 */
public class CurrentUser extends User{

	private static final long serialVersionUID = 1L;

	private Role role;

	public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	

}

