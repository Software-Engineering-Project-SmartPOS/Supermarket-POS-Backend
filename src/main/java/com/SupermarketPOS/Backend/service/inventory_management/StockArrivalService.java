package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import com.SupermarketPOS.Backend.model.inventory_management.StockArrival;
import com.SupermarketPOS.Backend.repository.inventory_management.StockArrivalRepository;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class StockArrivalService {
    private final StockArrivalRepository stockArrivalRepository;
    private final EmployeeService employeeService;

    public StockArrivalService(StockArrivalRepository stockArrivalRepository, EmployeeService employeeService) {
        this.stockArrivalRepository = stockArrivalRepository;
        this.employeeService = employeeService;
    }

    public StockArrival CreateNewStockArrival(PurchaseOrderItem purchaseOrderItem, Float quantity,  Timestamp arrivedAt){
        StockArrival newStockArrival = new StockArrival(
                purchaseOrderItem,
                quantity,
                null,
                arrivedAt
        );
        return stockArrivalRepository.save(newStockArrival);
    }

    public List<StockArrival> GetAllStockArrivals(Boolean transferred,Principal principal){
        Employee caller = employeeService.getByEmail(principal.getName());
        if (transferred == null){
            return stockArrivalRepository.findAllByBranchId(caller.getBranch().getId());
        }
        return stockArrivalRepository.findAllByBranchIdAndIsTransferredToStore(caller.getBranch().getId(),transferred);
    }

    public PurchaseOrderItem GetPurchaseOrderItemByStockArrivalId(Integer id){
        return stockArrivalRepository.findById(id).get().getPurchaseOrderItem();
    }

    public StockArrival GetStockArrivalById(Integer stockArrivalId) {
        return stockArrivalRepository.findById(stockArrivalId).get();
    }
}
