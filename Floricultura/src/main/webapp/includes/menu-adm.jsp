<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu-adm.css">
<nav id="menu">
    <div class="MyStore">
        <div>
            <h1 id="nome_menu">Lilly</h1>
        </div>
        <div>
            <h1 id="nome_store">Bloom's</h1>
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/img/home.png" alt="img casa" class="img">Loja</a>
	<a href="${pageContext.request.contextPath}/views/produtos/cadastro-produto.jsp"><img src="${pageContext.request.contextPath}/img/shop.png" class="img" alt="img produtos">Produtos</a>
	<a href="${pageContext.request.contextPath}/views/clientes/cadastro-cliente.jsp"><img src="${pageContext.request.contextPath}/img/user.png" class="img" alt="img clientes">Clientes</a>
	<a href="${pageContext.request.contextPath}/views/relatorios/relatorio-sintetico.jsp"><img src="${pageContext.request.contextPath}/img/report.png" class="img" alt="img relatorio">Relatórios</a>
	
</nav>
<script src="${pageContext.request.contextPath}/js/menu-adm.js"></script>