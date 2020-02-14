<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newLine","\n");
 %>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?a=search&num=0" method="post">
					<select name ="option">
						<option value="false">글쓴이 </option>
						<option value="true">제목</option>
					</select>
					<input type="text" id="kwd" name="kwd" value="${kwd }"> 
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>

					<c:set var="count" value="${fn:length(boardlist)}" />
					<c:set var="st" value="${(param.num)*10}"/>
				


					<c:forEach items='${boardlist}' var='vo' begin='${st}'
						end='${st +9}' step='1' varStatus='status'>


						<c:choose>
							<c:when
								test="${((vo.title eq '(삭제 된 글입니다.)')&&( empty vo.contents ) )}">
								<tr>
									<td>${count-status.index }</td>
									<c:choose>
										<c:when test="${vo.depth>0}">
											<td style="text-align:left ;padding-left: ${ 30 *vo.depth}px"><img
												src='/mysite02/assets/images/reply.png'> (삭제 된 글입니다.)</td>
										</c:when>

										<c:otherwise>
											<td style="text-align:left ;padding-left: ${ 30 *vo.depth}px">
												(삭제 된 글입니다.)
											</td>

										</c:otherwise>
									</c:choose>

									<td></td>
									<td></td>
									<td></td>

									
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${count-status.index }</td>
									<c:choose>
										<c:when test="${vo.depth>0}">
											<td style="text-align:left ;padding-left: ${ 30 *vo.depth}px"><img
												src='/mysite02/assets/images/reply.png'> <a
												href="${pageContext.servletContext.contextPath }/board?a=viewform&name=${vo.userName }&no=${vo.no}">${vo.title }</a></td>
										</c:when>

										<c:otherwise>
											<td style="text-align:left ;padding-left: ${ 30 *vo.depth}px">
												<a
												href="${pageContext.servletContext.contextPath }/board?a=viewform&name=${vo.userName }&no=${vo.no}">${vo.title }</a>
											</td>

										</c:otherwise>
									</c:choose>

									<td>${vo.userName}</td>
									<td>${vo.hit }</td>
									<td>${vo.regDate }</td>

									<c:if test="${vo.userName eq authUser.name }">
										<td><a
											href="${pageContext.servletContext.contextPath }/board?a=delete&no=${vo.no}"
											class="del">삭제</a></td>
									</c:if>
								</tr>
							</c:otherwise>
						</c:choose>


					</c:forEach>



				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<c:set var="num" value="${ param.num }" />
					
				<c:choose>
					<c:when test="${ count%10==0 }">
						<c:set var="limit" value="${(count/10)-1}" />
					</c:when>
					<c:otherwise>
						<c:set var="limit" value="${(count/10)}" />
					</c:otherwise>	
				</c:choose>	
				
					<c:choose>
					<c:when test="${ (not empty option)&&(not empty kwd) }">
						<c:set var="actionnum" value="search" />
					</c:when>
					<c:otherwise>
						<c:set var="actionnum" value="list" />
					</c:otherwise>	
				</c:choose>	
					
				
					<ul>
						<c:choose>
							<c:when test="${(num-1)>=0 }">
								<li><a href="${pageContext.request.contextPath}/board?a=${actionnum}&num=${num-1}&kwd=${kwd}&option=${option}">◀</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="" style="font-weight:bold; color:#ddd" >◀</a></li>
							</c:otherwise>
						</c:choose>
						
						<fmt:parseNumber var='s' integerOnly='true' type='number' value='${(num/5)}'></fmt:parseNumber>
					
						<c:forEach begin='${ s*5 }' end='${ (s*5)+4 }' var='k' step='1'>

							<c:choose>
								<c:when test="${ k<limit }">
									<c:choose>
										<c:when test="${  k==num }">
											<li class="selected"><a style="color: red"
												href="${pageContext.request.contextPath}/board?a=${actionnum}&num=${k}&kwd=${kwd}&option=${option}">${ k+1 }</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/board?a=${actionnum}&num=${k}&kwd=${kwd}&option=${option}">${ k+1 }</a></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<li><a href="" style="font-weight:bold; color:#ddd">${k+1}</a></li>
								</c:otherwise>

							</c:choose>

						</c:forEach>
						<c:choose>
							<c:when test="${(num+1)<limit }">
								
									<li><a href="${pageContext.request.contextPath}/board?a=${actionnum}&num=${num+1}&kwd=${kwd}&option=${option}">▶</a></li>
								
							</c:when>
							<c:otherwise>
								<li><a href="" style="font-weight:bold; color:#ddd" >▶</a></li>
							</c:otherwise>
						</c:choose>
						
					</ul>



				</div>
				<!-- pager 추가 -->
				<c:choose>
					<c:when test="${not empty authUser}">
						<div class="bottom">
							<a
								href="${pageContext.servletContext.contextPath }/board?a=writeform&no=1&truename=false"
								id="new-book">글쓰기</a>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>