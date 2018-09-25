<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" session="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.12.1.js"></script>
  </head>
  <body>
    <script>
    	function updateHit(no) {
        var company = prompt("질문 및 요청한 회사를 입력해주세요. \n향후 데이터 통계에 도움이 됩니다 :)","");
	        if(company!=null){
	          $.ajax({
	            url: "hitUpdate",
	            type: "get",
	            data: {
	              noParam : no,
	              companyParam : company
	            }
	          }).done(function(data) {
	            location.reload();
	          });
	        }
    	}
    </script>

    <center><a href="<c:url value="/admin" />" class="badge badge-secondary">관리자 사이트</a>  <a href="<c:url value="/client" />" class="badge badge-secondary">고객사 검색</a></center>
    <section class="jumbotron text-center">
        <div class="container">
          <h1 class="jumbotron-heading">Insight 2.0 FAQ</h1>
          <p>
            <form class="navbar-form pull-left" method="get" action="<c:url value="/"/>"> <!-- action 을 비워놔야 자신을 가리킨다 -->
              <input type="text" name="search_word" class="form-control" placeholder="검색어를 입력 후 enter를 누르세요" autofocus>
              <c:if test="${search_word != ''}">
              	<p> 입력한 검색어: <span id="txtHint">${ search_word }</span></p>
	            <button class="btn btn-info" onclick="window.location.href='/'">검색어 초기화</button>
              </c:if>
              <br/><br><span style=""> <input type="button" class="btn btn-primary" value="글쓰기" onClick="location.href='new.php'"></span>
              <br/>
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
          		<th scople="col" width="1%">문의 수</th>
          		<th scople="col" width="1%">추천</th>
      		</tr>
      		</thead>
      		<tbody>
     			<c:forEach var="faqContent" items="${ faqContent }" varStatus="status">
      				<tr>
      					<td>${ faqContent.title }</td>
      					<td>${ faqContent.content }</td>
      					<td>${ faqContent.hit }</td>
      					<td><button type="button" class="btn btn-primary btn-xs" onClick="updateHit(${faqContent.no})">추천</button></td>
      				</tr>
   				</c:forEach>
      		</tbody>
    	</table>
    	
      </div>
     <a style="display:scroll;position:fixed;bottom:80px; right:30px;" rel="nofollow"
     href="#"><img src="top.png"></a>

     <a style="display:scroll;position:fixed;bottom:40px; right:30px;" rel="nofollow"
     href="#scrollbottom" ><img src="down.png"></a>
     <div id="scrollbottom"></div>
</body>
</html>
