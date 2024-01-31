<%@ page import="com.poseidon.dao.CoffeeDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    CoffeeDAO coffeeDAO = new CoffeeDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/coffee.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>커피</h1>
					<div class="name">
						<form action="./order" method="post">
							<div class="coffeeselect">
								<div class="coffeeselect">
									<input type="radio" id="icec" name="menu" value="icec">
									<label for="icec">아이스 아메리카노</label>
									
									<input type="radio"id="hotc" name="menu" value="hotc">
									<label for="hotc">뜨거운 아메리카노</label>
									
									<input type="radio" id="icet" name="menu" value="icet">
									<label for="icet">차가운 차</label>
									
									<input type="radio" id="hott" name="menu" value="hott">
									<label for="hott">따뜻한 차</label>
									
									<input type="radio" id="none" name="menu" value="none">
									<label for="none">저는 안먹어요</label>
								</div>
							</div>

							<input type="submit" value="주문하기">
						</form>
					</div>
					<button onclick="url('./board?page=${param.page}')">게시판으로</button>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp" />
		</footer>
	</div>
</body>
</html>