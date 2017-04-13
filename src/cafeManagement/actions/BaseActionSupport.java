package cafeManagement.actions;


import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

/**
 * A base action for all actions, implements struts *Aware to easy access servlet request, response ... 
 * 
 * @author a_mgr
 *
 */
public class BaseActionSupport extends ActionSupport implements ServletRequestAware,ServletResponseAware,ParameterNameAware {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest servletRequest;
	private HttpServletResponse servletResponse;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		servletRequest = request;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.servletResponse =  response;
        
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    
    /**
     * get request as raw data, can be useful if request is a json
     * @return
     */
	public String requestRawData() {

		StringBuilder rawData = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = getServletRequest().getReader();
			while ((line = reader.readLine()) != null){
				rawData.append(line);
			}
		} catch (Exception e) {
			LOG.error("Can not read input", e);
		}
		return rawData.toString();
	}
	
	
	   /**
     * Search the action parameter with the list provided in
     * ActionValidParameters The struts will populate a bean
     * property if it is sent from html. So a hacker can easily send the other
     * value object parameters to server and the struts will populate the bean.
     * As an example, fundtransferVO.sabaReceipt if this method is disabled.
     * 
     * @return true if the parameter is valid
     * 
     */
    @Override
    public boolean acceptableParameterName(String parameterName) {
    	
    	// The non model driven actions accept parameters
        if (!(this instanceof ModelDriven)) {
            return true;
        }
        
        String actionName = ActionContext.getContext().getName().replace('-', '_');

        //The action is modeldriven but the action name is not defined in 
        //ActionValidParameters.class. Do not accept this parameter.
        //This may happen for global parameters which send to all requests (like clienttimestamp)
        if(!EnumUtils.isValidEnum(ActionValidParameters.class, actionName)){
            return false;
        }    
        // valid parameters will be a list of comma separated list of all
        // acceptable parameters
        String validParams = ActionValidParameters.valueOf(actionName).getValidParameters();

        // If the action is not defined in the list return
        if (StringUtils.isBlank(validParams)) {
            LOG.warn(
                    "The action {} if ModelDriven, define list of valid parameters to avoid security issues",
                    actionName);
            return false;
        }
        // Search all the list of parameters. In case of performance issues,
        // this search can be changed to:
        // Spiting the validParams to make an arraylist and then make a loop for
        // search.
        // The validPrams.contains should not be used at all !!
        Pattern pattern = Pattern.compile("(?<=^|,)" + parameterName
                + "(?=,|$)");
        Matcher matcher = pattern.matcher(validParams);
        boolean accepeted = matcher.find();
        //If the parameter is not valid it is suspicious that some one tries to attack and push value to actions
        if(accepeted){
            LOG.trace("The {} parameter is accepted in action {}", parameterName,
                actionName);}
        else{
            LOG.warn("The {} parameter is not defined as acceptable in action {}", parameterName,
                     actionName);
        }
        return accepeted;
    }

}
