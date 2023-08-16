package repository;

import config.MysqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<UserModel> findByEmailAndPassword(String email, String password) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try{
            String sql = "select * from users u where u.email = ? and u.password = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        }catch (Exception e){
            System.out.println("Error findByEmailAndPassword: " + e.getMessage());
        }finally {
            if(connection != null)
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findByEmailAndPassword: " + e.getMessage());
                }
        }
        return userModelList;
    }
    public List<UserModel> findAll(){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "select * from users u";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định và lưu vào đối tượng
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }
        } catch (Exception e) {
            System.out.println("Error findAll:"+e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findAll"+e.getMessage());
                }
            }
        }
        return userModelList;
    }
    public boolean insertUser(String email, String password, String fullname, int role_id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "insert into users(email,password,fullname,role_id) values(?,?,?,?)";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4,role_id);

            // int count = statement.executeUpdate();
            // isSuccess = count > 0;
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi querry insertUser" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối insertUser" + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean deleteById(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try{
            connection = MysqlConfig.getConnection();
            String sql = "delete from users u where u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi querry deleteUser" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteUser" + e.getMessage());
                }
            }
        }
        return isSuccess;
        }
    public boolean updateUser(String email, String password, String fullname, int role_id, int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "update users set email = ?, password = ?, fullname = ?, role_id = ? where id = ?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4,role_id);
            statement.setInt(5,id);

            // int count = statement.executeUpdate();
            // isSuccess = count > 0;
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi querry updateUser" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối updateUser" + e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}
