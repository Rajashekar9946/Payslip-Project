package com.example.PayslipEarningsService.Model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attendence")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-03T22:01:27.0154478300+05:30[Asia/Calcutta]")
public class AttendanceRequest {
    @Id
    private Long employeeCode;
    private Integer totalHoursWorked;
    private Integer leavesTaken;
    private Integer absences;
    private String payPeriod;
}
