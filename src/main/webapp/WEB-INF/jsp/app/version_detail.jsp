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
									<i class="fa fa-hand-o-right"></i><span class="break"></span>版本详情
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<!-- 表单 -->
								<form action="updateAppVersion.html" class="form-horizontal form-bordered" id="form" method="post" enctype="multipart/form-data">
									<!-- 包ID -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">ID</label>
										<div class="col-md-3">
											<input type="text" id="client_id" name="client_id" class="form-control" value="${download.client_id}" readonly/>
										</div>
									</div>
									<!-- 渠道 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">渠道</label>
										<div class="col-md-3">
											<input type="text" id="channel" name="channel" class="form-control" value="${channel}" readonly/>
										</div>
									</div>
									<!-- 版本代码 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">版本Code</label>
										<div class="col-md-3">
											<input type="text" id="version_code" name="version_code" class="form-control" value="${download.version_code}" readonly/>
										</div>
									</div>
									<!-- 版本号 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">版本号</label>
										<div class="col-md-3">
											<input type="text" id="version" name="version" class="form-control" value="${download.version}" readonly/>
										</div>
									</div>
									<!-- 下载地址 -->
									<div id="ios_donwload" class="form-group">
										<label class="col-sm-3 control-label" for="remark">下载地址</label>
										<!-- iOS下载地址 -->
										<div id="ios_download" class="col-md-3">
											<input type="text" id="url" name="url" class="form-control" value="${download.url}" readonly/>
										</div>
										<!-- 安卓下载地址 -->
										<div id="android_download" class="col-md-3">
											<a target="_blank" class="form-control" href="${download.url}">点击下载</a>
										</div>
									</div>
									<!-- 状态 -->
									<div class="form-group">
										<label class="col-md-3 control-label" for="select">状态</label>
										<div class="col-md-3">
											<select id="status" name="status" class="form-control" disabled>
												<option value="1" <c:if test="${download.status == 1}">selected</c:if>>初始化</option>
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
									<!-- 反馈 -->
									<div id="reason_content" class="form-group">
										<label class="col-sm-3 control-label" for="remark">拒绝原因</label>
										<div class="col-sm-6">
											<textarea id="reason" name="reason" class="form-control" rows="5" cols="20" readonly>${download.reason}</textarea>
										</div>
									</div>
									<!-- 操作按钮 -->
									<div class="row">
										<div class="col-sm-12">
											<div align="center">
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
			if ("${download.app.os}" == "ios") {
				$("#ios_download").css("display", "block");
				$("#android_download").css("display", "none");
			} else {
				$("#ios_download").css("display", "none");
				$("#android_download").css("display", "block");
			}
			
			if ($("#status").val() == 5) {
				$("#reason_content").css("display", "block");
			} else {
				$("#reason_content").css("display", "none");
			}
		})
		
		$("#status").change(function() {
			if ($("#status").val() == 5) {
				$("#reason_content").css("display", "block");
			} else {
				$("#reason_content").css("display", "none");
			}
		})
	
		$("#submit-btn").click(function() {
			if ($("#version_code").val() == "")
				return
			if ($("#version").val() == "") 
				return
			if ($("#channel").val() == "")
				return
			$("form").submit()
		})
	</script>
</body>

</html>