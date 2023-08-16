package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.UserModel;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> findAll(){
        Connection connection = null;
        List<RoleModel> listRoleModel = new ArrayList<>();
        try{
            String sql = "SELECT * FROM roles r";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();
                // Lấy giá trị cột chỉ định trong database lưu vào đối tượng
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDesc(resultSet.getString("description"));
                listRoleModel.add(roleModel);
            }
        }catch (Exception e){
            System.out.println("Lỗi thực thi câu querry listRole"+e.getMessage());
        }
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Lỗi đóng kết nối listRole"+e.getMessage());
            }
        }
        return listRoleModel;
    }

    public boolean insertRole(String name, String description){
        Connection connection = null;
        boolean isSuccess = false;
        try{
            String sql = "INSERT into roles (name,description) values (?,?)";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            isSuccess = preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi câu query insertRole"+e.getMessage());
        }
        if(connection!=null){
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Lỗi đóng kết nối insertRole"+e.getMessage());
            }
        }
        return isSuccess;
    }

    public boolean deleteById(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try{
            connection = MysqlConfig.getConnection();
            String sql = "delete from roles r where r.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            isSuccess = statement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi querry deleteRole" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteRole" + e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}
