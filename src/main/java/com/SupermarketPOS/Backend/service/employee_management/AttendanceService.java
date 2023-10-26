package com.SupermarketPOS.Backend.service.employee_management;

import com.SupermarketPOS.Backend.dto.employee_management.EmployeeAttendenceDetails;
import com.SupermarketPOS.Backend.model.employee_management.Attendance;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.repository.employee_management.AttendenceRepository;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import jakarta.transaction.Transactional;
import org.hibernate.id.IdentifierGeneratorAggregator;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class AttendanceService {
    private final AttendenceRepository attendenceRepository;
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final DateTimeService dateTimeService;

    public AttendanceService(AttendenceRepository attendenceRepository, EmployeeService employeeService, EmployeeRepository employeeRepository, DateTimeService dateTimeService) {
        this.attendenceRepository = attendenceRepository;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.dateTimeService = dateTimeService;
    }

    @Transactional
    public Boolean MarkAttendance(EmployeeAttendenceDetails employeeAttendenceDetails , Principal principal) {
        System.out.println("inside the service");

        Employee caller = employeeService.getByEmail(principal.getName());
        Employee employee = employeeRepository.findByIdAndBranchId(employeeAttendenceDetails.employeeId(), caller.getBranch().getId());
        LocalDateTime timeIn = dateTimeService.convertStringIntoLocalDateTime(employeeAttendenceDetails.timeIn());
        LocalDateTime timeOut = dateTimeService.convertStringIntoLocalDateTime(employeeAttendenceDetails.timeOut());
        LocalDate timeInDate = dateTimeService.convertStringIntoLocalDate(employeeAttendenceDetails.timeInDate());
        LocalDate timeOutDate = dateTimeService.convertStringIntoLocalDate(employeeAttendenceDetails.timeOutDate());

        Integer dayCount = (int) ChronoUnit.DAYS.between(timeInDate,timeOutDate) + 1;

        System.out.println(timeIn);
        System.out.println(timeOut);
        System.out.println(dayCount);

        for (int i = 0; i < dayCount; i++) {
            attendenceRepository.save(
                    new Attendance(
                            timeIn.plusDays(i),
                            timeOut.plusDays(i),
                            timeInDate.plusDays(i), // to increment to the next day
                            employee
                    ));
        }
        return  true;
    }

}
