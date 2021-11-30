<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>lab1</title>
    <link rel="stylesheet" href="styles.css" />
</head>
<jsp:body>
<div class="header">
    <img src="../../resources/book.png" alt="Book">
    <hr color="#f28f47" />
    <h1>Book Shop</h1>
    <hr color="#f28f47" />
</div>

<div class="main">
    <form action="${pageContext.request.contextPath}/newBook" method="get">
        <button type="submit" class="add-button">+ Книга</button>
    </form>

    <div class="table-book">
        <div class="head row">
            <div class="element">Название книги</div>
            <div class="element">Авторы</div>
            <div class="element">Год</div>
            <div class="element">Цена</div>
            <div class="filler"></div>
        </div>

        <c:if test="${!empty listBooks}">
            <c:forEach items="${listBooks}" var="book">
                <div class="row">
                    <div class="element">${book.getName()}</div>
                    <div class="element">${book.getAuthors()}</div>
                    <div class="element">${book.getYear()}</div>
                    <div class="element">${book.getPrice()}</div>
                    <form id="edit-form" action="${pageContext.request.contextPath}/editBook" method="get">
                        <input id="edit-id" name="id" value="${book.getId()}" hidden>
                        <div class="edit-modal-button">
                            <button id="#editButton" type="submit">
                                <img src="resources/pen.png" alt="ред.">
                            </button>
                        </div>
                    </form>
                    <form id="del-form" action="${pageContext.request.contextPath}/deleteBook" method="get">
                        <input id="delete-id" name="id" value="${book.getId()}" hidden>
                        <div class="del-button">
                            <button id="#deleteButton" type="submit">
                                <img src="resources/delete.png" alt="уд.">
                            </button>
                        </div>

                    </form>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</jsp:body>
</html>