package com.SupermarketPOS.Backend.service.sales_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.sales_management.RefundItem;
import com.SupermarketPOS.Backend.repository.sales_management.RefundItemRepository;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.stream.Collectors;

@Service
public class RefundItemService {
    private final RefundItemRepository refundItemRepository;
    public final EmployeeService employeeService;

    public RefundItemService(RefundItemRepository refundItemRepository, EmployeeService employeeService) {
        this.refundItemRepository = refundItemRepository;
        this.employeeService = employeeService;
    }

    public RefundItem GetRefundItemById(Integer id,Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return refundItemRepository.findById(id).stream()
                .filter(refundItem -> refundItem.getSalesItem().getSales().getBranch() == caller.getBranch())
                .collect(Collectors.toList()).get(0);
    }

}
