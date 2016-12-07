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
		<link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />
		<link href="assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet" />				
		<link href="assets/plugins/summernote/css/summernote.css" rel="stylesheet" />
		<link href="assets/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css" rel="stylesheet" />
		
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
				<div class="main ">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>添加红包触发计划</h6>							
									<div class="panel-actions">
										<a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
										<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<c:if test="${msg!=null}">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="alert alert-danger">
											<c:out value="${msg}"/>
										</div>
									</div>
								</c:if>
								<div class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
									<form action="createPlan.html"  class="form-horizontal form-bordered " id="editForm" method="post">
									<input type="hidden" name="id" value="${discount.id}">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">充值金额</label>
                                            <div class="col-md-3">
                                               <input type="text"  class="form-control" name= "limit_amount" value='<fmt:formatNumber    value="${discount.limit_amount/100}"   type="number" pattern="0" />' />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">奖励红包金额</label>
                                            <div class="col-md-3">
                                               <input type="text" class="form-control"  placeholder="奖励红包金额" name="reduce_amount" value='<fmt:formatNumber    value="${discount.reduce_amount/100}"   type="number" pattern="0" />' >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">折扣</label>
                                            <div class="col-md-3">
                                              <input type="text" class="form-control"  placeholder="折扣" name="discount" value='<c:out value="${discount.discount}"/>' >
                                            </div>
                                        </div>
                                      
                                          <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">红包触发计划</label>
                                             <div class="col-md-3">
                                              <a href="javascript:void(0);"id="position-1-error" class="bk-margin-top-10 bk-margin-bottom-10 btn btn-danger">例如当天触发50元</a>
                                             </div>
                                             <label><a href="javascript:void(0);" class="bk-margin-5 btn btn-primary btn-circle" type="button" onclick="plan()"><i class="fa fa-plus"></i></a></label>
                                        </div>
                                         <div class="form-group" id="plan"> 
                                         <c:if test="${planlist==null}">
                                          <label class="col-sm-3 control-label" for="remark"></label>
                                          <div class="row" >
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														<input type="number"  name="interval_date" class="form-control" value='' placeholder="天数:0" style="width:100px;" required/>
													</div>
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														 <input type="number"  name="face_value" class="form-control" value='' placeholder="触发50元" style="width:100px;" required/>
													</div>
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														<input type="text"  name="validity_date" class="form-control" value='' placeholder="有效期3天" style="width:100px;" required/>
													</div>
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														<input type="text" name="award_data" class="form-control" value='' placeholder="红包组成:10,10,10,10" style="width:150px;" required/>
													</div>
											</div>
                                         </c:if>
                                          <c:forEach var="plan" items="${planlist}">
                                          <label class="col-sm-3 control-label" for="remark" name="<c:out value="${plan.id}"></c:out>"></label>
                                          
                                          <div class="row" id="<c:out value="${plan.id}"></c:out>">
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														<input type="number"  name="interval_date" class="form-control" value='<c:out value="${plan.interval_date}"></c:out>' placeholder="天数:0" style="width:100px;" required/>
													</div>
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														 <input type="number"  name="face_value" class="form-control" value='<c:out value="${plan.face_value}"></c:out>' placeholder="触发50元" style="width:100px;" required/>
													</div>
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														<input type="text"  name="validity_date" class="form-control" value='<c:out value="${plan.validity_date}"></c:out>' placeholder="有效期3天" style="width:100px;" required/>
													</div>
													<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
														<input type="text" name="award_data" class="form-control" value='<c:out value="${plan.award_data}"></c:out>' placeholder="红包组成:10,10,10,10" style="width:150px;" required/>
													</div>
													<a href="javascript:void(0);" class="bk-margin-5 btn btn-primary btn-circle" onclick="planDel('<c:out value="${plan.id}"></c:out>')" type="button" ><i class="fa fa-times"></i></a>
											</div>
										</c:forEach>
                                         </div>
                                         
                                        <div class="row">
											<div class="col-sm-12">
												<div align="center">
													<button id="addToTable" class="btn btn-info" >保存 <i class="fa fa-save"></i></button>
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
		
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		<script src="assets/js/pages/form-validation.js"></script>
		<!-- Pages JS -->
		<!-- end: JavaScript-->
	<script type="text/javascript">
	    function doSubmit(){
	    	$("#page").attr("value","1");
	        $("#editForm").submit();
	    };
	    
	    function generateRandomAlphaNum(len) {
	        var rdmString = "";
	        for (; rdmString.length < len; rdmString += Math.random().toString().substr(2));
	        return rdmString.substr(0, len);
	    };
	    
	    function plan(){
	    	var rowId="rowid"+generateRandomAlphaNum(10);
	    	var str= "";
	    	str+="<label class='col-sm-3 control-label' for='remark' name='"+rowId+"'></label>";
	    	str+="<div class='row' id='"+rowId+"'>";
	    	str+="<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2'>";
	    	str+="<input type='number' name='interval_date' class='form-control' value='' style='width:100px;' required/> </div>";
	    	str+="<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2'>";
	    	str+="<input type='number'  name='face_value' class='form-control' value='' style='width:100px;' required/> </div>";
	    	str+="<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2'>";
	    	str+="<input type='text'  name='validity_date' class='form-control' value='' style='width:100px;' required/> </div>";
	    	str+="<div class='col-lg-2 col-md-2 col-sm-2 col-xs-2'>";
	    	str+="<input type='text' name='award_data' class='form-control' value='' style='width:150px;' required/> </div>";
	    	str+='<a href="javascript:void(0);" class="bk-margin-5 btn btn-primary btn-circle" onclick="planDel(\''+rowId+'\')" type="button" ><i class="fa fa-times"></i></a>';
	    	str+="</div>";
	    	
	    	$("#plan").append(str);
	    }
	    
	    function planDel(id){
	    	$("label[name="+id+"]").remove();
	    	$("#"+id).remove();
	    }
	</script>	
	</body>
	
</html>