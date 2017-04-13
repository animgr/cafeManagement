package cafeManagement.actions.management;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.code.rees.scope.conversation.annotations.BeginConversation;
import com.google.code.rees.scope.conversation.annotations.ConversationController;
import com.google.code.rees.scope.conversation.annotations.ConversationField;
import com.opensymphony.xwork2.ModelDriven;

import cafeManagement.actions.BaseActionSupport;
import cafeManagement.model.security.User;
import cafeManagement.model.security.UserRole;
import cafeManagement.service.servicefacade.UserManagementServices;

@ConversationController
public class CreateUser extends BaseActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(CreateUser.class);

	@ConversationField
	User user = new User();

	@Inject
	private UserManagementServices userManagementServices;

	private String userType;

	@Action("create-user")
	@BeginConversation
	public String form() {
		LOG.debug("Show the action");
		return SUCCESS;
	}

	@Action("create-user-confirm")
	public String confirm() {
		UserRole userRoleVO = new UserRole();
		userRoleVO.setRole("ROLE_ADMIN");
		userRoleVO.setUser(user);
		userRoleVO.setUserRoleId(1);
		Set<UserRole> userRoles = new HashSet<UserRole>(0);
		userRoles.add(userRoleVO);
		user.setUserRole(userRoles);
		user.setEnabled(true);
		userManagementServices.createUser(user);

		LOG.debug("creat user name {} ", user.getUsername());

		return SUCCESS;
	}

	@Override
	public User getModel() {
		return user;
	}

	public void setModel(User model) {
		this.user = model;
	}

	public String getUserRole() {
		return userType;
	}

	public void setUserRole(String userRole) {
		this.userType = userRole;
	}

}
