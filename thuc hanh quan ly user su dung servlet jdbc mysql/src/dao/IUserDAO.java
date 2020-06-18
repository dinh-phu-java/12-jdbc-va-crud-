package dao;

import model.User;

import java.util.List;

public interface IUserDAO {
    public boolean insertUser(User user);
    public User selectUser(int id);
    public List<User> selectAllUser();
    public boolean deleteUser(int id);
    public boolean updateUser(int id,String name,String email,String country);
    public int getRowCount();
}
