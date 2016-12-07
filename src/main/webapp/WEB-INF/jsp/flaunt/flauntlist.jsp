<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                            <h6><i class="fa fa-hand-o-right"></i><span class="break"></span>晒单列表</h6>
                            <div class="panel-actions">
                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                        <div class="panel-body">
                         <form action="luckyShowlist.html" id="editForm" method="post">
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
                                            产品组：<select  id="spuId" name="spuId" class="form-control" style="width:200px;">
                                            		<option value="<c:out value="-1"/>">请选择</option>
                                            		  <c:forEach var="spu" items="${spulist}">
                                              		  	<option value="<c:out value="${spu.id}"/>" <c:if test="${spu.id==spuId}">selected</c:if>><c:out value="${spu.title}"/> </option>
                                              		  </c:forEach>
                                            </select>
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
                                    <th style="text-align:center;width:70px;">期次</th>
                                    <th style="text-align:center;width:100px;">商品名称</th>
                                    <th style="text-align:center;width:80px;">用户昵称</th>
                                    <th style="text-align:center;width:80px;">用户手机</th>
                                    <th style="text-align:center;width:100px;">晒单时间</th>
                                    <th style="text-align:center;">晒单内容</th>
                                    <th style="text-align:center;width:120px;">晒单图</th>
                                    <th style="text-align:center;width:50px;">是否精华</th>
                                    <th style="text-align:center;width:50px;">是否优先</th>
                                    <th style="text-align:center;width:50px;">审核状态</th>
                                </tr>
                                </thead>
                                <tbody style="text-align:center;">
                                <c:forEach var="luckyShow" items="${luckyShowlist}">
                                    <tr class="gradeX">
                                        <td style="vertical-align: middle;"><c:out value="${luckyShow.period_id}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${proMap[luckyShow.product_id].title}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${luckyShow.nick}"/></td>
                                        <td style="vertical-align: middle;"><c:out value="${luckyShow.mobile}"/></td>
                                        <td style="vertical-align: middle;"><fmt:formatDate value="${luckyShow.gmt_create}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
                                        <td style="vertical-align: middle;"><c:out value="${luckyShow.content}"/></td> 
                                        <td style="vertical-align: middle;">
                                       <c:forEach items="${luckyShow.imglist}" varStatus="s" var="im">
                                       		<a class="thumb-image" href="<c:out value="${im}"/>">
												<img src="<c:out value="${im}"/>" class="img-responsive" alt="Project" style="float:left;width:100px;">
											</a>
										</c:forEach>
                                        </td>
                                        <td style="vertical-align: middle;">
                                        <c:if test="${luckyShow.good==1}">
                                        	<p style="color:green;"> 是</p>
                                        	<a href='javascript:void(0);' class="btn btn-info" onclick="updateGood('<c:out value="${luckyShow.id}"/>','0','','')">取消精华</a>
                                        </c:if>
                                        <c:if test="${luckyShow.good==0}">
                                       		 <p style="color:red;"> 否</p>
                                       		 <a href='javascript:void(0);' class="btn btn-info" onclick="updateGood('<c:out value="${luckyShow.id}"/>','1','','')">设置精华</a>
                                        </c:if>
                                        <td style="vertical-align: middle;">
	                                        <c:if test="${luckyShow.recommend==1}">
	                                        	<p style="color:green;"> 是</p>
	                                        	 <a href='javascript:void(0);' class="btn btn-info" onclick="updateGood('<c:out value="${luckyShow.id}"/>','','0','')">取消优先</a>
	                                        </c:if>
	                                        <c:if test="${luckyShow.recommend==0}">
	                                       		 <p style="color:red;"> 否</p>
	                                       		  <a href='javascript:void(0);' class="btn btn-info" onclick="updateGood('<c:out value="${luckyShow.id}"/>','','1','')">设置优先</a>
	                                        </c:if>
                                        </td>
                                        <td style="vertical-align: middle;">
                                        	<c:if test="${luckyShow.audit_status==1}">
	                                        	<p style="color:green;"> 是</p>
	                                        	<a href='javascript:void(0);' class="btn btn-info" onclick="updateGood('<c:out value="${luckyShow.id}"/>','','','0')">审核未通过</a>
	                                        </c:if>
	                                        <c:if test="${luckyShow.audit_status==0}">
	                                       		 <p style="color:red;"> 否</p>
	                                       		 <a href='javascript:void(0);' class="btn btn-info" onclick="updateGood('<c:out value="${luckyShow.id}"/>','','','1')">审核通过</a>
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
    
    function updateGood(id,good,recommend,status){
 	   var a=confirm("确认执行此操作?");
 	   if(a==true){
 		   $.get("/updateRecommend.html?id="+id+"&good="+good+"&recommend="+recommend+"&status="+status,function(data){
 			    var json=JSON.parse(data);
 			    if(json.code==0){
 			    	alert("操作成功");
 			    	location.href="/luckyShowlist.html?page=<c:out value="${pager.pageNumber}"/>";
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