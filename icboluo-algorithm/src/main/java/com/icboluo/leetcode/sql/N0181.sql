-- 181 员工收入高于经理
select a.name Employee
from Employee a
         inner join Employee b on a.managerId = b.id
where a.salary > b.salary;
-- 182 重复电子邮件
select email
from Person
group by email
having count(*) > 1;
-- 183 从不点菜的顾客
select a.name Customers
from Customers a
         left join Orders b on a.id = b.customerId
where b.id is null;
-- 196 删除重复的电子邮件
delete
t1 from person t1 ,person t2 where t1.email=t2.email and t1.id>t2.id;
-- 197 气温升高
select w1.id
from Weather w1,
     Weather w2
where datediff(w1.recordDate, w2.recordDate) = 1
  and w1.temperature > w2.temperature;
-- 511 游戏玩法分析
-- FIXME
select a1.player_id, a1.event_date first_login
from Activity a1,
     Activity a2
where a1.player_id = a2.player_id
  and a1.event_date < a2.event_date;
select player_id, min(event_date) first_login
from Activity
group by player_id;
-- 577 员工奖金
select e.name, b.bonus
from Employee e
         left join Bonus b on e.empId = b.empId
where b.bonus < 1000
   or b.bonus is null;
-- 584 寻找客户推荐人
select name
from Customer
where referee_id !=2 or referee_id is null;
select name
from Customer
where coalesce(referee_id, 0) != 2;
-- 586 下订单数量最多的客户
select customer_number
from Orders
group by customer_number
order by count(customer_number) desc limit 1;
-- 595 大国
select name, population, area
from World
where area >= 3000000
   or population >= 25000000;
