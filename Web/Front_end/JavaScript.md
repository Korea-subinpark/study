# JavaScript
* 정적인 HTML문서에 동적인 기능을 부여하기 위해 사용하는 스크립트 언어

## 변수
* 함수도 하나의 데이터가 될 수 있다
* 전역변수
    * 함수 외부에서 선언
    * 함수 내에서 var 키워드를 사용하지 않고 선언
* 지역변수
    * 함수 내에서 var 키워드를 사용하여 선언

## 외부 파일 선언
```<script type="text/javascript" src="out.js"></script>```

---

## 함수
### 1. 선언적 함수: 함수 이름이 있다. 이름으로 호출
```javascript
//함수의 정의
function sum(a, b) {
    return a + b;
}
//함수의 사용
alert(sum(2,3));
```
### 2. 익명 함수: 이름이 없는 함수
```javascript
//함수의 정의
var fun = function() {
    alert('hello');
}
//함수의 사용
fun();
```

### 3. 함수를 parameter로 사용
```javascript
function go() {
    alert("gogo!");
}

function funtest(some) {
    some();
}

funtest(go);
```

### 4. 함수를 리턴타입으로 사용
```javascript
function funtest2() {
    return function() {
        alert("hello, script!");
    }
}
var re = funtest2();
re();
funtest2()();
document.write(typeof(funtest2));//브라우저 창에 function이라고 출력
```

### 5. closure
* 사용범위가 끝난 데이터를 scope 밖에서 사용가능하게 해주는 기능. 함수를 리턴하면서 사용가능해진다
```javascript
function test(num) {
    var result = ++num;
    return function() {
        alert(result);
    }
}

var value = test(99);
value();
```

---

## JavaScript 내장 함수
### 1. 타이머 함수
|함수 이름|설명|
|---|---|
|setTimeout(function, millisecond)|일정 시간 후 함수를 한 번 실행|
|setInterval(function, millisecond|일정 시간마다 함수를 반복 실행|
|clearTimeout(id)|일정 시간 후 함수를 한 번 실행하는 것을 중지|
|clearInterval(id)|일정 시간마다 함수를 반복 실행하는 것을 중지|

### 2. eval 함수
* string을 자바스크립트 코드로 실행
```javascript
var command = "alert(123)";
evel(command);
```

---

## 객체
### 1. 리터럴 방식의 객체 생성 방법: 클래스 정의와 동시에 인스턴스 생성
```javascript
var user = {
    name:'tommy',
    address:'seoul',
    company:'abc trading'
};

document.write(user.name + "<br>");
document.write(user.address + "<br>");
document.write(user['company'] + "<br>");

for (var key in user) {//객체 안에 있는 key와 값을 출력
    document.write(key + " : " + user[key] + "<br>");
}
```

### 2. 함수 정의 방식을 사용하는 객체 생성 방법: 함수이름 자체가 클래스가 된다
```javascript
//클래스 정의
function User(name, address) {
    this.name = name;
    this.address = address;
    this.info = function() {
        document.write(this.name + " : " + this.address + "<br>");
    }
}

//객체 생성
var user1 = new User('jane', 'seoul');
var user2 = new User('billy', 'la');
var user3 = new User('harry', 'london');

user1.info();
user2.info();
user3.info();
```

---
## 이벤트, 핸들러

```html
<head>
<script type="text/javascript">
    function process() {
        alert('hello~');
    }

    function mouseOver() {
        alert('mouse over~');
    }

    function go(code) {
        if(code == 1) {
            open('first.html', '', 'width=300px height=300px scrollbars=yes');//popup 창 띄우기
        } else if(code == 2) {
            print();
        } else {
            close();
        }
    }
</script>
</head>
<body>
    <h1>event test</h1>
    <input type="button" value="click" onclick="process()">
    <input type="button" value="mouseOver" onmouseover="mouseOver()">
    <input type="button" value="open" onclick="go(1)">
    <input type="button" value="print" onclick="go(2)">
    <input type="button" value="close" onclick="go(3)">
</body>

```

## DOM
* 플랫폼과 언어에 독립적
