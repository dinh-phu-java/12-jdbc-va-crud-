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
        private static final String INSERT_USER_SQL="insert into users(id,name,email,country) values(?,?,?,?)";
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
    public boolean insertUser(User user) {

        boolean checkInsert=false;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USER_SQL);

            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getCountry());
            System.out.println(preparedStatement);
            checkInsert= preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return checkInsert;
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
    public boolean updateUser( int id,String name,String email,String country) {
            boolean rowUpdate=false;
            try{
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_USER_SQL);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,country);
                preparedStatement.setInt(4,id);
                rowUpdate= preparedStatement.executeUpdate()>0;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return rowUpdate;
    }
    @Override
    public int getRowCount(){
            int rowCount=-1;
        try {
            Connection connection=getConnection();
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("select count(*) as countId from users");
            while(rs.next()){
              rowCount=  rs.getInt("countId");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowCount;
    }
}
