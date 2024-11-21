<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu-lateral.css">
<nav>
    <div class="menu-lateral">
        <h1>Minha Conta</h1>
       
        <ul>
            <li><a href="${pageContext.request.contextPath}/views/perfil/meu-perfil.jsp?id=${sessionScope.idCliente}"><i><img src="${pageContext.request.contextPath}/img/user.png" /></i> Dados da Minha Conta</a></li>
            <li><a href="${pageContext.request.contextPath}/clientes?action=pedidos"><i><img src="${pageContext.request.contextPath}/img/encomenda.png" /></i>Meus Pedidos</a></li>
            <li><a href="${pageContext.request.contextPath}/views/perfil/meu-endereco.jsp?id=${sessionScope.idCliente}"><i><img src="${pageContext.request.contextPath}/img/address.png" /></i> Meu Endereço</a></li>
            <li><a href="${pageContext.request.contextPath}/index.jsp"><i><img src="${pageContext.request.contextPath}/img/home.png" /></i> Voltar para Loja</a></li>
            <li><a href="${pageContext.request.contextPath}/login?action=logout"><i><img src="${pageContext.request.contextPath}/img/logoff.png" /></i> Sair</a></li>
        </ul>
    </div>
</nav>

<script src="${pageContext.request.contextPath}/js/menu-adm.js"></script>