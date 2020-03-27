<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding= "UTF-8" %>
<%
	pageContext.setAttribute("newLine","\n");
 %> 

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${ pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${ pageContext.request.contextPath }/guestbook/add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="name" name="name" required="required"></td>
							<td>비밀번호</td><td><input type="password" name="password" required="required"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content" required="required"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
		
			<c:set  var="count" value="${fn:length(list)}"/>
			<c:forEach items='${list}' var='vo' varStatus='status' >
			<form action="${ pageContext.request.contextPath }/guestbook/delete" method="GET">
			<input type=hidden name="no" value="${vo.no }">
				<ul>
					<li>
						<table>
							<tr>
								<td>${count-status.index }</td>
								<td>${vo.name }</td>
								<td>${vo.regDate}</td>
								<td><input type="submit" VALUE="삭제 "></td>
							</tr>
							<tr>
								<td colspan=4>
								
								${fn:replace(vo.contents,newLine,"<br>") }
								</td>
							</tr>
						</table>
						<br>
					</li>
				</ul>
			</form>
			
			
			
			</c:forEach>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
	<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>