package com.icboluo;

import com.icboluo.mapper.ProvinceMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author icboluo
 * @since 2022-06-13 21:38
 */
public class MyBatis {
    public static void main(String[] args) throws IOException {
        // 该文件中拥有SQL的账号密码等设置
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // 一个SqlSessionFactory只能连接一个数据库实例（聚合了数据库配置或者聚合了数据库通道
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        System.out.println(mapper.queryAll());

        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();
    }
}
