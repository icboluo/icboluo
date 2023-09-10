package com.icboluo.z3_jedis.service;

import com.alibaba.fastjson.JSON;
import com.icboluo.z3_jedis.bean.Student;
import com.icboluo.z3_jedis.dao.StudentDao;
import com.icboluo.z3_jedis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDao();

    public String queryAll() {
        long start = System.currentTimeMillis();
        Jedis jedis = JedisUtil.getJedis();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        String jsonResult = jedis.get("H");
        //从redis中没有查到数据,从数据库中查数据
        if (jsonResult == null) {
            long l1 = System.currentTimeMillis();
            List<Student> students = studentDao.queryAll();
            long l2 = System.currentTimeMillis();
            System.out.println(l2 - l1);
            jsonResult = JSON.toJSONString(students);
            jedis.set("H", jsonResult);
        }
        return jsonResult;
    }
}
