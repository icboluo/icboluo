package com.icboluo.spring.accountqualifier;


import com.icboluo.spring.accountxml.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Account queryAccountById(Integer id) {
        System.out.println("daoimpl");
        return jdbcTemplate.queryForObject("select*from account where id =? ",new BeanPropertyRowMapper<>(Account.class),id);
    }

    @Override
    public List<Account> queryAccountList() {
        return jdbcTemplate.query("select *from account",new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public void saveAccount(Account account) {
        jdbcTemplate.update("INSERT INTO account VALUES (null,?,?)", account.getName(), account.getMoney());
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("UPDATE  account SET  money=? where id=?", account.getMoney(), account.getId());
    }

    @Override
    public void deleteAccountById(Integer id) {
        jdbcTemplate.update("DELETE from account where id=? ", id);
    }
}
