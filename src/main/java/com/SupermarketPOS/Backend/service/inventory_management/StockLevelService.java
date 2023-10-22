package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.StockLevelDetailInput;
import com.SupermarketPOS.Backend.dto.inventory_management.StockLevelInput;
import com.SupermarketPOS.Backend.dto.inventory_management.StockTransferInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.StockArrival;
import com.SupermarketPOS.Backend.model.inventory_management.StockLevel;
import com.SupermarketPOS.Backend.model.inventory_management.StockLevelStatus;
import com.SupermarketPOS.Backend.repository.inventory_management.StockArrivalRepository;
import com.SupermarketPOS.Backend.repository.inventory_management.StockLevelRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StockLevelService {
    private final StockLevelRepository stockLevelRepository;
    private final EmployeeService employeeService;
    private final StockArrivalService stockArrivalService;
    private final DateTimeService dateTimeService;
    private final StockArrivalRepository stockArrivalRepository;

    public StockLevelService(StockLevelRepository stockLevelRepository, EmployeeService employeeService, StockArrivalService stockArrivalService, DateTimeService dateTimeService, StockArrivalRepository stockArrivalRepository) {
        this.stockLevelRepository = stockLevelRepository;
        this.employeeService = employeeService;
        this.stockArrivalService = stockArrivalService;
        this.dateTimeService = dateTimeService;
        this.stockArrivalRepository = stockArrivalRepository;
    }

    @Transactional
    public StockLevel addToStockLevel(StockLevelInput stockLevelInput, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        StockArrival stockArrival = stockArrivalService.GetStockArrivalById(stockLevelInput.stockArrivalId());
        if (caller.getBranch() != stockArrival.getPurchaseOrderItem().getPurchaseOrder().getBranch()){
            throw new RuntimeException("Trying to add another branch stock arrival to the stock level");
        }
        StockLevel newStockLevel = new StockLevel(
                stockArrival.getPurchaseOrderItem().getItem(),
                caller.getBranch(),
                dateTimeService.convertStringIntoLocalDate(stockLevelInput.expiryDate()),
                stockLevelInput.quantity(),
                stockLevelInput.sellingPrice(),
                StockLevelStatus.IN_INVENTORY,
                stockArrivalService.GetStockArrivalById(stockLevelInput.stockArrivalId())

        );
        StockLevel saved = stockLevelRepository.save(newStockLevel);

         Float totalStockLevelQuantityCount = (float)  saved.getStockArrival().getStockLevels().stream()
                .mapToDouble(stockLevel -> stockLevel.getInventoryQuantity() + stockLevel.getStallQuantity())
                .sum();
        System.out.println("//////////////////////////////////////////////////////");
        System.out.println(totalStockLevelQuantityCount);
        System.out.println(stockArrival.getQuantity());
         if (totalStockLevelQuantityCount.equals(stockArrival.getQuantity())){
             System.out.println("inside");
             stockArrival.setIsTransferredToStore(true);
         }

         stockArrivalRepository.save(stockArrival);

        return saved;

    }

    @Transactional
    public StockLevel transferToStall(StockTransferInput transferDetails, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        StockLevel stockLevel = stockLevelRepository.findById(transferDetails.stockLevelId()).orElseThrow(()->new EntityNotFoundException("Stock Level is not found"));
        System.out.println(stockLevel.getStallQuantity());
        System.out.println(stockLevel.getInventoryQuantity());
        System.out.println(transferDetails);
        if (caller.getBranch()!= stockLevel.getBranch()){
            throw new RuntimeException("trying to transfer another branch stock level");
        }

        if(stockLevel.getInventoryQuantity()< transferDetails.transferQuantity()){
            throw new RuntimeException("No enough quantity in the inventory");
        }

        stockLevel.setInventoryQuantity(stockLevel.getInventoryQuantity()-transferDetails.transferQuantity());
        stockLevel.setStallQuantity(stockLevel.getStallQuantity()+ transferDetails.transferQuantity());

        if (stockLevel.getInventoryQuantity()> 0F && stockLevel.getInventoryQuantity() != stockLevel.getTotalQuantity()){
            stockLevel.setStatus(StockLevelStatus.IN_BOTH);
        }
        if (stockLevel.getInventoryQuantity()==0){
            stockLevel.setStatus(StockLevelStatus.IN_STALL);
        }



        return  stockLevelRepository.save(stockLevel);
    }

    public List<StockLevel> getAllStockLevels(Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return  stockLevelRepository.findAllByBranchId(caller.getBranch().getId());

    }

    public List<StockLevel> getAllStockLevelsByStatus(StockLevelStatus stockLevelStatus, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        System.out.println(stockLevelStatus);
        return stockLevelRepository.findAllByBranchIdAndStatus(caller.getBranch().getId(), stockLevelStatus);


    }

    public List<StockLevel> getAllStockLevelsByItemId(StockLevelDetailInput detailInput, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        System.out.println(detailInput.id());
        System.out.println(detailInput.status());
        System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

        return stockLevelRepository.findAllByBranchIdAndItemIdAndStatus(
                caller.getBranch().getId(),
                detailInput.id(),
                detailInput.status()
        );
    }

    public List<StockLevel> getAllStockLevelsByCategory(StockLevelDetailInput detailInput, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
//        return  null;
        List<StockLevel> stockLevels = stockLevelRepository.findAllByBranchIdAndStatus(
                caller.getBranch().getId(),
                detailInput.status()
        );
        return  stockLevels.stream()
                .filter(stockLevel -> stockLevel.getItem().getCategory().getId() == detailInput.id())
                .collect(Collectors.toList());
    }

    public StockLevel getStockLevelById(Integer id, Principal principal) {
        Employee caller = employeeService.getByEmail(principal.getName());
        return stockLevelRepository.findByBranchIdAndId(
                caller.getBranch().getId(),
                id
        );
    }
}
