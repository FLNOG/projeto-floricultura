<%@page import="model.Carrinho"%>
<%@page import="model.ItemVenda"%>
<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lilly Bloom's - Seu Carrinho</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrinho.css">
</head>
<body>

	<%
	Cliente usuarioLogado = (Cliente) session.getAttribute("usuarioLogado");

	if (usuarioLogado == null) {
    	%>
    		<jsp:include page="../../includes/menu-index.jsp" />
    	<%
	} else if ("1".equals(usuarioLogado.getTipo())) {
   		%>
   			<jsp:include page="../../includes/menu-index-adm.jsp" />
   		<%
	} else if ("0".equals(usuarioLogado.getTipo())) {
    	%>
    		<jsp:include page="../../includes/menu-index-cliente.jsp" />
    	<%
	}
	%>

<h1>Seu Carrinho de Compras</h1>

<%
    Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

    if (carrinho == null || carrinho.getItens().isEmpty()) {
%>
    <p>Seu carrinho está vazio.</p>
<%
    } else {
        List<ItemVenda> itens = carrinho.getItens();
%>
    <table>
        <thead>
            <tr>
                <th>Produto</th>
                <th>Quantidade</th>
                <th>Preço Unitário</th>
                <th>Total</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (ItemVenda item : itens) {
                    double totalItem = item.calcularTotalItem();
            %>
                <tr>
                    <td><%= item.getNome() %></td>
                    <td><%= item.getQtdItens() %></td>
                    <td>R$ <%= String.format("%.2f", item.getVlrProduto()) %></td>
                    <td>R$ <%= String.format("%.2f", totalItem) %></td>
                    <td>
                        <form action="${pageContext.request.contextPath}/vendas?action=remover" method="post">
                            <input type="hidden" name="idProduto" value="<%= item.getIdProduto() %>">
                            <input type="submit" value="Remover">
                        </form>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="3">Total do Carrinho</td>
                <td colspan="2">R$ <%= String.format("%.2f", carrinho.calcularTotal()) %></td>
            </tr>
        </tfoot>
    </table>
    <form action="${pageContext.request.contextPath}/vendas?action=finalizar" method="post">
        <input type="submit" value="Finalizar">
    </form>
<%
    }
%>

<jsp:include page="../../includes/footer.jsp" />
</body>
</html>