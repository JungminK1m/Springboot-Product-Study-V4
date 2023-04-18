<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <title>Product</title>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" />
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" rel="stylesheet" />
            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet" />
            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
        </head>
        <style>
            .gitlink {
                text-decoration-line: none;
                color: rgb(255, 105, 138);
                font-weight: bolder;
                background-color: rgb(255, 228, 154);
            }
            .center {
                display: flex;
                justify-content: center;
            }
        </style>

        <body>
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                <div class="container-fluid">
                    <h3 style="color: white;">🍌 쇼핑몰 🍓</h3>
                    
                    <div class="collapse navbar-collapse" id="collapsibleNavbar">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/product">상품목록페이지</a>
                            </li>
                            <c:choose>

                               <c:when test="${empty principal}">
                                <li class="nav-item">
                                    <a class="nav-link" href="/loginForm">로그인</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/adminLoginForm">관리자 로그인</a>
                                </li>
                               </c:when>

                               <c:when test="${principal.role == 'USER'}" >
                                <li class="nav-item">
                                    <a class="nav-link" href="/orders">주문조회</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/logout">로그아웃</a>
                                </li>
                               </c:when>

                               <c:otherwise>
                                <%-- ${principal.role == 'ADMIN'} 일 때 --%>
                                <li class="nav-item">
                                    <a class="nav-link" href="/productSave">상품등록페이지</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="/logout">로그아웃</a>
                                </li>
                               </c:otherwise>
                               
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </nav>
        </body>

        </html>