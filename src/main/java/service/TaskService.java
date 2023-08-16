package service;

import model.TaskModel;
import repository.TaskRepository;

import java.sql.SQLException;
import java.util.List;

public class TaskService {
    private TaskRepository taskRepository = new TaskRepository();

    public List<TaskModel> getAllTask(){
        return taskRepository.findAllTask();
    }

    public boolean addTask(String name, String start_date, String end_date, Integer user_id, Integer job_id, Integer status_id){
        return taskRepository.addTask(name, start_date, end_date, user_id, job_id, status_id);
    }

    public boolean deleteTask(Integer id){
        return taskRepository.deleteTask(id);
    }
}
