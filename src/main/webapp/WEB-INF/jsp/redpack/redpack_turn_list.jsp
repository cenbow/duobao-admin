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
    <link href="assets/css/jquery.datetimepicker.css" rel="stylesheet" />

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
								<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>红包轮次列表</h6>
								<div class="panel-actions">
	                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
	                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
	                            </div>
							</div>
							<div class="panel-body">
								<div class="row datatables-header form-inline">
									<div class="col-sm-12 col-md-12">
										<!-- 表单 -->
										<form action="redpackTurnList.html" id="editForm" method="post">
											<input type="hidden" id="page" name="page" value="${pager.pageNumber}" />
											<input type="hidden" id="redpack_id" name="redpack_id" value="${redpack_id}" />
											<table class="col-md-12">
												<tbody>
													<tr>
														<td>
				                                        	时间:
				                                        	<input type="text" class="form-control" size="12" name="start_date" id="start_date" placeholder="开始时间"  value="${start_date}"/>
				                                        	~
				                                        	<input type="text" class="form-control" size="12" name="end_date" id="end_date" placeholder="结束时间"  value="${end_date}"/>
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
											<td>ID</td>
											<td>轮次时间</td>
											<td>最小红包</td>
											<td>最大红包</td>
											<td>平均金额</td>
											<td>总金额</td>
											<td>红包总数</td>
											<td>剩余总数</td>
											<td>剩余总数</td>
										</tr>
									</thead>
									<tbody style="text-align:center; ">
										<c:forEach var="item" items="${list}">
											<tr class="gradeX">
												<td>${item.id}</td>
												<td>${item.timeRange}</td>
												<td align="right"><fmt:formatNumber value="${item.minAmount/100}" type="number" pattern="0"/>元</td>
												<td align="right"><fmt:formatNumber value="${item.maxAmount/100}" type="number" pattern="0"/>元</td>
												<td align="right"><fmt:formatNumber value="${item.avgAmount/100}" type="number" pattern="0"/>元</td>
												<td align="right"><fmt:formatNumber value="${item.totalAmount/100}" type="number" pattern="0"/>元</td>
												<td align="right">${item.totalCount}个</td>
												<td align="right">${item.remainCount}个</td>
												<td>
				                                	<span style="color: #20a8d8; cursor: pointer;" onclick="javascript:showAmountList('${item.amountList}');">红包明细</span>
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
	<script src="assets/js/jquery.datetimepicker.full.js"></script>
	<script src="assets/js/jquery.datetimepicker.js"></script>

	<script src="assets/js/jquery.mmenu.min.js"></script>
	<script src="assets/js/core.min.js"></script>
	<script src="assets/layer/layer.js"></script>

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
		
		function showAmountList(amountList) {
			var content = "";
			var arr = amountList.split(",");
			for(var i=0; i<arr.length; i++){
				content += (arr[i]/100);
				if(i < arr.length -1) {
					content += ", ";;
				}
			}
			
			layer.alert(content, {
				title: '红包明细',
			    skin: 'layui-layer-molv',
			    closeBtn: 0
			  });
			
			
		}
	</script>
</body>

</html>