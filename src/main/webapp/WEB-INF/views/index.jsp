<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="layout/header.jsp" %>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>환영합니다</title>
            <link rel="stylesheet" href="{pageContext.request.contextPath}/css/styles.css">
        </head>

        <body>
            <h2>환영합니다!</h2>
            <a href="/board/list">게시판 목록으로 이동</a>
            <%@ include file="layout/footer.jsp" %>
        </body>

        </html>