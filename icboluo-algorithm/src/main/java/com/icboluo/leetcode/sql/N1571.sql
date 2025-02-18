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
