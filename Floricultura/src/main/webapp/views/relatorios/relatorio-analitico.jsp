<%@ page import="java.util.List" %>
<%@ page import="model.ItemVenda" %>
<%@ page import="model.Produto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relatório Analítico - Lilly Bloom's</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/relatorios.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />

    <header>
        <h1>Relatório Analítico da Venda</h1>
    </header>

    <% 
        String mensagem = (String) request.getAttribute("mensagem");
        if (mensagem != null) {
            out.print("<div class='alert'>" + mensagem + "</div>"); 
        }
        
        double valorTotal = 0.0;

        List<ItemVenda> itensVenda = (List<ItemVenda>) request.getAttribute("itensVenda");
    %>

    <main>
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
                    if (itensVenda != null && !itensVenda.isEmpty()) {
                        for (ItemVenda item : itensVenda) {
                        	valorTotal += item.calcularTotalItem();
                %>
                <tr>
                    <td><%= item.getProduto().getDescricao() %></td>
                    <td><%= item.getQtdItens() %></td>
                    <td>R$ <%= String.format("%.2f", item.getVlrProduto()) %></td>
                    <td>R$ <%= String.format("%.2f", item.calcularTotalItem()) %></td>
                </tr>
                <%
                        }
                        System.out.println(valorTotal);
                        String msg = Double.toString(valorTotal);
                    } else {
                %>
                <tr>
                    <td colspan="4">Nenhum item encontrado para esta venda.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <div class="valorTotal">
	        <h3>Valor total das compras pesquisadas: R$ <%= String.format("%.2f", valorTotal) %></h3>
        </div>
        <br><br>
        <form action="${pageContext.request.contextPath}/views/relatorios/relatorio-sintetico.jsp" method="GET" class="form-prod">
    		<input type="submit" value="Voltar">
		</form>
    </main>
</body>
</html>