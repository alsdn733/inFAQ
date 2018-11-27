<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css"
	integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B"
	crossorigin="anonymous">
<link href="<%=request.getContextPath()%>/css/dialog.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.12.1.js"></script>
<script>
	function CustomPrompt() {
		this.render = function(dialog,no) {
			var winW = window.innerWidth;
			var winH = window.innerHeight;
			var dialogoverlay = document.getElementById('dialogoverlay');
			var dialogbox = document.getElementById('dialogbox');
			dialogoverlay.style.display = "block";
			dialogoverlay.style.height = winH + "px";
			dialogbox.style.left = (winW/2) - (400 * 0.5) + "px";
			dialogbox.style.top = (winH/2) - (202 * 0.5) + "px";
			dialogbox.style.display = "block";
			document.getElementById('dialogboxhead').innerHTML = "inSight 2.0 FAQ";
			document.getElementById('dialogboxbody').innerHTML = dialog;
			document.getElementById('dialogboxbody').innerHTML += '<br><br><input id="prompt_value" class="prompt_style">';
			document.getElementById('dialogboxfoot').innerHTML = '<button type="button" class="dialog_ok" onclick="Prompt.ok(\''+no+'\')">확인</button> <button type="button" class="dialog_cancel" onclick="Prompt.cancel()">취소</button> ';
			setTimeout(function(){prompt_value.focus();}, 1);   // prompt에 fous
		}
		this.ok = function(no) {
			console.log(no);
			var company = document.getElementById('prompt_value').value;
			if(company!=''){
				$.ajax({
		            url: "hitUpdate",
		            type: "get",
		            data: {
		              noParam : no,
		              companyParam : company,
		            }
		          }).done(function(data) {
		            location.reload();
		          });
				document.getElementById('dialogbox').style.display = "none";
				document.getElementById('dialogoverlay').style.display = "none";
				console.log("추천되었습니다.");
			}else {
				var message = "회사가 입력되지 않았습니다.";
				document.getElementById('dialogboxfoot').innerHTML += message;
				setTimeout(function(){prompt_value.focus();}, 1);   // prompt에 fous
				console.log("회사가 입력되지 않았습니다.");
			}
			
		}
		
		this.cancel = function() {
			document.getElementById('dialogbox').style.display = "none";
			document.getElementById('dialogoverlay').style.display = "none";
		}
		
	}
	var Prompt = new CustomPrompt();
	
	function changeText(val){
		document.getElementById('status').innerHTML = val;
	}
</script>
</head>
<body>
	<div id="dialogoverlay"></div>
	<div id="dialogbox">
		<div>
			<div id="dialogboxhead"></div>
			<div id="dialogboxbody"></div>
			<div id="dialogboxfoot"></div>
		</div>
	</div>
	<div align="center">
		<a href="<c:url value="/" />" class="badge badge-secondary">일반 사이트</a>
		<a href="<c:url value="/admin" />" class="badge badge-secondary">관리자
			사이트</a> <a href="<c:url value="/client" />" class="badge badge-secondary">고객사
			검색</a>
	</div>
	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">Insight 2.0 FAQ</h1>
			<p>
			<form class="navbar-form pull-left" method="get"
				action="<c:url value="/newBoard"/>">
				<!-- action 을 비워놔야 자신을 가리킨다 -->
				<input type="text" name="search_word" class="form-control"
					placeholder="검색어를 입력 후 enter를 누르세요" autofocus>
				<c:if test="${search_word != ''}">
					<p>
						입력한 검색어: <span id="txtHint">${ search_word }</span>
					</p>
					<button class="btn btn-info" onclick="window.location.href='/'">검색어
						초기화</button>
				</c:if>
				<br /> <br> <span style=""> <input type="button"
					class="btn btn-primary" value="글쓰기"
					onClick="location.href='<c:url value="/newBoard"/> '">
					</span> <br />					
			</form>
			</p>
		</div>
	</section>
	<div class="container">
		<table class="table" border="1" cellspacing="0">
			<thead class="thead-dark">
				<tr>
					<th scople="col" width="30%">질문</th>
					<th scople="col" width="40%">답변</th>
					<th scople="col" width="6%">문의 수</th>
					<th scople="col" width="1%">추천</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="faqContent" items="${ faqContent }"
					varStatus="status">
					<tr>
						<td>${ faqContent.title }</td>
						<td>${ faqContent.content }</td>
						<td>${ faqContent.hit }</td>
						<td><button type="button" class="btn btn-primary btn-xs"
								onClick="Prompt.render('질문 및 요청한 회사를 입력해주세요. \n향후 데이터 통계에 도움이 됩니다 :)',${faqContent.no})">추천</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<a style="display: scroll; position: fixed; bottom: 80px; right: 30px;"
		rel="nofollow" href="#"><img src="top.png"></a>

	<a style="display: scroll; position: fixed; bottom: 40px; right: 30px;"
		rel="nofollow" href="#scrollbottom"><img src="down.png"></a>
	<div id="scrollbottom"></div>
</body>
</html>
