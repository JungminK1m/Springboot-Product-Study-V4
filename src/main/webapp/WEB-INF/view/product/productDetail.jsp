<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="center">
            <div style="margin: 20px;">

                <form type="submit" action="/orders/${productId}" method="post" onsubmit="return qtyCheck();>


                    <%-- productName 과 ordersName 연결하기 --%>
                    <input name="ordersId" type="hidden" value="${product.productId}">
                    <input name="ordersName" type="hidden" value="${product.productName}">
                    <input name="ordersPrice" type="hidden" value="${product.productPrice}">
                    <table border="1" style="width: 500px; height: 200px; text-align: center;">

                        <tr style="border: 1px solid">
                            <th style="background-color: rgb(185, 185, 185)">상품명</th>
                            <th>${product.productName}</th>
                        </tr>
                        <tr style="border: 1px solid">
                            <th style="background-color: rgb(185, 185, 185)">상품명</th>
                            <td>${product.productPrice}원</td>
                        </tr>
                        <tr style="border: 1px solid">
                            <th style="background-color: rgb(185, 185, 185)">상품명</th>
                            <td>
                                <span id="productQty">${product.productQty}</span>
                                <span>개</span>
                            </td>
                        </tr>
                    </table>

                    <c:choose>
                        <%-- 로그인 했을 때만 구매하기 버튼 뜨게 하기 --%>
                        <c:when test="${empty principal}" >
                            <div class="center" style="margin-top: 40px; text-align: center;">
                                <h5>상품을 구매하시려면 로그인 해주세요😀</h5>
                            </div>
                        </c:when>

                        <%-- USER일 때는 구매하기 버튼 뜨게 하기 --%>
                        <c:when test="${principal.role == 'USER'}">
                            <div class="center" style="margin-top: 20px; text-align: center;">

                                수량 :<input name="ordersQty" type="number" min="0" class="form-control mb-3"
                                    style="width: 200px;">
                                <button
                                    style="width: 240px; height: 50px; margin-right: 20px; background-color: rgb(255, 210, 199);">구매하기</button>
                            </div>
                        </c:when>
                    </c:choose>
                </form>

                        <%-- ADMIN일 때는 수정하기/삭제하기 버튼 뜨게 하기 --%>
                        <c:if test="${principal.role == 'ADMIN'}">
                            <div class="center" style="margin-top: 20px; text-align: center;">
                                <form action="/product/${product.productId}/updateForm" method="get">
                                    <button
                                        style="width: 240px; height: 50px; margin-right: 20px; background-color: rgb(255, 210, 199);">수정하기</button>
                                </form>
                                <form action="/product/${product.productId}/delete" method="post">
                                    <button
                                        style="width: 240px; height: 50px; margin: auto; background-color: rgb(250, 255, 182);">삭제하기</button>
                                </form>
                            </div>
                        </c:if>

            </div>
        </div>

        <script>
            function qtyCheck() {
                let ordersQty = parseInt(document.getElementsByName("ordersQty")[0].value); // 주문수량 150
                let productQty = parseInt(document.getElementById("productQty")); //재고수량 95

                <%-- 반복 코드 줄이기 위해 return false; 를 변수화 --%>
                let ret = false

                console.log("orderQty : " + ordersQty);
                console.log("productQty : " + productQty);

                <%-- 주문 수량이 undefined인지 여부 --%> 
                if (ordersQty && productQty) {
                    // 주문수량이 존재함
                    // 주문수량 > 재고
                    if (ordersQty > productQty) {
                        alert("재고 수량을 초과하여 구매할 수 없습니다.");
                        
                    }
                    <%-- 주문수량 = 0 or 주문수량 < 0 --%> 
                    else if (ordersQty < 1) {
                        alert("1개 이상 구매할 수 있습니다.");
                    }
                    <%-- 그게 아니라면 true로 반환 !ret 이니까 true임 --%> 
                    else {
                        ret = !ret;
                    }
                } else {
                    <%-- 주문수량이 undefined임 --%> 
                    alert("주문수량을 입력해 주세요.");
                }
                return ret;
            }
        </script>
    <%@ include file="../layout/footer.jsp" %>