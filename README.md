# 쇼핑몰 프로젝트 4단계 📦
3단계에서는 구매자와 판매자(관리자)의 서버를 아예 분리해서 썼다면 (포트번호 다르게)<br/>
4단계는 서버는 동시에 사용하되, User 테이블에 role을 추가해 role로 구매자와 판매자(관리자)를 나누게 만들어 볼 것이다.<br/>


# 화면 구현 📺

### 관리자(판매자)👷‍♀️👷‍♂️
- 상품 등록, 수정, 삭제하기
- 상품 목록보기
  - 상품 상세보기
  <br/>

#### [관리자 상품 목록보기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/8dd8017a-e11e-4cd4-a3e7-0bab2640480b)
#### [관리자 상품 상세보기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/956f0569-8a1c-4eb0-a298-3f035c2564bb)
#### [관리자 상품 등록하기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/171958a0-19b2-4a9c-8f02-a2e604bdf447)
#### [관리자 상품 수정하기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/6c222540-5ed9-4bf7-8594-4107c0ebf76a)
  <br/>
 
--- 
### 구매자🙍‍♂️🙍‍♀️
- 상품 목록보기
  - 상품 상세보기
- 주문 조회 페이지
  - 주문 취소하기
<br/>

#### [구매자 상품 목록보기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/af0519c7-edf2-4de9-ab52-7c50e157b3bc)
#### [구매자 상품 상세보기]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/698a97e1-a675-4c72-9afc-05c5088ce66e)
#### [구매자 주문조회]
![image](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/273ae2e3-cf11-48a1-9fe9-a098061d40fd)
  <br/>
  
# 사용기술 🧪
![Springboot](https://img.shields.io/badge/-Springboot-6DB33F)
![Java](https://img.shields.io/badge/-Java-F09820)
![CSS](https://img.shields.io/badge/-CSS-1572B6)
![HTML](https://img.shields.io/badge/-HTML-E34F26)
![JS](https://img.shields.io/badge/-JavaScript-F7DF1E)
![Bootstrap](https://img.shields.io/badge/-Bootstrap-7952B3)
![MyBatis](https://img.shields.io/badge/-MyBatis-B10000)
![H2](https://img.shields.io/badge/-H2Console-41BDF5)  
  <br/>
  
# 기능구현 🔧
### 공통👨‍👩‍👧‍👦
- user 테이블에 role(권한) 추가하여 권한에 따른 화면 분리
  - 비로그인, 'USER' 로그인, 'ADMIN' 로그인 3가지 경우로 분리
    
  <br/>
- 로그인 유효성 체크
  
   <br/>

### 관리자(판매자)👷‍♀️👷‍♂️
#### ✔ 상품등록
- 상품명 중복체크

   <br/>
### 구매자🙍‍♂️🙍‍♀️
#### ✔ 회원가입 
- 사용 중인 아이디, 이메일로 가입불가

#### ✔ 상품구매
- 상품 구매 시 재고 업데이트
- 상품 구매 수량 자바스크립트로 유효성 검사
#### ✔ 주문조회
- 주문 취소 시 재고 업데이트

   <br/>
   
# ERD 다이어그램 📊
![쇼핑몰ERD](https://github.com/JungminK1m/Springboot-Product-Study-V4/assets/118741874/6b422b44-0d43-4b73-9529-3533832b8c96)


# 테이블 생성 📁
```sql
CREATE TABLE user_tb(
	user_id INT PRIMARY KEY auto_increment,
	user_name VARCHAR(20) NOT null,
	user_password VARCHAR(20) NOT null,
	user_email VARCHAR(20) NOT null,
	role VARCHAR(20) NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE product_tb(
	product_id INT PRIMARY KEY auto_increment,
	product_name VARCHAR(20) NOT null,
	product_price INT NOT null,
	product_qty INT NOT null,
	created_at TIMESTAMP NOT null
);

CREATE TABLE orders_tb(
    orders_id INT PRIMARY KEY auto_increment,
    orders_name varchar(20) NOT null,
    orders_price INT NOT null,
    orders_qty INT NOT null,
    product_id INT NOT null,
    user_id INT NOT null,
    created_at TIMESTAMP
);
```
# 더미 데이터 📰
```sql
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('ssar', '1234', 'ssar@nate.com','USER', NOW());
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('cos', '1234', 'cos@nate.com', 'USER', NOW());
INSERT INTO user_tb(user_name, user_password, user_email, role, created_at) VALUES ('admin', '1234', 'admin@nate.com', 'ADMIN', NOW());

INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('바나나', 3000, 98, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('딸기', 2000, 100, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('키위', 4000, 85, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('오렌지', 3500, 50, NOW());
INSERT INTO product_tb(product_name, product_price, product_qty, created_at) VALUES('사과', 1000, 200, NOW());

INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at) VALUES ('바나나', 3000, 2, 1, 1, NOW());
INSERT INTO orders_tb(orders_name, orders_price, orders_qty, product_id, user_id, created_at)  VALUES ('딸기', 2000, 5, 2, 2, NOW());
```

