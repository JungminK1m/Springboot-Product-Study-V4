<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>
        <div class="text-center m-4">
            <h1>구매목록 페이지</h1>
        </div>
        <div class="container">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>주문번호</th>
                        <th>상품명</th>
                        <th>상품가격</th>
                        <th>구매수량</th>
                        <th>가격</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${orderedProduct}" var="op" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${op.ordersName}</a></td>
                            <td>${op.ordersPrice}원</td>
                            <td>${op.ordersQty}개</td>
                            <td>${op.ordersPrice * op.ordersQty}원</td>
                            <td>
                                <form action="/ordersList/delete" method="post">
                                    <input name="ordersId" type="hidden" value="${op.ordersId}">
                                    <button class="btn btn-success btn-sm" type="submit">취소하기</button>
                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%@ include file="../layout/footer.jsp" %>