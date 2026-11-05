package com.murum.springboot.jobapplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Job> createJob(@RequestBody Job job){
        Job createdJo = jobService.create(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJo);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = jobService.getAll();
        if(jobs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<Job>  getJobById(@PathVariable Long id){
       return jobService.getById(id)
                .map(job -> ResponseEntity.ok(job))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        return jobService.update(id, updatedJob)
                .map(job -> ResponseEntity.ok(job))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean isRemoved = jobService.delete(id);
            if(isRemoved){
                return ResponseEntity.ok("job deleted successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO job found");
    }


}

