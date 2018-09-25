<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-1.12.1.js"></script>
</head>
<body>
	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">inSight 2.0 FAQ</h1>
			<p></p>
		</div>
	</section>
	<div class="container">
		<form method="post" action="<c:url value="/editContent/${ faqContent[0].no }"/>">
				질문
				<textarea class="form-control" name="title" cols="50" rows="4">${ faqContent[0].title } </textarea>
				<br>답변
				<textarea class="form-control" name="content" cols="50" rows="10">${ faqContent[0].content }</textarea>
				<br>
				<input type="submit" style="float: right;" class="btn btn-primary" value="수정" onClick="alert('수정되었습니다.')">
		</form>
	</div>
</body>
</html>
