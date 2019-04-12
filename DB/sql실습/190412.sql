
-- table 생성(copy)
create table emp2
(select * from emp);

-- key는 복사되지 않는다
create table emp3
(select * from emp where deptid in(30, 40, 50));

select * from emp3;


alter table emp3
add constraint pk_emp3
primary key(empid);

-- 자신의 매니저의 연봉이 15000이상
select e1.empid, e1.fname, e2.fname, e2.salary
from emp e1, emp e2
where e1.mgrid = e2.empid and e2.salary >= 15000
order by e2.salary desc;

-- 3개의 테이블 outer join
select e.empid, e.fname, d.deptid, d.deptname, j.jobtitle
from emp e left outer join dept d
on e.deptid = d.deptid
left join job j
on e.jobid = j.jobid;

select e.empid, e.fname, d.deptname, l.city
from emp e left outer join dept d
on e.deptid = d.deptid
left join loc l
on d.locid = l.locid;

-- 미국에 있는 부서 이름
select d.deptname, l.countryid
from dept d join loc l
on d.locid = l.locid and l.countryid = 'US';

select deptname
from dept
where locid in (select locid from loc where countryid = 'US');

-- 미국에 근무하는 사원들의 평균 월급보다 많이 받는 사원의 정보
select e.empid, e.fname, j.jobtitle
from emp e join job j
where e.jobid = j.jobid and e.salary > (select avg(salary)
											from emp
											where deptid in (select deptid
																from dept
																where locid in (select locid
																				from loc
																				where countryid = 'US')));
                                                                                


-- 60번 부서 직원들 연봉 중 아무거나 비교해서 큰 사원의 정보
select empid, deptid, salary
from emp
where salary > any (select salary from emp where deptid = 60);

select empid, deptid, salary
from emp
where salary > (select min(salary) from emp where deptid = 60);


-- 60번 부서 직원들 연봉 모두와 비교해서 큰 사원의 정보
select empid, deptid, salary
from emp
where salary > all (select salary from emp where deptid = 60);

select empid, deptid, salary
from emp
where salary > (select max(salary) from emp where deptid = 60);

-- =any = in
select empid, deptid, salary
from emp
where salary = any (select salary from emp where deptid = 60);

select empid, deptid, salary
from emp
where salary in (select salary from emp where deptid = 60);

-- =all = err
select empid, deptid, salary
from emp
where salary = all (select salary from emp where deptid = 60);

-- update:
update emp
set deptid = 40,
jobid = 'IT_PROG'
where empid = 111;

-- 사번이 129번 사원 연봉을 사번이 128번 사원의 연봉으로 수정
-- mysql에서는 자기 자신의 sub query를 이용하여 수정할 수 없다
-- inline view 사용
update emp
set salary = (select salary from(
				select salary
				from emp
                where empid = 128)x)
where empid = 129;


select salary
from emp
where empid in (128, 129);

rollback;

-- 부서별로 최소 연봉 받는 사람 정보, null 제외
select e.empid, e.fname, e.deptid, e.salary
from emp e join (select min(salary) minSal, deptid
					from emp
					group by deptid) x
on e.salary = x.minSal and e.deptid = x.deptid;


use tommy;

select * from starwars;
select * from roles;
select * from characters;
select * from casting;

-- 1. 전 스타워즈 시리즈의 상영년도, 영화제목, 배역, 출연배우 이름
select s.openyear 상영년도, s.episodename 영화제목, c.charactername 배역, ct.realname 출연배우
from starwars s, characters c, casting ct
where s.episodeid = ct.episodeid and ct.characterid = c.characterid;

-- 2. 에피소드 4에 출연한 배우는 모두 몇 명인지 구하는 쿼리
select count(realname) "에피소드4배우는 몇명"
from casting
where episodeid = 4;

-- 3. 각 배우 별 출연횟수를 조회하는 쿼리
select realname 배우, count(*) 출연횟수
from casting
group by realname;

-- 4. 에피소드 별 이름과 출연자 수
select s.episodename, x.cnt
from starwars s join (select count(*) cnt, episodeid from casting group by episodeid) x
on s.episodeid = x.episodeid; 

-- 5. 가장 많은 인원이 출연한 에피소드의 id
select episodeid, count(*) cc
from casting
group by episodeid
having cc >= all (select count(*) cnt from casting group by episodeid);

-- 6.제국의 역습에 등장하는 배우 이름
select realname
from casting
where episodeid = (select episodeid from starwars where episodename like "%제국의 역습%");

-- 7. '아미달라 여왕'이 등장했던 에피소드 id와 배우명
select episodeid, realname
from casting
where characterid = (select characterid from characters where charactername = "아미달라 여왕");

-- 8. 해리슨 포드가 등장했던 에피소드의 id와 에피소드 이름
select episodeid, episodename
from starwars
where episodeid in (select episodeid from casting where realname = "해리슨 포드");



use scott;

-- View
create or replace view vemp
as select empid, fname, deptid
from emp2
where deptid < 50 with check option;

select * from vemp;

insert into vemp values(701, 'queen', 20);


-- Index
create index email_index on emp(email);
create index name_index on emp(fname, lname);


select *
from emp
where phone = '650-4556-9990';




