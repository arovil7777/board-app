<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>

    <head>
        <title>게시글 작성</title>
    </head>

    <body>
        <h2>게시글 작성</h2>
        <form action="save" method="post">
            <input type="hidden" name="id" value="${board.id}">
            제목: <input type="text" name="title" value="${board.title}"><br>
            작성자: <input type="text" name="writer" value="${board.writer}"><br>
            내용: <textarea name="content">${board.content}</textarea><br>
            <button type="submit">저장</button>
        </form>

        <a href="/board/list">취소</a>
    </body>

    </html>