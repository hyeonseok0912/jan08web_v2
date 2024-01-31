<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지 - 댓글관리</title>
<link href="../css/admin.css?ver=0.19" rel="stylesheet" />
<link href="../css/member.css" rel="stylesheet" />
<script type="text/javascript" src="../js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['IP', '접속수'],
          <c:forEach items="${list2}" var="row2">
          ['${row2.iip}',     ${row2.icount}],
          </c:forEach>
        ]);

        var options = {
          title: 'My Daily Activities',
          pieHole: 0.4,
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
</script>
<script type="text/javascript">
$(function(){
	$('#searchBtn').click(function(){
		let ipsearch = $('#search').val();
		location.href="./ip?search="+ipsearch;
	});
});
</script>
</head>
<body>
	<div class="wrap">
		<!-- menu -->
		<%@ include file="menu.jsp"%>
		<div class="main">
			<article>
				<h2>IP관리</h2>
				0. 중복없이 ip 리스트 뽑기는? 1. 최다 접속 ip 5개 출력 2. 그래프 그리기 - 구글차트 - ip당 접속 건수
				- 10개? ${list3 } 3. 가장 최근에 접속한 ip

				<div class="nav-lists">
					<ul class="nav-lists-group">
						<li class="nav-lists-item" onclick="url('./ip?del=1')"><i
							class="xi-close-circle-o"></i> 보임</li>
						<li class="nav-lists-item" onclick="url('./ip?del=0')"><i
							class="xi-close-circle-o"></i> 숨김</li>
					</ul>
				</div>
				<div id="donutchart" style="width: 900px; height: 500px;"></div>
				<h1>가장 많이 접속한 IP</h1>
				<table>
					<thead>
						<tr>
							<th>IP</th>
							<th>방문횟수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list2 }" var="row2">
							<tr>
								<td>${row2.iip }</td>
								<td>${row2.icount }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<div>
						<h2>가장 최근에 접속한 IP</h2>
						<table>
							<thead>
								<tr>
									<th>IP</th>
									<th>방문기록</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list4 }" var="row4">
									<tr>
										<td>${row4.iip }</td>
										<td>${row4.idate }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="search">
						<input type="text" id="search"><button id="searchBtn">검색</button>
					</div>
					<button onclick="location.href='./ip'">초기화</button>
				</div>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>IP</th>
							<th>날짜</th>
							<th>URL</th>
							<th>기타</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
							<tr class="row">
								<td class="d1">${row.ino }</td>
								<td class="d1">${row.iip }</td>
								<td class="d2">${row.idate }</td>
								<td class="d1">${row.iurl }</td>
								<td class="title">${row.idate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</article>
		</div>
	</div>
</body>
</html>