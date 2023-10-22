package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderInput;
import com.SupermarketPOS.Backend.dto.inventory_management.PurchaseOrderItemInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.*;
import com.SupermarketPOS.Backend.repository.inventory_management.PurchaseOrderItemRepository;
import com.SupermarketPOS.Backend.repository.inventory_management.PurchaseOrderRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final EmployeeService employeeService;
    private final SupplierService supplierService;
    private final ItemService itemService;
    private final ItemSupplyService itemSupplyService;
    private final PurchaseOrderItemRepository purchaseOrderItemRepository;
    private final DateTimeService dateTimeService;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, EmployeeService employeeService, SupplierService supplierService, ItemService itemService, ItemSupplyService itemSupplyService, PurchaseOrderItemRepository purchaseOrderItemRepository, DateTimeService dateTimeService) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.employeeService = employeeService;
        this.supplierService = supplierService;
        this.itemService = itemService;
        this.itemSupplyService = itemSupplyService;
        this.purchaseOrderItemRepository = purchaseOrderItemRepository;
        this.dateTimeService = dateTimeService;
    }

    public List<PurchaseOrder> GetAllPurchaseOrders(Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return purchaseOrderRepository.findAllByBranchId(caller.getBranch().getId());
    }

    public PurchaseOrder GetPurchaseOrderById(Integer id ,Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return purchaseOrderRepository.findByIdAndBranchId(id, caller.getBranch().getId())
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));
    }
    public List<PurchaseOrder> GetPurchaseOrderBySupplierId(Integer supplierId, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return purchaseOrderRepository.findAllBySupplierIdAndBranchId(supplierId,caller.getBranch().getId());
    }
    public List<PurchaseOrder> GetPurchaseOrdersByStatus(PurchaseOrderStatus status, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return purchaseOrderRepository.findAllByOrderStatusAndBranchId(status , caller.getBranch().getId());
    }


    @Transactional
    public PurchaseOrder CreatePurchaseOrder(PurchaseOrderInput purchaseOrderInput , Principal principal) {
        //get the associate user
        Employee createdBy = employeeService.getByEmail(principal.getName());
        // get the supplier
        Supplier supplier = supplierService.GetSupplierById(purchaseOrderInput.SupplierId());
        PurchaseOrder newPurchaseOrder = purchaseOrderRepository.save(
                new PurchaseOrder(
                        supplier,
                        LocalDate.now(),
                        dateTimeService.convertStringIntoLocalDate(purchaseOrderInput.expectedDate()),
                        createdBy,
                        purchaseOrderInput.description(),
                        createdBy.getBranch(),
                        purchaseOrderInput.purchaseCost()

                )
        );

        List<PurchaseOrderItem> orderItemList = new ArrayList<>(); // to store the PurchaseOrderItems

        //get the purchaseOrderItemInput, create the orderItemList
        for (PurchaseOrderItemInput orderItem :purchaseOrderInput.purchaseOrderItemList()){
            ItemSupply itemSupply =itemSupplyService.getById(orderItem.itemSupplyId());

            if (itemSupply.getBranch() != createdBy.getBranch()){
                throw new RuntimeException("Given item supply is for your branch");
            }
            PurchaseOrderItem newOrderItem = new PurchaseOrderItem(
                    newPurchaseOrder,
                    itemService.getItemById(orderItem.itemId()),
                    itemSupplyService.getById(orderItem.itemSupplyId()),
                    orderItem.quantity(),
                    itemSupply.getUnitCost()
            );

            newOrderItem.setPurchaseOrderItemStatus(PurchaseOrderStatus.CREATE);
            // save and return the new purchaseOrderItem
            orderItemList.add(purchaseOrderItemRepository.save(newOrderItem));
        }

        return  newPurchaseOrder;

    }

    public boolean DeletePurchaseOrder(Integer id, Principal principal) {
        try {
            purchaseOrderRepository.deleteById(id);
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void UpdatePurchaseOrderStatus(PurchaseOrder purchaseOrder) {
        // get the purchaseOrderItems
        List<PurchaseOrderItem> purchaseOrderItems =  purchaseOrder.getPurchaseOrderItemList();

        //Update the purchaseCost , map each and get the sum
        float totalCostOfAllItemsArrived = (float) purchaseOrderItems.stream()
                .mapToDouble(orderItem-> orderItem.getTotalCost())
                .sum();
        purchaseOrder.setPurchaseCost(totalCostOfAllItemsArrived);

        // Check the statuses of all purchase order items
        boolean allCompleted = purchaseOrderItems.stream()
                .allMatch(orderItem -> orderItem.getPurchaseOrderItemStatus() == PurchaseOrderStatus.COMPLETED);

        // Update the status of the purchase order
        purchaseOrder.setOrderStatus(allCompleted ? PurchaseOrderStatus.COMPLETED : PurchaseOrderStatus.CONFIRMED);

        // Save the updated purchase order
        purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrderItem> GetPurchaseOrderItemByPurchaseOrderId(Integer id){
        return purchaseOrderItemRepository.findAllByPurchaseOrderId(id);
    }
}
