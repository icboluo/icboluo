-- 1965 缺少信息的员工
select *
from (select e.employee_id
      from employees e
               left join salaries s on e.employee_id = s.employee_id
      where s.employee_id is null
      union
      select s.employee_id
      from employees e
               right join salaries s on e.employee_id = s.employee_id
      where e.employee_id is null) a
order by employee_id

-- 3465 查找具有有效序列号的产品
select *
from products
where regexp_like(description, 'SN[0-9]{4}-[0-9]{4}[^0-9]+')
   OR regexp_like(description, 'SN[0-9]{4}-[0-9]{4}$')
ORDER BY product_id;
