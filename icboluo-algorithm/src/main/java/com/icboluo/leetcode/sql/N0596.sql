-- 596 班级人数超过5名学生
select class
from Courses
group by class
having count(class) >= 5;
-- 607 销售人员 FIXME
select name
from SalesPerson
where name in (select s.name
               from Orders o
                        left join SalesPerson s on o.sales_id = s.sales_id
                        left join Company c on o.com_id = c.com_id
               where c.name != 'RED');
select s.name
from SalesPerson
where s.name out in (select s.name
from Orders o
         left join SalesPerson s on o.sales_id = s.id
         left join Company c on o.com_id = c.id
where c.name = 'RED');
-- 610 三角判决
select *, if(x + y > z and x + z > y and y + z > x, 'Yes', 'No') triangle
from Triangle
-- 619 最大单个数字 optimize from 后面如果是子查询，应该取别名
select max(num) num
from (select num
      from MyNumbers
      group by num
      having count(*) = 1) temp_number;
-- 620 不无聊的电影
select *
from Cinema
where mod(id, 2) = 1
  and description != 'boring'
order by rating desc;
-- 627 交换工资
update Salary
set sex =
        case sex when 'm' then 'f' else 'm' end;
update Salary
set sex = if(sex = 'm', 'f', 'm');
-- 1050 至少合作过三次的演员和导演 至少合作过三次的演员和导演
select actor_id, director_id
from ActorDirector
group by actor_id, director_id
having count(*) >= 3;
-- 1068 产品销售分析
-- 当然，由于数据原因，也可以使用p表做主表做左链接
select p.product_name, s.year, s.price
from Sales s
         inner join Product p on s.product_id = p.product_id;
-- 1075 项目员工
select p.project_id, round(avg(e.experience_years), 2) average_years
from Project p
         left join Employee e on p.employee_id = e.employee_id
group by p.project_id;
-- 1084 销售分析3
select p.product_id, p.product_name
from Product p
         inner join Sales s on p.product_id = s.product_id
group by p.product_id
having max(s.sale_date) <= '2019-03-31'
   and min(s.sale_date) >= '2019-01-01';
