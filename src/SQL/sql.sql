create table student (s_id string,s_name string,s_birth string,s_sex string)row format delimited fields terminated by ' '
create table course (c_id string,c_name string,t_id string) row format delimited fields terminated by ' '
create table teacher(t_id string,t_name string)row format delimited fields terminated by ' '
create table score(s_id string,c_id string,s_score int)row format delimited fields terminated by ''


-- 1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数:
方法１：
select t1.s_id,t1.s_name,t1.c_id,t1.s_score
from(
select student.s_id,student.s_name,score.c_id,score.s_score
from student join score on student.s_id=score.s_id
where score.c_id='01'
)t1
join(
select student.s_id,student.s_name,score.c_id,score.s_score
from student join score on student.s_id=score.s_id
where score.c_id='02'
)t2 on t1.s_id=t2.s_id
where t1.s_score>t2.s_score

方法二：
select student.*,s1.s_score,s2.s_score
from student join score s1 on student.s_id=s1.s_id and s1.c_id='01'
join score s2 on student.s_id=s2.s_id and s2.c_id='02'
where s1.s_score>s2.s_score

-- 查询"01"课程比"02"课程成绩低的学生的信息及课程分数:
select student.*,s1.s_score,s2.s_score
from student join score s1 on student.s_id=s1.s_id and s1.s_id='01'
join score s2 on s1.s_id=s2.s_id and s2.s_id='02'
where s1.s_score<s2.s_score

-- 查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
select student.s_id,student.s_name,avg(s_score) average
from student left join score on student.s_id=score.s_id
group by student.s_id,student.s_name

方法二：
select student.*,tmp.*
from(
select Sid,avg(s_score) average
from score
group by s_id
where average<60
-- hive和mysql分组之后只能用having来比较或者使用子查询后在where中比较
)tmp join student on student.s_id=tmp.s_id;

-- – 4、查询平均成绩小于60分的同学的学生编号和学生姓名和平均成绩:
-- – (包括有成绩的和无成绩的)
方法１
select student.s_id,student.s_name,avg(s_score) average
from student left join score on student.s_id=score.s_id
group by student.s_id,student.s_name having average<60;

-------------------------------------------------------------
select student.s_id,student.s_name,s.average
from student left join(
	select score.s_id,avg(score.s_score) average
	from score
	group by score.s_id having average<60
)s on student.s_id=s.s_id

方法２：
select student.s_id,student.s_name,s.average
from student join(
select score.s_id,avg(score.s_score) average
from score
group by score.s_id having average<60
)s on student.s_id=s.s_id
union
select student.s_id,student.s_name,0 average
from student
where student.s_id not in (
select distinct score.s_id
from score
)

-- 5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩:
select student.s_id,student.s_name,count(score.c_id),sum(s_score)
from student left join score on student.s_id=score.s_id
group by student.s_id,student.s_name

select s2.*,s1.s_name
from student s1 join (
select s.s_id id,count(c_id),sum(s_score)
from student s left join score on s.s_id=score.s_id
group by id
)s2 on s1.s_id=s2.id

-- – 6、查询"李"姓老师的数量:
select count(*)
from teacher
where t_name like '李%'

-- – 7、查询学过"张三"老师授课的同学的信息:
select student.*
from student join score on student.s_id=score.s_id
join course on score.c_id=course.c_id
join teacher on course.t_id=teacher.t_id
where teacher.t_name='张三';

-- – 8、查询没学过"张三"老师授课的同学的信息:

select student.*
from student
where student.s_id not in (
select student.s_id
from student join score on student.s_id=score.s_id
join course on score.c_id=course.c_id
join teacher on course.t_id=teacher.t_id
where teacher.t_name='张三'
)

-- 查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息:
-- mysql可以但是在hive中取别名才行
select student.*
from student join score s1 on student.s_id=s1.s_id and s1.c_id='02'
where student.s_id in(
select s_id
from score
where score.c_id='01'
);

select student.*
from student join score on student.s_id=score.s_id and score.c_id='02'
where student.s_id in(
select s_id
from score
where score.c_id='01'
);

-- 方法二既可以支持hive又可以支持mysql
select student.*
from student join score s1 on student.s_id=s1.s_id and s1.c_id='01'
join score s2 on student.s_id=s2.s_id and s2.c_id='02'

