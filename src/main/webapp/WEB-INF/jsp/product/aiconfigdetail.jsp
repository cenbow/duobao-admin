<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
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
									<h6><i class="fa fa-hand-o-right"></i><span class="break"></span>添加商品AI干预</h6>							
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
									<form action="smartAiEdit.html"  class="form-horizontal form-bordered " id="editForm" method="post" >
									<input type="hidden" id="proid" name="proid" value="<c:out value="${product.id}"/>">
									<input type="hidden" id="ai_args_id" name="ai_args_id" value='<c:out value="${smartAi.id}"></c:out>' />
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">商品名称</label>
                                            <div class="col-md-3">
                                               <input type="text" id="title" name="title" class="form-control" value='<c:out value="${product.title}"/>' readonly/>
                                            </div>
                                        </div>
                                        <div class="form-group">
											<label class="col-md-3 control-label" for="select">类型</label>
											<div class="col-md-3">
												<select id="type" name="type" class="form-control input-lg" size="1" >
													<option value="0" <c:if test="${product.type==0}">selected</c:if> >实物</option>
													<option value="1" <c:if test="${product.type==1}">selected</c:if>>充值卡</option>
												</select>
											</div>
										</div>
										<div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">总分数</label>
                                            <div class="col-md-3">
                                               <input type="text" id="total_count" name="total_count" class="form-control" value='<c:out value="${product.total_count}"/>' readonly/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">列表图</label>
                                            <div class="col-md-3">
                                               <img src="<c:out value="${product.list_img}"/>" style="width:200px;height:200px;"/>
                                            </div>
                                        </div>
                                       
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">副标题</label>
                                            <div class="col-md-3">
                                               <input type="text" id="sub_title" name="sub_title" class="form-control" value='<c:out value="${product.sub_title}"></c:out>' readonly/>
                                            </div>
                                        </div>
                                       <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">干预概率</label>
                                            <div class="col-md-3">
                                               <input type="text" id="hit_rate" name="hit_rate" class="form-control" value='<c:out value="${smartAi.hit_rate}"></c:out>' required/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">干预数据JSON</label>
                                            <div class="col-md-3">
                                               <input type="text" id="ai_args" name="ai_args" class="form-control" value='<c:out value="${smartAi.ai_args}"></c:out>' />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">预计时间</label>
                                            <div class="col-md-3">
                                               <input type="text" id="expectTime"  class="form-control" value='<c:out value="${time}"></c:out>'/>
                                            </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">示例</label>
														<div class="form-group">	
															<div class="col-md-2" >
															<a href="javascript:void(0);"  onclick="toggle()" class="btn btn-info" >显示系统默认配置</a>
															</div>								
															</div>
										</div>
										<div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark"></label>
                                            <div class="form-group">
                                           			 <div class="col-md-3" id="shili">
														<textarea name="content" id="content" rows="72" class="form-control" readonly> <c:out value="${config}"></c:out></textarea>
													   </div>
											</div>
                                        </div>
                                        <div class="row">
											<div class="col-sm-12">
												<div align="center">
													<a href="javascript:void(0);"  onclick="expect()" class="btn btn-info" >预测</a>
													<button id="addToTable" class="btn btn-info" disabled="disabled" >保存 <i class="fa fa-save"></i></button>
													<button type="button" class="bk-margin-5 btn btn-danger" onclick="proAiListPage()">返&nbsp;回&nbsp;<i class="fa fa-reply"></i></button>
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
<script src="assets/kindeditor/kindeditor-min.js"></script> 
<script src="assets/kindeditor/lang/zh_CN.js"></script> 
		<!-- end: JavaScript-->
		<script type="text/javascript">
		$(function(){
			$("#shili").hide();
	        var json=  document.editForm.content.value;
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
		        document.editForm.content.value=targetJson;
		        return;
	 		}
	       
		});
		
		
	     function toggle(){ 
			$("#shili").toggle(500);
			 
		} 
		
		
		function expect(){
			var product=$("#proid").val();
			var ai_args=$("#ai_args").val();
			var hit_rate=$("#hit_rate").val();
			if(hit_rate==null || hit_rate==''){
				alert("请填写干预概率");
				return false;
			}
				 $.get("/getExpectTime.html?productId="+product+"&hitRate="+hit_rate+"&hitArgs="+ai_args,function(data){
	 			    var json=JSON.parse(data);
	 			    if(json.code==0){
	 			    	$("#expectTime").val(json.time);
	 			    	$("#addToTable").removeAttr("disabled");
	 			    }else{
	 			    	alert("请确认JSON");
	 			    	return false;
	 			    }
	 			  });
		}
		
		function proAiListPage(){
			location="productAilist.html";
		}
		</script>
	</body>
	
</html>