package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.LeaveRequest;
import com.storeflow.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {

        if (leaveRequest.getStatus() == null || leaveRequest.getStatus().isBlank()) {
            leaveRequest.setStatus("PENDING");
        }

        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public Optional<LeaveRequest> getLeaveRequestById(int id) {
        return leaveRequestRepository.findById(id);
    }

    public LeaveRequest updateLeaveRequest(
            int id,
            LeaveRequest leaveRequest) {

        LeaveRequest existingLeaveRequest =
                leaveRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave Request Not Found"));

        existingLeaveRequest.setEmployeeId(leaveRequest.getEmployeeId());
        existingLeaveRequest.setLeaveType(leaveRequest.getLeaveType());
        existingLeaveRequest.setStartDate(leaveRequest.getStartDate());
        existingLeaveRequest.setEndDate(leaveRequest.getEndDate());
        existingLeaveRequest.setReason(leaveRequest.getReason());
        existingLeaveRequest.setStatus(leaveRequest.getStatus());

        return leaveRequestRepository.save(existingLeaveRequest);
    }

    public LeaveRequest approveLeave(int id) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave Request Not Found"));

        leaveRequest.setStatus("APPROVED");

        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest rejectLeave(int id) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave Request Not Found"));

        leaveRequest.setStatus("REJECTED");

        return leaveRequestRepository.save(leaveRequest);
    }

    public String deleteLeaveRequest(int id) {

        if (leaveRequestRepository.existsById(id)) {
            leaveRequestRepository.deleteById(id);
            return "Leave Request Deleted Successfully";
        }

        return "Leave Request Not Found";
    }
}