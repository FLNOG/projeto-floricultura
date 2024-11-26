<%@page import="dao.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lilly Bloom's ADM - Clientes</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm-produto.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />
	
	<script src="${pageContext.request.contextPath}/js/tirar-null.js"></script>
	
	<header>
    	<h1>Cadastrar Cliente</h1>
    </header>

    	<% String mensagem = (String) request.getAttribute("mensagem"); %>
    	<% if (mensagem != null) { %>
        	<p><%= mensagem %></p>
    	<% } %>

	<main>
    	<form action="${pageContext.request.contextPath}/clientes?action=cadastrar" method="POST" class="form-prod">
        	<label><strong>Nome:</strong></label>
        	<input type="text" name="nome" required><br>

        	<label><strong>Email:</strong></label>
        	<input type="email" name="email" required><br>
        	
        	<label><strong>CPF / CNPJ:</strong></label>
        	<input type="text" name="cpfcnpj" required><br>

        	<label><strong>Telefone:</strong></label>
        	<input type="text" name="telefone" required><br>
        	
        	<label><strong>Data de Nascimento:</strong></label>
        	<input type="date" name="nascimento" required><br>
        	
        	<label><strong>Senha:</strong></label>
        	<input type="text" name="senha" required><br>
        	
        	<label><strong>Escolha uma opção:</strong></label><br>
			<label>
				<input type="radio" name="escolha" value="1"><Strong>Administrador</Strong>
				<input type="radio" name="escolha" value="0"><Strong>Cliente</Strong>
			</label>

        	<input type="submit" name="salvar" value="Cadastrar Cliente">
    	</form>
    	
    	<form action="${pageContext.request.contextPath}/clientes" method="GET" class="form-prod">
    		<input type="hidden" name="action" value="buscar">
    		<input type="submit" value="Buscar Clientes">
		</form>		
        <table>
            <thead>
                <tr>
                    <th>ID de Cliente</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Contato</th>
                    <th>Ajustes</th>
                </tr>
            </thead>
        
            <tbody>
                <%
                ClienteDAO clienteDAO = new ClienteDAO();
                List<Cliente> listaClientes = clienteDAO.listarClientes();
                for (Cliente cliente : listaClientes) {
                %>
                <tr>
                    <td><%=cliente.getIdCliente()%></td>
                    <td><%=cliente.getNome()%></td>
                    <td><%=cliente.getEmail()%></td>
                    <td><%=cliente.getTelefone()%></td>
                    <td>
                    	<a class="remover-editar" href="${pageContext.request.contextPath}/clientes?action=endereco&id=<%=cliente.getIdCliente()%>">
                            <img src="${pageContext.request.contextPath}/img/address.png" alt="Endereços" id="img-trash"></a>
                    
                        <a class="remover-editar" href="${pageContext.request.contextPath}/clientes?action=remover&id=<%=cliente.getIdCliente()%>">
                            <img src="${pageContext.request.contextPath}/img/trash.png" alt="Remover Cliente" id="img-trash"></a>
                            
                        <a class="remover-editar" href="${pageContext.request.contextPath}/clientes?action=editar&id=<%=cliente.getIdCliente()%>">
                            <img src="${pageContext.request.contextPath}/img/edit.png" alt="Editar Cliente" id="img-edit"></a>
                    </td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>