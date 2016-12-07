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
		
	<!-- start: CSS file-->
		
	<!-- Vendor CSS-->
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
	<link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
		
	<!-- Plugins CSS-->
	<link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />
		
	<!-- Theme CSS -->
	<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />
		
	<!-- Page CSS -->		
	<link href="assets/css/style.css" rel="stylesheet" />
	<link href="assets/css/add-ons.min.css" rel="stylesheet" />
		
	<!-- end: CSS file-->	
		
	<!-- Head Libs -->
	<script src="assets/plugins/modernizr/js/modernizr.js"></script>
		
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->		
</head>

<body>
	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<!-- Main Page -->
			<div id="content" class="col-sm-12 full">
				<div class="row">
					<div style="max-width:430px; padding:20px; margin:25px auto; width:100%;">
						<div class="panel">
							<div class="panel-body">					
								<div align="center">
									<img src="assets/img/logo.png" class="img-responsive" alt="" />
								</div>
								<c:if test="${errmsg != null}">
									<div align="center">
										<label style="color:red;">${errmsg}</label>
									</div>
								</c:if>
								<form class="form-horizontal login" action="login.html" method="post">
									<div class="bk-padding-left-20 bk-padding-right-20">
										<div class="form-group">
											<label>账号：</label>
											<div class="input-group input-group-icon">
												<input type="text" class="form-control bk-radius" id="username" name="username" placeholder="请输入您的账号" required/>
												<span class="input-group-addon">
													<span class="icon">
														<i class="fa fa-user"></i>
													</span>
												</span>
											</div>
										</div>
										<div class="form-group">
											<label>密码：</label>
											<div class="input-group input-group-icon">
												<input type="password" class="form-control bk-radius" id="password" name="password" placeholder="请输入您的密码" required/>
												<span class="input-group-addon">
													<span class="icon">
														<i class="fa fa-key"></i>
													</span>
												</span>
											</div>
										</div>
										<br/>
										<div>
											<div class="col-sm-4"></div>
											<div class="col-sm-4 text-center">
												<button type="button" class="btn btn-primary btn-block hidden-xs" onclick="login()">登&nbsp;录</button>
												<button type="button" class="btn btn-primary btn-block btn-lg visible-xs bk-margin-top-20" onclick="login()">登&nbsp;录</button>
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
		
	<!-- start: JavaScript-->
	
	<!-- Vendor JS-->				
	<script src="assets/vendor/js/jquery.min.js"></script>
	<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
	<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/skycons/js/skycons.js"></script>	
	
	<!-- Plugins JS-->
	<script src="assets/plugins/bootkit/js/bootkit.js"></script>
		
	<!-- Theme JS -->		
	<script src="assets/js/jquery.mmenu.min.js"></script>
	<script src="assets/js/core.min.js"></script>
		
	<!-- Pages JS -->
	<script src="assets/js/pages/page-login.js"></script>
		
	<script type="text/javascript">
		function login() {
			if ($("#username").val() == "")
				return
			if ($("#password").val() == "")
				return
			$("form").submit()
		}
	</script>
	<!-- end: JavaScript-->
</body>
	
</html>
