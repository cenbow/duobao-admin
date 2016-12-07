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
	
	<link href="assets/css/jquery.datetimepicker.css" rel="stylesheet" />
	<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />
	
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
									<i class="fa fa-hand-o-right"></i><span class="break"></span>编辑奖品选项
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<!-- 表单 -->
								<form action="saveRotateItemEdit.html" class="form-horizontal form-bordered" id="form" method="post" enctype="multipart/form-data">
									<input type="hidden" id="rotate_id" name="rotate_id" value="${rotate_id}"/>
									<input type="hidden" id="id" name="id" value="${rotate_item.id}"/>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">奖品名称<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="name" name="name" class="form-control" value="${rotate_item.name}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label" for="select">奖品类型<span class="required">*</span></label>
										<div class="col-md-3">
											<select class="form-control" style="width:100px;" id="type" name="type" onchange="javascript:doChange();">
												<option value="0" <c:if test="${rotate_item.type == 0}">selected</c:if>>再接再厉</option>
												<option value="1" <c:if test="${rotate_item.type == 1}">selected</c:if>>红包</option>
												<option value="2" <c:if test="${rotate_item.type == 2}">selected</c:if>>夺宝币</option>
												<option value="3" <c:if test="${rotate_item.type == 3}">selected</c:if>>实体奖品</option>
											</select>
										</div>
									</div>
									<div id="data">
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">奖品图标<span class="required">*</span></label>
										<div class="col-md-3">
											<c:if test="${not empty rotate_item.id}">
												<img src="${rotate_item.image}">
											</c:if>
											<input type="hidden" id="image_path" name="image_path" value="${rotate_item.image}" />
											<input type="file" id="image" name="image" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">库存<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="inventory" name="inventory" class="form-control" value="${rotate_item.inventory}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">权重<span class="required">*</span></label>
										<div class="col-sm-3">
											<input type="text" id="weight" name="weight" class="form-control" value="${rotate_item.weight}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">状态<span class="required">*</span></label>
										<div class="col-md-3">
											<select id="type" class="form-control" style="width:70px;" id="status" name="status">
												<option value="0" <c:if test="${rotate_item.status == 0}">selected</c:if>>未上线</option>
												<option value="1" <c:if test="${rotate_item.status == 1}">selected</c:if>>已上线</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">保底必中<span class="required">*</span></label>
										<div class="col-md-3">
											<select id="type" class="form-control" style="width:70px;" id="flag" name="flag">
												<option value="0" <c:if test="${rotate_item.flag == 0}">selected</c:if>>否</option>
												<option value="1" <c:if test="${rotate_item.flag == 1}">selected</c:if>>是</option>
											</select>
										</div>
									</div>
									<!-- 保存按钮 -->
									<div class="row">
										<div class="col-sm-12">
											<div align="center">
												<button type="submit" id="submit-btn" class="btn btn-info">保&nbsp;存&nbsp;<i class="fa fa-save"></i></button>
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
	
	<script src="assets/js/jquery.datetimepicker.full.js"></script>
	<script src="assets/js/jquery.datetimepicker.js"></script>
	
	<script src="assets/plugins/jquery-validation/js/jquery.validate.js"></script>
	<script src="assets/js/pages/form-validation.js"></script>
</body>
<script type="text/javascript">
	$(function(){
		doChange();
	});
	
	function doChange() {
		// 红包
		if($("#type").val() == 1) {
			var html = "";
			html+="<div class='form-group'>";
			html+="	<label class='col-sm-3 control-label' for='remark'>红包总额(元)<span class='required'>*</span></label>";
			html+="	<div class='col-md-3'>";
			html+="		<input type='text' id='amount' name='amount' class='form-control' value='${rotate_item.amount}' required/>";
			html+="	</div>";
			html+="</div>";
			html+="<div class='form-group'>";
			html+="	<label class='col-sm-3 control-label' for='remark'>拆分个数<span class='required'>*</span></label>";
			html+="	<div class='col-md-3'>";
			html+="		<input type='text' id='part_count' name='part_count' class='form-control' value='${rotate_item.partCount}' required/>";
			html+="	</div>";
			html+="</div>";
			html+="<div class='form-group'>";
			html+="	<label class='col-sm-3 control-label' for='remark'>过期天数<span class='required'>*</span></label>";
			html+="	<div class='col-md-3'>";
			html+="		<input type='text' id='part_count' name='expire_days' class='form-control' value='3' required readonly/>";
			html+="	</div>";
			html+="</div>";
			$("#data").html(html);
		} else if($("#type").val() == 2) {
			var html = "";
			html+="<div class='form-group'>";
			html+="	<label class='col-sm-3 control-label' for='remark'>夺宝币<span class='required'>*</span></label>";
			html+="	<div class='col-md-3'>";
			html+="		<input type='text' id='amount' name='amount' class='form-control' value='${rotate_item.amount}' required/>";
			html+="	</div>";
			html+="</div>";
			$("#data").html(html);
		} else {
			$("#data").html("");
		}
	}
</script>
</html>