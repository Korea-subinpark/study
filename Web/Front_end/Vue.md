# Vue.js

* javascript 기반 프론트엔드 프레임워크

* 예제 코드
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://unpkg.com/vue"></script>
</head>
<body>
<div id="app">
{{message}} <!-- vue 객체 안의 데이터 사용 -->
</div>

<script type="text/javascript">
	//Vue 객체 생성
	var vm = new Vue({
		el:"#app",//element
		data:{
			message: 'hello, vue!!!'
		}
	});
	
</script>
</body>
</html>

```

* 인스턴스 옵션
    * el
    * template
    * data
    * props
    * filters
    * computed
        * 데이터 변경 시 자동 실행된다
        * 알아서 자동 실행되므로 이벤트 처리 로직 등으로 사용 불가
    * watch
        * computed(간단한 연산정도)와 유사
        * 시간이 많이 걸리는 비동기처리에 적합


---
## v 태그
* v-bind : 태그 속성과 뷰 객체 데이터를 연결
* v-model : 데이터와 연결한다 주로 form태그 안에서 사용
* v-on : 이벤트 등록
* v-for : 반복문 사용
* v-show : 조건에 따라 화면에 표시


### v-if와 v-show의 차이
* v-if는 조건을 체크하여 화면에 보여줄지 결정
* v-show는 화면 구성 요소를 만들어 놓고 조건을 체크하여 display 값만 조정


---
## 실행 순서
1. Vue Lib loading
2. 화면요소 생성 - div, table, ...
3. Vue 생성 - el, data, methods, ...
4. Vue 객체 - 화면 요소와 link
5. 화면요소에 Vue 객체의 내용 반영
6. 사용자 확인

## Life Cycle
* beforeCreate
* created
    * data, method 사용 가능. 객체 생성 후에 호출되는 단계로 서버에서 데이터 받아오는 로직을 둔다
* mounted
    * 생성한 객체와 화면이 연결된 후 실행되는 함수. 화면제어 내용을 둔다
* updated
    * 데이터 변경 후 다시 화면을 그릴 때 실행되는 함수
