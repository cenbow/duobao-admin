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
									<i class="fa fa-hand-o-right"></i><span class="break"></span>创建新包
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<!-- 表单 -->
								<form action="createApp.html" class="form-horizontal form-bordered" id="form" method="post" enctype="multipart/form-data">
									<!-- 包ID -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">ID</label>
										<div class="col-md-3">
											<input type="text" id="client_id" name="client_id" class="form-control" required/>
										</div>
									</div>
									<!-- 设备 -->
									<div class="form-group">
										<label class="col-md-3 control-label" for="select">设备</label>
										<div class="col-md-3">
											<select id="os" name="os" class="form-control">
												<option value="ios">iOS</option>
												<option value="android">安卓</option>
											</select>
										</div>
									</div>
									<!-- 名称 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">名称</label>
										<div class="col-md-3">
											<input type="text" id="name" name="name" class="form-control" required/>
										</div>
									</div>
									<!-- 副标题 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">副标题</label>
										<div class="col-md-3">
											<input type="text" id="sub_title" name="sub_title" class="form-control" />
										</div>
									</div>
									<!-- 描述 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">描述</label>
										<div class="col-sm-6">
											<textarea id="description" name="description" class="form-control" rows="5" cols="20"></textarea>
										</div>
									</div>
									<!-- 关键字 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">关键字</label>
										<div class="col-md-6">
											<textarea id="keyword" name="keyword" class="form-control" rows="5" cols="20"></textarea>
										</div>
									</div>
									<!-- ICON -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">ICON</label>
										<div class="col-md-3">
											<input type="file" id="icon1" name="icon1" class="form-control" />
										</div>
									</div>
									<!-- 素材包 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">素材包</label>
										<div class="col-md-3">
											<input type="file" id="material" name="material" class="form-control" />
										</div>
									</div>
									<!-- 渠道 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">渠道</label>
										<div class="col-md-3">
											<!-- iOS渠道 -->
											<div id="ios_channel_content">
												<div class="checkbox-custom checkbox-inline" style="margin: 5px 5px 5px 0px;">
													<input type="checkbox" id="ios_channel" name="channels" value="appstore" onclick="return false" />
													<label for="inline-checkbox1">苹果商店（APP STORE）</label>
												</div>
											</div>
											<!-- 安卓渠道 -->
											<div id="android_channel_content">
												<c:forEach var="channel" items="${channel_list}">
													<c:if test="${channel.code != 'appstore'}">
														<div class="checkbox-custom checkbox-inline" style="margin: 5px 5px 5px 0px;">
															<input type="checkbox" id="android_channel" name="channels" value="${channel.code}" />
															<label for="inline-checkbox1">${channel.name}</label>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</div>
									</div>
									<!-- 水印标志 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">水印标志</label>
										<div class="col-md-3">
											<select id="watermark" name="watermark" class="form-control">
												<option value="">请选择</option>
												<option value="@!new_duobao_yin">@!new_duobao_yin</option>
											</select>
										</div>
									</div>
									<!-- 状态 -->
									<div class="form-group">
										<label class="col-md-3 control-label" for="select">状态</label>
										<div class="col-md-3">
											<select id="status" name="status" class="form-control">
												<option value="1">初始化</option>
												<option value="2">待打包</option>
											</select>
										</div>
									</div>
									<!-- 更多信息 -->
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">更多信息</label>
										<div class="col-md-6">
											<div class="checkbox-custom checkbox-inline">
												<input type="checkbox" id="extend-checkbox" name="extend-checkbox" />
												<label for="inline-checkbox"></label>
											</div>
										</div>
									</div>
									<!-- 扩展信息 -->
									<div id="extend" style="display: none">
										<!-- 版本代码 -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">版本Code</label>
											<div class="col-md-3">
												<input type="text" class="form-control" id="version_code" name="version_code" oninput="numberValidation()" value="1" />
											</div>
										</div>
										<!-- 版本号 -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">版本号</label>
											<div class="col-md-3">
												<input type="text" class="form-control" id="version" name="version" value="1.0" />
											</div>
										</div>
										<!-- 个推（Debug）AppID -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Debug）AppID</label>
											<div class="col-md-3">
												<input type="text" id="getui_d_appid" name="getui_d_appid" class="form-control" />
											</div>
										</div>
										<!-- 个推（Debug）AppKey -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Debug）AppKey</label>
											<div class="col-md-3">
												<input type="text" id="getui_d_appkey" name="getui_d_appkey" class="form-control" />
											</div>
										</div>
										<!-- 个推（Debug）AppMaster -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Debug）AppMaster</label>
											<div class="col-md-3">
												<input type="text" id="getui_d_appmaster" name="getui_d_appmaster" class="form-control" />
											</div>
										</div>
										<!-- 个推（Debug）AppSecret -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Debug）AppSecret</label>
											<div class="col-md-3">
												<input type="text" id="getui_d_appsecret" name="getui_d_appsecret" class="form-control" />
											</div>
										</div>
										<!-- 个推（Release）AppID -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Release）AppID</label>
											<div class="col-md-3">
												<input type="text" id="getui_r_appid" name="getui_r_appid" class="form-control" />
											</div>
										</div>
										<!-- 个推（Release）AppKey -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Release）AppKey</label>
											<div class="col-md-3">
												<input type="text" id="getui_r_appkey" name="getui_r_appkey" class="form-control" />
											</div>
										</div>
										<!-- 个推（Release）AppMaster -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Release）AppMaster</label>
											<div class="col-md-3">
												<input type="text" id="getui_r_appmaster" name="getui_r_appmaster" class="form-control" />
											</div>
										</div>
										<!-- 个推（Release）AppSecret -->
										<div class="form-group">
											<label class="col-sm-3 control-label" for="remark">个推（Release）AppSecret</label>
											<div class="col-md-3">
												<input type="text" id="getui_r_appsecret" name="getui_r_appsecret" class="form-control" />
											</div>
										</div>
										<!-- iOS扩展字段 -->
										<div id="ios_extend_content">
											<!-- iOS帐号 -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">iOS帐号</label>
												<div class="col-md-3">
													<input type="text" id="ios_account" name="ios_account" class="form-control" />
												</div>
											</div>
											<!-- iOS AppID -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">iOS AppID</label>
												<div class="col-md-3">
													<input type="text" id="ios_appid" name="ios_appid" class="form-control" />
												</div>
											</div>
											<!-- iOS BundleID -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">iOS BundleID</label>
												<div class="col-md-3">
													<input type="text" id="ios_bundleid" name="ios_bundleid" class="form-control" />
												</div>
											</div>
											<!-- iOS 友盟ID -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">iOS 友盟ID</label>
												<div class="col-md-3">
													<input type="text" id="ios_umeng_appid" name="ios_umeng_appid" class="form-control" />
												</div>
											</div>
										</div>
										<!-- Android扩展字段 -->
										<div id="android_extend_content">
											<!-- 安卓包名 -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">安卓包名</label>
												<div class="col-md-3">
													<input type="text" id="android_package" name="android_package" class="form-control" />
												</div>
											</div>
											<!-- 安卓友盟ID -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">安卓友盟ID</label>
												<div class="col-md-3">
													<input type="text" id="android_umeng_appid" name="android_umeng_appid" class="form-control" />
												</div>
											</div>
											<!-- 安装包 -->
											<div class="form-group">
												<label class="col-sm-3 control-label" for="remark">安装包</label>
												<div class="col-md-3">
													<input type="file" id="installpack" name="installpack" class="form-control" />
												</div>
											</div>
										</div>
									</div>
									<!-- 保存按钮 -->
									<div class="row">
										<div class="col-sm-12">
											<div align="center">
												<button id="submit-btn" class="btn btn-info">保&nbsp;存&nbsp;<i class="fa fa-save"></i></button>
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
			if ($("#os").val() == "ios") {
				$("#ios_channel_content").css("display", "block")
				$("#ios_extend_content").css("display", "block")
				$("#android_channel_content").css("display", "none")
				$("#android_extend_content").css("display", "none")
				$("#ios_channel").attr("checked", true)
			} else {
				$("#ios_channel_content").css("display", "none")
				$("#ios_extend_content").css("display", "none")
				$("#android_channel_content").css("display", "block")
				$("#android_extend_content").css("display", "block")
				$("#ios_channel").attr("checked", false)
			}
			if ($("#extend-checkbox").attr("checked") == "checked") {
				$("#extend").css("display", "block")
			} else {
				$("#extend").css("display", "none")
			}
		})

		$("#os").change(function() {
			if ($("#os").val() == "ios") {
				$("#ios_channel_content").css("display", "block")
				$("#ios_extend_content").css("display", "block")
				$("#android_channel_content").css("display", "none")
				$("#android_extend_content").css("display", "none")
				$("[name='channels']").attr("checked", false)
				$("#ios_channel").attr("checked", true)
			} else {
				$("#ios_channel_content").css("display", "none")
				$("#ios_extend_content").css("display", "none")
				$("#android_channel_content").css("display", "block")
				$("#android_extend_content").css("display", "block")
				$("[name='channels']").attr("checked", false)
			}
		})

		$("#extend-checkbox").click(function() {
			if ($("#extend-checkbox").attr("checked") == "checked") {
				$("#extend").css("display", "block")
			} else {
				$("#extend").css("display", "none")
			}
		})

		$("#submit-btn").click(function() {
			if ($("#client_id").val() == "")
				return
			if ($("#name").val() == "")
				return
			$("form").submit();
		})

		function numberValidation() {
			$("#version_code").val($("#version_code").val().replace(/\D/gi, ""))
		}
	</script>
</body>

</html>