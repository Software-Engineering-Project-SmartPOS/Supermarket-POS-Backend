package com.SupermarketPOS.Backend.service.sales_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import com.SupermarketPOS.Backend.model.sales_management.SalesItem;
import com.SupermarketPOS.Backend.repository.sales_management.SalesItemRepository;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesItemService {
    private final SalesItemRepository salesItemRepository;
    private final EmployeeService employeeService;

    public SalesItemService(SalesItemRepository salesItemRepository, EmployeeService employeeService) {
        this.salesItemRepository = salesItemRepository;
        this.employeeService = employeeService;
    }


    public List<SalesItem> GetAllTheSalesItem(Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        // return the sales related to the callers branch
        return salesItemRepository.findAll().stream()
                .filter(salesItem -> salesItem.getSales().getBranch() == caller.getBranch())
                .collect(Collectors.toList());
    }

    public List<SalesItem> GetAllTheSalesItem() {
        // return the sales related to the callers branch
        // this is to be used by the role owner
        return salesItemRepository.findAll();
    }

    public SalesItem GetSalesItemById(Integer id){
        return salesItemRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Sales Item not found"));
    }


}
