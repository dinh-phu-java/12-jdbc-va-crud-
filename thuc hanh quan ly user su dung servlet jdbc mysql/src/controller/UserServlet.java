package controller;

import dao.IUserDAO;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet",urlPatterns={"/home"})
public class UserServlet extends HttpServlet {
//    private static final long serialVersionUID=1L;
    private IUserDAO userDao;
    public void init(){
        userDao=new UserDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> userList=new ArrayList<>();
        String action=request.getParameter("action");
        String url="/views/view.jsp";
        if (action==null){
            action="view";
        }

        switch(action){
            default:
            userList.addAll(userDao.selectAllUser());
            url="/views/view.jsp";
            break;
        }

        request.setAttribute("userList",userList);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
}
