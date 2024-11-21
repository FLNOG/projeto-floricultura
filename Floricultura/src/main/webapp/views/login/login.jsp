<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Lilly Bloom's - Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
    <main>
        <article>
            <section>
                <h1> Login</h1>
                <header>
                	<% String mensagem = (String) request.getAttribute("mensagem"); %>
    				<% if (mensagem != null) { %>
        			<p><%= mensagem %></p>
    				<% } %>
                </header>
                <form action="${pageContext.request.contextPath}/login?action=login" method="POST" class="form-prod">
    				<div>
        				<label><strong>Email:</strong></label>
        				<input type="email" name="email" required><br>
    				</div>
    				<div>
        				<label><strong>Senha:</strong></label>
        				<input type="password" name="senha" required><br>
    				</div>
    				<input type="submit" name="salvar" value="Fazer login">
				</form>
                <aside>
                    <span>Não tem uma conta? <a href="${pageContext.request.contextPath}/views/login/cadastro.jsp">Faça seu cadastro.</a></span>
                </aside>
            </section>
            <section>
                <img src="${pageContext.request.contextPath}/img/Logo.svg" alt="">
            </section>
        </article>
    </main>
</body>
</html>