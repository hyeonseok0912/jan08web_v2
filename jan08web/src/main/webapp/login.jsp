<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"></script>
<style>
.login{
	margin: 0 auto;
	width: 300px;
	min-height: 300px;
	background-color: #c1c1c1;
	padding: 10px;
	box-sizing: border-box;
	text-align: center;
}

.login input{
	width: 100%;
	height: 30px;
	text-align: center;
	color: blue;
	margin-bottom: 10px;
	box-sizing: border-box;
}
.login button{
	width: 45%;
	height: 30px;
	color: blue;
	font-size: large;
}
.login button:hover{
	background-color: gray;
}
</style>
<script type="text/javascript">
function err(){
	let errBox = document.querySelector("#errorMSG");
	errBox.innerHTML = "<marquee>올바른 id와 pw를 입력하세요.</marquee>";
	errBox.style.color = 'red';
	errBox.style.fontSize = "14pt";
}
</script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<!-- jsp:은 출력 결과만 화면에 나옵니다. -->
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>login</h1>
					<c:if test="${param.error ne null}">
						<script type="text/javascript">
							//alert("올바른 암호와 아이디를 입력하세요.");
						</script>
					</c:if>
					
					<div class="login">
						<form action="./login" method="post">
						<img alt="login" src="./img/login.png">
							<input type="text" name="id" placeholder="아이디를 입력하세요">
							<input type="password" name="pw" placeholder="암호를 입력하세요">
							<button type="reset">지우기</button>
							<button type="submit">로그인</button>
							<div id="errorMSG"></div>
						</form>
						<a href="./join">회원가입</a>
					</div>
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
	<c:if test="${param.error ne null }">
		<script type = "text/javascript">
			err();
		</script>
	</c:if>
</body>
</html>