package com.icboluo.z3_jedis.service;

import com.alibaba.fastjson.JSON;
import com.icboluo.util.TimeRecord;
import com.icboluo.z3_jedis.bean.Student;
import com.icboluo.z3_jedis.dao.StudentDao;
import com.icboluo.z3_jedis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

public class StudentService {
    private StudentDao studentDao = new StudentDao();

    public String queryAll() {
        TimeRecord.start("getJedis");
        Jedis jedis = JedisUtil.getJedis();
        TimeRecord.recordMsg("jedis get val");
        String jsonResult = jedis.get("H");
        //从redis中没有查到数据,从数据库中查数据
        if (jsonResult == null) {
            TimeRecord.recordMsg("db select");
            List<Student> students = studentDao.queryAll();
            jsonResult = JSON.toJSONString(students);
            TimeRecord.recordMsg("jedis set");
            jedis.set("H", jsonResult);
        }
        TimeRecord.build();
        return jsonResult;
    }
}
