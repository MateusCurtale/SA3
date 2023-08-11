<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form method="post" action="ServletOi">
	  		<div class="mb-3">
	    		<label for="exampleInputEmail1" class="form-label">Usuário</label>
	    		<input type="text" class="form-control" name="usuario" id="exampleInputEmail1" aria-describedby="emailHelp">
	  		</div>
	  		<div class="mb-3">
	    		<label for="exampleInputPassword1" class="form-label">Senha</label>
	    		<input type="password" name="senha" class="form-control" id="exampleInputPassword1">
	  		</div>
	  		<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
	  		<button type="submit" class="btn btn-primary">Login</button>
	  		<a href="index.jsp" class="btn btn-primary" role="button" aria-disabled="true">Home</a>
		</form>
	<h4> ${mensagem} </h4>
	</div>
	
</body>
</html>