<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Lilly Bloom's - Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <main>
        <article>
            <section>
                <header>
                	<% String mensagem = (String) request.getAttribute("mensagem"); %>
    				<% if (mensagem != null) { %>
        			<p><%= mensagem %></p>
    				<% } %>
                </header>
                <h1> Login</h1>
                <form action="${pageContext.request.contextPath}/login?action=login" method="POST" class="form-prod">
    				<div class="senha-estilo">
        				<input type="email" name="email" id="email" required placeholder="Digite seu email"><br>
    				</div>

    				<div class="senha-estilo">
						<input type="password" id="senha" name="senha" required placeholder="Digite sua senha">
						<i class="bi bi-eye-fill" id="toggleSenha"></i>
					</div>
					<script src="${pageContext.request.contextPath}/js/visualiza-senha.js"></script>
					
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
