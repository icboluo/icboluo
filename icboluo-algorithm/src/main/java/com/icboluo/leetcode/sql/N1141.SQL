-- 1141 查询近30天活跃用户数
select activity_date day, count(distinct user_id) as active_users
from Activity
where activity_date > "2019-06-27" AND activity_date <= "2019-07-27"
group by activity_date
-- 1148 文章浏览量
select distinct (author_id) as id
from Views
where author_id = viewer_id
order by id
-- 1179 重新格式化部门表
select id, sum(if(month='Jan',revenue, null)) as Jan_Revenue
from Department
group by id
-- 1211 查询质量和百分比
select query_name,
       round(avg(cast(rating as decimal) / position), 2) as                   quality,
       round(sum(case when rating < 3 then 1 else 0 end) * 100 / count(*), 2) poor_query_percentage
from Queries
WHERE query_name IS NOT NULL
group by query_name
-- 1378
select uni.unique_id, e.name
from Employees e
         left join EmployeeUNI uni on e.id = uni = id
