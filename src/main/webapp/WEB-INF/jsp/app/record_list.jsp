<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

    <link href="assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />
    <link href="assets/plugins/jquery-datatables-bs3/css/datatables.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />

    <link href="assets/css/jquery.mmenu.css" rel="stylesheet" />
    <link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/add-ons.min.css" rel="stylesheet" />

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
			<div class="main">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="panel panel-default bk-bg-white">
							<div class="panel-heading bk-bg-white">
								<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>操作记录</h6>
							</div>
							<div class="panel-body">
								<form action="appVersionRecordList.html" id="editForm" method="get">
									<input type="hidden" id="page" name="page" value="${pager.pageNumber}" />
								</form>
								<table class="table table-bordered table-striped" id="datatable-default">
									<thead style="text-align:center">
										<tr>
											<td style="width:40px;">ICON</td>
											<td style="width:120px;">名称</td>
											<td>关键字</td>
											<td style="width:50px;">版本</td>
											<td style="width:60px;">版本号</td>
											<td style="width:90px;">渠道</td>
											<td style="width:70px;">素材包</td>
											<td style="width:70px;">状态</td>
											<td style="width:60px;">操作人</td>
											<td style="width:150px;">操作时间</td>
										</tr>
									</thead>
									<tbody style="text-align:center; ">
										<c:forEach var="record" items="${record_list}">
											<tr class="gradeX">
												<!-- ICON -->
												<td>
													<img height="40" width="40" src="${record.icon}"/>
												</td>
												<!-- 名称 -->
												<td style="vertical-align: middle;">${record.name}</td>
												<!-- 关键字 -->
												<td style="vertical-align: middle;">${record.keyword}</td>
												<!-- 版本Code -->
												<td style="vertical-align: middle;">${record.version_code}</td>
												<!-- 版本号 -->
												<td style="vertical-align: middle;">${record.version}</td>
												<!-- 渠道 -->
												<td style="vertical-align: middle;">${record.channel}</td>
												<!-- 素材包 -->
												<td style="vertical-align: middle;">
													<c:choose>
														<c:when test="${fn:length(record.material_url) > 0}">
															<a target="_blank" href="${record.material_url}">点击下载</a>
														</c:when>
													</c:choose>
												</td>
												<!-- 状态 -->
												<td style="vertical-align: middle;">
													<c:choose>
														<c:when test="${record.status == 1}">
															<label style="color:black">初始化</label>
														</c:when>
														<c:when test="${record.status == 2}">
															<label style="color:black">待打包</label>
														</c:when>
														<c:when test="${record.status == 8}">
															<label style="color:black">已打包</label>
														</c:when>
														<c:when test="${record.status == 3}">
															<label style="color:#FFE87C">审核中</label>
														</c:when>
														<c:when test="${record.status == 4}">
															<label style="color:#008000">审核通过</label>
														</c:when>
														<c:when test="${record.status == 5}">
															<label style="color:red">审核拒绝</label>
														</c:when>
														<c:when test="${record.status == 6}">
															<label style="color:#008000">上线</label>
														</c:when>
														<c:when test="${record.status == 7}">
															<label style="color:red">下线</label>
														</c:when>
													</c:choose>
												</td>
												<td style="vertical-align: middle;">${record.admin_name}</td>
												<td style="vertical-align: middle;">
													<fmt:formatDate value="${record.gmt_create}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="../pager.jsp"%>
								<br/>
								<!-- 返回按钮 -->
								<div align="center">
									<button type="button" id="close-btn" class="btn btn-danger" onclick="appDetailPage()">返&nbsp;回&nbsp;<i class="fa fa-reply"></i></button>
								</div>
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
		$("#select-btn").click(function() {
			$("#page").val(1)
			$("form").submit()
		})

		function numberValidation() {
			$("#version_code").val($("#version_code").val().replace(/\D/gi, ""))
		}
		
		function appDetailPage() {
			this.location.href = "appDetailPage.html?id=${client_id}"
		}
	</script>
</body>

</html>