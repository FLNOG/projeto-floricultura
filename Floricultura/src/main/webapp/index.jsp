<%@page import="dao.ProdutoDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Produto"%>
<%@page import="model.Cliente"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Lilly Bloom's - Floricultura Online</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/carrossel.css">
</head>
<body>
    
	<%
	Cliente usuarioLogado = (Cliente) session.getAttribute("usuarioLogado");

	if (usuarioLogado == null) {
    	%>
    		<jsp:include page="includes/menu-index.jsp" />
    	<%
	} else if ("1".equals(usuarioLogado.getTipo())) {
   		%>
   			<jsp:include page="includes/menu-index-adm.jsp" />
   		<%
	} else if ("0".equals(usuarioLogado.getTipo())) {
    	%>
    		<jsp:include page="includes/menu-index-cliente.jsp" />
    	<%
	}
	%>

    <header>
        <h1>Nossa Loja</h1>
    </header>

	<script src="js/carrossel.js" defer></script>

    <main>
		<div class="carrossel">
			<div class="slides">
				<img src="img/carrossel/1.jpg" alt="Imagem 1"> 
				<img src="img/carrossel/2.jpg" alt="Imagem 2"> 
				<img src="img/carrossel/3.jpg" alt="Imagem 3">
				<img src="img/carrossel/4.jpg" alt="Imagem 4">
			</div>
			<button class="prev" onclick="mudarSlide(-1)">&#10094;</button>
			<button class="next" onclick="mudarSlide(1)">&#10095;</button>
		</div>
		<div class="indicadores"></div>
		<%
            ProdutoDAO produtoDAO = new ProdutoDAO();
            
			List<Produto> listaProdutos = produtoDAO.listarProdutos();
			
			List<Produto> flores = produtoDAO.listarProdutosPorCategoria("flor");

         	List<Produto> ferramentasDeJardinagem = produtoDAO.listarProdutosPorCategoria("para");
        %>
        
		<h2 class="subtitulo">Flores</h2>
		<div class="produtos-container">
			<%
        		for (Produto produto : flores) {
        	%>
        	<div class="produto-item">
            	<img src="<%=produto.getUrl_imagem()%>" alt="<%=produto.getDescricao()%>" class="produto-imagem">
            	<h2><%=produto.getDescricao()%></h2>
            	<p class="produto-preco">R$ <%=produto.getPreco()%></p>

            	<form action="${pageContext.request.contextPath}/vendas?action=adicionar" method="POST" class="form-carrinho">
                	<input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>">
                	<input type="submit" value="Adicionar ao Carrinho" class="botao-carrinho">
            	</form>

            	<form action="${pageContext.request.contextPath}/vendas?action=comprar-agora" method="POST" class="form-comprar">
                	<input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>">
                	<input type="submit" value="Comprar Agora" class="botao-comprar">
            	</form>
        	</div>
        	<% 
        	}
        	%>
		</div>
		
      	<h2 class="subtitulo">Itens e Ferramentas</h2>
      	<div class="produtos-container">
        	<%
        		for (Produto produto : ferramentasDeJardinagem) {
        	%>
        	<div class="produto-item">
            	<img src="<%=produto.getUrl_imagem()%>" alt="<%=produto.getDescricao()%>" class="produto-imagem">
            	<h2><%=produto.getDescricao()%></h2>
            	<p class="produto-preco">R$ <%=produto.getPreco()%></p>

            	<form action="${pageContext.request.contextPath}/vendas?action=adicionar" method="POST" class="form-carrinho">
                	<input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>">
                	<input type="submit" value="Adicionar ao Carrinho" class="botao-carrinho">
            	</form>

            	<form action="ComprarAgoraController" method="POST" class="form-comprar">
                	<input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>">
                	<input type="submit" value="Comprar Agora" class="botao-comprar">
            	</form>
        	</div>
        	<% 
        	}
        	%>
        </div>
        
        <h2 class="subtitulo">Todos nossos produtos</h2>
		<div class="produtos-container">
            <%
            	for (Produto produto : listaProdutos) {
            %>
            <div class="produto-item">
                <img src="<%=produto.getUrl_imagem()%>" alt="<%=produto.getDescricao()%>" class="produto-imagem">
                <h2><%=produto.getDescricao()%></h2>
                <p class="produto-preco">R$ <%=produto.getPreco()%></p>
                
                <form action="${pageContext.request.contextPath}/vendas?action=adicionar" method="POST" class="form-carrinho">
                    <input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>">
                    <input type="submit" value="Adicionar ao Carrinho" class="botao-carrinho">
                </form>

                <form action="ComprarAgoraController" method="POST" class="form-comprar">
                    <input type="hidden" name="idProduto" value="<%=produto.getIdProduto()%>">
                    <input type="submit" value="Comprar Agora" class="botao-comprar">
                </form>
            </div>
            <%
            }
            %>
        </div>
    </main>
    <footer>
    	<jsp:include page="includes/footer.jsp" />
    </footer>
</body>
</html>