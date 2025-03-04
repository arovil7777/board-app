<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>게시판 목록</title>
            <link rel="stylesheet" href="/css/style.css">
        </head>

        <body>
            <%@ include file="../layout/header.jsp" %>
                <main>
                    <h2>게시판 목록</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>삭제</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="board" items="${boards}">
                                <tr>
                                    <td>${board.id}</td>
                                    <td><a href="detail/${board.id}">${board.title}</a></td>
                                    <td>${board.writer}</td>
                                    <td>${board.createdAt}</td>
                                    <td><a href="delete/${board.id}">삭제</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="${pageContext.request.contextPath}/board/form" class="btn btn-new">새 글 작성</a>
                </main>
                <%@ include file="../layout/footer.jsp" %>
        </body>

        </html>