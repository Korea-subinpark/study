select case 2
when 1 then 'class-1'
when 2 then 'class-2'
when 3 then 'class-3'
else 'other'
end;

# 부서코드 30-> seattle, 40->Newyork, 50->LA 나머지는 washington
select deptid, case deptid
when 30 then 'seattle'
when 40 then 'Newyork'
when 50 then 'LA'
else 'Washington'
end address
from emp;

-- comm 없으면 없음
select empid,
case comm
	when null then '없음'
    else comm
end comm
from emp;

select empid, ifnull(comm, '없음') comm
from emp;

-- group by
-- (부서번호가 50이상인 데이터 대상) 업무별 연봉 평균, 인원수
-- (연봉평균으로 내림차순 정렬, 별칭 '연봉평균', 소수점 2번째자리에서 반올림)
select jobid 업무, round(avg(salary), 1) 연봉평균, count(empid) 인원수
from emp
where deptid >= 50
group by jobid
having count(empid) >= 10
order by 연봉평균;


insert into emp(empid, fname, lname, email, phone, hdate, jobid, salary, comm, mgrid, deptid)
values(300, 'kim', 'taekhwan', 'taek@me.com', '010-2334-8909', now(), 'IT_PROG', 6000, 0.7, 103, 50);

insert into emp
values(301, 'lee', 'taekhwan', 'taek@me.com', '010-2334-8909', curdate(), 'IT_PROG', 6000, 0.7, 103, 50);

insert into emp(empid, fname, lname)
values(302, 'park', 'taekhwan');

select *
from emp
where empid >= 300;

-- update
update emp
set salary = 15000,
comm = 0.3,
deptid = 30
where empid = 300;

-- delete
delete from emp
where empid = 300;


-- create table
create table test(id int(1) primary key,
name varchar(10) not null);


insert into test values(1, 'dada');
insert into test values(2, 'tommy');

select * from test;

-- alter table
alter table test
add tel varchar(10); -- 새 컬럼 추가

alter table test
modify tel varchar(11); -- 컬럼 크기 수정

alter table test
drop tel; -- 컬럼 삭제

drop table test;
desc test;

-- pk 추가
alter table emp
add constraint pk_emp
primary key(empid);

alter table dept
add constraint pk_dept
primary key(deptid);

alter table job
add constraint pk_job
primary key(jobid);

alter table loc
add constraint pk_loc
primary key(locid);

-- fk 지정
alter table emp
add constraint fk_emp_jobid
foreign key(jobid)
references job(jobid);

alter table emp
add constraint fk_emp_deptid
foreign key(deptid)
references dept(deptid);

alter table dept
add constraint fk_dept_locid
foreign key(locid)
references loc(locid);

alter table emp
add constraint fk_emp_mgrid
foreign key(mgrid)
references emp(empid);




create table member(mid int(1) primary key, name varchar(10), address varchar(20));
create table orders(oid int(1) primary key, odate datetime, mid int(1));

-- insert 
insert into member values(111, 'jane', 'la'), (222, 'roy', 'seoul');
select * from member;

insert into orders values(4, now(), 111);
select * from orders;

delete from member
where mid = 111; -- error 삭제할 때는 자식 먼저

create table east(
eid decimal(3) primary key,
name varchar(20));

create table west(
wid decimal(3) primary key,
wdate datetime not null,
eid decimal(3));

-- fk 지정
alter table west
add constraint fk_west foreign key(eid)
references east(eid)
on delete cascade;

-- pk, fk, not null, unique, default
create table south(
sid decimal(3) primary key,
code varchar(10) unique,
email varchar(10) unique,
points int(1) default 0,
grade varchar(20) default 'silver');

insert into south(sid, code, email) values(1, 111, 'abc'); -- ok
insert into south(sid, code, email) values(2, null, 'abj'); -- ok

select * from south;


alter table orders
add constraint fk_oders_mid
foreign key(mid)
references member(mid);

drop table emp;
drop table dept;
drop table job;
drop table loc;

show tables;


-- inner join
-- 사번, 이름, 부서id, 부서명 -> emp, dept
select e.empid, e.fname, e.deptid, d.deptname
from emp e, dept d
where e.deptid = d.deptid;

-- ansi 표준
select e.empid, e.fname, e.deptid, d.deptname
from emp e inner join dept d
on e.deptid = d.deptid;

select e.empid, e.fname, e.deptid, d.deptname
from emp e join dept d
on e.deptid = d.deptid;


-- 사번, 이름, jobtitle
select e.empid, e.fname, j.jobtitle
from emp e, job j
where e.jobid = j.jobid;

-- fname이 'Alexander'인 사람의 부서이름
select e.fname, d.deptname
from emp e, dept d
where e.fname = 'Alexander' and e.deptid = d.deptid;

-- 부서 id, 부서명, city
select d.deptid, d.deptname, l.city
from dept d, loc l
where d.locid = l.locid;

# ansi 표준
select d.deptid, d.deptname, l.city
from dept d join loc l
on d.locid = l.locid;

-- 부서 id, 부서명, 관리자 이름
select d.deptid, d.deptname, e.fname
from dept d, emp e
where d.mgrid = e.empid;

-- 사번, 이름, 부서명, jobtitle
select e.empid, e.fname, d.deptname, j.jobtitle
from emp e, dept d, job j
where e.deptid = d.deptid and e.jobid = j.jobid;


-- empid, fname, deptname, city, country
select e.empid, e.fname, d.deptname, l.city, l.countryid
from emp e, dept d, loc l
where e.deptid = d.deptid and d.locid = l.locid;

#ansi 표준
select e.empid, e.fname, d.deptname, l.city, l.countryid
from emp e join dept d join loc l
on e.deptid = d.deptid and d.locid = l.locid;

-- 상관 정보 출력
select e.empid, e.fname 본인이름, e2.fname 상관이름
from emp e, emp e2
where e.mgrid = e2.empid;

-- outer join
select e.empid, e.fname, d.deptid, d.deptname
from emp e left outer join dept d
on e.deptid = d.deptid;

-- sub query
select fname, salary
from emp
where salary = (select min(salary) from emp);

-- 사번이 116번인 사람과 같은 일을 하는 사원의 정보
select *
from emp
where jobid = (select jobid from emp where empid = 116);

-- 평균 연봉보다 많이 받는 사람들의 정보
select *
from emp
where salary >= (select avg(salary) from emp)
order by salary desc;

-- 시애틀에 있는 부서 이름
select deptid, deptname
from dept
where locid = (select locid from loc where city = 'Seattle');

-- state가 null
select deptid, deptname, locid
from dept
where locid in (select locid from loc where state is null);


