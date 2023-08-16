package repository;

import config.MysqlConfig;
import model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TaskModel> findAllTask(){
        List<TaskModel> taskModelList = new ArrayList<>();
        Connection connection = null;
        try{
        String sql = "SELECT * FROM tasks t\n" +
                "JOIN users u on t.user_id = u.id\n" +
                "JOIN jobs j on t.job_id = j.id\n" +
                "JOIN status s on t.status_id = s.id";
        PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            TaskModel taskModel = new TaskModel();
            taskModel.setId(resultSet.getInt("id"));
            taskModel.setTask(resultSet.getString("name"));
            taskModel.setStart_date(resultSet.getString("start_date"));
            taskModel.setEnd_date(resultSet.getString("end_date"));
            taskModel.setName(resultSet.getString("u.fullname"));
            taskModel.setJob(resultSet.getString("j.name"));
            taskModel.setStatus(resultSet.getString("s.name"));

            taskModelList.add(taskModel);
        }
        }catch (Exception e){
            System.out.print("Lỗi thực hiện câu query findAllTask " + e.getMessage());
        }
        if(connection != null){
            try{
                connection.close();
            }catch (Exception e){
                System.out.print("Lỗi thực hiện đóng kết nối findAllTask " + e.getMessage());
            }
        }
        return taskModelList;
    }

    public boolean addTask(String name, String start_date, String end_date, Integer user_id, Integer job_id, Integer status_id){
        boolean isSuccess = false;
        Connection connection = null;
        try{
            String sql = "INSERT into tasks (name,start_date ,end_date,user_id,job_id,status_id)\n" +
                    "values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, start_date);
            preparedStatement.setString(3, end_date);
            preparedStatement.setInt(4, user_id);
            preparedStatement.setInt(5, job_id);
            preparedStatement.setInt(6, status_id);
            isSuccess = preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi câu query addJob" + e.getMessage());
        }
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Lỗi đóng kết nối query addJob" + e.getMessage());
            }
        }
        return isSuccess;
    }

    public boolean deleteTask(Integer id){
        boolean isSuccess = false;
        Connection connection = null;
        try{
            String sql = "DELETE FROM tasks t WHERE t.id = ?";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            isSuccess = preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi câu query deleteTask " + e.getMessage());
        }
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Lỗi đóng kết nối query deleteTask " + e.getMessage());
            }
        }
        return isSuccess;
    }
}
