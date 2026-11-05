package com.murum.springboot.jobapplication.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    List<Job> jobs = new ArrayList<>();
    Long jobId = 0L;

    @Override
    public Job create(Job job) {
        job.setId(++jobId);
        jobs.add(job);
        return job;
    }

    @Override
    public List<Job> getAll() {
        return jobs;
    }

    @Override
    public Optional<Job> getById(Long id) {
        for (Job job : jobs){
            if(job.getId().equals(id)){
                return Optional.of(job);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Job> update(Long id, Job job) {
        for(Job existingJob: jobs){
            if(existingJob.getId().equals(id)){
                existingJob.setTitle(job.getTitle());
                existingJob.setDescription(job.getDescription());
                existingJob.setLocation(job.getLocation());
                existingJob.setMinSalary(job.getMinSalary());
                existingJob.setMaxSalary(job.getMaxSalary());
                return Optional.of(existingJob);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return jobs.removeIf(job -> job.getId().equals(id));
    }
}
