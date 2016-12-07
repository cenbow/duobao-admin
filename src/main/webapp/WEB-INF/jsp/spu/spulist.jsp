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
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>SPU列表</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<div class="panel-body">
									<form action="spulist" class="form-horizontal form-bordered " id="editForm" method="post">
										<input type="hidden" id="page" name="page" value='<c:out value="${pager.pageNumber}"/>'/>
		                                 <a id="addToTable" class="btn btn-info" onclick="location.href='addSpu.html'" style="float:right;"><i class="fa fa-plus"></i>&nbsp;添加</a>
									</form>
									<table class="table table-bordered table-striped" id="datatable-default">
										<thead>
											<tr>
												<th style="text-align:center;width:100px;">SpuId</th>
												<th style="text-align:center;">名称</th>
												<th style="text-align:center;width:80px;">状态</th>
												<th style="text-align:center;width:180px;">操作</th>
											</tr>
										</thead>
										<tbody style="text-align:center;">
                                        <c:forEach var="spu" items="${spulist}">
                                            <tr class="gradeX">
                                                <td style="vertical-align: middle;"><c:out value="${spu.id}"/></td>
                                                <td style="vertical-align: middle;"><c:out value="${spu.title}"/></td>
                                                <td style="vertical-align: middle;">
                                                	<c:if test="${spu.deleted==0}">正常</c:if>
                                                	<c:if test="${spu.deleted==1}"><p style="color:red;">已删除</p></c:if>
                                                </td>
                                                <td class="actions">
	                                                <a href='spudetail.html?spuId=<c:out value="${spu.id}"/>' class="btn btn-info"><i class="fa fa-edit ">编辑</i></a>
	                                                <c:if test="${spu.deleted==0}">
	                                                	<a href="javascript:void(0)" onclick="del(<c:out value="${spu.id}"/>,1)" class="btn btn-danger"><i class="fa fa-trash-o ">下架</i></a>
	                                                </c:if>
	                                                <c:if test="${spu.deleted==1}">
	                                               		<a href="javascript:void(0)" onclick="del(<c:out value="${spu.id}"/>,0)" class="btn btn-success"><i class="fa fa-search-plus ">上架</i></a>
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

		<script src="assets/js/pages/form-elements.js"></script>
		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		
		<!-- end: JavaScript-->
		 <script type="text/javascript">
           function del(id,status){
        	   var a=confirm("确认执行此操作?");
        	   if(a==true){
        		   $.get("/spuDel.html?spuId="+id+"&status="+status,function(data){
        			    var json=JSON.parse(data);
        			    if(json.code==0){
        			    	alert("操作成功");
        			    	location.href="/spulist.html";
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