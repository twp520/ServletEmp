package com.twp.servletemp.servlet;

import com.twp.servletemp.dao.EmpDao;
import com.twp.servletemp.dao.UserDao;
import com.twp.servletemp.entity.Employee;
import com.twp.servletemp.entity.User;

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
            if (uri.equals("/list")) {//查询
                List<Employee> all = dao.findAll();
                //将数据交给jsp进行展示
                req.setAttribute("emps", all);
                req.getRequestDispatcher("listEmp.jsp").forward(req, resp);
            } else if (uri.equals("/add")) {//增加
                String name = req.getParameter("name");
                int age = Integer.parseInt(req.getParameter("age"));
                double salary = Double.parseDouble(req.getParameter("salary"));
                Employee employee = new Employee(name, age, salary);
                int result = dao.addEmp(employee);
                if (result == 1) {
                    resp.sendRedirect("list.do");
                } else writer.println("添加失败");
            } else if (uri.equals("/delete")) {//删除
                String id = req.getParameter("id");
                int result = dao.deleteById(Integer.parseInt(id));
                if (result == 1) {
                    resp.sendRedirect("list.do");
                } else writer.println("删除失败");
            } else if (uri.equals("/load")) {//根据id查询
                String id = req.getParameter("id");
                Employee employee = dao.findById(Integer.parseInt(id));
                req.setAttribute("emp", employee);
                req.getRequestDispatcher("updateEmp.jsp").forward(req, resp);
            } else if (uri.equals("/update")) {//更新
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                int age = Integer.parseInt(req.getParameter("age"));
                double salary = Double.parseDouble(req.getParameter("salary"));
                Employee employee = new Employee(id, name, age, salary);
                int result = dao.updateEmpById(employee);
                if (result == 1) {
                    resp.sendRedirect("list.do");
                } else writer.println("更新失败");
            } else if (uri.equals("/reg")) {//注册
                String name = req.getParameter("username");
                UserDao userDao = new UserDao();
                User user = userDao.findByName(name);
                if (user != null && user.getUserName().equals(name)) {
                    //已经存在
                    req.setAttribute("msg", "该用户已经存在！");
                    req.getRequestDispatcher("regist.jsp").forward(req, resp);
                } else {
                    //执行注册
                    writer.println("注册成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("系统繁忙");
        } finally {
            writer.close();
        }
    }
}
