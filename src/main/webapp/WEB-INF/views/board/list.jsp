<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>
            <title>게시판 목록</title>
        </head>

        <body>
            <h2>게시판 목록</h2>
            <table border="1">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>액션</th>
                </tr>
                <c:forEach var="board" items="${boards}">
                    <tr>
                        <td>${board.id}</td>
                        <td><a href="detail/${board.id}">${board.title}</a></td>
                        <td>${board.writer}</td>
                        <td>${board.createdAt}</td>
                        <td><a href="delete/${board.id}">삭제</a></td>
                    </tr>
                </c:forEach>
            </table>
            <a href="/board/form">새 글 작성</a>
        </body>

        </html>