-- 查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息:
select student.*
from student join score s1 on student.s_id=s1.s_id and s1.c_id='01'
where student.s_id not in(
select s_id
from score
where c_id='02'
)

select s_id,sum(s_score) s
from score
group by s_id
-- order by s或者sum(s_score)都可以
order by sum(s_score)

-- 11、查询没有学全所有课程的同学的信息:
-- mysql中
select student.*,tmp.num
from(
select s1.s_id,count(score.c_id) num
from student s1 left join score on s1.s_id=score.s_id
group by s1.s_id having num<(select count(c_id) from course)
)tmp join student on tmp.s_id=student.s_id;
-- hive中
select student.*,tmp.num
from(
select s1.s_id,count(score.c_id) num
from student s1 left join score on s1.s_id=score.s_id
group by s1.s_id having num<3
)tmp join student on tmp.s_id=student.s_id;

 -- 12、查询至少有一门课与学号为"01"的同学所学相同的同学的信息:
 select distinct student.*
 from student join score on student.s_id=score.s_id
 where c_id in(
 select c_id
 from score
 where s_id='01'
 )

-- hive中
select distinct student.*
from(
select c_id
from score
where s_id='01'
)tmp1 join(
select c_id,s_id
from score
)tmp2 on tmp1.c_id=tmp2.c_id join student on student.s_id=tmp2.s_id and student.s_id!='01'

-- 13、查询和"01"号的同学学习的课程完全相同的其他同学的信息:

select student.*
from student join
(
	select s_id,count(c_id) num
	from score
	where s_id not in(
		select s_id
		from score
		where c_id not in(
			select c_id
			from score
			where s_id='01'
		)
	)
	group by s_id having num=(
		select count(c_id)
		from score
		where s_id='01'
		group by s_id
	)
)tmp on student.s_id=tmp.s_id
where student.s_id!='01'
-- ----------------------------------
select student.*
from student join(
    select s_id,group_concat(c_id order by c_id separator '|') course_id
    from score
    where s_id!='01'
    group by s_id
)tmp1 on student.s_id=tmp1.s_id
join(
    select s_id,group_concat(c_id order by c_id separator '|') course_id
    from score
    where s_id='01'
)tmp2 on tmp1.course_id=tmp2.course_id

-- hive中
select student.*,tmp1.course_id 
from student join (
    select s_id ,concat_ws('|', collect_set(c_id)) course_id
    from score
    group by s_id having s_id not in (1)
)tmp1 on student.s_id = tmp1.s_id
join (
    select concat_ws('|', collect_set(c_id)) course_id2
    from score  where s_id=1
)tmp2 on tmp1.course_id = tmp2.course_id2;

-- – 14、查询没学过"张三"老师讲授的任一门课程的学生姓名:
select distinct student.s_id,student.s_name
from student
where s_id not in(
    select s_id
    from score join course on score.c_id=course.c_id
    join teacher on course.t_id=teacher.t_id
    where t_name='张三'
)
-- 15、查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩:
-- hive和mysql中通用
select student.s_id,student.s_name,average
from student join (
 select s_id,avg(s_score) average,count(c_id) num
 from score
 where s_score<60
 group by s_id having num>=2
)tmp on student.s_id=tmp.s_id

-- 16、检索"01"课程分数小于60，按分数降序排列的学生信息:
select student.*,s_score
from student join score on student.s_id=score.s_id and c_id='01' and s_score<60
order by s_score desc
 -- 17、按平均成绩从高到低显示所有学生的所有课程的成绩以及平均成绩:
 -- mysql中
select s_id,
max(case c_id when '01' then s_score else 0 end) '01',
max(case c_id when '02' then s_score else 0 end) '02',
max(case c_id when '03' then s_score else 0 end) '03',avg(s_score) average
from score
group by s_id
order by average desc
-- hive中
select a.s_id,tmp1.s_score as chinese,tmp2.s_score as math,tmp3.s_score as english,
    round(avg (a.s_score),2) as avgScore
