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
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span></h6>							
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
									<form action="/configEdit.html"  class="form-horizontal form-bordered " id="editForm" method="post">
									<input type="hidden" id="id" name="id" value="<c:out value="${config.id}"/>">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">ClientID</label>
                                            
                                            <div class="col-md-3">
                                               <input type="text" id="client_id" name="client_id" class="form-control" value='<c:out value="${config.client_id}"/>' />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">系统</label>
                                            <div class="col-md-3">
                                               <input type="text" id="os" name="os" class="form-control" value='<c:out value="${config.os}"/>' />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">版本</label>
                                            <div class="col-md-3">
                                               <input type="text" id="version" name="version" class="form-control" value='<c:out value="${config.version}"></c:out>'/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">K值</label>
                                            <div class="col-md-3">
                                               <input type="text" id="k" name="k" class="form-control" value='<c:out value="${config.k}"></c:out>'/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">V值</label>
														<div class="form-group">									
															<div class="col-md-3">
															<textarea name="v" id="v" rows="20" class="form-control" > <c:out value="${config.v}"></c:out></textarea>
															</div>
														</div>
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
		
		<!-- Pages JS -->
		<!-- end: JavaScript-->
	<script type="text/javascript">
		$(function(){

	        var json=  $("#v").val();
	 		if(json!=null && json!=''){
	 			 var i           = 0,
		            len          = 0,
		            tab         = "    ",
		            targetJson     = "",
		            indentLevel = 0,
		            inString    = false,
		            currentChar = null;
		             
		             
		        for (i = 0, len = json.length; i < len; i += 1) { 
		            currentChar = json.charAt(i);
		 
		            switch (currentChar) {
		            case '{': 
		            case '[': 
		                if (!inString) { 
		                    targetJson += currentChar + "\n" + repeat(tab, indentLevel + 1);
		                    indentLevel += 1; 
		                } else { 
		                    targetJson += currentChar; 
		                }
		                break; 
		            case '}': 
		            case ']': 
		                if (!inString) { 
		                    indentLevel -= 1; 
		                    targetJson += "\n" + repeat(tab, indentLevel) + currentChar; 
		                } else { 
		                    targetJson += currentChar; 
		                } 
		                break; 
		            case ',': 
		                if (!inString) { 
		                    targetJson += ",\n" + repeat(tab, indentLevel); 
		                } else { 
		                    targetJson += currentChar; 
		                } 
		                break; 
		            case ':': 
		                if (!inString) { 
		                    targetJson += ": "; 
		                } else { 
		                    targetJson += currentChar; 
		                } 
		                break; 
		            case ' ':
		            case "\n":
		            case "\t":
		                if (inString) {
		                    targetJson += currentChar;
		                }
		                break;
		            case '"': 
		                if (i > 0 && json.charAt(i - 1) !== '\\') {
		                    inString = !inString; 
		                }
		                targetJson += currentChar; 
		                break;
		            default: 
		                targetJson += currentChar; 
		                break;                    
		            } 
		        } 
		        $("#v").val(targetJson);
		        return;
	 		}
	       
		});
		</script>	
	</body>
	
</html>