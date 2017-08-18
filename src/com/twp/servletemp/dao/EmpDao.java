package com.twp.servletemp.dao;

import com.twp.servletemp.entity.Employee;

import java.sql.ResultSet;
import java.util.List;

public class EmpDao extends BaseDAO<Employee> {


    public List<Employee> findAll() throws Exception {
        String sql = "select * from t_emp";
        return find(sql, null);
    }

    public Employee findById(int id) throws Exception {
        String sql = "select * from t_emp where id=?";
        List<Employee> list = find(sql, new Object[]{id});
        if (list != null && list.size() > 0)
            return list.get(0);
        else return null;
    }

    @Override
    public Employee toEntity(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        double salary = rs.getDouble("salary");
        Employee employee = new Employee(id, name, age, salary);
        return employee;
    }
}
