<%--
  Created by IntelliJ IDEA.
  User: ouwa
  Date: 16/6/28
  Time: 下午5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!-- Sidebar -->
<div class="sidebar">
    <div class="sidebar-collapse">
        <!-- Sidebar Header Logo -->
        <div class="sidebar-header">
            <img src="assets/img/logo2.png" class="img-responsive" alt="" />
        </div>
        <!-- Sidebar Menu-->
        <div class="sidebar-menu">
            <nav id="menu" class="nav-main" role="navigation">
                <ul class="nav nav-sidebar">
                	<!-- 一级列表 -->
                	<c:forEach var="resource" items="${account.resourceList}">
                		<li class="nav-parent nav-expanded">
                			<a>
                				<i class="fa fa-tasks" aria-hidden="true"></i>
                				<span>${resource.resource_name}</span>
	                        </a>
	                        <ul class="nav nav-children" style="display: none;">
	                        	<!-- 二级列表 -->
	                        	<c:forEach var="item" items="${resource.childList}">
	                        		<li>
	                        			<a href="${item.url}">
	                        				<span class="text">${item.resource_name}</span>
	                        			</a>
	                        		</li>
	                        	</c:forEach> 
	                        </ul>
                		</li>
                	</c:forEach>
                </ul>
            </nav>
        </div>
        <!-- End Sidebar Menu-->
    </div>
    <!-- Sidebar Footer-->
    <div class="sidebar-footer"></div>
    <!-- End Sidebar Footer-->
</div>
<!-- End Sidebar -->
