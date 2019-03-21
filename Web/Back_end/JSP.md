# JSP
* 프리젠테이션과 비즈니스 로직 분리

* jsp(design) + pojo(plain old java object)

* jsp(txt형식) 파일을 tomcat(서버)이 같은 작업을 하는 java(servlet) 파일로 변환

---

## JSP 페이지 구성요소
1. Fixed template data
    * html
2. Element
    * Directive Element
        * container에 정보를 전달하는 역할
        * `<%@ directive {지시어 속성} %>`
        * directive 종류: page, include, taglib
            * page: jsp를 servlet으로 변환할 때 이 정보들을 참조
            * include: 외부 데이터(html, jsp)를 jsp 페이지에 포함시키기 위해 사용
                * include 태그와는 내부적인 차이가 있다
    * Scripting Element
        * 선언(Declaration)
            * jsp 페이지의 멤버필드나 메소드 선언시 사용
        * 식(Expression)
            * 변수 값, 계산 결과, 함수 호출 결과를 직접 출력
            * 문장 끝에 ;을 붙이지 않는다 ex) `<%= go() %>`
        * 스크립트렛(scriptlet)
            * java 코드를 기술하는 영역
            * servlet에서 코드 양이 많이지기 때문에 사용을 지양
        * 선언과 스크립트렛에서 변수 선언했을 때 차이점
            ```jsp
                <%!
                    int count = 100;
                %>
                <% 
                    int speed = 100;
                %>
                count: <%= ++count %><br>
                speed: <%= ++speed %>
            ```
            * count 변수는 웹 페이지를 새로고침 할 때마다 증가, speed 함수는 항상 101로 출력
            * 선언부 변수는 class field에 선언
            * 스크립트렛 변수는 service()의 내부에 선언
    * Action Element(jsp 태그)
        * jsp 액션(jsp 태그) - 표준화된 태그를 이용해서 객체의 생성 및 접근을 지원
        * `<구분자: 태그이름>`
            * 구분자는 태그이름의 중복을 피하기 위하여 사용
        * 태그 종류
            * useBean 태그 - java 객체를 생성하거나 기존에 이미 만들어진 객체를 리턴
                * `<jsp:useBean id="c" class="Car" scope="page"/>`
                    * scope(사용 범위) - `page` < `request`(같은 request 사용할 때-forward) < `session` < `application`(container가 종료될 때까지)
                    * scope를 조사해서 이미 존재하는 객체일 경우 새로 생성하지 않는다
                * `<% Car c = new Car();%>`
                * 두 코드는 같은 역할을 한다
             * forward 태그 - 제어를 다른 페이지로 이동시킨다
                * 형태1 - `<jsp:forward page="relativeURLspec"/>`
                * 형태2
                    ```jsp
                    <jsp:forward page="urlSpec">
                        {<jsp:param ... />}*
                    </jsp:forward>
                    ```
            