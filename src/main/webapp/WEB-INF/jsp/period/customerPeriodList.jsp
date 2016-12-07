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
                            <h6><i class="fa fa-hand-o-right"></i><span class="break"></span>用户消费信息</h6>
                            <div class="panel-actions">
                                <a href="#" class="btn-minimize"><i class="fa fa-caret-up"></i></a>
                                <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                        <div class="panel-body">
                         <form action="periodlistByCustomer" id="editForm" method="post">
                        <input type="hidden" id="page" name="page" value='<c:out value="${pager.pageNumber}"/>'/>
                        <div class="row datatables-header form-inline">
                            <div class="col-sm-12 col-md-12">
                                <table class="col-md-12">
                                    <tbody>
                                    <tr>
                                        <td>
                                        	期次：<input type="text" class="form-control" id="periodId" name="periodId" value='<c:out value="${periodId}"/>' style="width:80px;"/>
                                            手机号:<input type="text" class="form-control" id="mobile" name="mobile" value='<c:out value="${mobile}"/>' required/>
                                        </td>
                                        <td align="right">
                                            <button type="submit" class="bk-margin-5 btn btn-success"  >&nbsp;查 询&nbsp;</button>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                    <c:if test="${user!=null}">
                    
                    <div class="panel-body">
	                    <div class="bk-ltr bk-padding-bottom-20 bk-padding-left-20">
	                        <div class="row">
	                            <div class="col-12-4 col-md-6 col-sm-12 col-xs-12 bk-bg-white bk-padding-top-10">
	                                <img src='<c:out value="${user.portrait}"/>' class="img-circle" alt="" width="20px;"/><c:out value="${user.nick}"/>
	                            </div>
	                            <div class="col-12-4 col-md-6 col-sm-12 col-xs-12 bk-bg-white bk-padding-top-10">
	                                <i class="fa fa-jpy"></i> <c:out value="${user.balance}"/>
	                            </div>
	                        </div>
	                        <div class="row">
	                            <div class="col-12-4 col-md-6 col-sm-12 col-xs-12 bk-bg-white bk-padding-top-10">
	                                <i class="fa fa-tablet"></i> <c:out value="${user.mobile}"/>
	                            </div>
	                            <div class="col-12-4 col-md-6 col-sm-12 col-xs-12 bk-bg-white bk-padding-top-10">
	                                <i class="fa fa-calendar"></i> <fmt:formatDate value="${user.gmt_create}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
	                            </div>
	                        </div>
	                    </div>
                	</div>
	                    <div class="col-lg-12 col-md-10 col-sm-6 col-xs-12">
								<div class="tabs">
									<ul class="nav nav-tabs nav-justified">
										<li class="active">
											<a href="#home10" data-toggle="tab" class="text-center"><i class="fa fa-home"></i>夺宝记录</a>
										</li>
										<li>
											<a href="#profile10" data-toggle="tab" class="text-center">充值记录</a>
										</li>
									</ul>
									<div class="tab-content">
										<div id="home10" class="tab-pane active">
											<table class="table table-bordered table-striped" id="datatable-default">
                        <thead>
                        <tr>
                            <th>期次</th>
                            <th>夺宝记录</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${periodMap}" var="item" varStatus="status">
                            <tr class="gradeX">
                                <td class="col-md-5 col-sm-12">
                                    <table class="table mb-none" >
                                        <tbody>
                                        <tr>
                                            <td>商品</td>
                                            <td ><img src='<c:out value="${item.value.list_img}"/>' alt="" style="width:50px;height:30px;"/>
                                            <c:choose>  
												    <c:when test="${fn:length(item.value.title) > 25}">  
												        <c:out value="${fn:substring(item.value.title, 0, 25)}..." />  
												    </c:when>  
												   <c:otherwise>  
												      <c:out value="${item.value.title}" />  
												    </c:otherwise>  
												</c:choose>
                                        </tr>
                                        <tr>
                                            <td>期次</td>
                                            <td ><c:out value="${item.value.id}"/></td>
                                        </tr>
                                        <tr>
                                            <td>开始时间</td>
                                            <td><fmt:formatDate value="${item.value.start_time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                        </tr>
                                        <tr>
                                            <td>揭晓时间</td>
                                            <td>
                                                <c:if test="${item.value.status != 2}">未揭晓</c:if>
                                                <c:if test="${item.value.status == 2}"><fmt:formatDate value="${item.value.start_time}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>(幸运号:<c:out value="${item.value.lucky_code}"/>)</c:if>
                                            </td>
                                        </tr>
                                        <c:if test="${item.value.status == 2}">
                                        <tr>
                                            <td>幸运号码</td>
                                            <td>
                                            <c:out value="${item.value.lucky_code}"></c:out>
                                            </td>
                                        </tr>
                                        </c:if>
                                        </tbody>
                                    </table>
                                </td>
                                <td class="col-md-7 col-sm-12">
                                    <c:forEach var="userDuobaoRecord" items="${userDuobaoMap[item.value.id]}" >
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td class="col-md-3">购买时间</td>
                                                <td><fmt:formatDate value="${userDuobaoRecord.gmt_create}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                            </tr>
                                            <tr>
                                                <td>购买份数</td>
                                                <td><c:out value="${userDuobaoRecord.buy_count}"/></td>
                                            </tr>
                                            <tr>
                                                <td>是否中奖</td>
                                                <td >
                                                    <c:if test="${userDuobaoRecord.lucky == 0}"><i class="fa fa-times"></i>否</c:if>
                                                    <c:if test="${userDuobaoRecord.lucky == 1}"><i class="fa fa-check"></i>是</c:if>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>夺宝号码</td>
                                                <td   id="duobao_${status.index}" >
                                                <a href="#" onclick="javascript:doLayer('duobao_${status.index}','${item.value.id}','${userDuobaoRecord.user_id}')">夺宝号码</a>
                                                </td>
                                            </tr>
                                             <c:if test="${userDuobaoRecord.lucky == 1}">
                                            <!-- 实物 -->
                                            <c:if test="${item.value.type==0}">
                                            <tr>
                                           	 <td>订单状态</td>
                                           	 <td>
                                            	<c:if test="${orderMap[item.value.id].status==0}">
                                            		<p style="color:red;">等待用户确认地址</p>
                                            	</c:if>
                                            	<c:if test="${orderMap[item.value.id].status==1}">
                                            		<p style="color:red;">待发货&nbsp;&nbsp;&nbsp;&nbsp;
                                            		<a href="javascript:void(0)" id="addToTable" class="btn btn-info" onclick="mangerlog(${orderMap[item.value.id].id},0)">重新确认地址</a>
                                            		&nbsp;&nbsp;&nbsp;&nbsp; <font color="green">确认地址时间：<c:out value="${orderMap[item.value.id].commit_address_time}"></c:out></font>
                                            		</p>
                                            	</c:if>
                                            	<c:if test="${orderMap[item.value.id].status==2}">
                                            	<p style="color:green;">已发货 &nbsp;&nbsp;&nbsp;&nbsp; 发货时间：<c:out value="${orderMap[item.value.id].express_time}"></c:out></p>
                                            	</c:if>
                                            	</td>
                                            </tr>
                                            </c:if>
                                            <!-- 充值卡 -->
                                            <c:if test="${item.value.type==1}">
                                            <tr>
                                            <td>订单状态</td>
                                           	 <td>
                                            	<c:if test="${orderMap[item.value.id].rechargeStatus==null}">
                                            		<p style="color:red;">等待用户确认手机号</p>
                                            	</c:if>
                                            	<c:if test="${orderMap[item.value.id].rechargeStatus==1 || orderMap[item.value.id].rechargeStatus==-1}">
                                            		<p style="color:green;">充值中</p>
                                            	</c:if>
                                            	<c:if test="${orderMap[item.value.id].rechargeStatus==2}">
                                            		<p style="color:green;">充值成功</p>
                                            	</c:if>
                                            	<c:if test="${orderMap[item.value.id].rechargeStatus==-9}">
                                            		<p style="color:red;">无法充值 &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" id="addToTable" class="btn btn-info" onclick="mangerlog(${orderMap[item.value.id].id},1)">重新确认手机号</a></p>
                                            		
                                            	</c:if>
                                            	</td>
                                            	</tr>
                                            </c:if>
                                            </c:if>
                                        </tbody>
                                    </table>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
										</div>
										<div id="profile10" class="tab-pane">
											 <table class="table table-bordered table-striped" id="datatable-default">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>充值时间</th>
                            <th>金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="userAccountRecord" items="${userAccountRecordList}" varStatus="loop">
                            <tr class="gradeX">
                                <td><c:out value="${loop.index + 1}"/></td>
                                <td><fmt:formatDate value="${userAccountRecord.gmt_create}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><i class="fa fa-rmb"></i><fmt:formatNumber value="${userAccountRecord.amount}" pattern="#.00"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
										</div>
									</div>
								</div>
							</div>
						</c:if>
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
<script src="assets/js/pages/form-validation.js"></script>
<script src="assets/layer/layer.js"></script>
<!-- end: JavaScript-->
<script type="text/javascript">
</script>
<script type="text/javascript">
	function doLayer(id,periodid,userid) { 
		layer.open({
	  		  type: 2,
	  		  title: '用户夺宝号码',
	  		  shadeClose: true,
	  		  shade: 0.8,
	  		  area: ['380px', '50%'],
	  		  content: '/getBuyRecord.html?id='+periodid+'&userId='+userid 
  			}); 
		    } 
	
	function mangerlog(expressId,type){
		var mobile = $("#mobile").val();
		layer.prompt({
			  title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;亲,你的每一步操作都要仔细斟酌哦',
			  formType: 2 //prompt风格，支持0-2
			  ,shift: 4
			}, function(pass){
				if(pass==null || pass==''){
					layer.msg('请填写操作内容');
				}else{
   			    	 $.get("/customerLog.html?expressId="+expressId+"&mobile="+mobile+"&remark="+pass+"&type="+type,function(text){
   			    		 var json_text=JSON.parse(text);
		    			    if(json_text.code==0){
		    			    	layer.msg('操作成功!');
		    			    	location="/periodlistByCustomer.html?mobile="+mobile+"&periodId="+json_text.period_id;	
		    			    	}
		    			   else{
	    			    	alert("操作失败");
	    			    	return false;
	    			    }
   			 		 });
				}
			});

	}
	function perTips(id,remark,time){
		var tips = "操作日志："+remark+",操作时间:"+time;
		layer.tips(tips, '#' + id, {
	    	  tips: [1, '#3595CC'],
	    	  time: 3000
	    	});
	}
	
	

	
</script>
</body>

</html>