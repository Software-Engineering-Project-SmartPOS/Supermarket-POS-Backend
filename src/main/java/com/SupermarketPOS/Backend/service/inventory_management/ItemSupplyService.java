package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.ItemSupplyInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.Item;
import com.SupermarketPOS.Backend.model.inventory_management.ItemSupply;
import com.SupermarketPOS.Backend.model.inventory_management.Supplier;
import com.SupermarketPOS.Backend.repository.employee_management.EmployeeRepository;
import com.SupermarketPOS.Backend.repository.inventory_management.ItemSupplyRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ItemSupplyService {
    private final EmployeeRepository employeeRepository;
    private final ItemSupplyRepository itemSupplyRepository;
    private final SupplierService supplierService;
    private final ItemService itemService;

    public ItemSupplyService(EmployeeRepository employeeRepository, ItemSupplyRepository itemSupplyRepository, SupplierService supplierService, ItemService itemService) {
        this.employeeRepository = employeeRepository;
        this.itemSupplyRepository = itemSupplyRepository;
        this.supplierService = supplierService;
        this.itemService = itemService;
    }

    public ItemSupply CreateItemSupply(ItemSupplyInput itemSupplyInput, Principal principal){
        Employee associatedEmployee = employeeRepository.findByEmail(principal.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        Boolean alreadyAvailable = itemSupplyRepository.existsBySupplierIdAndItemIdAndBranchIdAndActiveAndUnitCost(
                itemSupplyInput.supplierId(),
                itemSupplyInput.itemId(),
                associatedEmployee.getBranch().getId(),
                true,
                itemSupplyInput.unitCost()
        );
        System.out.println("ffffffffffffffffffffffffffffffffffffffffff");
        System.out.println(alreadyAvailable);
        // already avaiable
        if (alreadyAvailable){
            throw new RuntimeException("ItemSupply is Already Available");
        }

        Supplier supplier = supplierService.GetSupplierById(itemSupplyInput.supplierId());
        Item item = itemService.getItemById(itemSupplyInput.itemId());

        Timestamp createdTime = new Timestamp(System.currentTimeMillis());

        ItemSupply newItemSupply = new ItemSupply(
                supplier,
                item,
                createdTime,
                associatedEmployee,
                associatedEmployee.getBranch(),
                itemSupplyInput.unitCost(),
                itemSupplyInput.active()
        );
         return itemSupplyRepository.save(newItemSupply);
    }

    public ItemSupply UpdateItemSupplyItemSupplyInput (ItemSupplyInput itemSupplyInput, Principal principal){
        //get the employee calling the request
        Employee associatedEmployee = employeeRepository.findByEmail(principal.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
        // get the ItemSupply by the id and the brandId of the associated employee
        // so employee can only change the itemSupply of his branch only
//        ItemSupply itemSupplyToUpdate = itemSupplyRepository.findById(itemSupplyInput.id()).orElseThrow(()-> new RuntimeException("ItemSupply not found or using is tring to change another branchs' ItemSupply. (not him branch) "));
            ItemSupply itemSupplyToUpdate = itemSupplyRepository.findByIdAndBranchId(itemSupplyInput.id(),associatedEmployee.getBranch().getId()).orElseThrow(()-> new RuntimeException("ItemSupply not found or using is tring to change another branchs' ItemSupply. (not him branch) "));

        //if associatedEmployee is trying to change the itemSupply of a another branch
        if (associatedEmployee.getBranch() != itemSupplyToUpdate.getBranch()){
            throw new RuntimeException("User cant change other branches itemSupplies");
        }

        //if supplier id is to change
        if(itemSupplyInput.supplierId() != null){
            Supplier newSupplier = supplierService.GetSupplierById(itemSupplyInput.supplierId());
            itemSupplyToUpdate.setSupplier(newSupplier);
        }

        //fi item need to be change
        if(itemSupplyInput.itemId() != null){
            Item newItem = itemService.getItemById(itemSupplyInput.itemId());
            itemSupplyToUpdate.setItem(newItem);
        }

        if( itemSupplyInput.unitCost() != null){
            itemSupplyToUpdate.setUnitCost(itemSupplyInput.unitCost());
        }

        //set updated time and the updated by
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        itemSupplyToUpdate.setUpdatedEmployee(associatedEmployee);
        itemSupplyToUpdate.setUpdatedDate(updatedTime);

        //save and return
        return itemSupplyRepository.save(itemSupplyToUpdate);
    }

    public List<ItemSupply> getAllItemSupplies(){
        return itemSupplyRepository.findAll();
    }
    public List<ItemSupply> getAllItemSuppliesForTheBranch(Principal principal){
        Employee caller =  employeeRepository.findByEmail(principal.getName()).orElseThrow(()-> new UsernameNotFoundException("No valid caller for the function"));
        return itemSupplyRepository.findAllByBranchId(caller.getBranch().getId());
    }


    //deleting the itemSupply
    public boolean deleteItemSupply(Integer id) {
        try {
            ItemSupply itemSupplyToDelete = itemSupplyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("ItemSupply not found"));

            itemSupplyRepository.delete(itemSupplyToDelete);
            return true;
        } catch (Exception e) {
            // Handle any exceptions that might occur during deletion
            e.printStackTrace(); // You can log the exception or take other appropriate action
            return false;
        }
    }

    //by branch
    public List<ItemSupply> getItemSuppliesByBranchId(Integer branchId) {
        return  itemSupplyRepository.findAllByBranchId(branchId);
    }


    //by itemId active
    public List<ItemSupply> getActiveItemSuppliesByItemId(Integer itemId) {
        return itemSupplyRepository.findAllByItemIdAndActive(itemId, true);
    }

    //by supplierId active
    public List<ItemSupply> getActiveItemSuppliesBySupplierId(Integer supplierId) {
        return itemSupplyRepository.findAllBySupplierIdAndActive(supplierId, true  );

    }

    //by id active
    public ItemSupply getActiveItemSupplyById(Integer id) {
        return itemSupplyRepository.findAllBySupplierIdAndActive( id, true).get(0); // a list is returned and taken the first element
    }

    //by id
    public ItemSupply getById(Integer id){
        return itemSupplyRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("item supply is not found"));
    }

    //by branchId active
    public List<ItemSupply> getActiveItemSuppliesByBranchId(Integer branchId ) {
        return itemSupplyRepository.findAllByBranchIdAndActive(branchId , true);
    }



}
