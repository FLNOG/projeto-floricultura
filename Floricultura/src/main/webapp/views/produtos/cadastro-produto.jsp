<%@page import="dao.ProdutoDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lilly Bloom's ADM - Produtos</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adm-produto.css">
</head>
<body>
    <jsp:include page="../../includes/menu-adm.jsp" />
    
    <script src="${pageContext.request.contextPath}/js/tirar-null.js"></script>

    <header>
        <h1>Produtos</h1>
    </header>

    <% 
      String mensagem = (String) request.getAttribute("mensagem");
      if (mensagem != null) {
          out.print("<div class='alert'>" + mensagem + "</div>"); 
      }
    %>
    
    <main>

        <form action="${pageContext.request.contextPath}/produtos?action=cadastrar" method="POST" class="form-prod">

            <label><strong>Nome:</strong></label>
            <input type="text" name="descricao" required>
            
            <label><strong>Quantidade:</strong></label>
            <input type="number" name="quantidade" required>
            
            <label><strong>Preço:</strong></label>
            <input type="text" step="0.01" name="preco" required>
            
            <label><strong>Detalhes do produto:</strong></label>
            <input type="text" name="detalhes" required>
            
            <label><strong>Url da imagem:</strong></label>
            <input type="text" name="imagem" required>
                
            <input type="submit" name="salvar" value="Cadastrar Produto">
        </form>

        <form action="${pageContext.request.contextPath}/produtos" method="GET" class="form-prod">
    		<input type="hidden" name="action" value="buscar">    		
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
                ProdutoDAO produtoDAO = new ProdutoDAO();
                List<Produto> listaProdutos = produtoDAO.listarProdutos();
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
                %>
            </tbody>
        </table>
    </main>
</body>
</html>