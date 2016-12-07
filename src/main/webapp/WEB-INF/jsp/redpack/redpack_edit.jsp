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
									<i class="fa fa-hand-o-right"></i><span class="break"></span>编辑红包活动
								</h6>
								<div class="panel-actions">
									<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<!-- 表单 -->
								<form action="saveredpackEdit.html" class="form-horizontal form-bordered" id="form" method="post">
									<input type="hidden" id="id" name="id" value="${redpack.id}"/>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">红包名称<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="name" name="name" class="form-control" value="${redpack.name}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">活动开始时间<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="start_date" id="start_date" placeholder="活动开始时间" value='${redpack.startDate}' required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">活动结束时间<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="end_date" id="end_date" placeholder="活动结束时间" value='${redpack.endDate}' required>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">随机金额最小值<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="range_min" id="range_min" value='${redpack.rangeMin}' required>
										</div>
										<div class="col-md-3">
											 <label class="control-label" style="color: #D1D1D1">单位(元)</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">随机金额最大值<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="range_max" id="range_max" value='${redpack.rangeMax}' required>
										</div>
										<div class="col-md-3">
											 <label class="control-label" style="color: #D1D1D1">单位(元)</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">平均金额<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" class="form-control" name="range_avg" id="range_avg" value='${redpack.rangeAvg}' required>
										</div>
										<div class="col-md-6">
											 <label class="control-label" style="color: #D1D1D1">单位(元)随机的红包金额绝大部分集中在这个值附近。</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">随机结束分钟数<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="end_minute" name="end_minute" class="form-control" value="${redpack.endMinute}" required/>
										</div>
										<div class="col-md-3">
											 <label class="control-label" style="color: #D1D1D1">单位(分钟)</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">每天可参与次数<span class="required">*</span></label>
										<div class="col-md-3">
											<input type="text" id="participate_count" name="participate_count" class="form-control" value="${redpack.participateCount}" required/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">轮次配置<span class="required">*</span></label>
										<div class="col-md-6"><label class="control-label" style="color: #D1D1D1">第一列：0800(代表:08:00即早上8点)；第二列：200(红包个数)</label></div>
									</div>
									<div class="form-group" id="turn-conf">
										<c:if test="${empty  redpack.turnList}">
											<label class="col-sm-3 control-label" rowId="0"></label>
											<div class="row" rowId="0">
												<div class="col-md-2">
													<input type="text" id="clock_0" name="clock" class="form-control" placeholder="0800" value="${item.clock}" required/>
												</div>
												<div class="col-md-2">
													<input type="text" id="count_0" name="count" class="form-control" placeholder="200" value="${item.count}" required/>
												</div>
												<div class="col-md-2">
													<a href="javascript:doPlus();" class="bk-margin-5 btn btn-primary btn-circle" type="button"><i class="fa fa-plus"></i></a>
												</div>
											</div>
										</c:if>
										<c:forEach items="${redpack.turnList}" var="item" varStatus="status">
											<label class="col-sm-3 control-label" rowId="${status.index}"></label>
											<div class="row" rowId="${status.index}">
												<div class="col-md-2">
													<input type="text" name="clock" class="form-control" placeholder="轮次时间。如:0800" value="${item.clock}" required/>
												</div>
												<div class="col-md-2">
													<input type="text" name="count" class="form-control" placeholder="红包个数。如:200" value="${item.count}" required/>
												</div>
												<div class="col-md-2">
													<c:choose>
														<c:when test="${status.first}">
															<a href="javascript:doPlus();" class="bk-margin-5 btn btn-primary btn-circle" type="button"><i class="fa fa-plus"></i></a>
														</c:when>
														<c:otherwise>
															<a href="javascript:doTimes(${status.index});" class="bk-margin-5 btn btn-primary btn-circle" type="button"><i class="fa fa-times"></i></a>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</c:forEach>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label" for="remark">状态</label>
										<div class="col-md-3">
											<select id="type" class="form-control" style="width:100px;" id="status" name="status">
												<option value="0" <c:if test="${redpack.status == 0}">selected</c:if>>未上线</option>
												<option value="1" <c:if test="${redpack.status == 1}">selected</c:if>>已上线</option>
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
		$(function(){
	    	$("#start_date").datetimepicker({
	    		lang:'ch',
	    		timepicker:false,
	    		format:'Y-m-d'
	    	});
	    	
	    	$("#end_date").datetimepicker({
	    		lang:'ch',
	    		timepicker:false,
	    		format:'Y-m-d'
	    	});
	    });
		
		function doPlus() {
			var index = $("label[rowId]").size();
			var html = "<label class='col-sm-3 control-label' rowId='"+ index +"'></label>";
			html +=  "<div class='row' rowId='"+index+"'>";
			html +=  "<div class='col-md-2'>";
			html +=  "	<input type='text' name='clock' class='form-control' placeholder='轮次时间。如:0800' value='' required/>";
			html +=  "</div>";
			html +=  "<div class='col-md-2'>";
			html +=  "	<input type='text' name='count' class='form-control' placeholder='红包个数。如:200' value='' required/>";
			html +=  "</div>";
			html +=  "<div class='col-md-2'>";
			html +=  "	<a href='javascript:doTimes("+index+");' class='bk-margin-5 btn btn-primary btn-circle' type='button'><i class='fa fa-times'></i></a>";
			html +=  "</div>";
			html +=  "</div>";
			$("#turn-conf").append(html);
		}
		
		function doTimes(id) {
			$("label[rowId="+id+"]").remove();
			$("div[rowId="+id+"]").remove();
		}
	</script>
</body>

</html>