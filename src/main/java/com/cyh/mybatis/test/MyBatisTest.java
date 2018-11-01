package com.cyh.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyh.mybatis.bean.Employee;
import com.cyh.mybatis.dao.EmployeeMapper;

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
            System.err.println("mapper.getClass(): " + mapper.getClass());
            System.err.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        LOGGER.debug("sqlSessionFactory: {}", sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee e = buildEmployee();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.addEmployeeWithBothParam(e);
            System.err.println("After insert. id = " + e.getId());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        LOGGER.debug("sqlSessionFactory: {}", sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee e = buildEmployee();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.addEmployeeWithBothParam2(e);
            System.err.println("After insert. id = " + e.getId());
        } finally {
            sqlSession.close();
        }
    }

    private Employee buildEmployee() {
        Employee e = new Employee();
        e.setLastName("cyh");
        e.setEmail("cyh@qq.com");
        e.setGender("0");
        return e;
    }

    @Test
    public void test04() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        LOGGER.debug("sqlSessionFactory: {}", sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee e = buildEmployee();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.addEmployeeWithoutParam(e);
            System.err.println("After insert. id = " + e.getId());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 批量插入时，也可以返回主键Id
     * @throws IOException
     */
    @Test
    public void test05() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        LOGGER.debug("sqlSessionFactory: {}", sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee1 = buildEmployee();
            Employee employee2 = buildEmployee();
            List<Employee> list = Arrays.asList(employee1, employee2);
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.batchInsertAndGenerateId(list);
            System.err.println("employee1.getId() " + employee1.getId());
            System.err.println("employee2.getId() " + employee2.getId());
        } finally {
            sqlSession.close();
        }
    }
}
