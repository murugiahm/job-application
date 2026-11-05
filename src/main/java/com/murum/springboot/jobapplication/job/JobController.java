package com.murum.springboot.jobapplication.job;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 *  GET /jobs               :   Get all jobs
 *  GET /jobs/{id}          :   Get a specific job by ID
 *  POST /jobs              :   Create a new job (request body should contain the job details)
 *  PUT /jobs/{id}          :   update a specific job by ID (request body should contain the updated job details)
 *  DELETE /jobs/{id}       :   Delete a specific job by ID
 *
 *  Example API URLs.
 *      GET {base_url}/jobs
 *      GET {base_url}/jobs/1
 *      POST {base_url}/jobs
 *      PUT {base_url}/jobs/1
 *      DELETE {base_url}/jobs/1
 *
 */


@RestController
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.create(job);
        return "job created successfully";
    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs(){
        return jobService.getAll();
    }

    @GetMapping("jobs/{id}")
    public Job getJobById(@PathVariable Long id){
        Optional<Job> job = jobService.getById(id);
        return job.orElse(null);
    }

    @PutMapping("/jobs/{id}")
    public String updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        Optional<Job> job = jobService.update(id, updatedJob);

        if(job.isEmpty()){
            return "NO job found";
        }
        return "job updated successfully";
    }

    @DeleteMapping("/jobs/{id}")
    public String deleteJob(@PathVariable Long id){
        boolean isRemoved = jobService.delete(id);
            if(isRemoved){
                return "job deleted successfully";
            }
        return "NO job found";
    }


}

