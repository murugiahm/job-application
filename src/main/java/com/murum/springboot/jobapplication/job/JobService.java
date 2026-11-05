package com.murum.springboot.jobapplication.job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    public Job create(Job job);
    public List<Job> getAll();
    public Optional<Job> getById(Long id);
    public Optional<Job> update(Long id, Job job);
    public boolean delete(Long id);
}
