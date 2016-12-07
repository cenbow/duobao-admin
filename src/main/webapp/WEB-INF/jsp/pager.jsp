<%--
  Created by IntelliJ IDEA.
  User: ouwa
  Date: 16/6/28
  Time: 下午11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--
1、需要有一个用于查询且element id为editForm的表单
2、editForm表单里面有一个element id为page的hidden input，用于存储当前页数或需要跳转的页数
3、request的attributes里面需要有一个名为pager的对象，类型是org.nutz.dao.pager.Pager
--%>
<c:if test="${pager.pageCount > 1}">
    <div class="row datatables-footer">
        <div class="col-sm-12 col-md-6">
            <div class="dataTables_info" id="datatable-default_info" role="status" aria-live="polite">
                显示 <c:out value="${(pager.pageNumber - 1) * pageSize + 1}"/>
                到
                <c:choose>
                    <c:when test="${pager.pageNumber < pager.pageCount}">
                        <c:out value="${pager.pageNumber * pager.pageSize}"/>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${pager.recordCount}"/>
                    </c:otherwise>
                </c:choose>,
                共 <c:out value="${pager.recordCount}"/> 条记录
            </div>
        </div>
        <div class="col-sm-12 col-md-6">
            <div class="dataTables_paginate paging_bs_normal" id="datatable-default_paginate">
                <ul class="pagination">
                    <li class='prev <c:if test="${pager.pageNumber <= 1}">disabled</c:if>'><a href="#" onclick='toPage(<c:out value="${pager.pageNumber - 1}"/>)'><span class="fa fa-chevron-left"></span></a></li>
                    <c:if test="${(pager.pageNumber - 5) > 0}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber - 5}"/>)'><c:out value="${pager.pageNumber - 5}"/></a></li></c:if>
                    <c:if test="${(pager.pageNumber - 4) > 0}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber - 4}"/>)'><c:out value="${pager.pageNumber - 4}"/></a></li></c:if>
                    <c:if test="${(pager.pageNumber - 3) > 0}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber - 3}"/>)'><c:out value="${pager.pageNumber - 3}"/></a></li></c:if>
                    <c:if test="${(pager.pageNumber - 2) > 0}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber - 2}"/>)'><c:out value="${pager.pageNumber - 2}"/></a></li></c:if>
                    <c:if test="${(pager.pageNumber - 1) > 0}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber - 1}"/>)'><c:out value="${pager.pageNumber - 1}"/></a></li></c:if>
                    <li class="active"><a href="#"><c:out value="${pager.pageNumber}"/></a></li>
                    <c:if test="${(pager.pageNumber + 1) <= pager.pageCount}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber + 1}"/>)'><c:out value="${pager.pageNumber + 1}"/></a></li></c:if>
                    <c:if test="${(pager.pageNumber + 2) <= pager.pageCount}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber + 2}"/>)'><c:out value="${pager.pageNumber + 2}"/></a></li></c:if>
                     <c:if test="${(pager.pageNumber + 3) <= pager.pageCount}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber + 3}"/>)'><c:out value="${pager.pageNumber + 3}"/></a></li></c:if>
                     <c:if test="${(pager.pageNumber + 4) <= pager.pageCount}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber + 4}"/>)'><c:out value="${pager.pageNumber + 4}"/></a></li></c:if>
                     <c:if test="${(pager.pageNumber + 5) <= pager.pageCount}"><li><a href="#" onclick='toPage(<c:out value="${pager.pageNumber + 5}"/>)'><c:out value="${pager.pageNumber + 5}"/></a></li></c:if>
                    <li class='next <c:if test="${pager.pageNumber >= pager.pageCount}">disabled</c:if>'><a href="#" onclick='toPage(<c:out value="${pager.pageNumber + 1}"/>)'><span class="fa fa-chevron-right"></span></a></li>
                </ul>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function toPage(page){
        	var Number =${pager.pageNumber};
        	var Count =${pager.pageCount} ;
        	if(Number>=Count  && page>Number){
        		return false;
        	}
        	if(page<=0){
        		return false;
        	}
            $("#page").val(page);
            $("#editForm").submit();
        }
    </script>
</c:if>
<c:if test="${pager.pageCount <= 1}">
    <div class="row datatables-footer">
        <div class="col-sm-12 col-md-6">
            <div class="dataTables_info" id="datatable-default_info" role="status" aria-live="polite">
                没有更多记录
            </div>
        </div>
    </div>
</c:if>