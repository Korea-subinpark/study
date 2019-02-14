# HTML
## Typical 3-Layer Architecture
* Presentation Layer (HTML, CSS, JavaScript)
    * 사용자에게 interface 제공
    * 입출력 기능
    * GUI, Client view, front-end
* Logic Layer (Servlet, JSP, CGI, PHP, ASP)
    * 요청에 따라 처리
    * middleware, back-end
* Data Layer (DBMS Server)
    * DB, FileSystem 관리
    * back-end

---

## HTML5
* Hyper Text Markup Language 5
* 웹 문서 제작을 위한 표준 기술
* HTML5 = HTML + CSS + JavaScript / 분리되지 않은 하나의 통합된 체제
    * HTML - content
    * CSS - style, design
    * JavaScript - programming 기능 추가

---
## Tag

### Tag 종류
|block<br>(한줄에 하나씩 배치)|inline<br>(연속배치)|
|---|---|
|div<br>h1~h6<br>p<br>목록 태그<br>table<br>form|span<br>a<br>input<br>글자 형식 태그|

### list Tag
|ol|ul|dl|
|---|---|---|
|순서가 있는 list|순서가 없는 list|definition list|
|`<li>`|`<li>`|`<dt>`, `<dd>`|

### Table
* code
```html
<table border="1">
<caption>고객리스트</caption>
	<tr><!-- table row -->
		<th>번호</th><!-- table header -->
		<th>이름</th>
		<th>주소</th>
	</tr>
	<tr>
		<td>111</td><!-- table data -->
		<td>tommy</td>
		<td>seoul, korea</td>
	</tr>
	<tr>
		<td>222</td>
		<td>jane</td>
		<td>tokyo, japan</td>
	</tr>
	<tr>
		<td>333</td>
		<td>june</td>
		<td>beijing, china</td>
	</tr>
    <tr>
		<td colspan="2">총인원</td>
		<td>3명</td>
	</tr>
</table>
```
<br>

* 실행 결과
<table border="1">
<caption>고객리스트</caption>
	<tr><!-- table row -->
        <th></th>
		<th>번호</th><!-- table header -->
		<th>이름</th>
		<th>주소</th>
	</tr>
	<tr>
        <td rowspan="4">5반</td>
		<td>111</td><!-- table data -->
		<td>tommy</td>
		<td>seoul, korea</td>
	</tr>
	<tr>
		<td>222</td>
		<td>jane</td>
		<td>tokyo, japan</td>
	</tr>
	<tr>
		<td>333</td>
		<td>june</td>
		<td>beijing, china</td>
	</tr>
    <tr>
		<td colspan="2">총인원</td>
		<td>3명</td>
	</tr>
</table>

---

## 입력 양식 태그

### 서버로 정보를 보내는 방식
|get|post|
|---|---|
|주소에 데이터를 입력해서<br> 보내는 방식(데이터 용량 제한)|별도로 데이터를 붙여 전송<br>(데이터 용량 제한 없음)|

* code
```html
<!-- form: 클라이언트에서 서버로 정보를 전송하는 태그
	action: 서버쪽에서 정보를 받을 파일 이름
	method: 서버로 정보를 보내는 방식 지정(get, post) -->
<form action ="welcome.jsp" method="post">
    num: <input type="text" name="num"><br>
    pass: <input type="password" name="pass"><br>
    <hr>
    <label><input type="checkbox" name="hobby">movie</label><br>
    <label><input type="checkbox" name="hobby">game</label><br>
    <label><input type="checkbox" name="hobby">trip</label><br>
    <label><input type="checkbox" name="hobby">reading</label><br>
    <hr>
    <label><input type="radio" name="season">spring</label><br>
    <label><input type="radio" name="season">summer</label><br>
    <label><input type="radio" name="season">fall</label><br>
    <label><input type="radio" name="season">winter</label><br>
    <hr>
    upload: <input type="file" name=:file1"><br>
    hidden value: <input type="hidden" name="hidden" value="999"><br>
    <hr>
    <textarea cols = "30" rows = "5" name = "ta"></textarea><br>
    <select name="car">
        <option value="one">morning</option>
        <option value="two">avante</option>
        <option value="three">k5</option>
        <option value="four">m5</option>
        <option value="five">grandeur</option>
    </select><br>
    <input type="reset" value="취소">
    <input type="submit" value="전송">
</form> 
```

<br>

* 실행 결과
<form action ="welcome.jsp" method="post">
    num: <input type="text" name="num"><br>
    pass: <input type="password" name="pass"><br>
    <hr>
    <label><input type="checkbox" name="hobby">movie</label><br>
    <label><input type="checkbox" name="hobby">game</label><br>
    <label><input type="checkbox" name="hobby">trip</label><br>
    <label><input type="checkbox" name="hobby">reading</label><br>
    <hr>
    <label><input type="radio" name="season">spring</label><br>
    <label><input type="radio" name="season">summer</label><br>
    <label><input type="radio" name="season">fall</label><br>
    <label><input type="radio" name="season">winter</label><br>
    <hr>
    upload: <input type="file" name=:file1"><br>
    hidden value: <input type="hidden" name="hidden" value="999"><br>
    <hr>
    <textarea cols = "30" rows = "5" name = "ta"></textarea><br>
    <select name="car">
        <option value="one">morning</option>
        <option value="two">avante</option>
        <option value="three">k5</option>
        <option value="four">m5</option>
        <option value="five">grandeur</option>
    </select><br>
    <input type="reset" value="취소">
    <input type="submit" value="전송">
</form>

---

