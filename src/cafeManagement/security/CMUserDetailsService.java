package cafeManagement.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafeManagement.dao.UserDAO;
import cafeManagement.model.security.UserRole;
import cafeManagement.security.accesspolicy.AccessPolicyHelper;
import cafeManagement.web.CurrentUser;

/**
 * An special security service which authenticate user and find his roles
 * 
 * @author a_mgr
 *
 */

@Service("userDetailsService")
public class CMUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Inject
	public AccessPolicyHelper accessPolicyhelper;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		cafeManagement.model.security.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		// we assume here a user can only have one role
		List<String> listNotAllowActions = accessPolicyhelper.getListOfNotAllowed(authorities.get(0).toString());
		for (String aNonAccessableAction : listNotAllowActions) {
			authorities.add(new SimpleGrantedAuthority("ROLE_NOT_ACCESS_TO_ACTION_" + aNonAccessableAction));
		}
		return buildUserForAuthentication(user, authorities);

	}

	/**
	 * Converts net.iranet.isc.cm.model.User to
	 * org.springframework.security.core.userdetails.User
	 * 
	 * @param user
	 * @param authorities
	 * @return
	 */
	private User buildUserForAuthentication(cafeManagement.model.security.User user,
			List<GrantedAuthority> authorities) {
		String username = user.getUsername();
		String password = user.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new CurrentUser(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);
	}

	/**
	 * create spring needed user authority list, which is actulay a set of
	 * SimpleGrantedAuthoritys
	 * 
	 * @param userRoles
	 * @return
	 */
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			System.out.println("userRole : " + userRole.getRole().toString());

			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(setAuths);
	}

}
