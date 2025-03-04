<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>게시판</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
        </head>

        <body>
            <header>
                <nav>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/">홈</a></li>
                        <li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
                        <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
                    </ul>
                </nav>
            </header>
            <main>