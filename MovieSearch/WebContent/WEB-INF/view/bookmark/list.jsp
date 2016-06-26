<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	나의 북마크
</div>

<div>
	<table summary="정보를 제공합니다." border="1" style="width:100%;">
		<caption>영화목록</caption>
		<colgroup>
			<col width="72" />
			<col />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">섬네일</th>
				<th scope="col"><a href="javascript:ssm.bookmark(0, 'ASC', 'title');">▲</a>제목<a href="javascript:ssm.bookmark(0, 'DESC', 'title');">▼</a></th>
				<th scope="col"><a href="javascript:ssm.bookmark(0, 'ASC', 'grades');">▲</a>평점<a href="javascript:ssm.bookmark(0, 'DESC', 'grades');">▼</a></th>
				<th scope="col"><a href="javascript:ssm.bookmark(0, 'ASC', 'openInfo');">▲</a>개봉일<a href="javascript:ssm.bookmark(0, 'DESC', 'openInfo');">▼</a></th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${list.totalElements > 0}">
			<c:forEach var="record" items="${list.content}" varStatus="status">
			<tr>
				<td><img src="<c:out value="${record.thumbnail}" />" onerror="this.src='/img/no_images.gif';" width="70" height="100" ></td>
				<td>
					<a href="<c:out value="${record.titleLink}" />" target="_blank"><c:out value="${record.title}" /></a>
				</td>
				<td><c:out value="${record.grades}" /></td>
				<td><c:out value="${record.openInfo}" /></td>
				<td>
					<a href="javascript:ssm.remove('${record.seq}');">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="5" style="text-align:center;">정보가 없습니다.</td>
			</tr>
		</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</div>
<div>
	<c:if test="${list.first == false}">
	<span>
		<a href="javascript:ssm.bookmark('<c:out value="${list.number - 1}" />', '${o}', '${s}');">이전</a>
	</span>
	</c:if>
	<c:if test="${list.last == false}">
	<span style="text-align:right;">
		<a href="javascript:ssm.bookmark('<c:out value="${list.number + 1}" />', '${o}', '${s}');" style="text-align:right;">다음</a>
	</span>
	</c:if>
</div>
