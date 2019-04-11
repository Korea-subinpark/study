# Database

## DBMS (데이터베이스 관리 시스템)
* 사용자와 데이터베이스 사이에서 질의처리와 접근관리
* 장점
    * 데이터의 중복성과 불일치성 감소
    * 보안
    * 백업과 복구
    * 일관된 데이터 유지

## Schema (스키마)
* 데이터베이스의 `구조`와 `제약조건`에 대해서 기술한 것

## 데이터베이스 언어
* DDL (데이터 정의어)
    * 데이터 간의 관계를 정의하거나 변경, 수정할 때 사용
    * 데이터베이스 관리자나 설계자가 사용
* DML (데이터 조작어)
    * 데이터를 검색, 수정, 삭제 등을 수행할 때 사용
    * 데이터베이스 사용자가 사용
* DCL (데이터 제어어)
    * 데이터의 보안, 무결성, 복구, 병행 수행 제어 등을 정의할 때나 사용자 권한 설정할 때 사용
    * 데이터베이스 관리자가 사용

---
## 데이터베이스 단위
* 애트리뷰트, 필드
* 튜플, 레코드

---

### DML
* select 문
    * select [DISTINCT | ALL] - 기본값 ALL, 중복값 제거는 DISTINCT
    * from - 추출에 필요한 테이블 (MySQL은 없어도 된다)
    * as - alias(생략 가능)
    * where - 조건
    * group by - 그룹의 기준
    * having - 그룹의 조건
    * order by - 정렬
    * limit - 원하는 만큼 출력

* 연산자 : in, between, is, like

* 함수
    * 데이터에 대한 계산, 데이터를 다른 형식으로 변환
    * count, sum, min, max, avg
    * concat, rpad, lpad
    * ceil, round, floor
    * date_format - 날짜 형식 변환
    * case - data type 변환
    * format - 소수점 자리수 지정, 1000단위마다 ','
    * bin, hex, oct
    * repeat
    * replace(문자열, 타겟 문자열, 대체할 문자열)
    * curdate, curtime, now, sysdate, year, month, dayofmonth, datediff
    * if(조건식, '참일 때', '거짓일때');
        * ex) `select if(100 > 200, 'true', 'false');`
    
* insert, delete

### DDL
* CREATE table
    * 데이터 타입
        |타입|정의|
        |--|--|
        |varchar(size)|가변 길이 문자열(1~255)|
        |char(size)|고정 길이 문자열(1~65535)|
        |decimal(p, s)|길이가 p인, 소수점 숫자가 s개|
        |date|날짜와 시간|
        |int|정수형|
        |longtext|최대 4GB의 문자 데이터|
        |json|json 형식|
        |float|소수점 7자리까지 표현|
        |real, double|소수점 15자리까지 표현|

[mySQL 문서](https://dev.mysql.com/doc/refman/8.0/en/)

### Key
* 수퍼 키(super key)
    * 유일성 만족, 최소성 만족하지 않는다
* 후보 키(candidate key)
    * 유일성 만족, 최소성 만족
* 기본 키(primary key)
    * 후보 키 중에서 선정된 키
    * null 값을 가질 수 없다
* 대체 키(alternate key)
    * 후보 키 중에서 기본 키를 제외한 나머지
* 외래 키(foreign key)
    * 다른 릴레이션의 기본 키를 참조하는 속성들의 집합


### join
* cross join
    * 모든 행에 대해 결합
* inner join
    * 공통되는 컬럼에 의해 결합
* self join
    * 한 개의 테이블을 두 개의 별도의 테이블처럼 이용
    * ex) 자신의 매니저 정보
* outer join
