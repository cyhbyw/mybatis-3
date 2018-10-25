package com.cyh.mybatis.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.cyh.mybatis.bean.Employee;

/**
 * @author CYH
 * @date 2018/1/27
 */
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    /**
     * Mapper 和 Provider 中都添加有 @Param 注解，但是'返回数据库Id策略'却不生效
     * Mapper 和 Provider 中都添加有 @Param 注解，但是'返回数据库Id策略'却不生效
     * Mapper 和 Provider 中都添加有 @Param 注解，但是'返回数据库Id策略'却不生效
     * org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [e, param1]
     * org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [e, param1]
     * org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [e, param1]
     * @param e
     */
    @InsertProvider(type = EmployeeProvider.class, method = "addEmployeeWithBothParam")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void addEmployeeWithBothParam(@Param("e") Employee e);

    /**
     * 此方法可行
     * 注意----注意----注意：此方法与上述方法的区别在于   id  <----> e.id
     * @param e
     */
    @InsertProvider(type = EmployeeProvider.class, method = "addEmployeeWithBothParam")
    @Options(useGeneratedKeys = true, keyProperty = "e.id", keyColumn = "e.id")
    void addEmployeeWithBothParam2(@Param("e") Employee e);

    /**
     * Mapper 和 Provider 中都没有添加 @Param 注解，'返回数据库Id策略'生效
     * @param e
     */
    @InsertProvider(type = EmployeeProvider.class, method = "addEmployeeWithoutParam")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void addEmployeeWithoutParam(Employee e);
}
