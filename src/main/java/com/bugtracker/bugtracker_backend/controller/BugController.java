package com.bugtracker.bugtracker_backend.controller;

import com.bugtracker.bugtracker_backend.entity.Bug;
import com.bugtracker.bugtracker_backend.entity.BugHistory;
import com.bugtracker.bugtracker_backend.service.BugService;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bug> getBug(@PathVariable Long id) {
        return bugService.getBugById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.createBug(bug);
    }

    @PutMapping("/{id}/status")
    public Bug updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return bugService.updateStatus(id, body.get("status"), body.get("changedBy"));
    }

    @GetMapping("/{id}/history")
    public List<BugHistory> getHistory(@PathVariable Long id) {
        return bugService.getBugHistory(id);
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return bugService.getDashboardStats();
    }

    @PostMapping("/suggest-priority")
    public Map<String, String> suggestPriority(@RequestBody Map<String, String> body) {
        return Map.of("priority", bugService.suggestPriority(body.get("description")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return ResponseEntity.ok().build();
    }
}
