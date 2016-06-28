<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="listArea" style="width:100%;">
	<ul>
		<li>검색어 : <c:out value="${q}" /></li>
		<li>총검색수 : <c:out value="${t}" /></li>
		<li>페이지 : <c:out value="${p}" /></li>
	</ul>
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
					<th scope="col">제목</th>
					<th scope="col">평점</th>
					<th scope="col">개봉일</th>
					<th scope="col">북마크</th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
			<c:when test="${t > 0}">
				<c:forEach var="item" items="${items}" varStatus="status">
				<tr>
					<td><img src="<c:out value="${item.thumbnail[0].content}" />" onerror="this.src='/img/no_images.gif';" width="70" height="100" ></td>
					<td>
						<a href="#" onclick="mv.detail('${status.index}');"><c:out value="${item.title[0].content}" /></a>
					</td>
					<td><c:out value="${item.grades[0].content}" /></td>
					<td><c:out value="${item.open_info[0].content}" /></td>
					<td>
						<a href="javascript:mv.save('${status.index}');">저장</a>
						<form id="save${status.index}" name="save${status.index}" method="post">
	 						<input type="hidden" name="thumbnail" value="<c:out value="${item.thumbnail[0].content}" />" />
							<input type="hidden" name="title" value="<c:out value="${item.title[0].content}" />" />
							<input type="hidden" name="grades" value="<c:out value="${item.grades[0].content}" />" />
							<input type="hidden" name="openInfo" value="<c:out value="${item.open_info[0].content}" />" />
							<input type="hidden" name="titleLink" value="<c:out value="${item.title[0].link}" />" />
						</form>
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
	<c:if test="${p > 1}">
	<span>
		<a href="javascript:mv.search('<c:out value="${q}" />', '<c:out value="${p - 1}" />');">이전</a>
	</span>
	</c:if>
	<c:if test="${t > (p * r)}">
	<span style="text-align:right;">
		<a href="javascript:mv.search('<c:out value="${q}" />', '<c:out value="${p + 1}" />');">다음</a>
	</span>
	</c:if>
</div>

<%-- 상세보기 영역 --%>
<div id="detailArea" style="display:none;">
	<c:forEach var="item" items="${items}" varStatus="status">
	<div id="detail${status.index}">
		<ul>
			<li><img src="<c:out value="${item.thumbnail[0].content}" />" onerror="this.src='/img/no_images.gif';" ></li>
			<li>
				<c:if test="${item.title[0].link ne ''}"><a href="<c:out value="${item.title[0].link}" />" target="_blank"></c:if>
				<c:out value="${item.title[0].content}" />
				<c:if test="${item.title[0].link ne ''}"></a></c:if>
			</li>
			<li><c:out value="${item.eng_title[0].content}" /></li>
			<li><c:out value="${item.grades[0].content}" /></li>
			<li>
				<c:forEach var="genre" items="${item.genre}" varStatus="genre_status">
					<c:if test="${genre.content ne ''}">
						<c:if test="${genre_status.index > 0}">/</c:if>
						<c:out value="${genre.content}" />
					</c:if>
				</c:forEach>
			</li>
			<li>
				<c:forEach var="open_info" items="${item.open_info}" varStatus="open_info_status">
					<c:if test="${open_info.content ne ''}">
						<c:if test="${open_info_status.index > 0}">, </c:if>
						<c:out value="${open_info.content}" />
					</c:if>
				</c:forEach>
			</li>
			<li>감독 : <c:out value="${item.director[0].content}" /></li>
			<li>주연
				<ul>
					<c:forEach var="actor" items="${item.actor}" varStatus="actor_status">
						<li><c:out value="${actor.content}" /></li>
					</c:forEach>
				</ul>
			</li>
			<c:if test="${item.photo1[0].content ne ''}">
			<li><img src="<c:out value="${item.photo1[0].content}" />" onerror="this.src='/img/no_images.gif';"></li>
			</c:if>
			<c:if test="${item.photo2[0].content ne ''}">
			<li><img src="<c:out value="${item.photo2[0].content}" />" onerror="this.src='/img/no_images.gif';"></li>
			</c:if>
			<c:if test="${item.photo3[0].content ne ''}">
			<li><img src="<c:out value="${item.photo3[0].content}" />" onerror="this.src='/img/no_images.gif';"></li>
			</c:if>
			<c:if test="${item.photo4[0].content ne ''}">
			<li><img src="<c:out value="${item.photo4[0].content}" />" onerror="this.src='/img/no_images.gif';"></li>
			</c:if>
			<c:if test="${item.photo5[0].content ne ''}">
			<li><img src="<c:out value="${item.photo5[0].content}" />" onerror="this.src='/img/no_images.gif';"></li>
			</c:if>
			<li><c:out value="${item.story[0].content}" escapeXml="false" /></li>
		</ul>
		<div><a href="#" onclick="mv.backList();">목록보기</a></div>
	</div>
	</c:forEach>
</div>