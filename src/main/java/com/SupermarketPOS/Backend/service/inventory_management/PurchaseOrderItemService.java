package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderItemArrivalInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderItem;
import com.SupermarketPOS.Backend.model.inventory_management.PurchaseOrderStatus;
import com.SupermarketPOS.Backend.model.inventory_management.StockArrival;
import com.SupermarketPOS.Backend.repository.inventory_management.PurchaseOrderItemRepository;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Objects;

@Service
public class PurchaseOrderItemService {
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;
    private final ItemService itemService;
    private final ItemSupplyService itemSupplyService;
    private final EmployeeService employeeService;
    private final PurchaseOrderService purchaseOrderService;
    private final StockArrivalService stockArrivalService;

    public PurchaseOrderItemService(PurchaseOrderItemRepository purchaseOrderItemRepository, ItemService itemService, ItemSupplyService itemSupplyService, EmployeeService employeeService, PurchaseOrderService purchaseOrderService, StockArrivalService stockArrivalService) {
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.itemService = itemService;
        this.itemSupplyService = itemSupplyService;
        this.employeeService = employeeService;
        this.purchaseOrderService = purchaseOrderService;
        this.stockArrivalService = stockArrivalService;
    }

    public PurchaseOrderItem HandleItemArrival(PurchaseOrderItemArrivalInput arrivalInput , Principal principal){
        // get the caller of the function
        Employee caller = employeeService.getByEmail(principal.getName());
        // get the PurchaseOrderItem
        PurchaseOrderItem orderItem = purchaseOrderItemRepository.findById(arrivalInput.purchaseOrderItemId()).orElseThrow(()-> new EntityNotFoundException("PurchaseOrderItem not found"));
        //check if the caller is changing a purchaseOrderItem in the same branch
        if(orderItem.getPurchaseOrder().getBranch() != caller.getBranch() ){
            throw new RuntimeException("Trying to change the purchaseOrderItem in another branch");
        }

        orderItem.setReceivedQuantity(orderItem.getReceivedQuantity()+arrivalInput.receivedQuantity());
        orderItem.setTotalCost(orderItem.getPurchaseItemUnitCost()*orderItem.getReceivedQuantity());

        //check if the all the items form that order item received
        if (Objects.equals(orderItem.getReceivedQuantity(), orderItem.getQuantity())){
            orderItem.setPurchaseOrderItemStatus(PurchaseOrderStatus.COMPLETED);
            // this completed check whether the order is completed(all times are received )
        }
        purchaseOrderItemRepository.save(orderItem);
        purchaseOrderService.UpdatePurchaseOrderStatus(orderItem.getPurchaseOrder());

        StockArrival newStockArrival = stockArrivalService
                .CreateNewStockArrival(
                        orderItem,
                        arrivalInput.receivedQuantity(),
                        new Timestamp(System.currentTimeMillis())
                );
        return orderItem;
    }


    public boolean DeletePurchaseOrderItem(Integer id ) {
        try {
            purchaseOrderItemRepository.deleteById(id);
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }
}
