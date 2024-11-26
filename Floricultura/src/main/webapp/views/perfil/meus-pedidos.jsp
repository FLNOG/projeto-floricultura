<%@ page import="java.util.List" %>
<%@ page import="model.ItemVenda" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Meus Produtos - Lilly Bloom's</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/meus-pedidos.css">
</head>
<body>
    <jsp:include page="../../includes/menu-lateral.jsp" />

    <header>
        <h1>Meus Pedidos</h1>
    </header>

    <% 
        String mensagem = (String) request.getAttribute("mensagem");
        if (mensagem != null) {
            out.print("<div class='alert'>" + mensagem + "</div>"); 
        }

        List<ItemVenda> itensComprados = (List<ItemVenda>) request.getAttribute("itensComprados");
    %>

    <main>
        <h2>Produtos Comprados</h2>
        
        <table>
            <thead>
                <tr>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Preço Unitário</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (itensComprados != null && !itensComprados.isEmpty()) {
                        for (ItemVenda item : itensComprados) {
                %>
                <tr>
                    <td><%= item.getProduto().getDescricao() %></td>
                    <td><%= item.getQtdItens() %></td>
                    <td>R$ <%= String.format("%.2f", item.getVlrProduto()) %></td>
                    <td>R$ <%= String.format("%.2f", item.calcularTotalItem()) %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">Nenhum pedido encontrado.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>
