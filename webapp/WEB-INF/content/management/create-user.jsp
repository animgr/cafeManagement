<%@ page contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<head>
<t:css />
<t:js />
</head>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<t:nav />

		<div class="col-md-5">
			<s:text name="form.createuser.desc" />
			<s:form id="createUserForm" action="create-user-confirm"
				namespace="/management" cssClass="form-horizontal">


				<div class="form-group col-md-13">
					<label class="control-label" id="username"><s:text
							name="label.username" /></label>
					<div class="col-sm-9">
						<s:textfield name="username" cssClass="form-control" />
					</div>
				</div>

				<div class="form-group col-md-13">
					<label class="control-label" id="password"><s:text
							name="label.password" /></label>
					<div class="col-sm-9">
						<s:textfield name="password" cssClass="form-control password" />
					</div>
				</div>
				<div class="form-group col-md-13">
					<label class="control-label" id="userRole"><s:text
							name="label.userRole" /></label>
					<div class="col-sm-9">
						<s:select id="userType" name="userType"  list="#{'01':'Manager', '02':'Waiter'}"
							listKey="top" 
							headerKey="" cssClass="form-control" required="true" />
					</div>
				</div>
				<div class="form-group form-actionbar">
					<div class="col-sm-offset-2 col-sm-7">

						<sj:submit id="creationSubmit" targets="dummy"
							validateFunction="formValidator"
							onBeforeTopics="beforeFormSubmit"
							onCompleteTopics="formServerErrorAnalyze" validate="true"
							key="form.btn.send" cssClass="btn btn-default" />
						<s:a href="/home-page.cm" cssClass="btn btn-default">
							<s:text name="form.btn.back" />
						</s:a>
					</div>
				</div>
			</s:form>
		</div>
		</div>
</body>

</html>
