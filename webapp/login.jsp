<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>


<head>
	<t:css/>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<s:text name="form.login.title" />
						</h3>
					</div>
					<div class="panel-body">

						<form name="loginForm"
							action="${contextPath}/j_spring_security_check" method="post">
							<fieldset>
								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
									<font color="red"> <s:text name="login.failed"/> <br/>
									<br /> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
									</font>
								</c:if>
								<div class="form-group">
									<s:textfield cssClass="form-control"
										placeholder="%{getText('label.username')}" autofocus="true"
										name="j_username" dir="auto"/>
								</div>
								<div class="form-group">
									<s:password cssClass="form-control"
										placeholder="%{getText('label.password')}" autofocus="true"
										name="j_password" dir="auto" />
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<s:submit key="btn.login"
									cssClass="btn btn-default btn-lg btn-success btn-block" />
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>