package com.twp.servletemp.servlet;

import com.twp.servletemp.dao.EmpDao;
import com.twp.servletemp.entity.Employee;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmpServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        EmpDao empDao = new EmpDao();
        try {
            List<Employee> all = empDao.findAll();
            all.stream().forEach(employee -> {
                writer.println(employee.toString());
                writer.println("<br/>");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
