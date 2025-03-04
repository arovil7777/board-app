<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>게시글 작성</title>
            <link rel="stylesheet" href="{pageContext.request.contextPath}/css/styles.css">
        </head>

        <body>
            <%@ include file="../layout/header.jsp" %>
                <main>
                    <h2>${board.id == null ? "게시글 작성" : "게시글 수정"}</h2>
                    <form action="${pageContext.request.contextPath}/board/save" method="post">
                        <input type="hidden" name="id" value="${board.id}">
                        <label for="title">제목</label>
                        <input type="text" name="title" id="title" value="${board.title}" required><br>

                        <label for="writer">작성자</label>
                        <input type="text" name="writer" id="writer" value="${board.writer}" required><br>

                        <label for="content">내용</label>
                        <textarea name="content" id="content" row="5" required>${board.content}</textarea><br>

                        <button type="submit">${board.id == null ? "등록" : "수정"}</button>
                        <a href="${pageContext.request.contextPath}/board/list" class="btn btn-back">취소</a>
                    </form>
                </main>
                <%@ include file="../layout/footer.jsp" %>
        </body>

        </html>