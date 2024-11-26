<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Lilly Bloom's - Cadastro</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<main>
        <article>
            <section>
                <img src="${pageContext.request.contextPath}/img/Logo.svg" alt="">
            </section>
            <section>
                <header>
                	<% String mensagem = (String) request.getAttribute("mensagem"); %>
    				<% if (mensagem != null) { %>
        			<p><%= mensagem %></p>
    				<% } %>
                </header>
                <h1> Cadastro</h1>
                <form action="${pageContext.request.contextPath}/login?action=cadastro" method="POST" class="form-prod">
					
					<div class="senha-estilo">
        				<input type="text" name="nome" required placeholder="Digite seu apelido">
    				</div>
					<br>
					
					<div class="senha-estilo">
        				<input type="email" name="email" required placeholder="Digite seu email"><br>
    				</div>
					
					<div class="senha-estilo">
						<input type="password" id="senha" name="senha" required placeholder="Digite sua senha"><br>
						<i class="bi bi-eye-fill" id="toggleSenha"></i>
					</div>

					<script src="${pageContext.request.contextPath}/js/visualiza-senha.js"></script>
					
					<div class="senha-estilo">
						<input type="password" id="confirmarSenha" name="confirmarSenha" required placeholder="Confirme sua senha"><br>
					</div>
					
					<input type="hidden" name="tipo" value="0">

        			<input type="submit" name="salvar" value="Cadastrar-se">
    			</form>        
    			<script>
    				document.querySelector("form").addEventListener("submit", function(event) {
        			var senha = document.getElementById("senha").value;
        			var confirmarSenha = document.getElementById("confirmarSenha").value;
        
			        if (senha !== confirmarSenha) {
			            alert("As senhas não coincidem!");
			            event.preventDefault();
			        	}
			    	});
				</script>
                <aside>
                    <span>Você já tem uma conta? <a href="${pageContext.request.contextPath}/views/login/login.jsp">Faça seu login.</a></span>
                </aside>
            </section>
        </article>
    </main>
</body>
</html>