<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>오류 페이지</title>
            <link rel="stylesheet" href="{pageContext.request.contextPath}/css/styles.css">
        </head>

        <body>
            <h2>오류가 발생했습니다.</h2>
            <p>요청하신 페이지를 찾을 수 없습니다.</p>
            <a href="/board/list">게시판 목록으로 돌아가기</a>
            <%@ include file="../layout/footer.jsp" %>
        </body>

        </html>