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
								<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>包列表</h6>
								<div class="panel-actions">
	                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
	                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
	                            </div>
							</div>
							<div class="panel-body">
								<div class="row datatables-header form-inline">
									<div class="col-sm-12 col-md-12">
										<!-- 表单 -->
										<form action="applist.html" id="editForm" method="post">
											<input type="hidden" id="page" name="page" value="${pager.pageNumber}" />
											<table class="col-md-12">
												<tbody>
													<tr>
														<td>
															ID：<input type="text" class="form-control" style="width:70px;" id="client_id" name="client_id" value="${client_id}" />
	                                            			&nbsp;
	                                            			名称：<input type="text" class="form-control" style="width:70px;" id="name" name="name" value="${name}" />
	                                            			&nbsp;
	                                            			版本号：<input type="text" class="form-control" style="width:70px;" id="version" name="version" value="${version}" />
	                                            			&nbsp;
	                                            			设备：
	                                            			<select id="type" class="form-control" style="width:70px;" id="os" name="os">
	                                            				<option value="">全部</option>
	                                            				<option value="ios" <c:if test="${os == 'ios'}">selected</c:if>>iOS</option>
	                                            				<option value="android" <c:if test="${os == 'android'}">selected</c:if>>安卓</option>
	                                            			</select>
	                                            			&nbsp;
	                                            			渠道：
	                                            			<select id="type" class="form-control" style="width:70px;" id="channel" name="channel">
	                                            				<option value="">全部</option>
	                                            				<!-- 渠道列表 -->
	                                            				<c:forEach var="c" items="${channel_list}">
	                                            					<option value="${c.code}" <c:if test="${c.code == channel}">selected</c:if>>${c.name}</option>
	                                            				</c:forEach>
	                                            			</select>
	                                            			&nbsp;
	                                            			状态：
	                                            			<select id="type" class="form-control" style="width:70px;" id="status" name="status">
																<option value="">全部</option>
																<option value="1" <c:if test="${status == 1}">selected</c:if>>初始化</option>
																<option value="2" <c:if test="${status == 2}">selected</c:if>>待打包</option>
																<option value="8" <c:if test="${status == 8}">selected</c:if>>已打包</option>
																<option value="3" <c:if test="${status == 3}">selected</c:if>>审核中</option>
																<option value="4" <c:if test="${status == 4}">selected</c:if>>审核通过</option>
																<option value="5" <c:if test="${status == 5}">selected</c:if>>审核拒绝</option>
																<option value="6" <c:if test="${status == 6}">selected</c:if>>上线</option>
																<option value="7" <c:if test="${status == 7}">selected</c:if>>下线</option>
															</select>
															&nbsp;
															<!-- 查询 -->
															<button type="button" id="select-btn" class="bk-margin-5 btn btn-success"><i class="fa fa-search"></i>&nbsp;查&nbsp;询</button>
															<!-- 添加 -->
															<button type="button" id="create-btn" class="bk-margin-5 btn btn-success" onclick="createVersionPage()"><i class="fa fa-plus"></i>&nbsp;新版本</button>
														</td>
													</tr>
												</tbody>
											</table>
										</form>
									</div>
								</div>
								<br/>
								<table class="table table-bordered table-striped" id="datatable-default">
									<thead style="text-align:center">
										<tr>
											<td style="width:40px;">ICON</td>
											<td style="width:80px;">ID</td>
											<td>APP名称</td>
											<td style="width:60px;">设备</td>
											<td style="width:100px;">渠道</td>
											<td style="width:60px;">版本号</td>
											<td style="width:80px;">状态</td>
											<td style="width:150px;">操作时间</td>
											<td style="width:170px;">操作</td>
										</tr>
									</thead>
									<tbody style="text-align:center; ">
										<c:forEach var="app" items="${applist}">
											<tr class="gradeX">
												<!-- ICON -->
												<td>
													<img height="40" width="40" src="${app.icon}"/>
												</td>
												<!-- ID -->
												<td style="vertical-align: middle;">
													<a href="appDetailPage.html?id=${app.client_id}">${app.client_id}</a>
												</td>
												<!-- 名称 -->
												<td style="vertical-align: middle;">${app.name}</td>
												<!-- 设备 -->
												<td style="vertical-align: middle;">
													<c:choose>
														<c:when test="${app.os == 'ios'}">iOS</c:when>
														<c:when test="${app.os == 'android'}">安卓</c:when>
														<c:otherwise>未知</c:otherwise>
													</c:choose>
												</td>
												<!-- 渠道 -->
												<td style="vertical-align: middle;">${app.channel}</td>
												<!-- 版本 -->
												<td style="vertical-align: middle;">${app.version}</td>
												<!-- 状态 -->
												<td style="vertical-align: middle;">
													<c:choose>
														<c:when test="${app.status == 1}">
															<label style="color:#000000">初始化</label>
														</c:when>
														<c:when test="${app.status == 2}">
															<label style="color:#000000">待打包</label>
														</c:when>
														<c:when test="${app.status == 8}">
															<label style="color:#000000">已打包</label>
														</c:when>
														<c:when test="${app.status == 3}">
															<label style="color:#FFE87C">审核中</label>
														</c:when>
														<c:when test="${app.status == 4}">
															<label style="color:#008000">审核通过</label>
														</c:when>
														<c:when test="${app.status == 5}">
															<label style="color:#FF0000">审核拒绝</label>
														</c:when>
														<c:when test="${app.status == 6}">
															<label style="color:#008000">上线</label>
														</c:when>
														<c:when test="${app.status == 7}">
															<label style="color:#FF0000">下线</label>
														</c:when>
														<c:otherwise>未知</c:otherwise>
													</c:choose>
												</td>
												<!-- 操作时间 -->
												<td style="vertical-align: middle;">
													<fmt:formatDate value="${app.gmt_modified}" pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<!-- 编辑按钮 -->
												<td style="vertical-align: middle;">
													<a href="appVersionDetailPage.html?id=${app.id}" class="btn btn-info"><i class="fa fa-search">查看</i></a>
													&nbsp;
													<a href="appVersionEditPage.html?id=${app.id}" class="btn btn-warning"><i class="fa fa-edit">编辑</i></a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="../pager.jsp"%>
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
	
		function createVersionPage() {
			this.location.href = "createAppVersionPage.html"
		}
	</script>
</body>

</html>