# Servlet
* java 플랫폼에서 컴포넌트를 기반으로 한 웹 애플리케이션을 개발할 때 활용하는 기술
* JSP는 Servlet을 기반으로 한 기술이며 서로 상호보완적인 기술이다
---
## 특징
* 서버 프로토콜 종류에 관계없이 HTTP, FTP, SMTP 등 여러 가지 애플리케이션 기반의 응용프로그램을 개발할 수 있다
* Servlet container로는 오픈 소스인 Tomcat이 대표적이다
* Servlet container는 표준 스펙을 지원하고, Servlet 표준 API에서 제공되는 추상 클래스와 인터페이스들을 구현하는 클래스들을 제공하므로, 특정 Servlet container를 기반으로 개발하고 테스트하더라도 얼마든지 다른 container 기반으로 교체 가능하다

---
## 단점
* Servlet 코드 상에서 HTML 코드를 표현하는 것이 불편하기 때문에 JSP가 등장하게 되었다
* 프로그램 내에서 HTML을 처리하기 때문에 간단한 표현을 변경할 때에도 컴파일을 다시 해야 한다

---
## Servlet API
* javax.servlet 패키지
    * Servlet 인터페이스
    * ServletRequest 인터페이스
    * ServletResponse 인터페이스
    * ServletConfig 인터페이스
    * ServletContext 인터페이스
    * RequestDispatcher 인터페이스
    * ServletInputStream 클래스
    * ServletOutputStream 클래스
    * GenericServlet 클래스

* javax.servlet.http 패키지
    * HttpServletRequest 인터페이스
    * HttpServletResponse 인터페이스
    * HttpSession 인터페이스
    * HttpServlet 클래스
        * doGet(), doPost() 메소드 재정의
    * Cookie 클래스
    * HttpUtils 클래스

---
* request 구성
    * request line, header, body 로 구분

* response 구성
    * response line, header, body로 구분

---
* 예제 코드
```java
//@WebServlet: url mapping값 지정, 중복되면 에러 발생
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {//서블릿 조건: HttpServlet 상속
	
	//doGet(): service method, 클라이언트로부터 요청이 들어오면 응답을 만들어 내는 메소드 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //서버에서 클라이언트로 나갈 문서의 타입을 지정
        response.setContentType("text/html;charset=euc-kr");
        
        //응답 내보낼 때 필요한 출력 스트림 얻기
        PrintWriter out = response.getWriter();
        
        out.println("<html><body>");
        out.println("<h1>hello servlet!!!입니다</h1>");
        out.println("</body></html>");
    }

}

```

* 로그인 폼에서 입력한 내용 받아서 출력하기
```java
@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //클라이언트가 보낸 한글 안깨지도록 처리
        request.setCharacterEncoding("euc-kr");
        
        //html form에서 전송한 데이터를 받아 화면에 출력
        response.setContentType("text/html;charset=euc-kr");
        PrintWriter out = response.getWriter();

        //1. form 태그로 보내온 데이터 받기
        String id = request.getParameter("id");
        String pass =  request.getParameter("pass");
        
        //2. 화면에 출력
        out.println("<html><body><h1>data from cliend</h1>");
        out.println("ID:" + id + "<br>");
        out.println("PASS:" + pass);
        out.println("</body></html>");
    }

}
```
---
* Servlet의 라이프 사이클
* 관련 메소드
    * init()
        * Servlet container는 요청을 처리할 Servlet의 인스턴스를 생성한 후, 초기화 작업을 수행할 수 있도록 init()을 내부적으로 자동 호출한다
        * 생명 주기에서 단 한 번만 실행된다
    * service()
        * 요청을 처리할 수 있도록 호출한다
    * destroy()
        * 요청과 관련된 작업을 모두 마치게 되면, Servlet container에 의해 thread pool에 들어가거나 메모리에서 내려가게 된다
---

* Servlet의 페이지 이동
    * forward
        * 동일 서버 내부에서 이동하는 가장 기본적인 메소드
        * 최초의 request 객체가 그대로 유지되어 전달된다
        ```java
        @WebServlet("/Forward")
        public class Forward extends HttpServlet {
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //HelloServlet으로 화면을 넘김: forward 방식 사용(server 내부에서 request 객체가 계속 사용됨)
                //RequestDispatcher dispatcher = request.getRequestDispatcher("Hello");//servlet으로 forward
                RequestDispatcher dispatcher = request.getRequestDispatcher("html/hello.jsp");//jsp로 forward
                request.setAttribute("address", "seoul");
                dispatcher.forward(request, response);//요청을 넘김
            }

        }
        ```
    * redirect
        * 타 서버까지 자유롭게 이동할 수 있다
        * 새로운 request를 생성
        * 총 2번의 request와 response가 일어난다
        ```java
        @WebServlet("/Redirect")
        public class Redirect extends HttpServlet {
            
            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                //HelloServlet으로 화면을 넘김: redirect 방식 사용(클라이언트가 새로운 request 객체를 만들어 요청함)
                
                request.setAttribute("address", "tokyo");
                response.sendRedirect("Hello");//요청을 넘김
            }
        }
        ```
    * include
        * 현재 페이지에 다른 페이지를 포함하는 것
        * include된 Servlet을 먼저 수행하고 원래 Servlet의 나머지 부분을 수행
        * include된 Servlet의 response는 본래 Servlet의 최종 response에 포함된다
        * 대상으로 html, jsp 모두 가능
        