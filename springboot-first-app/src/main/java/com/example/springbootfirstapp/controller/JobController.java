package com.example.springbootfirstapp.controller;

import com.example.springbootfirstapp.model.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/jobs/")
public class JobController {

    private final List<Job> jobs = new ArrayList<>();

    @GetMapping("")
    public List<Job> getAllJobs() {
        return jobs;
    }

    @GetMapping("{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        Optional<Job> foundJob = jobs.stream().filter(job -> job.getId().equals(id)).findFirst();
        return foundJob.map(job -> ResponseEntity.ok().body(job)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        job.setId(UUID.randomUUID().toString()); //Tạo id không trùng lặp rồi đưa về kiểu string
        jobs.add(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(job.getId());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateJob(@PathVariable String id, @RequestBody Job updatedJob) {
        Optional<Job> foundJob = jobs.stream().filter(job -> job.getId().equals(id)).findFirst();
        if (foundJob.isPresent()) {
            Job existingJob = foundJob.get();
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setLocation(updatedJob.getLocation());
            existingJob.setMinSalary(updatedJob.getMinSalary());
            existingJob.setMaxSalary(updatedJob.getMaxSalary());
            existingJob.setEmailTo(updatedJob.getEmailTo());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        Optional<Job> foundJob = jobs.stream().filter(job -> job.getId().equals(id)).findFirst();
        if (foundJob.isPresent()) {
            jobs.remove(foundJob.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("random")
    public ResponseEntity<Job> getRandomJob() {
        if (jobs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        int randomIndex = (int) (Math.random() * jobs.size());
        return ResponseEntity.ok(jobs.get(randomIndex));
    }

    @GetMapping("sort")
    public ResponseEntity<List<Job>> sortJobsByMaxSalary(@RequestParam("max_salary") String sortOrder) {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sortedJobs.sort((job1, job2) -> job2.getMaxSalary() - job1.getMaxSalary());
        } else {
            sortedJobs.sort((job1, job2) -> job1.getMaxSalary() - job2.getMaxSalary());
        }
        return ResponseEntity.ok(sortedJobs);
    }
}
