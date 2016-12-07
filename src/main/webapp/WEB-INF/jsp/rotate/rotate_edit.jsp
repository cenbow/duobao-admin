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
									<i class="fa fa-hand-o-right"></i><span class="break"></span>编辑活动
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<!-- 表单 -->
								<form action="saveRotateEdit.html" class="form-horizontal form-bordered" id="form" method="post">
									<input type="hidden" id="rotate_id" name="rotate_id" value="${rotate.id}"/>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">活动名称<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="rotate_name" name="rotate_name" class="form-control" value="${rotate.name}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">活动开始时间<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="start_time" id="start_time" placeholder="活动开始时间" value='<fmt:formatDate value="${rotate.startTime}" pattern="yyyy-MM-dd HH:mm" />' required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">活动结束时间<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="end_time" id="end_time" placeholder="活动结束时间" value='<fmt:formatDate value="${rotate.endTime}" pattern="yyyy-MM-dd HH:mm" />' required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">每天可参与次数<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="participate_count" name="participate_count" class="form-control" value="${rotate.participateCount}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">状态</label>
										<div class="col-md-3">
											<select id="type" class="form-control" style="width:100px;" id="status" name="status">
												<option value="0" <c:if test="${rotate.status == 0}">selected</c:if>>未上线</option>
												<option value="1" <c:if test="${rotate.status == 1}">selected</c:if>>已上线</option>
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

	<script type="text/javascript">
		$(function() {
			$('#start_time').datetimepicker({
            	lang:'ch',
            	timepicker:true,
            	format:'Y-m-d H:i'

            });
			$('#end_time').datetimepicker({
            	lang:'ch',
            	timepicker:true,
            	format:'Y-m-d H:i'

            });
		})
	</script>
</body>

</html>