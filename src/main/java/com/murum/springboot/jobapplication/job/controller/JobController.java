package com.murum.springboot.jobapplication.job.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  GET /api/jobs           -   find all jobs
 *  GET /api/jobs/{id}      -   find job by ID
 *  POST /api/jobs          -   post a job
 *  PUT /api/jobs/{id}      -   update a specific job
 *  DELETE /api/jobs/{id}   -   Delete a specific job
 */

@RestController
public class JobController {


    @GetMapping("/api/jobs")
    public String findAll(){
        return "find all jobs";
    }



}

