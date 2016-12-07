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
								<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>中奖纪录</h6>
								<div class="panel-actions">
	                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
	                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
	                            </div>
							</div>
							<div class="panel-body">
								<div class="row datatables-header form-inline">
									<div class="col-sm-12 col-md-12">
										<!-- 表单 -->
										<form action="rotateRecords.html" id="editForm" method="post">
											<input type="hidden" id="page" name="page" value="${pager.pageNumber}" />
											<input type="hidden" id="rotate_id" name="rotate_id" value="${rotate_id}" />
											<table class="col-md-12">
												<tbody>
													<tr>
														<td>
	                                            			手机号码：<input type="text" class="form-control" style="width:140px;" id="mobile" name="mobile" value="${mobile}" />
	                                            			&nbsp;
	                                            			奖品类型：
															<select class="form-control" style="width:140px;" id="type" name="type">
																<option value="" >全部</option>
																<option value="0" <c:if test="${type == 0}">selected</c:if>>再接再厉</option>
																<option value="1" <c:if test="${type == 1}">selected</c:if>>红包</option>
																<option value="2" <c:if test="${type == 2}">selected</c:if>>夺宝币</option>
																<option value="3" <c:if test="${type == 3}">selected</c:if>>实体奖品</option>
															</select>
															&nbsp;
															<!-- 查询 -->
															<button type="submit" id="select-btn" class="bk-margin-5 btn btn-success"><i class="fa fa-search"></i>&nbsp;查&nbsp;询</button>
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
											<td>用户ID</td>
											<td>手机号码</td>
											<td>奖品名称</td>
											<td>活动图标</td>
											<td>兑换码</td>
											<td>状态</td>
											<td width="30%">备注</td>
											<td>中奖时间</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody style="text-align:center; ">
										<c:forEach var="item" items="${list}">
											<tr class="gradeX">
												<td>${item.user_id}</td>
												<td>${item.mobile}</td>
												<td>${item.name}</td>
												<td><img src="${item.image}"  style="height:25px;"></td>
												<td>${item.exchange_code}</td>
												<td>
													<c:if test="${not empty item.exchange_code}">
														<c:choose>
															<c:when test="${item.status == 0}">
																<label style="color:#FF0000">未兑换</label>
															</c:when>
															<c:when test="${item.status == 1}">
																<label style="color:#008000">已兑换</label>
															</c:when>
															<c:otherwise>未知</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${empty item.exchange_code}">
														-
													</c:if>
												</td>
												<td>${item.remark}</td>
												<td><fmt:formatDate value="${item.gmt_create}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><a href="rotateRecordEdit.html?id=${item.id}" class="btn btn-warning"><i class="fa fa-edit">编辑</i></a></td>
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
</body>

</html>