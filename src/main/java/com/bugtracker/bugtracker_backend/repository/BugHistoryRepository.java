package com.bugtracker.bugtracker_backend.repository;

import com.bugtracker.bugtracker_backend.entity.BugHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugHistoryRepository extends JpaRepository<BugHistory, Long> {

    List<BugHistory> findByBugIdOrderByChangedAtAsc(Long bugId);
}
