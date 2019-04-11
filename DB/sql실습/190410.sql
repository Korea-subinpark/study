select empid, fname, email, hdate, deptid
from emp;

-- 모든 데이터 검색
select * from emp;

-- 중복값 제거
select distinct deptid, jobid
from emp;

select distinct jobid
from emp;

-- 정렬
select empid, fname, salary
from emp
order by salary desc;

select empid, fname, hdate
from emp
order by hdate, fname;

select empid, fname, salary
from emp
order by salary desc, fname;

-- alias(컬럼 별칭)
select empid as 사번, fname 이름
from emp;

select empid 사번, fname 이름, salary 연봉, salary * 1.1 새연봉
from emp;

select empid 사번, fname 이름, comm 커미션, comm * 1.3 새커미션
from emp;

-- 조건달기
select empid, fname, salary
from emp
where salary > 9000;

select empid, fname, salary
from emp
where hdate > '2005-01-01';

-- 1. 연봉이 6500 이상인 레코드
select *
from emp
where salary >= 6500;

-- 2. jobid가 'FI_ACCOUNT'인 레코드
select *
from emp
where jobid = 'FI_ACCOUNT';

-- 3. 150번이후 사번 레코드
select *
from emp
where empid > 150;

-- 4. locid가 1300인 부서의 도시
select distinct city
from loc
where locid = 1300;

-- 5. 미국에 있는 loc id
select distinct locid
from loc
where countryid = 'US';

-- 6. jobtitle이 'President'인 사람의 최소/최대 연봉
select minsal, maxsal
from job
where jobtitle = 'President';

-- 7. locid가 1700번 이상인 부서의 이름
select deptname
from dept
where locid >= 1700;

select fname, email, deptid
from emp
where deptid in (30, 40, 50);

select *
from emp
where empid >= 150 and salary >= 6000;

select empid, fname, salary, hdate
from emp
where salary between 6000 and 9000 and hdate > '2005.06.30'
order by hdate;

select empid, fname
from emp
where fname like 'M%';

select lname
from emp
where lname like '%e%'
order by lname;

select phone
from emp
where phone like '%423%';

select empid, lname
from emp
where lname like 'au____';

select *
from emp
where comm is not null;

select *
from emp
where deptid is null;

select *
from emp
where mgrid is null;

select empid, fname, deptid
from emp
where deptid != 50;

select *
from emp
where hdate between '2005-03-11' and '2005-05-11'
order by hdate;

select empid, fname, salary
from emp
order by salary desc
limit 5;

select count(empid) 인원수
from emp;

select count(*)
from emp;

select count(deptid)
from emp;

select count(deptid)
from emp
where deptid = 50;

select avg(salary) 연봉평균
from emp;

select min(salary), max(salary)
from emp;

select avg(salary)
from emp
where deptid = 50;

select empid, fname, lname, concat(fname, ' ', lname) name
from emp;

select lpad(fname, 10, 222)
from emp;

select rpad(fname, 10, 5)
from emp;

select lpad(fname, 15, ' ')
from emp;

select rpad(fname, 15, ' ')
from emp;

select substr(fname, 3, 2), substring(fname, 3, 2)
from emp;

select ceil(9.1), round(4.666, 2), floor(3.9);

select round(avg(salary), 1)
from emp;

select fname, date_format(hdate, '%Y / %M / %d')
from emp;

select fname, cast(salary as char)
from emp;

select format(12345.12345, 4);

select bin(31), hex(31), oct(31);

select repeat('hellO', 5);

select replace('this is mysql', 'my', 'your');

select replace(fname, 'e', 'xx') str, fname
from emp;

select curdate(), curtime(), now(), sysdate();

select year(curdate()), month(curdate()), dayofmonth(curdate());




-- 0. emp 테이블의 모든 레코드
select * from emp;

-- 1. 직원의 id가 158인 레코드의 fname, lname, phone 검색
select fname, lname, phone
from emp
where empid = 158;

