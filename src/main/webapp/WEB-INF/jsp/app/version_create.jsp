<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Backyard</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	
	<link rel="shortcut icon" href="assets/ico/favicon.ico" type="image/x-icon" />
	<link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
	<link rel="apple-touch-icon" sizes="57x57" href="assets/ico/apple-touch-icon-57x57.png" />
	<link rel="apple-touch-icon" sizes="72x72" href="assets/ico/apple-touch-icon-72x72.png" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/ico/apple-touch-icon-76x76.png" />
	<link rel="apple-touch-icon" sizes="114x114" href="assets/ico/apple-touch-icon-114x114.png" />
	<link rel="apple-touch-icon" sizes="120x120" href="assets/ico/apple-touch-icon-120x120.png" />
	<link rel="apple-touch-icon" sizes="144x144" href="assets/ico/apple-touch-icon-144x144.png" />
	<link rel="apple-touch-icon" sizes="152x152" href="assets/ico/apple-touch-icon-152x152.png" />

	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
	<link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
	<link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="assets/vendor/css/pace.preloader.css" rel="stylesheet" />

	<link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />
	<link href="assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet" />
	<link href="assets/plugins/summernote/css/summernote.css" rel="stylesheet" />
	<link href="assets/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" rel="stylesheet" />
	
	<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />
	<link href="assets/css/style.css" rel="stylesheet" />
	<link href="assets/css/add-ons.min.css" rel="stylesheet" />
	
	<script src="assets/plugins/modernizr/js/modernizr.js"></script>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<%@ include file="../header.jsp"%>
	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<%@ include file="../menu.jsp"%>
			<!-- Main Page -->
			<div class="main ">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-heading bk-bg-white">
								<h6>
									<i class="fa fa-hand-o-right"></i><span class="break"></span>新版本
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<!-- 表单 -->
								<form action="createAppVersion.html" class="form-horizontal form-bordered" id="form" method="post" enctype="multipart/form-data">
									<input type="hidden" id="os" name="os"/>
									<input type="hidden" id="channel" name="channel"/>
									<!-- 包ID -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">ID</label>
										<div class="col-md-3">
											<select id="client_id" name="client_id" class="form-control" required>
												<option value="">请选择</option>
												<!-- APP列表 -->
												<c:forEach var="app" items="${applist}">
													<option value="${app.client_id}" <c:if test="${app.client_id == download.client_id}">selected</c:if>>${app.client_id}＃${app.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- 渠道 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">渠道</label>
										<div class="col-md-3">
											<!-- iOS渠道 -->
											<input type="text" id="ios_channel" name="ios_channel" value="APP STORE" class="form-control" readonly/>
											<!-- 安卓渠道 -->
											<select id="android_channel" name="android_channel" class="form-control" required>
												<option value="">请选择</option>
												<!-- 渠道列表 -->
												<c:forEach var="c" items="${channel_list}">
													<c:if test="${c.code != 'appstore'}">
														<option value="${c.code}" <c:if test="${c.code == channel}">selected</c:if>>${c.name}</option>
													</c:if>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- 版本Code -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">版本Code</label>
										<div class="col-md-3">
											<input type="text" id="version_code" name="version_code" class="form-control" oninput="numberValidation()" value="${download.version_code}" required/>
										</div>
									</div>
									<!-- 版本号 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">版本号</label>
										<div class="col-md-3">
											<input type="text" id="version" name="version" class="form-control" value="${download.version}" required/>
										</div>
									</div>
									<!-- 下载地址 -->
									<div id="ios_content" class="form-group">
										<label class="col-sm-3 control-label" for="remark">下载地址</label>
										<div class="col-md-3">
											<input type="text" id="url" name="url" class="form-control" value="${download.url}"/>
										</div>
									</div>
									<div id="android_content" class="form-group">
										<label class="col-sm-3 control-label" for="remark">安装包</label>
										<div class="col-md-3">
											<input type="file" id="installpack" name="installpack" class="form-control" />
										</div>
									</div>
									<!-- 状态 -->
									<div class="form-group">
										<label class="col-md-3 control-label" for="select">状态</label>
										<div class="col-md-3">
											<select id="status" name="status" class="form-control" size="1" >
												<option value="2" <c:if test="${download.status == 2}">selected</c:if>>待打包</option>
												<option value="8" <c:if test="${download.status == 8}">selected</c:if>>已打包</option>
												<option value="3" <c:if test="${download.status == 3}">selected</c:if>>审核中</option>
												<option value="4" <c:if test="${download.status == 4}">selected</c:if>>审核通过</option>
												<option value="5" <c:if test="${download.status == 5}">selected</c:if>>审核拒绝</option>
												<option value="6" <c:if test="${download.status == 6}">selected</c:if>>上线</option>
												<option value="7" <c:if test="${download.status == 7}">selected</c:if>>下线</option>
											</select>
										</div>
									</div>
									<!-- 操作按钮 -->
									<div class="row">
										<div class="col-sm-12">
											<div align="center">
												<button id="submit-btn" class="btn btn-info">保&nbsp;存&nbsp;<i class="fa fa-save"></i></button>
												&nbsp;
												<a href="javascript: history.go(-1)">
													<button type="button" id="close-btn" class="btn btn-danger">返&nbsp;回&nbsp;<i class="fa fa-reply"></i></button>
												</a>
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
	</div>

	<div class="clearfix"></div>

	<script src="assets/vendor/js/jquery.min.js"></script>
	<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
	<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/skycons/js/skycons.js"></script>
	<script src="assets/vendor/js/pace.min.js"></script>

	<script src="assets/plugins/moment/js/moment.min.js"></script>
	<script src="assets/plugins/summernote/js/summernote.js"></script>
	<script src="assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="assets/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script src="assets/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script src="assets/plugins/sparkline/js/jquery.sparkline.min.js"></script>

	<script src="assets/js/jquery.mmenu.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<script type="text/javascript">
		$(function() {
			$("#ios_content").css("display", "none");
			$("#ios_channel").css("display", "none");
			$("#android_content").css("display", "none");
			
			<c:forEach var="app" items="${applist}">
				if ($("#client_id").val() == "${app.client_id}") {
					if ("${app.os}" == "ios") {
						$("#ios_content").css("display", "block");
						$("#ios_channel").css("display", "block");
						$("#android_content").css("display", "none");
						$("#android_channel").css("display", "none");
						$("#channel").val("appstore")
					} else {
						$("#ios_content").css("display", "none");
						$("#ios_channel").css("display", "none");
						$("#android_content").css("display", "block");
						$("#android_channel").css("display", "block");
					}
					$("#os").val("${app.os}")
				}
			</c:forEach>
		})
		
		$("#client_id").change(function() {
			$("#ios_content").css("display", "none");
			$("#android_content").css("display", "none");
			
			<c:forEach var="app" items="${applist}">
				if ($("#client_id").val() == "${app.client_id}") {
					if ("${app.os}" == "ios") {
						$("#ios_content").css("display", "block");
						$("#ios_channel").css("display", "block");
						$("#android_content").css("display", "none");
						$("#android_channel").css("display", "none"); 
						$("#android_channel").attr("disabled", "disabled");
						$("#channel").val("appstore")
					} else {
						$("#ios_content").css("display", "none");
						$("#ios_channel").css("display", "none");
						$("#android_content").css("display", "block");
						$("#android_channel").css("display", "block");
						$("#android_channel").removeAttr("disabled");
						$("#channel").val($("#android_channel").val())
					}
					$("#os").val("${app.os}")
				}
			</c:forEach>
		})

		$("#android_channel").change(function() {
			$("#channel").val($("#android_channel").val())
		})
		
		$("#submit-btn").click(function() {
			if ($("#client_id").val() == "")
				return
			if ($("#channel").val() == "")
				return
			if ($("#version_code").val() == "")
				return
			if ($("#version").val() == "") 
				return
			$("form").submit()
		})
		
		function numberValidation() {
			$("#version_code").val($("#version_code").val().replace(/\D/gi, ""))
		}
	</script>
</body>

</html>