# spring03_assignment
## 프로젝트 명 : 스프링 입문 주차 개인 과제
- 회원가입, 로그인, 댓글 작성/수정/삭제 기능이 추가된 나만의 블로그 백엔드 서버 만들기
## 과제 링크
- http://54.180.101.225/posts

## 요구사항

- 서비스 완성
  1. 전체 게시글 목록 조회 API
     - 제목, 작성자명, 작성 날짜를 조회하기
     - 작성 날짜 기준으로 내림차순 정렬하기
  2. 게시글 작성 API
     - 제목, 작성자명, 비밀번호, 작성 내용을 입력하기
  3. 게시글 조회 API
     - 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기(검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
  4. 게시글 비밀번호 확인 API
    - 비밀번호를 입력 받아 해당 게시글의 비밀번호와 일치여부 판단하기
  5. 게시글 수정 API
     - 제목, 작성자명, 비밀번호, 작성 내용을 수정되게 하기
  6. 게시글 삭제 API
     - 글이 삭제되게 하기
- AWS 배포
  1. RDS 연결
     - MySQL을 이용하기
  2. EC2 배포
     - Ubuntu EC2 를 구매한 뒤, 8080 포트와 80번 포트를 연결하여 포트 번호 없이도 서비스에 접속 가능하게 하기
- Use Case
  
  ![image](https://user-images.githubusercontent.com/109592005/185185395-ce6ef835-6103-4abb-b793-593bd4ba8dd9.png)

## 환경
- Java 11
- Spring Boot 2.7.1
- IntelliJ Ultimate 2022.1
- Spring Web
- Lombok
- H2
- JPA
- MySQL
## API 설계
|  화면  |      기능      | Method |    URL     |    Return    |
|:----:|:------------:|:------:|:----------:|:------------:|
| 메인화면 | 전체 게시글 목록 조회 |  GET   |   posts    | Lsit\<Post\> | 
| 메인화면 |    게시글 조회    |  GET   | posts/{id} |     Post     | 
| 메인화면 |    게시글 작성    |  POST  |   posts    |     Post     |
| 메인화면 |    게시글 비밀번호 확인    |  POST  |   posts/{id}    |     Boolean     |
| 메인화면 |    게시글 수정    |  PUT   | posts/{id} |     Long     |  
| 메인화면 |    게시글 삭제    | DELETE | posts{id}  |     Long     | 

## why
1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
  - 게시글 id의 경우는 query / param 형식으로 전달
  - 나머지 필요한 정보들의 경우 body 형식으로 전달
2. 어떤 상황에 어떤 방식의 request를 써야하나요?
  - 조회: GET, 등록: POST, 수정: PUT, 삭제: DELETE
3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
  - 리소스(posts)가 명확히 식별되고, http 메소드를 통해 역할을 구분지었다.
4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
  - contoller는 창구역할을 하므로 메인 비즈니스 로직은 되도록 service 단에서 이루어지도록 구현했다.
  - repository는 DB와의 소통창구이며, 리소스 추가, 수정, 조회, 삭제 작업을 한다.
5. 작성한 코드에서 빈(Bean)을 모두 찾아보세요!
  - PostController, PostService, PostRepository
