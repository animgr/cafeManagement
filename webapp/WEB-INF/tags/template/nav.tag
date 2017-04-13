<%@tag description="Set navigations" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><s:text name="site.name"/></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-left">               
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                         <c:url value="/j_spring_security_logout" var="logoutUrl" />
                        <a href="${logoutUrl}"><i class="fa fa-sign-out fa-fw"></i><s:text name="menu.logout"/></a>
                       
                    </ul>
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <!-- menu -->
                        <li>
<!--                         here we used the spring security tag, we can do it more dynamically for more actions! TODO! -->
                        <sec:authorize access="not hasRole('ROLE_NOT_ACCESS_TO_ACTION_create-user')">  
                            <s:url action="create-user" namespace="/management" var="url"/>                            
                            <a class="active" href="${url}"><i class="fa fa-dashboard"></i> <s:text name="menu.createUser"/> </a>
                       </sec:authorize>
<%--                             <s:url action="view-assigned-tables" namespace="/xmls" var="url"/>                             --%>
<%--                             <a class="active" href="${url}"><i class="fa fa-calculator"></i> <s:text name="menu.amountlimitation"/> </a> --%>

<%--                             <s:url action="create-users" namespace="/xmls" var="url"/>                             --%>
<%--                             <a class="active" href="${url}"><i class="fa fa-user"></i> <s:text name="menu.exceptionaluser"/> </a> --%>
                                                           
                            
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>


