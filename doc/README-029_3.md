# 29_3 - 리팩토링: 소켓 생성부분을 캡슐화 하기

### 1: 서버와 통신을 담당할 실제 작업자의 규칙을 정의
- dao.proxy.Worker 인터페이스를 정의한다.
### 2: DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의
- dao.proxy.DaoProxyHelper 를 정의한다.
### 3: DaoProxyHelper를 사용하도록 DaoProxy를 변경
- dao.proxy.XxxDaoProxy 를 변경한다.
### 4: DaoProxyHelper가 추가된 것에 맞춰 ClientApp 을 변경
- ClientApp 변경한다.
  