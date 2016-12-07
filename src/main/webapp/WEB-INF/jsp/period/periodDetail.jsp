<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <jsp:useBean id="now" class="java.util.Date" /> 
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
					<div class="invoice">
						<div class="row header">
							<div class="col-sm-6">					
								<div class="panel panel-body bk-bg-white">
									<h5><em>期次详情:</em></h5>
									<p><strong>期次：<c:out value="${period.id}"/></strong></p>
									<p>商品：<c:out value="${period.title}"/></p>											
									<p>商品图：<img style="width:200px;height:200px;"src=<c:out value="${period.list_img}"/>></img>
									<p>揭晓时间：<fmt:formatDate value="${period.award_time}" pattern="yyyy-MM-dd  HH:mm:ss" /></p>
								</div>							
							</div>
							<div class="col-sm-4">								
								<div class="panel panel-body bk-bg-white">
									<h5><em>中奖者信息:</em></h5>
									<p><img style="width:100px;height:100px;"src=<c:out value="${user.portrait}"/>></img>
									<p><strong>昵称：<c:out value="${user.nick}"/></strong></p>
									<p>联系方式：<c:out value="${user.mobile}"/></p>	
									<p>注册时间：<fmt:formatDate value="${user.gmt_create}" pattern="yyyy-MM-dd  HH:mm:ss" /></p>	
									<p>中奖号码：<c:out value="${period.lucky_code}"/></p>	
									<p>购买份数：<c:out value="${period.lucky_buy_count}"/></p>
									<p>订单号：<c:out value="${period.lucky_order_id}"/></p>
																							
								</div>						
							</div>
						</div>	
					</div>
					
					<div class="timeline">
							<div class="tm-body" >
								<div class="tm-title">
									<h3 class="h5 text-uppercase">期次开始:<fmt:formatDate value="${period.gmt_create}" pattern="yyyy-MM-dd  HH:mm:ss" /></h3>
								</div>
								<ol class="tm-items">
									<li>
										<div class="tm-info">
											<div class="tm-icon"><i class="fa fa-star"></i></div>
											<time class="tm-datetime" datetime="2015-03-27 09:34">
												<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5">开奖<fmt:formatDate value="${period.award_time}" pattern="yyyy-MM-dd" /></div>
												<div class="tm-datetime-time"><fmt:formatDate value="${period.award_time}" pattern="HH:mm:ss" /></div>
											</time>
										</div>
										<div class="tm-box">
											<p>
												<img style="width:50px;height:50px;"src=<c:out value="${user.portrait}"/>></img>中奖者：<c:out value="${user.nick}"/>，手机号：<c:out value="${user.mobile}"/>，中奖号码：<c:out value="${period.lucky_code}"/>
											</p>
										</div>
									</li>
									<!-- 实物开始 -->
									<c:if test="${period.type==0}">
									
									<c:if test="${express.commit_address_time==null || express.status==0}">
									<li>
											<div class="tm-info">
												<div class="tm-icon"><i class="fa fa-thumbs-up"></i></div>
												<time class="tm-datetime" datetime="2015-03-15 08:19">
													<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></div>
												</time>
											</div>
											<div class="tm-box">
												<p>
													<strong>等待用户确认地址</strong>
												</p>
											</div>
										</li>
									</c:if>
										 <c:if test="${express.commit_address_time!=null  && express.status==1}">
										<li>
											<div class="tm-info">
												<div class="tm-icon"><i class="fa fa-thumbs-up"></i></div>
												<time class="tm-datetime" datetime="2015-03-15 08:19">
													<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5"><fmt:formatDate value="${express.commit_address_time}" pattern="yyyy-MM-dd" /></div>
													<div class="tm-datetime-time"><fmt:formatDate value="${express.commit_address_time}" pattern="HH:mm:ss" /></div>
												</time>
											</div>
											<div class="tm-box">
												<p>
													<strong>用户已经确认地址，等待发货！</strong>
												</p>
											</div>
										</li>
										<li>
										<div class="tm-info">
											<div class="tm-icon"><i class="fa fa-map-marker"></i></div>
											<time class="tm-datetime" datetime="2015-02-02 08:27">
												<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5">
												<c:if test="${express.express_time==null}">
													<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
												</c:if>
													<c:if test="${express.express_time!=null}">
													<fmt:formatDate value="${express.express_time}" pattern="yyyy-MM-dd" />
												</c:if>
												</div>
												<div class="tm-datetime-time"><fmt:formatDate value="${express.express_time}" pattern="HH:mm:ss" /></div>
											</time>
										</div>
										<div class="tm-box">
											<p>
												中奖者发货地址信息
											</p>
											<blockquote class="primary">
												<p>收货人：<strong><c:out value="${express.name}"/></strong></p>
												<p>联系方式：<strong><c:out value="${express.mobile}"/></strong></p>
												<p>地址   <strong> <c:out value="${express.province}"/><c:out value="${express.city}"/><c:out value="${express.district}"/><c:out value="${express.address}"/></strong></p>
											</blockquote>
											<form action="expressOrder"  id="editForm" method="post">
												<input type="hidden" name="periodExpressId" value="<c:out value="${express.id}"/>"> 
												<p>物流选择：<select id="express_id" name="express_id" style="width:130px;">
																<c:forEach var="exp" items="${expresslist}">
		                                              		  		<option value="<c:out value="${exp.id}"/>" <c:if test="${exp.id == express.express_id}">selected</c:if>> <c:out value="${exp.title}"/> </option>
		                                              		 	 </c:forEach>
															</select>
												</p>
												<p>快递单号：<input type="text" id="express_code" name="express_code" value="<c:out value="${express.express_code}"/>"></p>
												<c:if test="${express.status==1}">
													 <button type="button" class="bk-margin-5 btn btn-success" onclick="doSubmit();">&nbsp;发货&nbsp;</button>
												</c:if>
											</form>
											<div id="gmap-checkin-example" class="mb-sm" style="height: 80px; width: 100%;"></div>
										</div>
									</li>
									</c:if>
									<c:if test="${express.status==2}">
										<li>
										<div class="tm-info">
											<div class="tm-icon"><i class="fa fa-star"></i></div>
											<time class="tm-datetime" datetime="2015-03-27 09:34">
												<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></div>
											</time>
										</div>
										<div class="tm-box">
											<p>
												中奖者发货地址信息
											</p>
											<blockquote class="primary">
												<p>收货人：<strong><c:out value="${express.name}"/></strong></p>
												<p>联系方式：<strong><c:out value="${express.mobile}"/></strong></p>
												<p>地址   <strong> <c:out value="${express.province}"/><c:out value="${express.city}"/><c:out value="${express.district}"/><c:out value="${express.address}"/></strong></p>
											</blockquote>
											<form action="expressOrder"  id="editForm" method="post">
												<input type="hidden" name="periodExpressId" value="<c:out value="${express.id}"/>"> 
												<p>物流选择：<select id="express_id" name="express_id" style="width:130px;">
																<c:forEach var="exp" items="${expresslist}">
		                                              		  		<option value="<c:out value="${exp.id}"/>" <c:if test="${exp.id==express.express_id}">selected</c:if>>  <c:out value="${exp.title}"/></option>
		                                              		 	 </c:forEach>
															</select>
												</p>
												<p>快递单号：<input type="text" id="express_code" name="express_code" value="<c:out value="${express.express_code}"/>"></p>
											</form>
										
											<p>
												<strong>等待用户签收！</strong>
											</p>
										</div>
									</li>
									</c:if>
									<c:if test="${express.status==3}">
										<li>
										<div class="tm-info">
											<div class="tm-icon"><i class="fa fa-picture-o"></i></div>
											<time class="tm-datetime" datetime="2015-03-27 09:34">
												<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5">用户已签收！</div>
											</time>
										</div>
										<div class="tm-box">
											<p>
												<strong>用户已签收！</strong>
											</p>
										</div>
									</li>
									</c:if>	
								</c:if>
									<!-- 实物结束 -->
									<!-- 充值卡开始 -->
									<c:if test="${period.type==1}">
										<c:if test="${express.commit_address_time==null}">
										<li>
												<div class="tm-info">
													<div class="tm-icon"><i class="fa fa-thumbs-up"></i></div>
													<time class="tm-datetime" datetime="2015-03-15 08:19">
														<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></div>
													</time>
												</div>
												<div class="tm-box">
													<p>
														<strong>等待用户确认手机号</strong>
													</p>
												</div>
											</li>
										</c:if>
										<c:if test="${express.commit_address_time!=null}">
												<li>
													<div class="tm-info">
														<div class="tm-icon"><i class="fa fa-thumbs-up"></i></div>
														<time class="tm-datetime" datetime="2015-03-15 08:19">
															<div class="tm-datetime-date bk-bg-white bk-radius text-center bk-padding-5"><fmt:formatDate value="${express.express_time}" pattern="yyyy-MM-dd" /></div>
															<div class="tm-datetime-time"><fmt:formatDate value="${express.express_time}" pattern="HH:mm:ss" /></div>
														</time>
													</div>
													<div class="tm-box">
														<p>
															<strong>
																<c:if test="${express.status==2}">
																	充值成功！充值号码：<c:out value="${express.mobile}"/>
																</c:if>
																<c:if test="${express.status==3}">
																	充值失败 !充值号码：<c:out value="${express.mobile}"/>，失败原因：<c:out value="${express.remark}"/>
																</c:if>
															</strong>
														</p>
													</div>
												</li>
										</c:if>
									</c:if>
									<!-- 充值卡结束 -->
								</ol>
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
        <script type="text/javascript">
            function doSubmit(){
                $("#editForm").submit();
            }
           
        </script>
		<!-- end: JavaScript-->
		
	</body>
	
</html>