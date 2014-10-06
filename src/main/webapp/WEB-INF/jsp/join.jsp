<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<link rel="stylesheet" href="css/egovframework/mbl/cmm/jquery.mobile-1.3.2.css">
	<link rel="stylesheet" href="css/egovframework/mbl/cmm/EgovMobile-1.3.2.css">
	<script type="text/javascript" src="js/egovframework/mbl/cmm/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/egovframework/mbl/cmm/jquery.mobile-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/egovframework/mbl/cmm/EgovMobile-1.3.2.js"></script>
	<title>회원가입</title>
</head>
<body> 
	<div data-role="header" class="ui-body-g center"> 
		<h4>회원가입</h4>
	</div>
	<div data-role="content"> 
	 <form action="join_exe.do" method="post">
	 <table style="margin-left:auto; margin-right:auto; width:100%;">
	 	<tr>
		 	<th>아이디</th>
		 	<td><input type="text" name="userId"></td>
	 	</tr>
	 	<tr>
		 	<th>비밀번호</th>
		 	<td><input type="password" name="passwd"></td>
	 	</tr>
	 	<tr>
		 	<th>이름</th>
		 	<td><input type="text" name="username"></td>
	 	</tr>
	 	<tr>
		 	<th>이메일	</th>
		 	<td><input type="text" name="email"></td>
	 	</tr>
	 	<tr>
	 		<td>
	 		<a href="index.do" data-role="button">취소</a></td>
	 		<td><input type="submit" value="회원가입 완료"></td>
	 </table>
	 </form>
	 </div>
	  
	 <div data-role="footer"> 
	 </div>
</body>
</html>