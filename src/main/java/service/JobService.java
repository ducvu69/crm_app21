package service;

import model.JobModel;
import repository.JobRepository;

import java.util.Date;
import java.util.List;

public class JobService {
    private JobRepository jobRepository = new JobRepository();

    public List<JobModel> findAllJob(){
        return jobRepository.findAllJob();
    }

    public boolean addJob(String name, String start_date, String end_date){
        return jobRepository.addJob(name, start_date, end_date);
    }

    public boolean deleteJob(Integer id){
        return jobRepository.deleteJob(id);
    }
}
