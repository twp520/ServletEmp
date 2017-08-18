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

public class ActionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        //获得请求的URI
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        EmpDao dao = new EmpDao();
        try {
            if (uri.equals("/list")) {
                List<Employee> all = dao.findAll();
                writer.println("<table border='1' width='500px'>");
                all.stream().forEach(employee -> {
                    writer.println("<tr>");
                    writer.println("<td>" + employee.getId() + "</td>");
                    writer.println("<td>" + employee.getName() + "</td>");
                    writer.println("<td>" + employee.getAge() + "</td>");
                    writer.println("<td>" + employee.getSalary() + "</td>");
                    writer.println("<td><a href='delete.do?id=" + employee.getId() + "'>删除</a></td>");
                    writer.println("<td><a href='load.do'>修改</a></td>");
                    writer.println("</tr>");
                });
                writer.println("</table>");
            } else if (uri.equals("/add")) {

            } else if (uri.equals("/delete")) {
                String id = req.getParameter("id");
                int result = dao.deleteById(Integer.parseInt(id));
                if (result == 1) {
                    resp.sendRedirect("list.do");
                } else writer.println("删除失败");
            } else if (uri.equals("/load")) {

            } else if (uri.equals("/update")) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统繁忙");
        } finally {
            writer.close();
        }
    }
}
