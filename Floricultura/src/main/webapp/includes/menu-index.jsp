<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu-index.css">
<header class="menu-container">
    <div class="logo">
    	<a href="${pageContext.request.contextPath}/index.jsp">
        	<img src="${pageContext.request.contextPath}/img/Logo.svg" alt="Logo da Loja" class="logo-imagem">
    	</a>
    </div>
    <nav class="menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Página Inicial</a></li>
            <li><a href="${pageContext.request.contextPath}/views/login/login.jsp">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/views/login/cadastro.jsp">Cadastrar-se</a></li>
        </ul>
    </nav>
</header>