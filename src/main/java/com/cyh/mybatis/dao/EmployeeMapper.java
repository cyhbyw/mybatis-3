package com.cyh.mybatis.dao;

import com.cyh.mybatis.bean.Employee;

/**
 * @author CYH
 * @date 2018/1/27
 */
public interface EmployeeMapper {

    Employee getEmpById(Integer id);
}
