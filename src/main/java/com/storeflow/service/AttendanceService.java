package com.storeflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Attendance;
import com.storeflow.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(int id) {
        return attendanceRepository.findById(id);
    }

    public Attendance updateAttendance(int id, Attendance attendance) {

        Attendance existingAttendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance Not Found"));

        existingAttendance.setEmployeeId(attendance.getEmployeeId());
        existingAttendance.setAttendanceDate(attendance.getAttendanceDate());
        existingAttendance.setStatus(attendance.getStatus());
        existingAttendance.setCheckIn(attendance.getCheckIn());
        existingAttendance.setCheckOut(attendance.getCheckOut());
        existingAttendance.setOvertimeHours(attendance.getOvertimeHours());
        existingAttendance.setRemarks(attendance.getRemarks());

        return attendanceRepository.save(existingAttendance);
    }

    public String deleteAttendance(int id) {

        if (attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
            return "Attendance Deleted Successfully";
        }

        return "Attendance Not Found";
    }
}