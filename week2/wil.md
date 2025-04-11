# Week2 WIL
## 스프링 빈과 스프링 컨테이너의 개념
### 스프링 빈 (Spring Bean)
- **정의:** 어플리케이션 전역에서 사용할 **공용 객체**로, **스프링 컨테이너**에 저장해두고 필요 시 받아서 사용
- **특징:**
  - 빈은 서로 의존하며 상호 요청할 수 있음
  - 빈을 사용하는 주체 역시 스프링 빈으로 등록되어야 프레임워크가 자동으로 빈을 가져다 줄 수 있음
### 스프링 컨테이너
- **정의:** 스프링 빈이 저장되는 공간으로, **어플리케이션 컨텍스트(Application Context)**라고도 함
- **역할:** 빈의 생성, 관리, 제공 등을 담당하는 객체 저장소 역할

## 스프링 빈을 컨테이너에 저장(등록)하는 방법
### 1. 설정 파일 작성 (수동 등록)
- 자바 클래스로 설정 파일을 작성하며, 클래스에 `@Configuration` 어노테이션을 붙여 설정 파일임을 명시
- 빈으로 등록할 메서드에 `@Bean` 어노테이션을 추가하여 수동으로 빈을 생성하고 관리

예시:
```java
@Configuration
public class TestConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
```
### 2. 컴포넌트 스캔 (자동 등록)
- 빈으로 등록할 클래스에 `@Component` 어노테이션을 붙임
- 스프링이 어플리케이션 시작 시 자동으로 컴포넌트를 검색하여 빈으로 등록
- 자동 스캔을 위해 설정 클래스에 `@ComponentScan` 어노테이션을 사용

예시:
```java
@Component
public class MyBean {
    // class content
}
```
## 의존성 주입 (Dependency Injection, DI) 개념과 방법
### 의존성 주입의 개념
- 컨테이너에 저장된 빈(객체)가 빈(다른 객체) 사이의 의존성을 프레임워크가 주입하는 것
- 목적:
  - **OCP(Open Closed Principle)** 원칙 준수로 유지보수가 용이
  - 객체 재사용으로 메모리 효율성 향상
### 스프링에서 의존성을 주입하는 방법
의존성을 주입하는 방법으로는 생성자 주입, 필드 주입, 세터 주입(메서드 주입)이 있으며, 주입 표시로 `@Autowired` 어노테이션을 사용
#### 1. 생성자 주입
- 주입받는 의존성을 `final` 필드로 선언 가능 (의존성이 변하지 않을 때 유리)
- 생성자에 `@Autowired`를 명시하거나 생성자가 하나일 때 생략 가능
- Lombok의 `@RequiredArgsConstructor`를 사용하면 자동 생성자 코드까지 생략 가능

예시:
```java
@Component
@RequiredArgsConstructor
public class MyBean {
    private final MySubBean mySubBean;
}
```
#### 2. 필드 주입 (Field Injection)
- 필드에 바로 `@Autowired`를 붙여 사용 (final은 사용 불가)
- 주로 테스트 환경에서 간편히 사용

예시:
```java
@Component
public class MyBean {

    @Autowired
    private MySubBean mySubBean;
}
```

## 테스트 실행 결과

![test_result](<test_result.png>)