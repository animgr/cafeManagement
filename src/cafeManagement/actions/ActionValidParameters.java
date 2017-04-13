package cafeManagement.actions;
/**
 * The valid parameters which each action accepts.
 * The action names are separated with '-' but as parameter names could not have '-', we use '_'
 * 
 * For complex beans (beans with child beans) the parent bean, child bean and child bean properties should be set
 * ex: for child property toAccount.accountNo one should add "toAccount,accountNo,toAccount.accountNo"
 * 
 * The properties which do not need to be set in the model like "struts.validateOnly,struts.enableJSONValidation" should not
 * be listed here! The framework will get them via request and do not call the BaseActionSupport.acceptableParameterName() method.
 * 
 * 
 */
public enum ActionValidParameters { 	
	create_user_confirm("username,password,userType");

    
    private final String validParameters;
   
    private ActionValidParameters(String type) {
        this.validParameters = type;
    }

    public String getValidParameters() {
        return validParameters;
    }
}