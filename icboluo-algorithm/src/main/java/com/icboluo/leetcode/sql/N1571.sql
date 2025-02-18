-- 1581 到访但未进行任何交易的客户
select v.customer_id, count(0) count_no_trans
from Visits v
         left join Transactions t on v.visit_id=t.visit_id
where t.transaction_id is null
group by v.customer_id

-- 1587 银行账户摘要
select u.name, sum(t.amount) balance
from Users u
         left join Transactions t on u.account = t.account
group by u.account, u.name
having balance>10000;

-- 1633 参加比赛的用户百分比
select contest_id, round(count(distinct user_id) * 100 / (select count(user_id) from Users), 2) percentage
from Register
group by contest_id
order by percentage desc, contest_id

-- 1661 每台机器的平均加工时间 FIXME
select machine_id,
       round(
               (select avg(a1.timestamp)
                from Activity a1
                where a1.activity_type = 'end'
                  and a1.machine_id = a.machine_id)
                   -
               (select avg(a1.timestamp)
                from Activity a1
                where a1.activity_type = 'start'
                  and a1.machine_id = a.machine_id)
       ) processing_time
from Activity a
group by machine_id;

-- 1667 修复表格中的名称
select user_id, concat(upper(substring(name, 1, 1)), lower(substring(name, 2))) name
from users
order by user_id;

-- 1683 无效推文
select tweet_id
from tweets
where char_length(content) > 15;

-- 1693 每日线索和合作伙伴
select date_id, make_name, count(distinct lead_id) unique_leads, count(distinct partner_id) unique_partners
from dailysales
group by date_id, make_name;

-- 1729 查找关注者数量
select user_id, count(*) followers_count
from followers
group by user_id
order by user_id;

-- 1731 每个员工下属有多少名员工
select e1.employee_id, e1.name, count(*) reports_count, round(avg(e.age)) average_age
from employees e
         inner join employees e1 on e.reports_to = e1.employee_id
group by e1.employee_id
order by e1.employee_id;
