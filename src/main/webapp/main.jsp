<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
 	
	<!-- 	jQuery -->
	<script type="text/javascript" src="js/egovframework/mbl/cmm/jquery-1.9.1.min.js"></script>
	
	<!-- jQuery Mobile -->
	<script type="text/javascript" src="js/egovframework/mbl/cmm/jquery.mobile-1.3.2.min.js"></script>
	<link rel="stylesheet" href="css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css">
	
	<!-- eGov Mobile -->
	<script type="text/javascript" src="js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
	<link rel="stylesheet" href="css/egovframework/mbl/cmm/EgovMobile-1.3.2.css">
	 
	<title>모바일공간정보시각화서비스</title>
</head>
<body>
 <div data-role="page" id="mainpage">
	<div data-role="header" class="ui-body-g center"> 
		<h4>로그인</h4>
	</div>
	<div data-role="content">
		<form method="post" action="login.do" data-ajax="false">
		<table style="margin-left:auto; margin-right:auto; width:100%;">
		<tr>
			<th>아 이 디</th>
			<td><input type="text" name="userId" ></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>			
			<td><a href="join_page.do" data-role="button" rel="external">회원가입</a></td>
			<td><input type="submit" value="로그인" ></td>
		</tr>
		</table>
		</form>
	</div>
	<div data-role="footer" class="ui-body-g center"  data-position="fixed" data-tap-toggle="false">
	<h4>footer</h4>
	</div>
</div> 
</body>
</html>
