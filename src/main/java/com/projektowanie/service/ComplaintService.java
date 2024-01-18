package com.projektowanie.service;

import com.projektowanie.exception.ComplaintNotFoundException;
import com.projektowanie.model.Complaint;
import com.projektowanie.model.ComplaintStatus;
import com.projektowanie.repository.ComplaintRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {
    private final ComplaintRepository complaintRepository;

    public List<Complaint> getAllUnresolvedComplaints() {
        return complaintRepository.findAllByComplaintStatus(ComplaintStatus.SUBMITTED);
    }

    public Complaint getComplaint(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new ComplaintNotFoundException(
                        String.format("Complaint with id=%s was not found!", id)
                ));
    }

    @Transactional
    public void confirmComplaint(Complaint complaint) {
        complaint.setComplaintStatus(ComplaintStatus.RESOLVED);
    }

    @Transactional
    public void rejectComplaint(Complaint complaint) {
        complaint.setComplaintStatus(ComplaintStatus.REJECTED);
    }
}