from score a
left join (select s_id,s_score  from score s1 where  c_id='01')tmp1 on  tmp1.s_id=a.s_id
left join (select s_id,s_score  from score s2 where  c_id='02')tmp2 on  tmp2.s_id=a.s_id
left join (select s_id,s_score  from score s3 where  c_id='03')tmp3 on  tmp3.s_id=a.s_id
group by a.s_id,tmp1.s_score,tmp2.s_score,tmp3.s_score 
order by avgScore desc;

-- 18.查询各科成绩最高分、最低分和平均分：以如下形式显示：课程ID，课程name，最高分，最低分，平均分，及格率，中等率，优良率，优秀率:

select course.c_id,course.c_name,
       tmp.maxScore,tmp.minScore,tmp.avgScore,
       tmp.passRate,tmp.moderate,tmp.goodRate,tmp.excellentRates 
from course
join(
    select c_id,max(s_score) as maxScore,
    min(s_score) minScore,
    avg(s_score) avgScore,
    sum(case when s_score>=60 then 1 else 0 end)/count(c_id) passRate,
    sum(case when s_score>=60 and s_score<70 then 1 else 0 end)/count(c_id) moderate,
    sum(case when s_score>=70 and s_score<80 then 1 else 0 end)/count(c_id) goodRate,
    sum(case when s_score>=80 and s_score<90 then 1 else 0 end)/count(c_id) excellentRates
    from score
    group by c_id
)tmp on tmp.c_id=course.c_id;

-- 19、按各科成绩进行排序，并显示排名:
-- – row_number() over()分组排序功能(mysql没有该方法)
-- mysql中
select s1.c_id,s1.s_score
from score s1
where 3>=(
select count(distinct(s2.s_score))
from score s2
where s2.s_score>s1.s_score and s1.c_id=s2.c_id
)
-- hive中
select tmp.c_id,tmp.s_score,tmp.rank
from(
 select c_id,s_score,row_number() over(partition by c_id order by s_score desc) rank
 from score
)tmp
where tmp.rank<=4

 -- 20、查询学生的总成绩并进行排名:
 -- mysql中
 select s_id,sum(s_score) s
 from score
 group by s_id
 order by s desc

 -- hive中
 select score.s_id,sum(s_score) s,row_number() over(order by sum(s_score) desc) rank
 from score
 group by s_id

 select score.s_id,sum(s_score) sumscore,row_number()over(order by sum(s_score) desc) Ranking
 from score
 group by score.s_id
 -- 加不加order by没有区别
 order by sumscore desc;

-- 21、查询不同老师所教不同课程平均分从高到低显示:
select round(avg(s_score),2) average
from score join course on score.c_id=course.c_id
     join teacher on teacher.t_id=course.t_id
group by teacher.t_id
order by average desc

 -- 22、查询所有课程的成绩第2名到第3名的学生信息及该课程成绩:

select student.*,tmp.sumScore
from student join(
 select s_id,sum(s_score) sumScore
 from score
 group by s_id
 order by sumScore desc
 limit 1,2
 )tmp on student.s_id=tmp.s_id

 select * 
 from(
 	select tmp1.* 
 	from (
 		select * 
 		from score 
 		where c_id='01' 
 		order by s_score desc 
 		limit 3
 		)tmp1 
 	order by s_score asc 
 	limit 2) a 
 union all 
 select * 
 from (
 	select tmp2.* 
 	from (
 		select * 
 		from score 
 		where c_id='02' 
 		order by s_score desc 
 		limit 3)tmp2 
 	order by s_score asc 
 	limit 2
 	) b 
 union all 
 select * 
 from (
 	select tmp3.* 
 	from (
 		select * 
 		from score 
 		where c_id='03' 
 		order by s_score desc 
 		limit 3
 		)tmp3 
 	order by s_score asc 
 	limit 2
 	) c; 

  -- 23、统计各科成绩各分数段人数：课程编号,课程名称,[100-85],[85-70],[70-60],[0-60]及所占百分比

  select tmp.id,sum1,sum1/num rate_60,sum2,sum2/num rate_60_70,sum3,sum3/num rate_70_80,sum4,sum4/num rate_80
  from(
  select s.c_id id,c_name,count(s.c_id) num,
  sum(case when s_score<60 then 1 else 0 end) sum1,
  sum(case when s_score<70 and s_score>=60 then 1 else 0 end) sum2,
  sum(case when s_score>=85 then 1 else 0 end) sum4,
  sum(case when s_score>=70 and s_score<85 then 1 else 0 end) sum3
  from score s join course c on s.c_id=c.c_id
  group by c.c_id,c_name
  )tmp

