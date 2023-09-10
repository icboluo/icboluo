package com.icboluo.spring.accounttransaction.dao;

import com.icboluo.spring.accountxml.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
/*public class IAccountDao extends JdbcDaoSupport implements IAccoutDao {*/
public class IAccountDao implements IAccoutDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account queryAccountByName(String sourceName) {
        return jdbcTemplate.queryForObject("select*from account where name=? ", new BeanPropertyRowMapper<>(Account.class), sourceName);
    }

    @Override
    public void updateAccount(Account account) {
      jdbcTemplate.update("UPDATE  account set money =? where id=?", account.getMoney(), account.getId());
    }


}
