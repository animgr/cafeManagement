package cafeManagement.actions;


import org.apache.struts2.convention.annotation.Action;

/**
 * 
 * @author a_mgr
 *
 */
public class HomePage extends BaseActionSupport {

	private static final long serialVersionUID = 1L;


	@Action("home-page")
	public String form()  {
		return SUCCESS;
	}


}