--24、查询学生平均成绩及其名次:
select student.s_id,student.s_name,tmp.*
 from student join(
 select s_id,avg(s_score) average,row_number() over(order by avg(s_score) desc) rank
 from score
 group by s_id
)tmp on student.s_id=tmp.s_id

 -- 25、查询各科成绩前三名的记录
-- mysql
select tmp.c_id,student.s_id,student.s_name,tmp.Score
from student join(
 select s1.c_id,s1.s_id,s1.s_score Score
 from score s1
 where 3>(
  select count(distinct(s2.s_score))
  from score s2
  where s1.c_id=s2.c_id and s1.s_score<s2.s_score
 )
)tmp on student.s_id=tmp.s_id
 order by tmp.c_id,Score desc

 -- hive
 select tmp.c_id,tmp.s_id,student.s_name,tmp.s_score,tmp.rank
 from(
  select s_id,c_id,s_score,row_number() over(partition by c_id order by s_score desc) rank
  from score
 )tmp join student on student.s_id=tmp.s_id
 where tmp.rank<=3

-- 26、查询每门课程被选修的学生数:
select c_id,count(s_id)
from score
group by c_id

select c.c_id,c.c_name,tmp.number from course c
    join (select c_id,count(1) as number from score
        where score.s_score<60 group by score.c_id)tmp
    on tmp.c_id=c.c_id;

 -- 27、查询出只有两门课程的全部学生的学号和姓名:
select student.s_id,student.s_name
from(
 select s_id,count(c_id) num
 from score
 group by s_id having num=2
)tmp join student on tmp.s_id=student.s_id

 -- 28、查询男生、女生人数:
 select sum(case s_sex when '男' then 1 else 0 end) male,
        sum(case s_sex when '女' then 1 else 0 end) female
 from student

select s_sex,count(s_sex)
from student
group by s_sex

-- 查询同名同性学生名单，并统计同名人数:
select count(*)
from student s1 join student s2 on s1.s_name=s2.s_name and s1.s_sex=s2.s_sex
where s1.s_id!=s2.s_id

-- 31、查询1990年出生的学生名单:
select student.*
from student
where student.s_birth like "1990%"

 --32、查询每门课程的平均成绩，结果按平均成绩降序排列，平均成绩相同时，按课程编号升序排列:
 select c_id,avg(s_score) average
 from score
 group by c_id
 order by average desc,c_id asc;

 -- 33、查询平均成绩大于等于85的所有学生的学号、姓名和平均成绩:

select student.s_id,student.s_name,average
from student join(
 select s_id,avg(s_score) average
 from score
 group by s_id having average>85
)tmp on student.s_id=tmp.s_id

 -- 34、查询课程名称为"数学"，且分数低于60的学生姓名和分数:

select student.s_name,mathScore
from student join(
 select s_id,score.s_score mathScore
 from score join course on score.c_id=course.c_id
 where c_name='数学' and s_score<60
)tmp on student.s_id=tmp.s_id

-- 35、查询所有学生的课程及分数情况:
select s_name,
       max(case c_name when '语文' then s_score else 0 end) chinese,
       max(case c_name when '数学' then s_score else 0 end) math,
       max(case c_name when '英语' then s_score else 0 end) Eenlish,
       sum(s_score)
from student join score on student.s_id=score.s_id
             join course on score.c_id=course.c_id
group by s_name

-- 36、查询任何一门课程成绩在70分以上的学生姓名、课程名称和分数:
select s_name,
       max(case c_name when '语文' and s_score>70 then s_score else 0 end) chinese,
       max(case c_name when '数学' and s_score>70 then s_score else 0 end) math,
       max(case c_name when '英语' and s_score>70 then s_score else 0 end) Eenlish,
       sum(s_score)
from student join score on student.s_id=score.s_id
             join course on score.c_id=course.c_id
group by s_name


select student.s_name,course.c_name,tmp.s_score
from(
 select s_id,c_id,s_score
 from score
 where s_score>70
)tmp join student on tmp.s_id=student.s_id join course on tmp.c_id=course.c_id

 -- 37、查询课程不及格的学生:
