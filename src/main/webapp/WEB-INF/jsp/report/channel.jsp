<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Basic -->
    <meta charset="UTF-8" />
    <title>Backyard</title>

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="assets/ico/apple-touch-icon.png" />
    <link rel="apple-touch-icon" sizes="57x57" href="assets/ico/apple-touch-icon-57x57.png" />
    <link rel="apple-touch-icon" sizes="72x72" href="assets/ico/apple-touch-icon-72x72.png" />
    <link rel="apple-touch-icon" sizes="76x76" href="assets/ico/apple-touch-icon-76x76.png" />
    <link rel="apple-touch-icon" sizes="114x114" href="assets/ico/apple-touch-icon-114x114.png" />
    <link rel="apple-touch-icon" sizes="120x120" href="assets/ico/apple-touch-icon-120x120.png" />
    <link rel="apple-touch-icon" sizes="144x144" href="assets/ico/apple-touch-icon-144x144.png" />
    <link rel="apple-touch-icon" sizes="152x152" href="assets/ico/apple-touch-icon-152x152.png" />

    <!-- start: CSS file-->
    <!-- Vendor CSS-->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
    <link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <link href="assets/vendor/css/pace.preloader.css" rel="stylesheet" />
    <link href="assets/css/jquery.datetimepicker.css" rel="stylesheet" />

    <!-- Plugins CSS-->
    <link href="assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet" />
    <link href="assets/plugins/jquery-datatables-bs3/css/datatables.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
    <link href="assets/plugins/bootstrap-datepicker/css/datepicker-theme.css" rel="stylesheet" />


    <!-- Theme CSS -->
    <link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

    <!-- Page CSS -->
    <link href="assets/css/style.css" rel="stylesheet" />
    <link href="assets/css/add-ons.min.css" rel="stylesheet" />
		
	<!-- end: CSS file-->	
    
	
	<!-- Head Libs -->
	<script src="assets/plugins/modernizr/js/modernizr.js"></script>
	<!-- end: CSS file-->	
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->	
</head>

