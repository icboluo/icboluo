package com.icboluo.spring.accountqualifier;

import com.icboluo.spring.accountxml.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao2")
public class AccountDaoImpl2 extends JdbcDaoSupport implements IAccountDao {


    @Override
    public Account queryAccountById(Integer id) {
        System.out.println("daoimpl2");
        return getJdbcTemplate().queryForObject("select*from account where id =? ", new BeanPropertyRowMapper<>(Account.class), id);
    }

    @Override
    public List<Account> queryAccountList() {
        return getJdbcTemplate().query("select *from account", new BeanPropertyRowMapper<>(Account.class));
    }

    @Override
    public void saveAccount(Account account) {
        getJdbcTemplate().update("INSERT INTO account VALUES (null,?,?)", account.getName(), account.getMoney());
    }

    @Override
    public void updateAccount(Account account) {
        getJdbcTemplate().update("UPDATE  account SET  money=? where id=?", account.getMoney(), account.getId());
    }

    @Override
    public void deleteAccountById(Integer id) {
        getJdbcTemplate().update("DELETE from account where id=? ", id);
    }
}
