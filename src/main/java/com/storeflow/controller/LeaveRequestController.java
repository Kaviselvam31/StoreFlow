package com.storeflow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storeflow.entity.LeaveRequest;
import com.storeflow.service.LeaveRequestService;

@RestController
@RequestMapping("/leave-request")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(
            LeaveRequestService leaveRequestService) {

        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public LeaveRequest saveLeaveRequest(
            @RequestBody LeaveRequest leaveRequest) {

        return leaveRequestService.saveLeaveRequest(leaveRequest);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {

        return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/{id}")
    public Optional<LeaveRequest> getLeaveRequestById(
            @PathVariable int id) {

        return leaveRequestService.getLeaveRequestById(id);
    }

    @PutMapping("/{id}")
    public LeaveRequest updateLeaveRequest(
            @PathVariable int id,
            @RequestBody LeaveRequest leaveRequest) {

        return leaveRequestService.updateLeaveRequest(
                id,
                leaveRequest);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approveLeave(
            @PathVariable int id) {

        return leaveRequestService.approveLeave(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest rejectLeave(
            @PathVariable int id) {

        return leaveRequestService.rejectLeave(id);
    }

    @DeleteMapping("/{id}")
    public String deleteLeaveRequest(
            @PathVariable int id) {

        return leaveRequestService.deleteLeaveRequest(id);
    }
}