<%@page import="dao.ProdutoDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Produto"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lilly Bloom's ADM - Buscar Produto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm-produto.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />
    
    <script src="${pageContext.request.contextPath}/js/tirar-null.js"></script>

    <header>
        <h1>Buscar Produto</h1>
    </header>

    <main>
        <form action="${pageContext.request.contextPath}/produtos" method="GET" class="form-prod">
    		<input type="hidden" name="action" value="buscar">
    		<label><strong>Buscar Produto:</strong></label>
    		<input type="text" name="descricaoBusca" placeholder="Nome do produto" required>
    		<input type="submit" value="Buscar Produtos">
		</form>

        <table>
            <thead>
                <tr>
                    <th>ID de Produto</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Estoque</th>
                    <th>Ajustes</th>
                </tr>
            </thead>
            <tbody>
                <%
					List<?> listaProdutosObj = (List<?>) request.getAttribute("listaProdutos");
					List<Produto> listaProdutos = new ArrayList<>();
					if (listaProdutosObj != null) {
    					for (Object obj : listaProdutosObj) {
        					if (obj instanceof Produto) {
            					listaProdutos.add((Produto) obj);
        						}
    					}
					}

					if (!listaProdutos.isEmpty()) {
    					for (Produto produto : listaProdutos) {
				%>
                <tr>
                    <td><%=produto.getIdProduto()%></td>
                    <td><%=produto.getDescricao()%></td>
                    <td>R$ <%=produto.getPreco()%></td>
                    <td><%=produto.getQuantidade()%> und</td>
                    <td>
                        <a class="remover-editar" href="${pageContext.request.contextPath}/produtos?action=remover&id=<%=produto.getIdProduto()%>">
                            <img src="${pageContext.request.contextPath}/img/trash.png" alt="Remover produto" id="img-trash"></a>
                            
                        <a class="remover-editar" href="${pageContext.request.contextPath}/produtos?action=editar&id=<%=produto.getIdProduto()%>">
                            <img src="${pageContext.request.contextPath}/img/edit.png" alt="Editar produto" id="img-edit"></a>
                    </td>
                </tr>
                <%
                    }
                } else {
                    out.print("<tr><td colspan='5'>Nenhum produto encontrado.</td></tr>");
                }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>