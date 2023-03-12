# 1차 프로젝트 - 도서관 시스템

## 🔍 진행 방식

- 미션은 **기능 요구 사항, 프로그래밍 요구 사항, 과제 진행 요구 사항** 세 가지로 구성되어 있다.
- 세 개의 요구 사항을 만족하기 위해 노력한다. 특히 기능을 구현하기 전에 기능 목록을 만들고, 기능 단위로 커밋 하는 방식으로 진행한다.
- 기능 요구 사항에 기재되지 않은 내용은 스스로 판단하여 구현한다.

## 🚨 프로젝트 제출 전 체크 리스트

- 기능 구현을 완료한 뒤 아래 가이드에 따라 테스트를 실행했을 때 모든 테스트가 성공하는지 확인한다.
- **테스트가 실패할 경우 미제출 처리**되므로, 반드시 확인 후 제출한다.

### 테스트 실행 가이드

- 터미널에서 `java -version`을 실행하여 Java 버전이 11인지 확인한다. 또는 Eclipse 또는 IntelliJ IDEA와 같은 IDE에서 Java 11로 실행되는지 확인한다.
- 터미널에서 Mac 또는 Linux 사용자의 경우 `./gradlew clean test` 명령을 실행하고,   
  Windows 사용자의 경우  `gradlew.bat clean test` 명령을 실행할 때 모든 테스트가 아래와 같이 통과하는지 확인한다.

```
BUILD SUCCESSFUL in 0s
```

---

## 🎯 프로젝트 개요

- Spring Boot 2.7.9
- Java 11
- Gradle
- Dependencies
  - Spring Web
  - Spring Data JPA
  - H2 Database
  - Validation
  - Lombok

### H2 데이터베이스 설정

- 파일 생성: `jdbc:h2:~/library`
- 이후 접속: `jdbc:h2:tcp://localhost/~/library`

### 패키지 구조

- domain
- repository
- service
- 필요하다면 패키지 추가는 가능하다.

### ERD
[ERD](https://www.erdcloud.com/d/9j2ZL2bnLWmcc99dm)

---

## 🚀 기능 요구 사항

도서관 시스템에 들어가는 기능을 구현해야 한다. 도서관 시스템 기능은 아래와 같다.

- 모든 테이블은 ERD에 명시된 데이터 타입과 길이 제한이 적용된다. 모든 컬럼에 대해 `NOT NULL` 조건이 붙는다. 
- 도서 정보 테이블을 제외한 모든 테이블의 PK는 `@GeneratedValue`를 적용한다.
- 모든 사용자 정의 예외 클래스는 `IllegalArgumentException`를 상속받는다.
  - 등록, 수정시 데이터에 null값이 존재하는 경우 `NullExistException`를 발생시킨다.
  - 등록, 수정시 데이터 범위를 벗어난 경우 `DataOutOfRangeException`를 발생시킨다.
### 회원 기능 요구 사항

- 회원을 등록할 수 있다.
  - 연락처, 이메일은 중복 등록이 불가능하다. 중복된 경우 `DuplicateException`를 발생시킨다.
  - 생년월일이 미래인 경우 `DateException`를 발생시킨다.
  - 이메일 포멧(example@excample.com)이 맞지 않는 경우 `EmailFormatException`를 발생시킨다. 
- 회원을 전체 조회, 이름으로 조회 할 수 있다.
- 회원의 정보 중 연락처, 이메일, 주소(우편번호, 메인주소, 상세주소)를 수정할 수 있다.
  - 연락처, 이메일은 중복 등록이 불가능하다. 중복된 경우 `DuplicateException`를 발생시킨다.
  - 이메일 포멧(example@excample.com)이 맞지 않는 경우 `EmailFormatException`를 발생시킨다.
- 회원 정보를 삭제할 수 있다.

### 카테고리 기능 요구 사항

- 카테고리를 추가할 수 있다.
  - 카테고리 이름은 중복될 수 없다. 중복된 경우 `DuplicateException`를 발생시킨다.
- 등록된 카테고리를 전체 조회할 수 있다.
- 카테고리의 이름을 수정할 수 있다.
  - 카테고리 이름은 중복될 수 없다. 중복된 경우 `DuplicateException`를 발생시킨다.
- 등록된 카테고리를 삭제할 수 있다.

### 도서 정보 기능 요구 사항

- 도서의 정보를 등록할 수 있다.
  - ISBN, 도서명은 중복될 수 없다. 중복된 경우 `DuplicateException`를 발생시킨다.
- 등록된 도서를 전체 조회, 카테고리, 도서명, 출판사, 저자로 조회할 수 있다.
- 등록된 도서의 정보는 수정할 수 없다.
- 등록된 도서 정보를 삭제할 수 있다.

### 도서 기능 요구 사항

- 도서관에 새로운 도서를 등록할 수 있다.
- 등록된 도서는 전체 조회, isbn, 대출 예약여부로 조회할 수 있다.
- 등록된 도서는 삭제할 수 있다.

### 도서 대출 기능

- 도서를 대출할 수 있다.
  - 한 사람당 최대 5권까지 대출이 가능하다. 5권을 넘어선 경우 `MaxLoanException`를 발생시킨다.
  - 대출 기간은 빌린 시점으로부터 일주일이다.
  - 예약이 된 도서는 대출이 불가능하다. 불가능할 경우 `ReservationException`를 발생시킨다. 
- 도서를 반납할 수 있다.

### 도서 예약 기능

- 도서 예약을 등록할 수 있다.
- 등록된 도서 예약을 조회할 수 있다.
- 등록된 도서 예약을 취소(삭제)할 수 있다.

### 추가 요구 사항

- BookStatus, LoanStatus는 Java Enum을 적용한다.
  - BookStatus: RESERVATION, LOAN, NONE
  - LoanStatus: LOAN, RETURN
- 회원 테이블에서 주소(우편번호, 메인주소, 상세주소)는 값타입(Address)를 적용한다.
- 단방향 매핑으로 설계를 한다. 필요시 최소한의 양방향 매핑을 적용한다.
- 모든 도메인 클래스에는 Setter를 사용하지 않는다. 

---

## 🎯 프로그래밍 요구 사항

- JDK 11 버전에서 실행 가능해야 한다. **JDK 11에서 정상적으로 동작하지 않을 경우 0점 처리한다.**
- `build.gradle` 파일을 변경할 수 없다.
- [Java 코드 컨벤션](https://github.com/woowacourse/woowacourse-docs/tree/master/styleguide/java) 가이드를 준수하며 프로그래밍한다.
- 프로그램 구현이 완료되면 `ApplicationTest`의 모든 테스트가 성공해야 한다. **테스트가 실패할 경우 미제출 처리한다.**
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
  - 함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.
- else 예약어를 쓰지 않는다.
  - 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
  - else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
- Java Enum을 적용한다.
- 도메인 로직에 단위 테스트를 구현해야 한다.
- **테스트 주도 설계(TDD)를 적용**한다.
- **MVC 패턴을 적용**한다.
- **Controller는 구현하지 않는다.**

### 추가 요구 사항

- **JPQL만 사용 가능하다.**

---

## ✏️ 과제 진행 요구 사항

- 프로젝트는 [spring-library](https://github.com/Chaos0103/spring-library) 저장소를 Fork & Clone해 시작한다.
- **기능을 구현하기 전 `docs/README.md`에 구현할 기능 목록을 정리**해 추가한다.
- **Git의 커밋 단위는 앞 단계에서 `docs/README.md`에 정리한 기능 목록 단위**로 추가한다.
  - [커밋 메시지 컨벤션](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 가이드를 참고해 커밋 메시지를 작성한다.

