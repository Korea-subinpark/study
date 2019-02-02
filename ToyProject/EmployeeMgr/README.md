# Employee Management Program

<table border="1">
<thead>
<th>프로젝트명</th>
<th>날짜</th>
<th>계기</th>
</thead>
<tbody>
<tr>
<td>Employee Management Program</td>
<td>2019.02.01</td>
<td>-Java 문법 이해도 점검<br>-Project 문서화 능력 향상<br>
-학습한 내용들을 연계해 보기 위하여 진행</td>
</tr>
<tr>
<td>내용</td>
<td colspan=2>
-사원들의 정보(사번, 이름, 직책, 부서)를 관리한다.<br>
-사용자 ui로 Swing을 사용<br>
-사원 정보에 대한 Insert, Delete, Update, Search 기능을 제공<br>
-Save 버튼을 누르면 파일로 저장<br>
-서버로 전송 버튼을 누르면 Thread를 이용하여 서버로 전송한다.<br>
-서버는 데이터를 수신하여 콘솔창에 출력한다.<br>
-사용자 Exception 정의로 찾는 사번이 없을 때, 중복되는 사번이 있을 때<br>
상태 메세지 창에 에러 메세지를 출력한다.<br>
-Comparator를 이용하여 사원 객체를 사번 순으로 정렬하여 보여준다.
</td>
</tr>
<tr>
<td>결과</td>
<td colspan=2>
Java에서 학습하였던 내용 중<br>
-Network<br>
-I/O<br>
-Thread<br>
-Exception<br>
-Singleton Pattern<br>
-GUI 등을 이용해 구현하며 이해도 향상
</td>
</tbody>
</table>
<br>
<br>

---
<br>

# Class Description

## EmPloyee
* 사원 정보를 저장하는 객체
* empNo(사번), name(이름), position(직책), dept(부서) 저장
* Encapsulation 적용
* 파일에 객체를 저장하기 위하여 Serializable
* Constructor, get, set, toString 함수

<br>

## IEmpMgr (Interface)
* 구현할 함수 정의<br>

public void load(String filename) -> 파일에서 객체를 읽는 함수<br>
public void save(String filename) -> 파일에 객체를 쓰는 함수<br>
public void add(Employee b) throws DuplicateException
-> parameter로 전달된 객체를 저장하는 함수<br>
public List<Employee> search() -> 전체 사원정보를 반환하는 함수<br>
public Employee search(int num) throws RecordNotFoundException
-> 사번으로 사원을 검색하는 함수<br>
public List<Employee> search(String name) throws RecordNotFoundException -> 이름으로 사원을 검색하는 함수<br>
public update(Employee b) throws RecordNotFoundException ->
parameter로 전달된 사원 객체와 이름이 같은 객체를 찾아 정보를 수정하는 함수<br>
public void delete(int num) throws RecordNotFoundException ->
사번으로 사원 정보를 삭제하는 함수<br>

<br>

## EmpMgrImpl
* IEmpMgr을 implements
* Singleton pattern으로 구현
* 사원 정보 저장, 삭제, 수정, 검색 기능 수행
* 사원 객체들을 List로 저장
* Interface에 없는 추가 함수
    * exceptionCheck(int num) throws DuplicateException, RecordNotFoundException -> 사번을 parameter로 받아 전체 직원을 순회하며 중복된 사번이 있는지, 없는 사번인지 Exception을 발생시키는 함수
* 같은 java파일에 있는 외부 Class
    * SortedByNo -> Comparator를 implements 하여 사번을 기준으로 정렬할 때 사용
    * DuplicateException -> 중복된 사번이 존재할 때 발생하는 Exception
    * RecordNotFoundException -> 사번이 존재하지 않을 때 발생하는 Exception

<br>

## EmpUI
* 사용자 UI 제공
* Swing으로 구현되었다.
* WindowListener, ActionListener 이용
* Inner Class
    * EmpClient
        * 서버로 데이터를 전송할 때 Client 역할을 하는 Thread
        * Socket, OutputStream, ObjectOutputStream

<br>

## EmpServer
* 데이터를 전송받는 서버
* 수신한 데이터를 콘솔 창에 출력한다.
* ServerSocket, InputStream, ObjectInputStream

---
<br>

# Class Diagram

[!cld](img/EmpMgr.PNG)


---
# 실행 결과

[!run](img/EmpMgrRun.PNG)