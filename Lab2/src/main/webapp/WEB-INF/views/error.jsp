<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 28.11.2021
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <style type="text/css">
        body {
            padding-top: 5%;
            display: block;
            margin: 0 auto;
            text-align: center;
        }
        h1 {
            font-size: 40px;
            text-align: center;
        }
        p {
            font-size: 28px;
            text-align: center;
        }
        button{
            display: block;
            font-size: 24px;
            padding: 10px 160px;
            background: white;
            border: 2px solid;
            margin: 0 auto;
            cursor: pointer;
        }
        button:hover{
            background: grey;
        }
    </style>
</head>
<body>
<h1>Произошла ошибка</h1>
<p>Статус ошибки ${pageContext.errorData.statusCode}</p>
<button id="go-back">Ok</button>
</body>
</html>
<script>
    document.getElementById("go-back").addEventListener('click', ()=>{
        history.back();
    });
</script>
