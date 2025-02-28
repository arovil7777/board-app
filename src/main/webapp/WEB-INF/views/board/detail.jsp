<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
        <title>게시글 상세보기</title>
    </head>

    <body>
        <h2>${board.title}</h2>
        <p>작성자: ${board.writer}</p>
        <p>${board.content}</p>
        <p>작성일: ${board.createdAt}</p>
        <a href="/board/list">목록</a>
    </body>

    </html>