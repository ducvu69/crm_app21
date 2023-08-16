package repository;

import com.mysql.cj.MysqlConnection;
import config.MysqlConfig;
import model.JobModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRepository {
    public List<JobModel> findAllJob(){
        Connection connection = null;
        List<JobModel> jobModelList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM jobs j";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                JobModel jobModel = new JobModel();
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setStart_date(resultSet.getString("start_date"));
                jobModel.setEnd_date(resultSet.getString("end_date"));

                jobModelList.add(jobModel);
            }
        }catch (Exception e){
            System.out.println("Lỗi thực thi câu query findAllJob" + e.getMessage());
        }
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Lỗi đóng kết nối query findAllJob" + e.getMessage());
            }
        }
        return jobModelList;
    }

    public boolean addJob(String name, String start_date, String end_date){
        boolean isSuccess = false;
        Connection connection = null;
        try{
            String sql = "INSERT into jobs (name,start_date,end_date) values (?,?,?)";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, start_date);
            preparedStatement.setString(3, end_date);
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

    public boolean deleteJob(Integer id){
        boolean isSuccess = false;
        Connection connection = null;
        try{
            String sql = "DELETE FROM jobs j WHERE j.id = ?";
            PreparedStatement preparedStatement = MysqlConfig.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            isSuccess = preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi câu query deleteJob" + e.getMessage());
        }
        if(connection != null){
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Lỗi đóng kết nối query deleteJob" + e.getMessage());
            }
        }
        return isSuccess;
    }
}
