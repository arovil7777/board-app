<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>게시글 상세보기</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        </head>

        <body>
            <%@ include file="../layout/header.jsp" %>
                <main>
                    <div class="detail-container">
                        <h2 class="detail-title">${board.title}</h2>
                        <p><strong>작성자:</strong> ${board.writer}</p>
                        <p><strong>작성일:</strong> ${board.createdAt}</p>
                        <p class="detail-content">${board.content}</p>

                        <div>
                            <a href="${pageContext.request.contextPath}/board/update/${board.id}"
                                class="btn btn-update">수정</a>
                            <a href="${pageContext.request.contextPath}/board/delete/${board.id}"
                                class="btn btn-delete">삭제</a>
                            <a href="${pageContext.request.contextPath}/board/list" class="btn btn-back">목록</a>
                        </div>
                    </div>
                </main>
                <%@ include file="../layout/footer.jsp" %>
        </body>

        </html>