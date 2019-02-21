# Ajax
* JavaScript에 의한 비동기적인 통신으로 XML 또는 JSON 기반의 데이터를 클라이언트인 웹 브라우저와 서버 사이에서 교환하는 방식
* 페이지 이동 없이 화면을 동적으로 변경할 수 있다

## 동기 통신과 비동기 통신
### 1. 동기 통신
* ex)form 태그
* 서버에 요청한 뒤에 응답을 기다린다

### 2. 비동기 통신
* 서버에 요청한 뒤에 응답을 기다리지 않고 다른 로직을 실행한다

## Ajax 실행 process
1. 이벤트 발생
2. XHR 객체 생성
3. client 요청
4. controller/model 실행 및 view로 응답
5. 응답
6. 응답 처리 메소드 실행

---

## XHR 객체의 속성
* readyState
    * 0: request가 초기화 되지 않음(open() 호출 전)
    * 1: request가 셋업되고 보내지 않음(send() 호출 전)
    * 2: request가 보내지고, 진행중일 경우(send() 호출 후)
    * 3: Server로부터 데이터가 전송중일 경우
    * 4: Server의 응답이 완료된 상태

* status
    * 200: 정상적으로 응답받은 경우
    * 404: 페이지를 찾지 못한 경우
    * 405: 요청 방식이 다른 경우
    * 500: 서버 오류 발생

* onreadystatechange
    * readyState 속성이 바뀔 때마다 호출된다

* responseText
    * 서버로부터 응답된 문자열 데이터를 보유
* responseXML
    * 서버로부터 응답된 xml 데이터를 보유
    * DOM 객체로 파싱할 수 있다

* 예제 코드

```javascript
var xhr = null;
function getXHR() {
    xhr = new XMLHttpRequest();
}

//first.jsp(서버)로 ajax요청을 보내서 결과를 받아오는 함수
function getData() {
    getXHR();//XMLHttpRequest 생성
    xhr.open('get', 'first.jsp', true);//ajax통신 준비. true: 비동기통신
    xhr.onreadystatechange = callback;//callback 함수 지정
    xhr.send(null);//ajax통신 요청(get 방식으로 요청할 때 null)
}

function postData() {
    getXHR();//XMLHttpRequest 생성
    xhr.open('post', 'first.jsp', true);//ajax통신 준비. true: 비동기통신
    xhr.onreadystatechange = callback;//callback 함수 지정
    xhr.send();//ajax통신 요청(post 방식으로 요청할 때 비워둔다)
}

function callback() {
    if(xhr.readyState == 4 && xhr.status == 200) {
        var result = document.getElementById("display");
        result.value = xhr.responseText;
    }
}
```

---

## 클라이언트에서 서버로 보낼 데이터가 있을 때
### 1. get 방식

* 파일명 뒤에 데이터를 붙여서 보낸다
* 파일명과 데이터는 ?로 구분한다
* 각 데이터는 &로 나누어 진다

```javascript
function process() {//ajax 요청
    var id = document.getElementById("id");
    var pass = document.getElementById("pass");
    
    var param = "?id=" + id.value + "&pass=" + pass.value;
    
    getXHR();//XMLHttpRequest 생성
    xhr.open('get', 'paramTest.jsp' + param, true);//ajax통신 준비. true: 비동기통신
    xhr.onreadystatechange = callback;//callback 함수 지정
    xhr.send(null);//ajax통신 요청(get 방식으로 요청할 때 null)
    
    id.value = '';
    pass.value = '';
}
```


### 2. post 방식

* setRequestHeader를 지정한다
* send 함수의 parameter로 데이터를 보낸다
* 파일명과 데이터를 구분할 필요가 없기 때문에 ?가 붙지 않는다

```javascript
function process2() {//ajax 요청
    var id = document.getElementById("id");
    var pass = document.getElementById("pass");
    
    var param = "id=" + id.value + "&pass=" + pass.value;
    
    getXHR();//XMLHttpRequest 생성
    xhr.open('post', 'paramTest.jsp', true);//ajax통신 준비. true: 비동기통신
    xhr.onreadystatechange = callback;//callback 함수 지정
    
    //post 방식으로 데이터 보낼 때 지정해야 되는 부분(지정하지 않으면 데이터가 제대로 전달 안됨)
    xhr.setRequestHeader('Content-Type',
            'application/x-www-form-urlencoded;charset=utf-8');
    
    xhr.send(param);//ajax통신 요청(get 방식으로 요청할 때 null)
    
    id.value = '';
    pass.value = '';
}
```
---

## 서버에서 JSON 형식의 데이터가 오는 경우
* 데이터는 text, XML 두가지 형식으로 받을 수 있다
* JSON 데이터는 text 형식으로 받고 eval 함수를 이용하여 JSON 객체로 변환해 주어야 한다

```javascript
function callback() {
    if(xhr.readyState == 4 && xhr.status == 200) {
        processJson();
    }
}

function processJson() {
    var text = xhr.responseText;//text 형식으로 받음
    var json = eval("(" + text + ")");//문자열 형식 -> json객체로 변환
    var msg = '';
    
    //json 배열 형식인 경우 for문으로 모든 데이터를 출력해볼 수 있다
    for(i = 0; i < json.length; i++) {
        msg += "<h1>" + json[i] + "</h1>";
    }
    //json collection 형식일 경우에는 key값을 이용하여 데이터를 찾아낸다
    
    var result = document.getElementById("result");
    result.innerHTML = msg;
}			
```

