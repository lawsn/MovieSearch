<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="margin-top:20px;">
	나의 북마크
</div>

<div>
	<table summary="나의 북마크의 섬네일, 제목, 평점, 개봉일, 등록일, 삭제를 보여줍니다." border="1" style="width:100%;">
		<caption>영화목록</caption>
		<colgroup>
			<col width="72" />
			<col />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">섬네일</th>
				<th scope="col"><a href="javascript:mv.bookmark(0, 'ASC', 'title');">▲</a>제목<a href="javascript:mv.bookmark(0, 'DESC', 'title');">▼</a></th>
				<th scope="col"><a href="javascript:mv.bookmark(0, 'ASC', 'grades');">▲</a>평점<a href="javascript:mv.bookmark(0, 'DESC', 'grades');">▼</a></th>
				<th scope="col"><a href="javascript:mv.bookmark(0, 'ASC', 'openInfo');">▲</a>개봉일<a href="javascript:mv.bookmark(0, 'DESC', 'openInfo');">▼</a></th>
				<th scope="col"><a href="javascript:mv.bookmark(0, 'ASC', 'createTime');">▲</a>등록일<a href="javascript:mv.bookmark(0, 'DESC', 'createTime');">▼</a></th>
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
				<td><c:out value="${record.createDate}" /></td>
				<td>
					<a href="javascript:mv.remove('${record.seq}');">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="6" style="text-align:center;">저장한 북마크가 없습니다.</td>
			</tr>
		</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</div>

<%-- 이전,다음 페이징 처리 영역 --%>
<div>
	<c:if test="${list.first == false}">
	<span>
		<a href="javascript:mv.bookmark('<c:out value="${list.number - 1}" />', '${o}', '${s}');">이전</a>
	</span>
	</c:if>
	<c:if test="${list.last == false}">
	<span style="text-align:right;">
		<a href="javascript:mv.bookmark('<c:out value="${list.number + 1}" />', '${o}', '${s}');" style="text-align:right;">다음</a>
	</span>
	</c:if>
</div>
