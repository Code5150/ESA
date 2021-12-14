<%@ page import="java.util.List" %>
<%@ page import="com.example.esa_lab2.dto.Book" %>
<%@ page import="com.example.esa_lab2.dto.Author" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>lab1</title>
    <link rel="stylesheet" href="../../resources/styles/styles.css" />
</head>
<body>
<div class="header">
    <img src="../../resources/images/book.png" alt="Book">
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
            List<Book> books = (List<Book>) request.getAttribute("books");
            if(books != null) {
                for (Book book : books) {
                    out.println("<div class=\"row\">");
                    out.println("<div class=\"element\">" + book.getName() + "</div>");
                    String authorsToStr = book.getAuthors().stream().map(Author::getName)
                            .limit(2).reduce((a, b) -> b != null ? a + ", " + b: a).orElseThrow();
                    if (book.getAuthors().size() > 2) { authorsToStr += "..."; }
                    out.println("<div class=\"element\">" + authorsToStr + "</div>");
                    out.println("<div class=\"element\">" + (book.getEditionYear().getYear() + 1900) + "</div>");
                    out.println("<div class=\"element\">" + book.getPrice() + "</div>");
                    out.println(new StringBuilder().append("<form id=\"edit-form\" action=\"")
                            .append(request.getContextPath()).append("/editBook\" method=\"get\">")
                            .append("<input id=\"id\" name=\"id\" value=\"").append(book.getId()).append( "\" hidden>")
                            .append("<div class=\"edit-modal-button\">")
                            .append("<button id=\"#editButton\" type=\"submit\">")
                            .append("<img src=\"resources/images/pen.png\" alt=\"ред.\"/>")
                            .append("</button>")
                            .append("</div>")
                            .append("</form>")
                    );
                    out.println(new StringBuilder().append("<form id=\"del-form\" action=\"")
                            .append(request.getContextPath())
                            .append("/deleteBook\" method=\"delete\">")
                            .append("<input id=\"id\" name=\"id\" value=\"")
                            .append(book.getId()).append("\" hidden>")
                            .append("<div class=\"del-button\">")
                            .append("<button type=\"submit\">")
                            .append("<img src=\"resources/images/delete.png\" alt=\"уд.\" />")
                            .append("</button>")
                            .append("</div>")
                            .append("</form>")
                    );
                    out.println("</div>");
                }
            }
         %>
    </div>
</div>
</body>
</html>