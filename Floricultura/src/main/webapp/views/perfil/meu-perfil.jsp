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
        
        if (idClienteSessao == null) {
            out.println("<p>Acesso negado: sessão expirada ou inválida.</p>");
            return;
        }

        if (idClienteParam == null || !idClienteParam.equals(idClienteSessao.toString())) {
            out.println("<p>Acesso negado: cliente não encontrado ou ID incorreto.</p>");
            return;
        }

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.buscarPorId(idClienteSessao);

        if (cliente == null) {
            out.println("<p>Erro: cliente não encontrado no banco de dados.</p>");
            return;
        }
    %>

    <form action="${pageContext.request.contextPath}/clientes?action=meu-perfil" method="POST" class="form-prod">
        <input type="hidden" name="id" value="<%= cliente.getIdCliente() %>">

        <label><strong>Nome:</strong></label>
        <input type="text" name="nome" value="<%= cliente.getNome() %>" required>

        <label><strong>CPF / CNPJ:</strong></label>
        <input type="text" name="cpf" value="<%= cliente.getCpf() %>" required>

        <label><strong>Nascimento:</strong></label>
        <input type="date" name="nascimento" value="<%= cliente.getNascimento() %>" required>

        <label><strong>Email:</strong></label>
        <input type="text" name="email" value="<%= cliente.getEmail() %>" required>

        <label><strong>Contato:</strong></label>
        <input type="text" name="telefone" value="<%= cliente.getTelefone() %>" required>

        <label><strong>Senha:</strong></label>
        <input type="password" name="senha" value="<%= cliente.getSenha() %>" required>

        <input type="submit" value="Salvar">
    </form>
    <!--  <form action="${pageContext.request.contextPath}/views/perfil/meu-perfil.jsp" method="GET" class="form-prod">
        <input type="hidden" name="id" value="${sessionScope.idCliente}">
        <input type="submit" value="Cancelar"> 
    </form> -->
</body>
</html>