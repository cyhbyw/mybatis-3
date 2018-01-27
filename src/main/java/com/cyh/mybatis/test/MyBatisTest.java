package com.cyh.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cyh.mybatis.bean.Employee;
import com.cyh.mybatis.dao.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CYH
 * @date 2018/1/27
 */
public class MyBatisTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisTest.class);

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        LOGGER.debug("sqlSessionFactory: {}", sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println("mapper.getClass(): " + mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }

    }
}
