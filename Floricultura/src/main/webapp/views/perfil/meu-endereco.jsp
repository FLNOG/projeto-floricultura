<%@page import="dao.ClienteDAO"%>
<%@page import="model.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lilly Bloom's - Meu Perfil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/meu-perfil.css">
</head>
<body>
	<jsp:include page="../../includes/menu-lateral.jsp" />
	
	<script src="${pageContext.request.contextPath}/js/tirar-null.js"></script>

	<%
	    Integer idClienteSessao = (Integer) session.getAttribute("idCliente");
	    String idClienteParam = request.getParameter("id");

	    if (idClienteParam == null || idClienteSessao == null || !idClienteParam.equals(idClienteSessao.toString())) {
	        out.println("<p>Acesso negado ou cliente não encontrado.</p>");
	        return;
	    }

	    ClienteDAO clienteDAO = new ClienteDAO();
	    Cliente cliente = clienteDAO.buscarEndereco(idClienteSessao);

	    if (cliente == null) {
	        out.println("<p>Cliente não encontrado.</p>");
	        return;
	    }
	%>

	<form action="${pageContext.request.contextPath}/clientes?action=meu-endereco" method="POST" class="form-prod">
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
	<!-- <form action="${pageContext.request.contextPath}/views/perfil/meu-endereco.jsp" method="GET" class="form-prod">
        <input type="hidden" name="id" value="${sessionScope.idCliente}">
        <input type="submit" value="Cancelar">
    </form> !-->
</body>
</html>