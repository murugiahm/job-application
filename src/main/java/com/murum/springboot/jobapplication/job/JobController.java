package com.murum.springboot.jobapplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    Long jobId = 0L;

    @PostMapping("/jobs")
    public ResponseEntity<Job> create(@RequestBody Job job){
        job.setId(++jobId);
        jobs.add(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(job);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        if(jobs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("jobs/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id){
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return ResponseEntity.ok(job);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> update(@PathVariable Long id, @RequestBody Job updatedJob){
        for(Job job: jobs){
            if(job.getId().equals(id)){
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                return ResponseEntity.ok(job);
                //job updated successfully";
            }
        }
       // return "NO job found";
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        boolean isRemoved = jobs.removeIf(job -> job.getId().equals(id));
            if(isRemoved){
                return ResponseEntity.ok("job deleted successfully");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO job found");
    }


}

