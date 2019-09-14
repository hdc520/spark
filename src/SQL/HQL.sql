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

select student.*
from student join
(
select s_id,count(c_id) num
from score
where s_id not in(
select s_id
from score
where c_id not in(select c_id
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