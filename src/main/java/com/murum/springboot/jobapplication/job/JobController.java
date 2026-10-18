package com.murum.springboot.jobapplication.job;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    List<Job> jobs = new ArrayList<>();

    @PostMapping("/jobs")
    public String create(@RequestBody Job job){
        jobs.add(job);
        return "job created successfully";
    }

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobs;
    }

    @GetMapping("jobs/{id}")
    public Job findById(@PathVariable Long id){
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

}

