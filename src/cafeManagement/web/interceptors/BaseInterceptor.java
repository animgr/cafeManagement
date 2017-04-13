package cafeManagement.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * All Interceptors must extend this base class and implement the intercept
 * method
 * 
 * @author a_mgr
 * 
 */
public class BaseInterceptor extends AbstractInterceptor implements
        StrutsStatics {
    private static final long serialVersionUID = 1L;

    public BaseInterceptor() {
        super();
    }

    /**
     * Get the HTTPServlet request for the invocation
     * @return HttpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return ServletActionContext.getRequest();

    }
    
    /**
     * Get Action Name
     * @return
     */
    public String getActionName(){
        return ActionContext.getContext().getName();
    }
    
    /**
     * Get Action NameSpace
     * @return
     */
    public String getActionNameSpace(){
        return ActionContext.getContext().getActionInvocation().getProxy().getNamespace();
    }
    
    
    /**
     * Get the HTTPServlet request for the invocation
     * @return HttpServletRequest
     */
    public HttpServletResponse getHttpServletResponse() {
        return ServletActionContext.getResponse();

    }

    /**
     * Override this method to add custom functionality for interceptor
     */
    public String intercept(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }

}