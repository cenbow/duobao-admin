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
        <div class="main ">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default bk-bg-white">
                        <div class="panel-heading bk-bg-white">
                            <h6><i class="fa fa-hand-o-right"></i><span class="break"></span>期次列表</h6>
                            <div class="panel-actions">
                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                        <div class="panel-body">
                         <form action="" id="editForm" method="post">
                        <input type="hidden" id="page" name="page" value='<c:out value="${pager.pageNumber}"/>'/>
                        <div class="row datatables-header form-inline">
                            <div class="col-sm-12 col-md-12">
                                <table class="col-md-12">
                                    <tbody>
                                    <tr>
                                        <td>
                                        	期次：<input type="text" class="form-control" id="periodId" name="periodId" value='<c:out value="${periodId}"/>' style="width:80px;"/>
                                            &nbsp;
                                            注册手机号：<input type="text" class="form-control" id="mobile" name="mobile" value='<c:out value="${mobile}"/>'/>
                                            &nbsp;
                                            充值状态：<select  id="status" name="status" class="form-control" >
                                            		<option value="-1" <c:if test="${status=='-1'}">selected</c:if>>请选择</option>
                                              		<option value="0" <c:if test="${status=='0'}">selected</c:if>>未确认手机号</option>
                                              		<option value="1,-1" <c:if test="${status=='1,-1'}">selected</c:if>>充值中</option>
                                              		<option value="2" <c:if test="${status=='2'}">selected</c:if>>充值成功</option>
                                              		<option value="-9" <c:if test="${status=='-9'}">selected</c:if>>无法充值</option>
                                            </select>
                                        </td>
                                        <td align="right">
                                            <button type="button" class="bk-margin-5 btn btn-success" onclick="doSubmit('rechargePeriodlist');">&nbsp;查 询&nbsp;</button>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td>
                                    		时间：
                                    		<input type="text" class="form-control" name="startTime" id="startTime"  placeholder="开始时间"  value='<c:out value="${startTime}"/>'>
	          	  						  	<input type="text" class="form-control" name="endTime" id="endTime"  placeholder="结束时间" value='<c:out value="${endTime}"/>'>
                                    	</td>
                                    	<td align="right">
                                    		<a href="javascript:void(0)" type="button" class="bk-margin-5 btn btn-success" onclick="doSubmit('rechargeExcelImport')">&nbsp;数据导出&nbsp;</a>
                                    	</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                            <table class="table table-bordered table-striped" id="datatable-default">
                                <thead >
                                <tr >
                                    <th style="text-align:center;width:80px;">期次</th>
                                    <th style="text-align:center;width:100px;">用户ID</th>
                                    <th style="text-align:center;width:100px;">充值手机号</th>
                                    <th style="text-align:center;width:100px;">注册手机号</th>
                                    <th style="text-align:center;width:100px;">用户名</th>
                                    <th style="text-align:center;width:80px;">充值金额(单位:元)</th>
                                    <th style="text-align:center;width:80px;">当前状态</th>
                                    <th style="text-align:center;width:80px;">尝试充值次数</th>
                                    <th style="text-align:center;">备注</th>
                                </tr>
                                </thead>
                                <tbody style="text-align:center;">
                                <c:forEach var="period" items="${periodlist}">
                                    <tr class="gradeX">

                                        <td style="vertical-align: middle;"><c:out value="${period.period_id}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.user_id}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.mobile}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.res_mobile}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.name}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.amount/100}"/></td>
                                        <td style="vertical-align: middle;">
	                                        <!-- 状态 0-初始化 1-创建订单 2-充值成功 9-客服手动关闭 -1可恢复的充值失败 -9不可恢复的充值失败 -->
	                                        <c:if test="${period.status==0}">
	                                        	<span  class="label label-primary">等待充值</span>
	                                        </c:if>
	                                         <c:if test="${period.status==1||period.status==-1}">
	                                        	<span  class="label label-info">充值中</span>
	                                        </c:if>
	                                        <c:if test="${period.status==2}">
	                                        	<span class="label label-success">充值成功</span>
	                                        </c:if>
	                                        <c:if test="${period.status==-9}">
	                                        	<span  class="label label-danger">无法充值</span>
	                                        </c:if>
	                                        <c:if test="${period.status==9}">
	                                        <span  class="label label-warning">已经关闭</span>
	                                        </c:if>
	                                         <c:if test="${period.order_status==0}">
	                                        		 <span  class="label label-warning">未确认手机号</span>
	                                        </c:if>
                                        </td>
                                        <td style="vertical-align: middle;"><c:out value="${period.times}"/></td>
                                        <td style="vertical-align: middle;">
                                        	<c:out value="${period.remark}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <%@ include  file="../pager.jsp"%>
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
<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="assets/js/jquery.datetimepicker.full.js"></script>
<script src="assets/js/jquery.datetimepicker.js"></script>
<script src="assets/js/pages/form-elements.js"></script>

<!-- Theme JS -->
<script src="assets/js/jquery.mmenu.min.js"></script>
<script src="assets/js/core.min.js"></script>

<!-- end: JavaScript-->
<script type="text/javascript">
$(function(){
	$("#startTime").datetimepicker({
		lang:'ch',
		timepicker:false,
		format:'Y-m-d'
	});
	
	$("#endTime").datetimepicker({
		lang:'ch',
		timepicker:false,
		format:'Y-m-d'
	});
});

    function doSubmit(url){
    	$("#page").attr("value","1");
    	$("#editForm").attr("action",url);
        $("#editForm").submit();
        $("#editForm").attr("action","");
    }
    

    function updateExpress(id){
    	   var a=confirm("确认执行此操作?");
    	   if(a==true){
    		   $.get("/updateExpress.html?expressId="+id,function(data){
    			    var json=JSON.parse(data);
    			    if(json.code==0){
    			    	alert("操作成功");
    			    	location.href="/rechargePeriodlist.html?status=-9&page=<c:out value="${pager.pageNumber}"/>";
    			    }else{
    			    	alert("操作失败");
    			    	return false;
    			    }
    			  });
    	   }
    }
</script>
</body>

</html>