select student.*,c_id
from(
 select s_id,c_id
 from score
 where s_score<60
)tmp join student on tmp.s_id=student.s_id

-- 38、查询课程编号为01且课程成绩在80分以上的学生的学号和姓名:
select student.s_id,student.s_name
from(
 select s_id
 from score
 where c_id='01' and s_score>=80
)tmp join student on student.s_id=tmp.s_id

-- 9、求每门课程的学生人数:
select c_id,count(s_id)
from score
group by c_id

 -- 40、查询选修"张三"老师所授课程的学生中，成绩最高的学生信息及其成绩:
 select student.s_id,student.s_name,s_score
 from student join(
  select s_id,c_id,s_score
  from score s1
  where s_score>=all(
   select s_score
   from score s2
   where s1.c_id=s2.c_id)
 )tmp on student.s_id=tmp.s_id join course on tmp.c_id=course.c_id join teacher on course.t_id=teacher.t_id
 where t_name='张三'

-- hive中
select student.*,maxScore
from student join(
    select s_id,score.c_id,max(s_score) maxScore
    from score join course on score.c_id=course.c_id join teacher on teacher.t_id=course.t_id
    where t_name='张三'
    group by s_id,score.c_id
    limit 1
)tmp on student.s_id=tmp.s_id

select student.*,s_score
from student join(
select s_id,score.c_id,s_score
    from score join course on score.c_id=course.c_id join teacher on teacher.t_id=course.t_id
    where t_name='张三'
    order by s_score desc 
    limit 1
)tmp on student.s_id=tmp.s_id


-- 41、查询不同课程成绩相同的学生的学生编号、课程编号、学生成绩:
select distinct s1.*,s2.*
from score s1,score s2
where s1.c_id!=s2.c_id and s1.s_score=s2.s_score

-- 42、查询每门课程成绩最好的前三名:
-- mysql中
select s1.c_id,s1.s_id,s1.s_score Score
from score s1
where 3>(
 select count(distinct(s2.s_score))
 from score s2
 where s1.c_id=s2.c_id and s2.s_score>s1.s_score
)
order by s1.c_id,Score desc;
-- hive中
select tmp.c_id,student.s_id,student.s_name,tmp.Score
from student join(
 select s1.c_id,s1.s_id,s1.s_score Score
 from score s1
 where 3>(
  select count(distinct(s2.s_score))
  from score s2
  where s1.c_id=s2.c_id and s2.s_score>s1.s_score
 )
)tmp on student.s_id=tmp.s_id
order by tmp.c_id,tmp.Score desc

select tmp.c_id,student.s_id,student.s_name,tmp.s_score,tmp.rank
from student join(
     select s_id,c_id,s_score,row_number() over(partition by c_id order by s_score desc) rank
     from score
)tmp on student.s_id=tmp.s_id
where tmp.rank<3

-- 43、统计每门课程的学生选修人数（超过5人的课程才统计）:
-- – 要求输出课程号和选修人数，查询结果按人数降序排列，若人数相同，按课程号升序排列
select c_id,count(c_id) num
from score
group by c_id having num>5
order by num desc,c_id

-- 44、检索至少选修两门课程的学生学号:
select s_id, count(c_id)
from score
group by s_id having count(c_id)>=2

-- 45、查询选修了全部课程的学生信息:
select student.*,tmp.num
from student join(
 select s_id, count(c_id) num
 from score
 group by s_id having num=3
)tmp on student.s_id=tmp.s_id

-- 46、查询各学生的年龄(周岁):
-- – 按照出生日期来算，当前月日 < 出生年月的月日则，年龄减一
select s_name,s_birth,
	floor(
		(datediff(current_date,s_birth) - floor((year(current_date) - year(s_birth))/4))/365
		) as age
from student;

select student.*,floor(datediff(CURRENT_DATE,s_birth)/365) age
from student

-- 47、查询本周过生日的学生:
select * from student where weekofyear(CURRENT_DATE)+1 =weekofyear(s_birth);

-- 留存率表 user(u_id,time)
select (
       select count(distinct u_id)
       from user
       where time='today'
     )/(
       select count(1)
       from user
       where time='yesterday'
     )