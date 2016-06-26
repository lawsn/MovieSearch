<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery-3.0.0.min.js"></script>
<script type="text/javascript" src="/js/ssm.js"></script>
<title>다음영화검색</title>
</head>
<body>
	<h1>Hello, MOVIE!</h1>
	<div>
		영화제목 검색 <input type="text" id="q" name="q" />
		<input type="button" value="검색" onclick="ssm.search(document.getElementById('q').value, 1);" />
		<input type="button" value="검색2" onclick="ssm.search2(document.getElementById('q').value, 1);" />
		<input type="button" value="북마크" onclick="ssm.bookmark(0, 'DESC', 'seq');" />
	</div>
	<div id="contentArea" style="display:none;"></div>
</body>
</html>