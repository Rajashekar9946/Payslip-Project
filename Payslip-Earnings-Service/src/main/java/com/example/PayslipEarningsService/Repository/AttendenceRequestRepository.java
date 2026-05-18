package com.example.PayslipEarningsService.Repository;

import com.example.PayslipEarningsService.Model.AttendanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendenceRequestRepository extends JpaRepository<AttendanceRequest, Long> {
}
