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
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>添加新商品</h6>							
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
									<form action="createProduct.html"  class="form-horizontal form-bordered " id="editForm" method="post"enctype="multipart/form-data" >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">商品名称</label>
                                            <div class="col-md-3">
                                               <input type="text" id="title" name="title" class="form-control" value=''required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
											<label class="col-md-3 control-label" for="select">类型</label>
											<div class="col-md-3">
												<select id="type" name="type" class="form-control input-lg" size="1">
													<option value="0">实物</option>
													<option value="1">充值卡</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-3 control-label" for="select">所属SPU</label>
											<div class="col-md-3">
												<select id="spu_id" name="spu_id" class="form-control input-lg" size="1" required>
												<option value="<c:out value=""/>">请选择</option>
												 <c:forEach var="spu" items="${spulist}">
                                              		  	<option value="<c:out value="${spu.id}"/>"><c:out value="${spu.title}"/> </option>
                                              		  </c:forEach>
												</select>
											</div>
										</div>	
										<div class="form-group">
											<label class="col-md-3 control-label" for="select">所属类目</label>
											<div class="col-md-4">
												 <c:forEach var="cate" items="${catelist}" varStatus='s'>
												 <div class="checkbox-custom checkbox-inline">
													<input type="checkbox" id="inline-checkboxs${s.index}" name="cate_id" value="<c:out value="${cate.id}"/>"> 
													<label for="inline-checkbox${s.index}"> <c:out value="${cate.title}"/></label>
												</div>
                                                </c:forEach>
											</div>
										</div>
										
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">列表图</label>
                                            <div class="col-md-3">
                                               <input type="file" id="list_img" name="list_img" class="form-control" value='' required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">轮播图</label>
                                            <div class="col-md-3">
                                               <input type="file" name="loop_img1" class="form-control" value='' required/>
                                               <input type="file" name="loop_img2" class="form-control" value='' />
                                               <input type="file" name="loop_img3" class="form-control" value='' />
                                               <input type="file" name="loop_img4" class="form-control" value='' />
                                               <input type="file" name="loop_img4" class="form-control" value='' />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">副标题</label>
                                            <div class="col-md-3">
                                               <input type="text" id="sub_title" name="sub_title" class="form-control" value='' required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">真实价格(单位:元)</label>
                                            <div class="col-md-3">
                                               <input type="number" id="real_cost" name="real_cost" class="form-control" value='0'  onkeyup="this.value=this.value.replace(/\D/g,'')" required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">库存</label>
                                            <div class="col-md-3">
                                               <input type="number" id="numbers" name="numbers"  class="form-control" value='0' onkeyup="this.value=this.value.replace(/\D/g,'')"  required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">夺宝总份数</label>
                                            <div class="col-md-3">
                                               <input type="number" id="total_count" name="total_count" class="form-control" value='0' onkeyup="this.value=this.value.replace(/\D/g,'')"  required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">默认购买份数</label>
                                            <div class="col-md-3">
                                               <input type="number" id="default_count" name="default_count" class="form-control" value='10' onkeyup="this.value=this.value.replace(/\D/g,'')"  required/>
                                            </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">夺宝类型</label>
                                            <div class="col-md-3">
                                               <select id="duobao_type" name="duobao_type" class="form-control input-lg" size="1" required>
													<option value="1">1元夺宝</option>
													<option value="10">10元夺宝</option>
													<option value="100">100元夺宝</option>
												</select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">标签</label>
                                            <div class="col-md-3">
                                              		<input type="checkbox" id="is_new" name="is_new" par="tag" value="1"> 
													<label for="is_new">新</label>
													<input type="checkbox" id="is" name="is_hot" par="tag" value="1"> 
													<label for="is_hot">抢</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">权重</label>
                                            <div class="col-md-3">
                                               <input type="number" id="weight" name="weight" class="form-control" value='0' required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">详情页面</label>
														<div class="form-group">									
															<div class="col-md-7">
															<textarea name="content" style="width:600px;height:400px;visibility:hidden;"></textarea>
															</div>
														</div>
										</div>
                                        <div class="row">
											<div class="col-sm-12">
												<div align="center">
													<button id="addToTable" class="btn btn-info" >保存 <i class="fa fa-save"></i></button>
													<button type="button" class="bk-margin-5 btn btn-danger" onclick="proListPage()">返&nbsp;回&nbsp;<i class="fa fa-reply"></i></button>
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
<script src="assets/kindeditor/kindeditor-min.js"></script> 
<script src="assets/kindeditor/lang/zh_CN.js"></script> 
        <script type="text/javascript">
        
        $("input[name='cate_id']").live("click",function(){
        	if($("input[name='cate_id']:checked").length>3){
        		$(this).removeAttr("checked");
        		alert("最多选三个！");
        	}
        });
        
        $("input[par='tag']").live("click",function(){
        	if($("input[par='tag']:checked").length>1){
        		$(this).removeAttr("checked");
        		alert("最多选1个！");
        	}
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
            
            function proListPage(){
            	location="productlist.html";
            }
        </script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>