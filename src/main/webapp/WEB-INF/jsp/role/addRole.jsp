<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

	<head>
	
		<!-- Basic -->
    	<meta charset="UTF-8" />

        <title>Backyard</title>
	 
		<!-- Mobile Metas -->
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		
		<!-- Favicon and touch icons -->
		<link rel="shortcut icon" href="assets/ico/favicon.ico" type="image/x-icon" />
		<link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
		<link rel="apple-touch-icon" sizes="57x57" href="assets/ico/apple-touch-icon-57x57.png" />
		<link rel="apple-touch-icon" sizes="72x72" href="assets/ico/apple-touch-icon-72x72.png" />
		<link rel="apple-touch-icon" sizes="76x76" href="assets/ico/apple-touch-icon-76x76.png" />
		<link rel="apple-touch-icon" sizes="114x114" href="assets/ico/apple-touch-icon-114x114.png" />
		<link rel="apple-touch-icon" sizes="120x120" href="assets/ico/apple-touch-icon-120x120.png" />
		<link rel="apple-touch-icon" sizes="144x144" href="assets/ico/apple-touch-icon-144x144.png" />
		<link rel="apple-touch-icon" sizes="152x152" href="assets/ico/apple-touch-icon-152x152.png" />
		
	   <!-- Vendor CSS-->
		<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
    <link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <link href="assets/vendor/css/pace.preloader.css" rel="stylesheet" />
    <link href="assets/css/jquery.datetimepicker.css" rel="stylesheet" />

    <!-- Plugins CSS-->
    <link href="assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />
    <link href="assets/plugins/jquery-datatables-bs3/css/datatables.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />


    <!-- Theme CSS -->
    <link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

    <!-- Page CSS -->
    <link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/add-ons.min.css" rel="stylesheet" />
		
		<!-- end: CSS file-->	
	    
		
		<!-- Head Libs -->
		<script src="assets/plugins/modernizr/js/modernizr.js"></script>
		<!-- end: CSS file-->	
	    
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->	
		
	</head>
	
	<body>

        <%@ include  file="../header.jsp"%>
		
		<!-- Start: Content -->
		<div class="container-fluid content">	
			<div class="row">

                <%@ include  file="../menu.jsp"%>
						
				<!-- Main Page -->
				<div class="main ">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>添加角色</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<c:if test="${msg!=null}">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="alert alert-danger">
											<c:out value="${msg}"/>
										</div>
									</div>
								</c:if>
								<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<form action="roleAdd.html"  class="form-horizontal form-bordered " id="editForm" method="post">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">角色名称</label>
                                            <div class="col-md-3">
                                               <input type="text" id="role_name" name="role_name" class="form-control" value='<c:out value="${role.role_name}"/>'required/>
                                            </div>
                                        </div>
										
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">角色权限</label>
                                            <div class="col-md-6">
                                              <c:forEach var="resource" items="${resourcelist}" varStatus='s'>
												 <div class="checkbox-custom checkbox-inline">
													<input type="checkbox" id="inline-checkboxs${s.index}" name="rosourceId" value="<c:out value="${resource.id}"/>"> 
													<label for="inline-checkbox${s.index}"> <c:out value="${resource.resource_name}"/></label>
												</div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="row">
											<div class="col-sm-12">
												<div align="center">
													<button id="addToTable" class="btn btn-info" >保存 <i class="fa fa-save"></i></button>
													<button type="button" class="bk-margin-5 btn btn-danger" onclick="roleListPage()">返&nbsp;回&nbsp;<i class="fa fa-reply"></i></button>
												</div>
											</div>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>		
				</div>
				<!-- End Main Page -->
			
			</div>
		</div><!--/container-->
		
		
		<div class="clearfix"></div>		
		
		
		<!-- start: JavaScript-->
		
		<!-- Vendor JS-->				
<script src="assets/vendor/js/jquery.min.js"></script>
		<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/vendor/skycons/js/skycons.js"></script>
		<script src="assets/vendor/js/pace.min.js"></script>
		
		<!-- Plugins JS-->
		<script src="assets/plugins/moment/js/moment.min.js"></script>	
		<script src="assets/plugins/summernote/js/summernote.js"></script>
		<script src="assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
		<script src="assets/plugins/bootstrap-markdown/js/markdown.js"></script>
		<script src="assets/plugins/bootstrap-markdown/js/to-markdown.js"></script>
		<script src="assets/plugins/sparkline/js/jquery.sparkline.min.js"></script>
		<script src="assets/js/jquery.datetimepicker.full.js"></script>
		<script src="assets/js/jquery.datetimepicker.js"></script>
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		<script src="assets/js/pages/form-validation.js"></script>
		<!-- Pages JS -->
        <script type="text/javascript">
        $('#send_time').datetimepicker({
    		lang:'ch',
    		timepicker:true,
    		format:'Y-m-d H:i:s'
    	
    	});
        function roleListPage(){
        	location="rolelist.html";
        }
        </script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>