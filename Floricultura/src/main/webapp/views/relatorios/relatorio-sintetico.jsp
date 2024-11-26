<%@ page import="dao.RelatorioDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Venda"%>
<%@ page import="model.ItemVenda"%>
<%@ page import="model.Produto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Relatório de Vendas - Lilly Bloom's</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/relatorios.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />

    <header>
        <h1>Relatório de Vendas Sintético</h1>
    </header>

    <% 
        String mensagem = (String) request.getAttribute("mensagem");
        if (mensagem != null) {
            out.print("<div class='alert'>" + mensagem + "</div>"); 
        }
        
        double valorTotal = 0.0;
    %>

    <main>
        <form action="${pageContext.request.contextPath}/relatorio" method="GET" class="form-prod">
            <label><strong>Data Início:</strong></label>
            <input type="date" name="dataInicio" required>
            
            <label><strong>Data Fim:</strong></label>
            <input type="date" name="dataFim" required>
            
            <input type="submit" name="salvar" value="Gerar Relatório">
        </form>
        
        <br>

        <table>
            <thead>
                <tr>
                    <th>ID Venda</th>
                    <th>Cliente</th>
                    <th>Data da Venda</th>
                    <th>Valor Total</th>
                    <th>Relatório Analítico</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Venda> vendas = (List<Venda>) request.getAttribute("relatorio");
                    if (vendas != null && !vendas.isEmpty()) {
                        for (Venda venda : vendas) {
                        	valorTotal += venda.getVlrTotal();
                %>
                <tr>
                    <td><%= venda.getIdVenda() %></td>
                    <td><%= (venda.getCliente() != null) ? venda.getCliente().getNome() : "Cliente não informado" %></td>
                    <td><%= venda.getDataVenda() %></td>
                    <td>R$ <%= venda.getVlrTotal() %></td>
                    <td>
                    	<a class="remover-editar" href="${pageContext.request.contextPath}/relatorio?action=analitico&id=<%=venda.getIdVenda()%>">
                            <img src="${pageContext.request.contextPath}/img/more.png" alt="Relatório Analítico" id="img-trash"></a>
                    </td>
                </tr>
                <%
                        }
                        System.out.println(valorTotal);
                        String msg = Double.toString(valorTotal);
                    } else {
                %>
                <tr>
                    <td colspan="5">Nenhuma venda encontrada para o período informado.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <div class="valorTotal">
	        <h3>Valor total das compras pesquisadas: R$ <%= String.format("%.2f", valorTotal) %></h3>
        </div>
    </main>
</body>
</html>