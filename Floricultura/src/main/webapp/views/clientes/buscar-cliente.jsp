<%@page import="dao.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Cliente"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lilly Bloom's ADM - Buscar Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm-produto.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />
    
    <script src="${pageContext.request.contextPath}/js/tirar-null.js"></script>

    <header>
        <h1>Buscar Produto</h1>
    </header>

    <main>
        
        <form action="${pageContext.request.contextPath}/clientes" method="GET" class="form-prod">
    		<input type="hidden" name="action" value="buscar">
    		<label><strong>Buscar Produto:</strong></label>
    		<input type="text" name="NomeBusca" placeholder="Nome do Cliente" required>
    		<input type="submit" value="Buscar Clientes">
		</form>

        <table>
            <thead>
                <tr>
                    <th>ID de Cliente</th>
                    <th>Nome</th>
                    <th>Email</th>
                    <th>Contato</th>
                    <th>Ajustes</th>
                </tr>
            </thead>
            <tbody>
                <%
					List<?> listaClientesObj = (List<?>) request.getAttribute("listaClientes");
					List<Cliente> listaClientes = new ArrayList<>();
					if (listaClientesObj != null) {
    					for (Object obj : listaClientesObj) {
        					if (obj instanceof Cliente) {
            					listaClientes.add((Cliente) obj);
        						}
    					}
					}

					if (!listaClientes.isEmpty()) {
    					for (Cliente cliente : listaClientes) {
				%>
                <tr>
                    <td><%=cliente.getIdCliente()%></td>
                    <td><%=cliente.getNome()%></td>
                    <td><%=cliente.getEmail()%></td>
                    <td><%=cliente.getTelefone()%></td>
                    <td>
                        <a class="remover-editar" href="${pageContext.request.contextPath}/clientes?action=remover&id=<%=cliente.getIdCliente()%>">
                            <img src="${pageContext.request.contextPath}/img/trash.png" alt="Remover cliente" id="img-trash"></a>
                            
                        <a class="remover-editar" href="${pageContext.request.contextPath}/clientes?action=editar&id=<%=cliente.getIdCliente()%>">
                            <img src="${pageContext.request.contextPath}/img/edit.png" alt="Editar cliente" id="img-edit"></a>
                    </td>
                </tr>
                <%
                    }
                } else {
                    out.print("<tr><td colspan='5'>Nenhum cliente encontrado.</td></tr>");
                }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>