<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lab1</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<body>
<div class="header">
    <img src="./resources/book.png" alt="Book">
    <hr color="#f28f47" />
    <h1>Book Shop</h1>
    <hr color="#f28f47" />
</div>
<div class="main">
    <form action="${pageContext.request.contextPath}/editBook" method="post">
        <div class="modal-content">
            <input name="id" type="hidden" value="${id}" />
            <div class="modal-header">Книга №${id}</div>
            <p>Название <input name="name" id="nameBook" type="text" value="${name}" required/></p>
            <p>Авторы <input name="author" id="authorBook" type="text" value="${authors}" required/></p>
            <p>Год <input name="year" id="yearBook" type="number" value="${year}" required/></p>
            <p>Жанры <input name="genre" id="genreBook" type="text" value="${genres}" required/></p>
            <p>Цена <input name="price" id="price" type="number" value="${price}" required/></p>
            <p>Описание</p>
            <p>
                <textarea name="description" id="descriptionBook" rows="10" cols="45" type="text" maxlength="50">
                    ${description}</textarea>
            </p>
            <div class="modal-buttons">
                <button id="go-back" class="modal-button" type="button">Закрыть</button>
                <button  class="modal-button" type="submit">Сохранить</button>
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
