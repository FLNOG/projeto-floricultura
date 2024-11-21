<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra Realizada com Sucesso</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/compra-sucesso.css">
</head>
<body>
    <div class="container">
        <div class="checkmark">✔️</div>
        <h1>Compra realizada com sucesso!</h1>
        <p>Obrigado por comprar conosco! Sua compra foi concluída com sucesso.</p>
        <a href="${pageContext.request.contextPath}/index.jsp" class="button">Voltar à Loja</a>
    </div>
</body>
</html>