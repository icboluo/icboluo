-- 1741 计算每个员工花费的总时间
select event_day day, emp_id, sum(out_time) - sum(in_time) total_time
from employees
group by emp_id, event_day;

-- 这个的效率更高
select  event_day day, emp_id, sum(out_time - in_time) total_time
from employees
group by emp_id, event_day;

-- 1757 可回收低脂产品
select product_id
from products
where low_fats = 'Y'
  and recyclable = 'Y';

-- 1789 每个员工的主要部门
select employee_id, department_id
from employee
where primary_flag = 'Y'
   or employee_id in (select employee_id from employee group by employee_id having count(employee_id) = 1)

-- 1795 重新排列产品表
select product_id, 'store1' store, store1 price
from products
where store1 is not null
union all
select product_id, 'store2' store, store2 price
from products
where store2 is not null
union all
select product_id, 'store3' store, store3 price
from products
where store3 is not null

-- 1873 计算特别奖金
select employee_id, salary bonus
from employees
where employee_id% 2<>0 and  name not like 'M%'
union
select employee_id, 0 bonus
from employees
where employee_id% 2=0 or  name  like 'M%'
order by employee_id

-- 1890 2020年最新登录
select user_id, max(time_stamp) last_stamp
from logins
where year (time_stamp) =2020
group by user_id



