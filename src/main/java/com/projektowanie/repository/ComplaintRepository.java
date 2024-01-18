package com.projektowanie.repository;

import com.projektowanie.model.Complaint;
import com.projektowanie.model.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findAllByComplaintStatus(ComplaintStatus status);
}
