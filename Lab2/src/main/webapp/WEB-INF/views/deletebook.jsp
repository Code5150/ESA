<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 28.11.2021
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lab1</title>
    <link rel="stylesheet" href="styles.css" /></title>
</head>
<body>
<div class="header">
    <img src="../../resources/book.png" alt="Book">
    <hr color="#f28f47" />
    <h1>Book Shop</h1>
    <hr color="#f28f47" />
</div>
<div class="main">
    <form action="${pageContext.request.contextPath}/deleteBook" method="post">
        <div class="modal-content">
            <input name="id" type="hidden" value="${id}" />
            <div class="modal-header">Удалить книгу №${id} ?</div>
            <p>Название <input name="name" id="nameBook" type="text" value="${name}" disabled/></p>
            <div class="modal-buttons">
                <button id="go-back" class="modal-button" type="button">Отменить</button>
                <button  class="modal-button" type="submit">Удалить</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
<script>
    document.getElementById("go-back").addEventListener('click', ()=>{
        history.back();
    });
</script>
