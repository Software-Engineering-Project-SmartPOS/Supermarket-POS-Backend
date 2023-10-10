package com.SupermarketPOS.Backend.service.sales_management;

import com.SupermarketPOS.Backend.dto.sales_management.RefundInput;
import com.SupermarketPOS.Backend.dto.sales_management.RefundItemInput;
import com.SupermarketPOS.Backend.dto.sales_management.SalesInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.sales_management.Refund;
import com.SupermarketPOS.Backend.model.sales_management.RefundItem;
import com.SupermarketPOS.Backend.model.sales_management.Sale;
import com.SupermarketPOS.Backend.model.sales_management.SalesItem;
import com.SupermarketPOS.Backend.repository.sales_management.RefundItemRepository;
import com.SupermarketPOS.Backend.repository.sales_management.RefundRepository;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefundService {
    private final RefundRepository refundRepository;
    private final EmployeeService employeeService;
    private final SalesService salesService;
    private final RefundItemRepository refundItemRepository;

    public RefundService(RefundRepository refundRepository, EmployeeService employeeService, SalesService salesService, RefundItemRepository refundItemRepository) {
        this.refundRepository = refundRepository;
        this.employeeService = employeeService;
        this.salesService = salesService;
        this.refundItemRepository = refundItemRepository;
    }

    public Refund CreateNewRefund(RefundInput refundInput, Principal principal){
        Employee caller = employeeService.getByEmail(principal.getName());
        Sale sales = salesService.GetSalesById(refundInput.salesId());
        Refund newRefund= refundRepository.save(
                new Refund(
//                        salesService.GetSalesById(refundInput.salesId())
                        sales
                )
        );

        for (RefundItemInput  refundItemInput : refundInput.refundItemList()){
            SalesItem salesItem = sales.getSalesItemList().stream()
                    .filter(salesItem1 -> salesItem1.getId()==refundItemInput.salesItemId())
                    .collect(Collectors.toList())
                    .get(0);
            refundItemRepository.save(
                    new RefundItem(
                            newRefund,
                            salesItem,
                            refundItemInput.quantity(),
                            salesItem.getUnitPrice()*refundItemInput.quantity()
                    )
            );
        }
        return newRefund;
    }

    public List<Refund> GetAllTheRefunds(Principal principal){
        Employee caller = employeeService.getByEmail(principal.getName());
//        get the refund belongs to callers' branch
        return refundRepository.findAll().stream()
                .filter(refund -> refund.getSales().getBranch() == caller.getBranch())
                .collect(Collectors.toList());
    }


}
