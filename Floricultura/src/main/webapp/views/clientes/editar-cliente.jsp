<%@page import="model.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lilly Bloom's ADM - Editar Clientes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm-produto.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />
    
    <script src="${pageContext.request.contextPath}/js/tirar-null.js"></script>
	
    <header>
        <h1>Editar Cliente</h1>
    </header>
    <main>
    	<% 
			Cliente cliente = (Cliente) request.getAttribute("cliente");
			if (cliente == null) {
    			out.println("Cliente não encontrado.");
    		return;
			}
		%>

		<form action="${pageContext.request.contextPath}/clientes?action=editar" method="POST" class="form-prod">
    		<input type="hidden" name="id" value="<%=cliente.getIdCliente()%>">
    	
    		<label><strong>Nome:</strong></label>
    		<input type="text" name="nome" value="<%=cliente.getNome()%>" required>
    	
    		<label><strong>CPF / CPNJ:</strong></label>
    		<input type="text" name="cpf" value="<%=cliente.getCpf()%>" required>
    	
    		<label><strong>Nascimento:</strong></label>
    		<input type="date" name="nascimento" value="<%=cliente.getNascimento()%>" required>
    		
    		<label><strong>Email:</strong></label>
            <input type="text" name="email" value="<%=cliente.getEmail()%>" required>
    	
    		<label><strong>Contato:</strong></label>
    		<input type="text" name="telefone" value="<%=cliente.getTelefone()%>" required>
    		
    		<label><strong>Senha:</strong></label>
    		<input type="text" name="senha" value="<%=cliente.getSenha()%>" required>
    	
    		<input type="submit" value="Salvar">
		</form>

		<form action="${pageContext.request.contextPath}/views/clientes/cadastro-cliente.jsp" method="GET" class="form-prod">
    		<input type="submit" value="Cancelar">
		</form>
	</main>
</body>
</html>