<body>
<%@ include  file="../header.jsp"%>
<!-- Start: Content -->
<div class="container-fluid content">
<div class="row">
<%@ include  file="../menu.jsp"%>
<!-- Main Page -->
<div class="main">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="panel panel-default bk-bg-white">
                <div class="panel-heading bk-bg-white">
                    <h6><i class="fa fa-hand-o-right"></i><span class="break"></span>渠道报表</h6>
                    <div class="panel-actions">
                        <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
                        <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                    </div>
                </div>
                <div class="panel-body">
                    <form id="editForm" action="reportChannel.html" method="post">
                        <input type="hidden" id="page" name="page" value='<c:out value="${pager.pageNumber}"/>'/>
                        <div class="row datatables-header form-inline">
                            <div class="col-sm-12 col-md-12">
                                <table>
                                    <tbody>
                                    <tr>
                                        <td>
                                            客户端:<input type="text" class="form-control" size="12" name="clientId" value="${clientId}"/>
                                        </td>
                                        <td>
                                            系统:<input type="text" class="form-control" size="12" name="os" value="${os}"/>
                                        </td>
                                        <td>
                                            渠道:<input type="text" class="form-control" size="12" name="channel" value="${channel}"/>
                                        </td>
                                        <td>
                                        	时间:
                                        	<input type="text" class="form-control" size="12" name="startDate" id="startDate" placeholder="开始时间"  value="${startDate}"/>
                                        	~
                                        	<input type="text" class="form-control" size="12" name="endDate" id="endDate" placeholder="结束时间"  value="${endDate}"/>
                                        </td>
                                        <td align="right">
                                            <button type="button" class="bk-margin-5 btn btn-success" onclick="doSubmit();">&nbsp;查 询&nbsp;</button>
                                        	<button type="button" class="bk-margin-5 btn btn-link" onclick="window.location.href='reportChannelCharts.html'"><i class="fa fa-link"></i>查看趋势</button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                    
                    <div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-table red"></i><span class="break"></span><span style="color: red">注: 当天数据每5分钟更新一次</span></h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered table-striped" id="datatable-default">
					                        <thead>
						                        <tr>
						                        	<th>日期</th>
						                        	<th>客户端</th>
						                            <th>系统</th>
						                            <th>渠道</th>
						                            <th>激活人数</th>
						                            <th>注册人数</th>
						                            <th>注册率</th>
						                            <th>付费人数</th>
						                            <th>新增付费人数</th>
						                            <th>新增付费率</th>
						                            <th>付费额(元)</th>
						                            <th>新增付费额(元)</th>
						                            <th>ARPPU</th>
						                        </tr>
					                        </thead>
					                        <tbody>
						                        <c:choose>
													<c:when test="${empty list}">
														<tr class="gradeX">
															<td colspan="13">暂无数据！</td>
														</tr>
													</c:when>
													<c:otherwise>
														<c:forEach items="${list}" var="item" varStatus="status">
															<tr class="gradeX">
								                            	<td><a href="reportChannelHour.html?date=${item.date}&clientId=${item.client_id}&os=${item.os}&channel=${item.channel}">${item.date}</a></td>
								                                <td>
								                                	<c:if test="${not empty item.icon}">
								                                		<img src="${item.icon}" style="height:20px;">
								                                	</c:if>
								                                	${item.client_id}
								                                </td>
								                                <td>${item.os}</td>
								                                <td>${item.channel}</td>
								                                <td align="right">${item.activate_num}</td>
								                                <td align="right">${item.register_num}</td>
								                                <td align="right">${item.register_rate}</td>
								                                <td align="right">${item.pay_num}</td>
								                                <td align="right">${item.new_pay_num}</td>
								                                <td align="right">${item.new_pay_rate}</td>
								                                <td align="right" id="pay_${status.index}" onclick="javascript:doLayer('pay_${status.index}','${item.pay_amount_fmt}','${item.recharge_amount_fmt}');">
								                                	<span style="color: #20a8d8; cursor: pointer;">${item.pay_amounts_fmt}</span>
								                                </td>
								                                <td align="right" id="recharge_${status.index}" onclick="javascript:doLayer('recharge_${status.index}','${item.new_pay_amount_fmt}','${item.new_recharge_amount_fmt}');">
								                                	<span style="color: #20a8d8; cursor: pointer;">${item.new_pay_amounts_fmt}</span>
								                                </td>
								                                <td align="right">${item.arppu}</td>
								                            </tr>
														</c:forEach>
													</c:otherwise>
												</c:choose>
												<tr>
													<c:choose>
														<c:when test="${isSameDay eq true}">
															<td colspan="4" align="right">总计:</td>
															<td align="right">${activate_num_total}</td>
															<td align="right">${register_num_total}</td>
															<td align="right">${activate_total_rate}</td>
															<td align="right">${pay_num_total}</td>
															<td align="right">${new_pay_num_total}</td>
															<td align="right">${new_pay_total_rate}</td>
															<td align="right" id="t_pay_${status.index}" onclick="javascript:doLayer('t_pay_${status.index}','${pay_amount_total}','${recharge_amount_total}');">
								                            	<span style="color: #20a8d8; cursor: pointer;">${pay_amounts_total}</span>
								                            </td>
								                            <td align="right" id="tn_pay_${status.index}" onclick="javascript:doLayer('tn_pay_${status.index}','${new_pay_amount_total}','${new_recharge_amount_total}');">
								                            	<span style="color: #20a8d8; cursor: pointer;">${new_pay_amounts_total}</span>
								                            </td>
								                            <td align="right"></td>
														</c:when>
														<c:otherwise>
															<td colspan="10" align="right">总计:</td>
															<td align="right" id="t_pay_${status.index}" onclick="javascript:doLayer('t_pay_${status.index}','${pay_amount_total}','${recharge_amount_total}');">
								                            	<span style="color: #20a8d8; cursor: pointer;">${pay_amounts_total}</span>
								                            </td>
								                            <td align="right"></td>
								                            <td align="right"></td>
														</c:otherwise>
													</c:choose>
					                       		</tr>
					                        </tbody>
					                    </table>
					                    <%@ include  file="../pager.jsp"%>
									</div>
								</div>
							</div>
						</div>
		            </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End Main Page -->

</div>
</div><!--/container-->


<div class="clearfix"></div>


<!-- start: JavaScript-->

<!-- Vendor JS-->
		<script src="assets/vendor/js/jquery.min.js"></script>
		<script src="assets/vendor/js/jquery-2.1.1.min.js"></script>
		<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/vendor/skycons/js/skycons.js"></script>
		<script src="assets/vendor/js/pace.min.js"></script>
		
		<!-- Plugins JS-->
		<script src="assets/plugins/moment/js/moment.min.js"></script>	
		<script src="assets/plugins/summernote/js/summernote.js"></script>
		<script src="assets/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
		<script src="assets/plugins/bootstrap-markdown/js/markdown.js"></script>
		<script src="assets/plugins/bootstrap-markdown/js/to-markdown.js"></script>
		<script src="assets/plugins/sparkline/js/jquery.sparkline.min.js"></script>
		<script src="assets/js/jquery.datetimepicker.full.js"></script>
		<script src="assets/js/jquery.datetimepicker.js"></script>
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		<script src="assets/layer/layer.js"></script>
		<script type="text/javascript">
		    $(function(){
		    	$("#startDate").datetimepicker({
		    		lang:'ch',
		    		timepicker:false,
		    		format:'Y-m-d'
		    	});
		    	
		    	$("#endDate").datetimepicker({
		    		lang:'ch',
		    		timepicker:false,
		    		format:'Y-m-d'
		    	});
		    });
		    
		    function doLayer(id, payAmount, rechargeAmount) {
		    	var tips = "支付：" + payAmount + "<br/>充值：" + rechargeAmount;
		    	//tips层-左
		    	layer.tips(tips, '#' + id, {
		    	  tips: [4, '#78BA32'],
		    	  time: 3000
		    	});
		    }
		    
		    function doSubmit(){
		        $("#editForm").submit();
		    }
		</script>
</body>
</html>