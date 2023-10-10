package com.SupermarketPOS.Backend.service.sales_management;

import com.SupermarketPOS.Backend.dto.sales_management.SalesInput;
import com.SupermarketPOS.Backend.dto.sales_management.SalesItemInput;
import com.SupermarketPOS.Backend.model.customer_management.Customer;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import com.SupermarketPOS.Backend.model.sales_management.Sale;
import com.SupermarketPOS.Backend.model.sales_management.SalesItem;
import com.SupermarketPOS.Backend.repository.sales_management.SalesItemRepository;
import com.SupermarketPOS.Backend.repository.sales_management.SalesRepository;
import com.SupermarketPOS.Backend.service.customer_management.CustomerService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import com.SupermarketPOS.Backend.service.inventory_management.StockLevelService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class SalesService {
    private final SalesRepository salesRepository;
    private final SalesItemRepository salesItemRepository;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final StockLevelService stockLevelService;

    public SalesService(SalesRepository salesRepository, SalesItemRepository salesItemRepository, EmployeeService employeeService, CustomerService customerService, StockLevelService stockLevelService) {
        this.salesRepository = salesRepository;
        this.salesItemRepository = salesItemRepository;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.stockLevelService = stockLevelService;
    }

    @Transactional
    public Sale CreateSales(SalesInput salesInput, Principal principal){
        System.out.println("in the Create sales in sales service");
        Employee caller = employeeService.getByEmail(principal.getName());
        Customer customer = customerService.getCustomerByCustomerId(salesInput.customerId());
        Sale newSales = salesRepository.save(
                new Sale(
                        customer,
                        caller.getBranch(),
                        new Timestamp(System.currentTimeMillis()),
                        salesInput.paymentType()
                )
        );
        for(SalesItemInput salesItemInput : salesInput.salesItemsInput()){
            StockLevel stockLevel = stockLevelService.getStockLevelById(salesItemInput.stockLevelId(),principal);

            // save the new sales item
             salesItemRepository.save(
                    new SalesItem(
                            newSales,
                            stockLevel,
                            salesItemInput.quantity(),
                            stockLevel.getSellingPrice(),
                            (stockLevel.getDiscount() != null) ? stockLevel.getDiscount() : 0

                    )
             );

             if(stockLevel.getStallQuantity() < salesItemInput.quantity()){
                 throw new RuntimeException("not enough stocks in the stall");
             }
             stockLevel.setStallQuantity(stockLevel.getStallQuantity()  - salesItemInput.quantity());
             stockLevel.setSoldQuantity(stockLevel.getSoldQuantity() + salesItemInput.quantity());

        }

        return newSales;
    };

    public Sale GetSalesById(Integer id, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return salesRepository.findByIdAndBranchId(id, caller.getBranch().getId());
    }

    public Sale GetSalesById(Integer id) {
        return salesRepository.findById(id).orElseThrow(()-> new RuntimeException("Sales with given id is not found"));
    }

    public List<Sale> GetAllSales(Principal principal){
        Employee caller = employeeService.getByEmail(principal.getName());
        return salesRepository.findAllByBranchId(caller.getBranch().getId());
    }




}
