package com.twp.servletemp.dao;

import com.twp.servletemp.entity.Employee;
import com.twp.servletemp.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public int deleteById(int id) throws Exception {
        String sql = "DELETE FROM t_emp WHERE id=?";
        return deleteById(sql, id);
    }

    public int addEmp(Employee employee) throws Exception {
        String sql = "INSERT INTO t_emp VALUE(NULL,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        conn = DBUtil.getDbConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employee.getName());
        pstmt.setInt(2, employee.getAge());
        pstmt.setDouble(3, employee.getSalary());
        int result = pstmt.executeUpdate();
        DBUtil.close(pstmt);
        DBUtil.close(conn);
        return result;
    }

    public int updateEmpById(Employee employee) throws Exception {
        String sql = "UPDATE t_emp SET name=?,age=?,salary=? WHERE id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        conn = DBUtil.getDbConnection();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, employee.getName());
        pstmt.setInt(2, employee.getAge());
        pstmt.setDouble(3, employee.getSalary());
        pstmt.setInt(4, employee.getId());
        int result = pstmt.executeUpdate();
        DBUtil.close(pstmt);
        DBUtil.close(conn);
        return result;
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
