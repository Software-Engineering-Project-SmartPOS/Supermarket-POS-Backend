package com.SupermarketPOS.Backend.controller.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.EmployeeAttendenceDetails;
import com.SupermarketPOS.Backend.dto.employee_management.WorkingHoursRequest;
import com.SupermarketPOS.Backend.service.employee_management.AttendanceService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @MutationMapping
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    public Boolean MarKEmployeeAttendance(@Argument EmployeeAttendenceDetails employeeAttendanceDetails, Principal principal){
        System.out.println("inside the markEmployeeAttendance");



        return attendanceService.MarkAttendance(employeeAttendanceDetails,principal);
    }

    @QueryMapping
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_STORE_MANAGER"})
    public double GetWorkingHours(@Argument WorkingHoursRequest workingHoursRequest){
        return attendanceService.GetTotalWorkingHoursForEmployeeInDateRange(
                workingHoursRequest.employeeId(),
                workingHoursRequest.from(),
                workingHoursRequest.to()
        );
    }
}
