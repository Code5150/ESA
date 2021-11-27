<%@ page import="java.util.List" %>
<%@ page import="com.example.esa_lab1.dto.Book" %>
<%@ page import="com.example.esa_lab1.dto.Author" %>
<%@ page import="com.example.esa_lab1.dao.BookDAO" %>
<%@ page import="com.example.esa_lab1.utils.HibernateUtils" %>
<%@ page import="jakarta.servlet.jsp.PageContext" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
    <form action="${pageContext.request.contextPath}/newBook" method="get">
        <button type="submit" class="add-button">+ Книга</button>
    </form>

    <div class="table-book">
        <div class="head row">
            <div class="element">Название книги</div>
            <div class="element">Автор</div>
            <div class="element">Год</div>
            <div class="element">Цена</div>
            <div class="filler"></div>
        </div>

        <%

            if (!HibernateUtils.entityManagerExists()) HibernateUtils.createEntityManager();

            List<Book> books = BookDAO.selectAll();
            if(books != null) {
                for (Book book : books) {
                    out.println("<div class=\"row\">");
                    out.println("<div class=\"element\">" + book.getName() + "</div>");
                    StringBuilder authorsToStr = new StringBuilder();
                    int numAuthors = 2;
                    for (Author author : book.getAuthors()) {
                        numAuthors--;
                        if (numAuthors < 0) {
                            authorsToStr.append("...");
                            break;
                        }
                        authorsToStr.append(author.getName()).append(" ");
                    }
                    out.println("<div class=\"element\">" + authorsToStr + "</div>");
                    out.println("<div class=\"element\">" + (book.getEditionYear().getYear() + 1900) + "</div>");
                    out.println("<div class=\"element\">" + book.getPrice() + "</div>");
                    out.println("" +
                            "<form id=\"edit-form\" action=\"" + request.getContextPath() + "/editBook\" method=\"get\">" +
                            "<input id=\"id\" name=\"id\" value=\"" + book.getId() + "\" hidden>" +
                            "<div class=\"edit-modal-button\">" +
                            "<button id=\"#editButton\" type=\"submit\">" +
                            "<img src=\"./resources/pen.png\" alt=\"ред.\"/>" +
                            "</button>" +
                            "</div>" +
                            "</form>"
                    );
                    out.println("" +
                            "<form id=\"del-form\" action=\"" + request.getContextPath() + "/deleteBook\" method=\"delete\">" +
                            "<input id=\"id\" name=\"id\" value=\"" + book.getId() + "\" hidden>" +
                            "<div class=\"del-button\">" +
                            "<button type=\"submit\">" +
                            "<img src=\"./resources/delete.png\" alt=\"уд.\" />" +
                            "</button>" +
                            "</div>" +
                            "</form>");
                    out.println("</div>");
                }
            }

            //HibernateUtils.destroyEntityManager();
         %>
    </div>
</div>
</body>
</html>