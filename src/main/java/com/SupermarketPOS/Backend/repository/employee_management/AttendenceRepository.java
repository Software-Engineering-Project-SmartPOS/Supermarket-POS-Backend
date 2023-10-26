package com.SupermarketPOS.Backend.repository.employee_management;

import com.SupermarketPOS.Backend.model.employee_management.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendance, Integer> {

//    @Query(value = "SELECT SUM(EXTRACT(EPOCH FROM (LEAST(to_timestamp(:toDate, 'YYYY-MM-DD HH24:MI:SS'), a.time_out) - GREATEST(to_timestamp(:fromDate, 'YYYY-MM-DD HH24:MI:SS'), a.time_in))) / 3600) AS totalWorkingHours " +
//            "FROM Attendance a " +
//            "WHERE a.employee.id = ?1 " +
//            "AND a.date >= ?2 " +
//            "AND a.date <= ?3")
//    Optional<Double> getTotalWorkingHoursForEmployeeInDateRange( Integer employeeId, String fromDate,  String toDate);


    List<Attendance> findAllByEmployeeId(Integer employeeId);


//    @Query("SELECT a FROM Attendance a WHERE a.employeeId = ?1 and a. ")
//    List<Attendance> findAllByEmployeeIdCondition(Integer employeeId);
}
