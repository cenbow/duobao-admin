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
                            <h6><i class="fa fa-hand-o-right"></i><span class="break"></span>用户未晒单期次列表</h6>
                            <div class="panel-actions">
                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                        <div class="panel-body">
                         <form action="getPersionNotLuckShowlist.html" id="editForm" method="post">
                        <input type="hidden" id="page" name="page" value='<c:out value="${pager.pageNumber}"/>'/>
                        <div class="row datatables-header form-inline">
                            <div class="col-sm-12 col-md-12">
                                <table class="col-md-12">
                                    <tbody>
                                    <tr>
                                        <td>
                                        	期次：<input type="text" class="form-control" name="periodId" value='<c:out value="${periodId}"/>'/>
                                            &nbsp;
                                            商品名称：<input type="text" class="form-control" name="productName" value='<c:out value="${productName}"/>'/>
                                        	&nbsp;
                                        	<button type="button" class="bk-margin-5 btn btn-success" onclick="doSubmit();"><i class="fa fa-search"></i>&nbsp;查 询&nbsp;</button>
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
                                    <th style="text-align:center;width:80px;">商品Id</th>
                                    <th style="text-align:center;">商品名称</th>
                                    <th style="text-align:center;width:200px;">商品图</th>
                                    <th style="text-align:center;width:100px;">揭晓时间</th>
                                    <th style="text-align:center;width:100px;">中奖用户</th>
                                    <th style="text-align:center;width:100px;">电话号码</th>
                                    <th style="text-align:center;width:100px;">操作</th>
                                </tr>
                                </thead>
                                <tbody style="text-align:center;">
                                <c:forEach var="period" items="${periodlist}">
                                    <tr class="gradeX">
										<td style="vertical-align: middle;"><c:out value="${period.id}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.product_id}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${proMap[period.product_id].title}"/></td>
                                        <td style="vertical-align: middle;">
                                            <img src="<c:out value="${proMap[period.product_id].list_img}"/>" style="width:200px;height:80px;">
                                        </td>
                                        <td style="vertical-align: middle;">
                                        	<fmt:formatDate value="${period.award_time}" pattern="yyyy-MM-dd  HH:mm:ss" />
                                        </td>
                                        <td style="vertical-align: middle;"><c:out value="${period.nick}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${period.mobile}"/></td>
                                        <td style="vertical-align: middle;">
                                        	<c:if test="${operatelogMap[period.id] != null}">
                                        	<span  class="label label-warning">已经回访</span>
                                        </c:if>
                                        <c:if test="${operatelogMap[period.id] == null}">
                                       		<a href="javascript:void(0)" id="addToTable" class="btn btn-info" onclick="mangerlog(${period.id},3,${period.mobile})">回访记录</a>
                                        </c:if>
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
<script src="assets/layer/layer.js"></script>
<!-- end: JavaScript-->
<script type="text/javascript">
$('#startTime').datetimepicker({
	lang:'ch',
	timepicker:true,
	format:'Y-m-d H:i:s'

});
$('#endTime').datetimepicker({
	lang:'ch',
	timepicker:true,
	format:'Y-m-d H:i:s'

});
    function doSubmit(){
    	$("#page").attr("value","1");
        $("#editForm").submit();
    }
    
    function mangerlog(periodId,type,mobile){
		layer.prompt({
			  title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲,你的每一步操作都要仔细斟酌哦',
			  formType: 2 //prompt风格，支持0-2
			  ,shift: 4
			}, function(pass){
				if(pass==null || pass==''){
					layer.msg('请填写操作内容');
				}else{
   			    	 $.get("/customerShowLog.html?periodId="+periodId+"&mobile="+mobile+"&remark="+pass+"&type="+type,function(text){
   			    		 var json_text=JSON.parse(text);
		    			    if(json_text.code==0){
		    			    	layer.msg('操作成功!');
		    			    	location="/getPersionNotLuckShowlist.html?periodId="+json_text.period_id;	
		    			    	}
		    			   else{
	    			    	alert("操作失败");
	    			    	return false;
	    			    }
   			 		 });
				}
			});

	}
</script>
</body>

</html>