package com.icboluo.spring.accountxml.mapper;

import com.icboluo.spring.accountxml.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountMapper implements IAccountMapper {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAccount(Account account) {
        jdbcTemplate.update("insert into account VALUES (null,?,?)", account.getName(), account.getMoney());
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("UPDATE account set money=? where id=?", account.getMoney(), account.getId());
    }

    @Override
    public void deleteAccountById(Integer accountId) {
        jdbcTemplate.update("DELETE from account where id =? ", accountId);
    }

    @Override
    public Account queryAccountById(Integer accountId) {
        return jdbcTemplate.queryForObject("select *from account  where id=? ", new BeanPropertyRowMapper<>(Account.class),accountId);
    }

    @Override
    public List<Account> queryAccountList() {
        return jdbcTemplate.query("SELECT *from account ", new BeanPropertyRowMapper<>(Account.class));
    }
}
