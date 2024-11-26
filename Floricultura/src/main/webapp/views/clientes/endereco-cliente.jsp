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

		<form action="${pageContext.request.contextPath}/clientes?action=editar-endereco" method="POST" class="form-prod">
    		<input type="hidden" name="id" value="<%=cliente.getIdCliente()%>">
    	
    		<label><strong>Rua:</strong></label>
    		<input type="text" name="logradouro" value="<%=cliente.getLogradouro()%>" required>
    	
    		<label><strong>Número:</strong></label>
    		<input type="text" name="numero" value="<%=cliente.getNumero()%>" required>
    	
    		<label><strong>CEP:</strong></label>
    		<input type="text" name="cep" value="<%=cliente.getCep()%>" required>
    		
    		<label><strong>Bairro:</strong></label>
            <input type="text" name="bairro" value="<%=cliente.getBairro()%>" required>
    	
    		<label><strong>Cidade:</strong></label>
    		<input type="text" name="cidade" value="<%=cliente.getCidade()%>" required>
    		
    		<label><strong>UF:</strong></label>
    		<input type="text" name="uf" value="<%=cliente.getUf()%>" required>
    		
    		<label><strong>Complemento:</strong></label>
    		<input type="text" name="complemento" value="<%=cliente.getComplemento()%>" required>
    		
    		<label><strong>Apelido:</strong></label>
    		<input type="text" name="apelido" value="<%=cliente.getApelido()%>" required>
    		
    		<input type="submit" value="Salvar">
		</form>

		<form action="${pageContext.request.contextPath}/views/clientes/cadastro-cliente.jsp" method="GET" class="form-prod">
    		<input type="submit" value="Cancelar">
		</form>
	</main>
</body>
</html>