package com.projektowanie.controller;

import com.projektowanie.model.Complaint;
import com.projektowanie.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @GetMapping("/manage")
    public String getManageComplaintScreen(Model model) {
        model.addAttribute("complaints", complaintService.getAllUnresolvedComplaints());
        return "complaint/manageComplaintsScreen";
    }

    @GetMapping("/manage/{id}")
    public String manageComplaint(@PathVariable Long id, Model model) {
        model.addAttribute("complaint", complaintService.getComplaint(id));
        return "complaint/resolveComplaintScreen";
    }

    @PostMapping("/confirm")
    public String confirmComplaint(@ModelAttribute Complaint complaint, Model model) {
        complaintService.confirmComplaint(complaint);
        return "redirect:/complaints/manage";
    }

    @PostMapping("/reject")
    public String rejectComplaint(@ModelAttribute Complaint complaint, Model model) {
        complaintService.rejectComplaint(complaint);
        return "redirect:/complaints/manage";
    }
}
