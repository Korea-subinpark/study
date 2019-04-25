# Spring
* Enterprise Application에서 필요로 하는 기능을 제공하는 프레임워크
* JEE가 제공하는 다수의 기능을 지원하는 Lightweight Application 프레임워크


## DI(Dependency Injection)
* 객체 사이의 의존 관계가 객체가 아닌 외부에 의해 설정
1. 생성자를 통해 injection
2. setter를 통해 injection
3. annotation을 통해 injection

## IoC(Inversion of Control)
* 객체의 생성에서부터 의존관계 설정, 생명주기 관리까지 모든 객체에 대한 제어권이 바뀐 것을 의미



## AOP(Aspect-oriented Programming)
* 관점 지향 프로그래밍
* 전체 애플리케이션 구현 기능을 두 가지로 구분
    * 핵심 업무 - biz logic
    * 공통 업무
* 용어

    |용어|설명|
    |---|---|
    |Aspect|여러 객체에 공통으로 적용되는 사항|
    |Advice|Aspect 안에 있는 코드|
    |joinpoint|Advice가 적용될 수 있는 위치|
    |pointcut|여러 개의 joinpoint를 묶은 것|
    |Advisor|Advice와 pointcut을 묶은 것|
    |Aspect|핵심 업무 중간에 공통 업무를 끼워넣는 것|

* 구현 방식
    * XML 기반
    * Annotation 기반
    * Proxy 기반

* Advice Type


* Weaving되는 시점
    * compile time
        * 소스코드는 따로 있었지만 컴파일 단계에서 생성된 .class 실행파일에 합쳐져 있다
    * loading time
        * 컴파일 후 로딩단계에서 weaving
    * runtime
        * proxy 객체가 요청을 먼저 받아서 weaving 처리



---

## TransactionManager
* xml파일에 bean 등록 후 @Transactional 붙여준다



---
## Spring MVC

|구성요소|설명|
|---|---|
|DispatcherServlet|요청을 전달 받는다|
|HandlerMapping|클라이언트의 요청 URL을 어떤 Controller가 처리할지 결정|
|Controller|요청을 처리한 뒤 결과를 DispatcherServlet에 알림|
|ViewResolve|Controller의 처리 결과를 보여줄 View 결정|
|View|응답 화면 생성|


