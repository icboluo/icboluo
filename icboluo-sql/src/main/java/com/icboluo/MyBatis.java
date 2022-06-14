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
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProvinceMapper mapper = sqlSession.getMapper(ProvinceMapper.class);
        System.out.println(mapper.queryAll());

        sqlSession.commit();
        sqlSession.flushStatements();
        sqlSession.close();
    }
}
