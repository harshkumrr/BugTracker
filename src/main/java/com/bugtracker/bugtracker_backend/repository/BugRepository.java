package com.bugtracker.bugtracker_backend.repository;

import com.bugtracker.bugtracker_backend.entity.Bug;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {

    List<Bug> findByAssignedTo(String assignedTo);

    List<Bug> findByStatus(String status);

    List<Bug> findByPriority(String priority);

    long countByStatus(String status);

    long countByPriority(String priority);
}
