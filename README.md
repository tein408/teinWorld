# teinWorld
Spring Boot Project


## 개발 목적
- Web 백엔드의 보편적인 기술 중 하나인 Spring boot와 Spring Security의 기본기를 다지기 위함입니다.

## 개발 환경
- Java 11
- Spring boot 2.5.3, Spring Data JPA 2.5.3, Spring Security 5.5.1
- H2 database 1.4.200
- gradle 1.0.11
- thymeleaf 3.0.12
- bootstrap 5.0.2

## 문제 해결
<details>
  <summary>로그인 관련 문제</summary>
  <div markdown="1">

1. 문제 확인
- 회원 가입 요청 후 DB에 회원 정보 정상 저장됨
- 로그인 요청 시 주소줄에서 /login?error 발생
- 콘솔창 확인해보면 `Expected: class java.lang.Long, got class java.lang.String;` 맞지 않는 타입의 클래스 제공되어 발생한 문제라는 것을 알 수 있었다.
- @Slf4j 이용해 로그 확인해보니 이메일을 이용해 회원 정보를 찾는 메서드에서 전달 받는 인자 값이 전달되지 않아 발생하는 문제였다.
2. 문제 해결
- login 페이지를 custom하고, 로그인에 필요한 id/password의 name도 custom 해야했는데 그러지 않아서 발생한 문제이므로
- `Security Config.java`에서 `loginProcessingUrl(), usernameParameter(), passwordParameter()` 을 사용해 내가 원하는 값으로 custom 하였다. 

  </div>
</details>

<details>
  <summary>회원 정보 검색</summary>
  <div markdown="1">

1. 문제 확인
- 회원 정보 중 이메일을 이용해 회원 정보 검색 결과를 출력하고자 하였으나 타입이 맞지 않다는 error 발생
2. 문제 해결
- EntityManager에서 제공하는 메서드의 정확한 확인을 위해 [공식 문서](https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html) 확인
- EntityManager의 메서드 중 find()는 primary key를 이용해 값을 찾는 메서드
- 이를 제대로 알고 사용했어야 하는데, 여기에 PK가 아닌 다른 컬럼 값을 넣어 타입이 맞지 않는 문제가 발생
- find() 대신 custom query를 쓸 수 있는 `createQuery()`를 이용해 값을 검색하고,
- unique 제약 조건이 걸린 컬럼이므로 위 값에 다시 `getSingleResult()` 사용해 결과값을 받아왔다.

  </div>
</details>

## 기타
- 소스 코드 버전 관리를 위해 git을 사용합니다.
  - 보다 좋은 commit message를 남기기 위해 [Udacity Git Commit Message Style Guide](https://udacity.github.io/git-styleguide/) 를 참조하였습니다.

<!--
<details markdown="1">
<summary>접기/펼치기</summary>
test
</details>
1 r
2
3
-->
