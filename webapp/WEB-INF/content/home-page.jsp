<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<head>
<t:css />
<t:js />
</head>

<body>
	<div id="wrapper">
		<!-- Navigation -->
		<t:nav />

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-7">
					<div class="col-lg-12">
						<div class="jumbotron">
							<h1>
								<s:text name="header.homepage" />
							</h1>
							<p>
								<s:text name="header.homepage.desc" />
							</p>
							</p>
						</div>
					</div>
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
</body>

</html>