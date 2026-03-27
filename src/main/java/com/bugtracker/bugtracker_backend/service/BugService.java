package com.bugtracker.bugtracker_backend.service;

import com.bugtracker.bugtracker_backend.entity.Bug;
import com.bugtracker.bugtracker_backend.entity.BugHistory;
import com.bugtracker.bugtracker_backend.repository.BugHistoryRepository;
import com.bugtracker.bugtracker_backend.repository.BugRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BugService {

    private final BugRepository bugRepository;
    private final BugHistoryRepository historyRepository;
    private final PriorityEngine priorityEngine;

    public BugService(
            BugRepository bugRepository,
            BugHistoryRepository historyRepository,
            PriorityEngine priorityEngine
    ) {
        this.bugRepository = bugRepository;
        this.historyRepository = historyRepository;
        this.priorityEngine = priorityEngine;
    }

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    public Optional<Bug> getBugById(Long id) {
        return bugRepository.findById(id);
    }

    public Bug createBug(Bug bug) {
        if (bug.getPriority() == null || bug.getPriority().isBlank()) {
            bug.setPriority(priorityEngine.suggestPriority(bug.getDescription()));
        }
        if (bug.getStatus() == null || bug.getStatus().isBlank()) {
            bug.setStatus("Open");
        }
        return bugRepository.save(bug);
    }

    public Bug updateStatus(Long id, String newStatus, String changedBy) {
        Bug bug = bugRepository.findById(id).orElseThrow();
        String oldStatus = bug.getStatus();

        if (oldStatus != null && oldStatus.equals(newStatus)) {
            return bug;
        }

        BugHistory history = new BugHistory();
        history.setBugId(id);
        history.setChangedBy(changedBy);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        historyRepository.save(history);

        bug.setStatus(newStatus);
        return bugRepository.save(bug);
    }

    public List<BugHistory> getBugHistory(Long bugId) {
        return historyRepository.findByBugIdOrderByChangedAtAsc(bugId);
    }

    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("total", bugRepository.count());
        stats.put("open", bugRepository.countByStatus("Open"));
        stats.put("inProgress", bugRepository.countByStatus("In Progress"));
        stats.put("resolved", bugRepository.countByStatus("Resolved"));
        stats.put("critical", bugRepository.countByPriority("Critical"));
        return stats;
    }

    public String suggestPriority(String description) {
        return priorityEngine.suggestPriority(description);
    }

    public void deleteBug(Long id) {
        bugRepository.deleteById(id);
    }
}
