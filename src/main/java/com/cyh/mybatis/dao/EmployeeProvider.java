package com.cyh.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.cyh.mybatis.bean.Employee;

/**
 * @author: CYH
 * @date: 2018/10/25 0025 21:50
 */
public class EmployeeProvider {

    public String addEmployeeWithBothParam(@Param("e") Employee e) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tbl_employee");
        sql.VALUES("last_name", "#{e.lastName}");
        sql.VALUES("email", "#{e.email}");
        sql.VALUES("gender", "#{e.gender}");
        return sql.toString();
    }

    public String addEmployeeWithoutParam(Employee e) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tbl_employee");
        sql.VALUES("last_name", "#{lastName}");
        sql.VALUES("email", "#{email}");
        sql.VALUES("gender", "#{gender}");
        return sql.toString();
    }

}
