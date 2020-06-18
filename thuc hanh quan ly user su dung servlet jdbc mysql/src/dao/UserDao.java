package dao;

import model.User;
import sun.applet.AppletResourceLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDAO{
        private String jdbcUrl= "jdbc:mysql://localhost:3306/demo";
        private String jdbcUserName="root";
        private String jdbcPassword="qazWSX1@";
        private static final String INSERT_USER_SQL="insert into users"+"(name,email,country) values"+"(?,?,?);";
        private static final String SELECT_USER_BY_ID="select * from users where id=?";
        private static final String SELECT_ALL_USERS="select * from users";
        private static final String DELETE_USER_SQL= "delete from users where id=?";
        private static final String UPDATE_USER_SQL="update users set name=?,email=?,country=? where id=?";

        public UserDao(){

        }

        protected Connection getConnection(){

            Connection connection=null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection= DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return connection;
        }

    @Override
    public void insertUser(User user) {
        System.out.println(INSERT_USER_SQL);
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User selectUser(int id) {
        User user=null;
        try{
            Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                String email=rs.getString("email");
                String country=rs.getString("country");
                user=new User(id,name,email,country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUser() {
        ArrayList<User> userList= new ArrayList<>();
            try{
                Connection connection= getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USERS);
                ResultSet rs=preparedStatement.executeQuery();

                while(rs.next()){
                    int id=rs.getInt("id");
                    String name=rs.getString("name");
                    String email=rs.getString("email");
                    String country=rs.getString("country");
                    userList.add(new User(id,name,email,country));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return userList;
    }

    @Override
    public boolean deleteUser(int id) {
            boolean rowDelete=false;
            try{
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER_SQL);
                preparedStatement.setInt(1,id);
                rowDelete=preparedStatement.executeUpdate() > 0;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return rowDelete;
    }

    @Override
    public boolean updateUser( User user) {
            boolean rowUpdate=false;
            try{
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER_SQL);
                preparedStatement.setString(1,user.getName());
                preparedStatement.setString(2,user.getEmail());
                preparedStatement.setString(3,user.getCountry());
                preparedStatement.setInt(4,user.getId());
                rowUpdate= preparedStatement.executeUpdate()>0;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return rowUpdate;
    }
}