-- 2. fname이 'D'로 시작하는 직원의 id와 이름, 부서ID 검색
select empid id, concat(fname, ' ' , lname) name, deptid 부서ID
from emp
where fname like 'D%';

-- 3. 부서번호가 30인 사원들의 사번과 fname, jobid, salary, deptid 출력
select empid 사번, jobid, salary, deptid
from emp
where deptid = 30;

-- 4. comm이 0.2이상인 사원의 사번과 fname, comm 출력
select empid 사번, fname, comm
from emp
where comm >= 0.2;

-- 5. 입사일이 1999년 12월31일 이후인 직원의 사번과 fname, 입사일 출력(입사일로 오름차순 정렬)
select empid 사번, fname, hdate 입사일
from emp
where hdate > '2005-12-31'
order by hdate;

-- 6. 부서번호가 20에서 50번 사이의 직원이름과 부서ID를 검색(부서id로 오름차순 정렬)
select concat(fname, ' ' , lname) 직원이름, deptid 부서ID
from emp
where deptid between 20 and 50
order by deptid;

-- 7. 부서번호가 없는 직원의 id와 email, 이름 검색
select empid id, email, concat(fname, ' ', lname) 이름
from emp
where deptid is null;

-- 8. jobid가 'ST_CLERK', 'SA_MAN', 'SA_REP'인 직원의 id와 이름, jobid
select empid id, concat(fname, ' ', lname) 이름, jobid
from emp
where jobid in ('ST_CLERK', 'SA_MAN', 'SA_REP');

-- 9. 전화번호가 650으로 시작하는 직원의 id와 이름, 부서ID, 전화번호 검색
select empid id, concat(fname, ' ', lname) 이름, deptid 부서ID, phone 전화번호
from emp
where phone like '650%';

-- 10. comm이 null이 아닌 직원 중 그 값이 0.3 이상인 레코드(comm으로 정렬)
select *
from emp
where comm is not null and comm >= 0.3
order by comm;

-- 11. 아래와 같은 형식으로 출력 되도록 Query 작성
-- 		사번				설명
-- 		101		Steven King IS A IT_PROG
select empid 사번, concat(fname, ' ', lname, ' is a ', jobid) 설명
from emp;

-- 12. salary가 높은 상위 5명
select *
from emp
order by salary desc
limit 5;

-- 13. 입사일 순으로 사원 정보 정렬(오래된 순)
select *
from emp
order by hdate;

-- 14. 부서 ID가 40번이 아닌 부서에 속한 직원들 중 salary가 8000이상인 레코드
select *
from emp
where deptid != 40 and salary >= 8000;

-- 15. empid, fname, salary, 10% 증가된 salary(새연봉)
-- 새연봉 순으로 정렬
select empid, fname, salary, salary * 1.1 새연봉
from emp
order by 새연봉;

-- 16. hdate가 '2005-02-15'에서 '2006-12-31' 사이의 레코드(사번, 이름, 전화번호)
select empid 사번, concat(fname, ' ', lname) 이름, phone 전화번호
from emp
where hdate between '2005-02-15' and '2006-12-31';

select empid, hdate, now(), datediff(now(), hdate), datediff(now(), hdate)/30
from emp;

select datediff(now(), '2019-04-09');

select if(100 > 200, 'true', 'false');

select empid, fname, hdate, if(hdate > '2006-06-30', 'young', 'old')
from emp;

-- comm을 안받으면 -, 받으면 comm 출력
select if(comm is null, '-', comm)
from emp;

select ifnull(comm, '-')
from emp;

select ifnull(deptid, 'nope')
from emp;

select avg(salary), deptid, jobid
from emp
group by deptid, jobid;

select count(empid) 인원수, deptid
from emp
group by deptid
order by 인원수 desc;

select count(empid) 인원수, round(avg(salary)) 평균연봉, jobid
from emp
-- where deptid >= 60
group by jobid
having 인원수 >= 20
order by 평균연봉 desc;
