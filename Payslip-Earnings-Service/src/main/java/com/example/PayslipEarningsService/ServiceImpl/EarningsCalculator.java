package com.example.PayslipEarningsService.ServiceImpl;

import com.example.PayslipEarningsService.DTO.EmployeeResponse;
import com.example.PayslipEarningsService.Model.AttendanceRequest;
import com.example.PayslipEarningsService.Model.EarningsResponse;
import com.example.PayslipEarningsService.Util.MathUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;

@Component
public class EarningsCalculator {

    public EarningsResponse calculateEarnings(AttendanceRequest request, EmployeeResponse employeeResponse) {
        Long employeeCode = Optional.ofNullable(request.getEmployeeCode())
                .orElseThrow(() -> new IllegalArgumentException("Employee code can't be null"));
        Double salary = employeeResponse.getSalary();

        double monthlyBaseSalary = MathUtil.setToTwoDecimalPlaces(salary * 0.50 / 12);
        double montlyHouseRentAllowance = MathUtil.setToTwoDecimalPlaces(salary * 0.25 / 12);
        double monthlyReconciledFlexPay = MathUtil.setToTwoDecimalPlaces(salary * 0.10 / 12);
        double monthlyAdvanceVariablePay = MathUtil.setToTwoDecimalPlaces(salary * 0.15 / 12);

        double deduction = Optional.ofNullable(request.getTotalHoursWorked())
                .filter(hoursWorked -> hoursWorked < 180)
                .map(hoursWorked -> MathUtil.setToTwoDecimalPlaces(((180 - hoursWorked) / 180.0) * monthlyBaseSalary))
                .orElse(0.0);

        String[] payPeriodParts = request.getPayPeriod().split("-");
        String monthName = payPeriodParts[0].toUpperCase(Locale.ENGLISH);
        int year = Integer.parseInt(payPeriodParts[1]);
        Month month = Optional.ofNullable(Month.valueOf(monthName))
                .orElseThrow(() -> new IllegalArgumentException("Invalid month name: " + monthName));
        LocalDate payPeriodDate = LocalDate.of(year, month, 1);

        LocalDate leaveYearStart = LocalDate.of(payPeriodDate.getYear(), Month.MARCH, 1);
        if (payPeriodDate.isBefore(leaveYearStart)) {
            leaveYearStart = leaveYearStart.minusYears(1);
        }
        LocalDate leaveYearEnd = leaveYearStart.plusYears(1).minusDays(1);

        long monthsInLeaveyear = ChronoUnit.MONTHS.between(leaveYearStart, payPeriodDate) + 1;
        double allowedLeaves = monthsInLeaveyear * 1.75;

        double leavesTakenThisYear=Optional.ofNullable(request.getLeavesTaken()).orElse((int) 0.0);
        double excessleaveDeduction=Optional.of(leavesTakenThisYear)
                .filter(leaves->leaves>allowedLeaves)
                .map(leaves->MathUtil.setToTwoDecimalPlaces((leaves-allowedLeaves)*(monthlyBaseSalary/30)))
                .orElse(0.0);

        double absenceDeduction=Optional.ofNullable(request.getAbsences())
                .map(absences->MathUtil.setToTwoDecimalPlaces(absences*(monthlyBaseSalary/30)))
                .orElse(0.0);

        double totalDeductions = MathUtil.setToTwoDecimalPlaces(deduction+excessleaveDeduction+absenceDeduction);

        double totalEarnings=MathUtil.setToTwoDecimalPlaces(monthlyBaseSalary+montlyHouseRentAllowance+monthlyReconciledFlexPay+monthlyAdvanceVariablePay-totalDeductions);
        return new EarningsResponse(
                employeeCode,request.getPayPeriod(),monthlyBaseSalary,montlyHouseRentAllowance,monthlyReconciledFlexPay,monthlyAdvanceVariablePay,totalEarnings
        );
    }
}
