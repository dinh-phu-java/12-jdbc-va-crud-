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
        String action=request.getParameter("action");
        String url="/views/view.jsp";
        String message="";
        if (action==null){
            action="view";
        }
        switch(action){
            case "create":
               if(createUser(request,response)) {
                   url="/views/thanks.jsp";
                   message="Create Completed";
               }else{
                    url="/views/error.jsp";
                    message ="Can't create User";
               }
                break;
            case "edit":
                if (updateUser(request,response)){
                    url="/views/thanks.jsp";
                    message="Update Completed";
                }else{
                    url="/views/error.jsp";
                    message="Can't Update";
                }
                break;
            default:
                url="/views/view.jsp";
                break;
        }
        request.setAttribute("message",message);
        request.setAttribute("userList",userDao.selectAllUser());
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> userList=new ArrayList<>();
        String action=request.getParameter("action");
        String url="/views/view.jsp";
        if (action==null){
            action="view";
        }
        String message="";
        switch(action){
            case "create":
                url="/views/create.jsp";
                break;
            case "edit":
                int editId=Integer.parseInt(request.getParameter("id")) ;
                User editUser=userDao.selectUser(editId);
                request.setAttribute("editUser",editUser);
                url="/views/edit.jsp";
                break;
            case "delete":
                if (deleteUser(request,response)){
                    url="/views/thanks.jsp";
                    message="Delete Completed";
                }else{
                    url="/views/error.jsp";
                    message="Can't Delete";
                }
                break;
            default:
            userList.addAll(userDao.selectAllUser());
            url="/views/view.jsp";
            break;
        }

        request.setAttribute("userList",userList);
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    private boolean deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int deleteId=Integer.parseInt(request.getParameter("id"));

        boolean checkDelete=userDao.deleteUser(deleteId);
        return  checkDelete;
    }


    private boolean updateUser(HttpServletRequest request, HttpServletResponse response) {
        int userId=Integer.parseInt(request.getParameter("userId"));
        String userName=(String)request.getParameter("userName");
        String userEmail=(String)request.getParameter("userEmail");
        String userCountry=(String)request.getParameter("userCountry");

        boolean checkUpdate= userDao.updateUser(userId,userName,userEmail,userCountry);
        return checkUpdate;
    }

    private boolean createUser(HttpServletRequest request, HttpServletResponse response) {
        boolean inserted=false;

        int rowCount=userDao.getRowCount();
        System.out.println(rowCount);
        int newUserId= rowCount+1;

        String userName=(String)request.getParameter("userName");
        String userEmail=(String)request.getParameter("userEmail");
        String userCountry=(String)request.getParameter("userCountry");
        System.out.println(userName + " " +userEmail+" "+ userCountry);
        User newUser= new User(newUserId,userName,userEmail,userCountry);
        System.out.println(newUser.getId());

        inserted=userDao.insertUser(newUser);
        System.out.println(inserted);
        return inserted;

    }
}
