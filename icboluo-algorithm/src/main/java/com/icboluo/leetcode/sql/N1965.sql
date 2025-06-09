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
order by employee_id;

-- 1978 经理离职的员工
select e1.employee_id
from employees e1
         left join employees e2 on e1.manager_id = e2.employee_id
where e1.salary < 30000
  and e2.employee_id is null
  and e1.manager_id is not null
order by employee_id;

-- 2356 每位教师教授的独特科目数量
select teacher_id, count(distinct subject_id) cnt
from teacher
group by teacher_id

-- 3436 查找有效电子邮件
select *
from users
where regexp_like(email, '^[a-zA-Z0-9_]+@[a-zA-Z0-9_]+\.com$')
order by user_id


-- 3465 查找具有有效序列号的产品
select *
from products
where regexp_like(description, 'SN[0-9]{4}-[0-9]{4}[^0-9]+')
   OR regexp_like(description, 'SN[0-9]{4}-[0-9]{4}$')
ORDER BY product_id;

-- 3570 查找没有可用副本的书籍
select lb.book_id, lb.title, lb.author, lb.genre, lb.publication_year, lb.total_copies current_borrowers
from library_books lb
where lb.total_copies = (select count(*)
                         from borrowing_records br
                         where br.book_id = lb.book_id
                           and br.return_date is null)
order by lb.total_copies desc, lb.title
