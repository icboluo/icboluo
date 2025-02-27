-- 1741 计算每个员工花费的总时间 FIXME
select event_day emp_id, event_day day, sum(out_time) - sum(in_time) total_time
from employees
group by emp_id, event_day;

-- 这个的效率更高
select event_day emp_id, event_day day, sum(out_time - in_time) total_time
from employees
group by emp_id, event_day;

-- 1757 可回收低脂产品
select product_id
from products
where low_fats = 'Y'
  and recyclable = 'Y';




