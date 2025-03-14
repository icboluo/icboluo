-- 1141 查询近30天活跃用户数
select activity_date day, count(distinct user_id) as active_users
from Activity
where activity_date > "2019-06-27"
  AND activity_date <= "2019-07-27"
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

-- 1251 平均销售价格
select p.product_id, ifnull(round(sum(units * price) / sum(units), 2), 0) average_price
from prices p
         left join UnitsSold u
                   on p.product_id = u.product_id
                       and u.purchase_date between p.start_date and p.end_date
group by product_id

-- 1280 学生与考试 FIXME
select e.student_id, student_name, subject_name, count(*) attended_exams
from examinations e
         left join students sut on e.student_id = sut.student_id
         left join subjects sub on e.subject_name = sub.subject_name
group by student_id, student_name, subject_name
order by student_id, sub.subject_name

--1327 列出某一时期内订购的产品 FIXME
select p.project_name, sum(o.unit) unit
from products p
         left join orders o on p.product_id = o.product_id
where year (o.order_date)='2020' and month (o.order_date)='02'
group by product_id
having sum (o.unit)>=100
order by product_id
-- 1378
select uni.unique_id, e.name
from Employees e
         left join EmployeeUNI uni on e.id = uni = id
--1407 顶级旅行者 FIXME
select u.name, nullif(sum(r.distance), 0) travelled_distance
from user u
         left join rides r on u.id = r.user_id
group by u.id
order by travelled_distance desc, u.name
-- 1484 按日期分组销售产品
select sell_date,
       count(distinct product)                                       num_sold,
       group_concat(distinct product order by product separator ',') products
from Activities
group by sell_date
order by sell_date;
-- 1517 查找拥有有效邮箱的用户
select *
from Users
where mail regexp '^[a-zA-Z][a-zA-Z0-9_\.\-]*@leetcode(\\?com)?\\.com$'

--1527 患有某种疾病的患者 FIXME
select *
from patients
where conditions regexp '\\bDIAB1'
