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
				<div class="main ">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="panel panel-default bk-bg-white">
								<div class="panel-heading bk-bg-white">
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>站内信详情</h6>							
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
									<form action="updateLetter.html"  class="form-horizontal form-bordered " id="editForm" method="post">
									<input type="hidden" id="id" name="id" class="form-control" value='<c:out value="${letter.id}"/>'/>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">标题</label>
                                            <div class="col-md-3">
                                               <input type="text" id="title" name="title" class="form-control" value='<c:out value="${letter.title}"/>'required/>
                                            </div>
                                        </div>
										
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">发送时间</label>
                                            <div class="col-md-3">
                                             <input type="text" class="form-control" name="send_time" id="send_time"  placeholder="发送时间"  value='<fmt:formatDate value="${letter.send_time}" pattern="yyyy-MM-dd  HH:mm:ss" />'>
                                            </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">发送目标</label>
                                            <div class="col-md-3">
                                             <input type="text" id="userId" name="userId" class="form-control" value='<c:out value="${letter.dest}"/>'/>
                                            </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">事件</label>
                                            <div class="col-md-3">
                                             <input type="text" id="event_type" name="event_type" class="form-control" value='<c:out value="${letter.event_type}"/>'/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">target_id</label>
                                            <div class="col-md-3">
                                             <input type="text" id="target_id" name="target_id" class="form-control" value='<c:out value="${letter.target_id}"/>'/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">target_url</label>
                                            <div class="col-md-3">
                                             <input type="text" id="target_url" name="target_url" class="form-control" value='<c:out value="${letter.target_url}"/>'/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">内容</label>
														<div class="form-group">									
															<div class="col-md-7">
															<textarea name="content" style="width:600px;height:400px;visibility:hidden;"><c:out value="${letter.content}"/></textarea>
															</div>
														</div>
										</div>
                                        <div class="row">
											<div class="col-sm-12">
												<div align="center">
													<button id="addToTable" class="btn btn-info" >保存 <i class="fa fa-save"></i></button>
													<button type="button" class="bk-margin-5 btn btn-danger" onclick="letterListPage()">返&nbsp;回&nbsp;<i class="fa fa-reply"></i></button>
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
		<script src="assets/js/jquery.datetimepicker.full.js"></script>
		<script src="assets/js/jquery.datetimepicker.js"></script>
		<!-- Theme JS -->		
		<script src="assets/js/jquery.mmenu.min.js"></script>
		<script src="assets/js/core.min.js"></script>
		<script src="assets/js/pages/form-validation.js"></script>
		<!-- Pages JS -->
<script src="assets/kindeditor/kindeditor-min.js"></script> 
<script src="assets/kindeditor/lang/zh_CN.js"></script> 
        <script type="text/javascript">
        $('#send_time').datetimepicker({
    		lang:'ch',
    		timepicker:true,
    		format:'Y-m-d H:i:s'
    	
    	});
        
            var editor;
            KindEditor.ready(function(K) {
            	editor = K.create('textarea[name="content"]', {
            		allowFileManager : true
            	});
            	K('input[name=getHtml]').click(function(e) {
            		alert(editor.html());
            	});
            	K('input[name=isEmpty]').click(function(e) {
            		alert(editor.isEmpty());
            	});
            	K('input[name=getText]').click(function(e) {
            		alert(editor.text());
            	});
            	K('input[name=selectedHtml]').click(function(e) {
            		alert(editor.selectedHtml());
            	});
            	K('input[name=setHtml]').click(function(e) {
            		editor.html('<h3>Hello KindEditor</h3>');
            	});
            	K('input[name=setText]').click(function(e) {
            		editor.text('<h3>Hello KindEditor</h3>');
            	});
            	K('input[name=insertHtml]').click(function(e) {
            		editor.insertHtml('<strong>插入HTML</strong>');
            	});
            	K('input[name=appendHtml]').click(function(e) {
            		editor.appendHtml('<strong>添加HTML</strong>');
            	});
            	K('input[name=clear]').click(function(e) {
            		editor.html('');
            	});
            });
            
            function letterListPage(){
            	location="/letterlist.html"
            }
        </script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>