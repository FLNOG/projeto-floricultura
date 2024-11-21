<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Lilly Bloom's - Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
</head>
<body>
	<main>
        <article>
            <section>
                <img src="${pageContext.request.contextPath}/img/Logo.svg" alt="">
            </section>
            <section>
                <h1> Cadastre-se</h1>
                <header>
                	<% String mensagem = (String) request.getAttribute("mensagem"); %>
    				<% if (mensagem != null) { %>
        			<p><%= mensagem %></p>
    				<% } %>
                </header>
                <form action="${pageContext.request.contextPath}/login?action=cadastro" method="POST" class="form-prod">
        			<div>
        				<label><strong>Apelido:</strong></label>
        				<input type="text" name="nome" required><br>
					</div>
        			<div>
        				<label><strong>Email:</strong></label>
        				<input type="email" name="email" required><br>
					</div>
					<div>
        				<label><strong>Senha:</strong></label>
        				<input type="password" name="senha" id="senha" required><br>
					</div>
					<div>
        				<label><strong>Confirmar Senha:</strong></label>
        				<input type="password" name="confirmarSenha" id="confirmarSenha" required><br>
    				</div>
					<input type="hidden" name="tipo" value="0">

        			<input type="submit" name="salvar" value="Cadastrar Cliente">
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