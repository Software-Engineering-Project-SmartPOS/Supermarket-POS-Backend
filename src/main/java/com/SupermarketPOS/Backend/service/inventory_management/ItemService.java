package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.ItemInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.Brand;
import com.SupermarketPOS.Backend.model.inventory_management.Category;
import com.SupermarketPOS.Backend.model.inventory_management.Item;
import com.SupermarketPOS.Backend.repository.inventory_management.BrandRepository;
import com.SupermarketPOS.Backend.repository.inventory_management.CategoryRepository;
import com.SupermarketPOS.Backend.repository.inventory_management.ItemRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final EmployeeService employeeService;
    private final DateTimeService dateTimeService;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    // Inject dependencies
    public ItemService(ItemRepository itemRepository, EmployeeService employeeService, DateTimeService dateTimeService, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.employeeService = employeeService;
        this.dateTimeService = dateTimeService;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public Item createItem(ItemInput itemInput, Principal principal) {
        // get user calling the function based on the token
        Employee updatingUser = employeeService.getByEmail(principal.getName());

        // Check if an item with the same barcode already exists
        Item existingItemWithBarcode = itemRepository.findByBarcodeNo(itemInput.barcodeNo());
        if (existingItemWithBarcode != null) {
            throw new IllegalArgumentException("An item with the same barcode already exists.");
        }

        Category itemCategory = categoryRepository.findById(itemInput.categoryId()).orElseThrow(()-> new IllegalArgumentException("Category not found with id"));
        Brand itemBrand = brandRepository.findById(itemInput.brandId()).orElseThrow(()-> new IllegalArgumentException("Brand not found with id"));
//
//        Category itemCategory = categoryRepository.findById(itemInput.categoryId()).orElse(null);
//        Brand itemBrand = brandRepository.findById(itemInput.brandId()).orElse(null);
        // Create a new item
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Item newItem = new Item(
                itemInput.itemCode(),
                itemInput.name(),
                itemInput.barcodeNo(),
                itemInput.description(),
                itemCategory,
                itemBrand,
                itemInput.unitOfMeasure(),
                createdAt,
                null, // Set updatedAt as null when creating
                itemInput.active(),
                itemInput.reorderLevel(),
                itemInput.returnable()
        );
        newItem.setCreatedBy(updatingUser);
        return itemRepository.save(newItem);
    }

    public Item updateItem(ItemInput itemInput, Principal principal) {
        Employee updatedBy = employeeService.getByEmail(principal.getName());

        // Find the item to update
        Item itemToUpdate = itemRepository.findById(itemInput.id())
                .orElseThrow(() -> new UsernameNotFoundException("Item not found"));

        // Update the item properties as needed
        if (itemInput.itemCode() != null) {
            itemToUpdate.setItemCode(itemInput.itemCode());
        }
        if (itemInput.name() != null) {
            itemToUpdate.setName(itemInput.name());
        }
        if (itemInput.barcodeNo() != null) {
            itemToUpdate.setBarcodeNo(itemInput.barcodeNo());
        }
        if (itemInput.description() != null) {
            itemToUpdate.setDescription(itemInput.description());
        }


        if (itemInput.categoryId() != null) {
            Category newCategory = categoryRepository.findById(itemInput.categoryId()).get();
            itemToUpdate.setCategory(newCategory);
        }

        if (itemInput.brandId() != null) {
            Brand newBrand = brandRepository.findById(itemInput.brandId()).get();
            itemToUpdate.setBrand(newBrand);
        }
        if (itemInput.unitOfMeasure() != null) {
            itemToUpdate.setUnitOfMeasure(itemInput.unitOfMeasure());
        }
        if (itemInput.active() != null) {
            itemToUpdate.setActive(itemInput.active());
        }
        if (itemInput.reorderLevel() != null) {
            itemToUpdate.setReorderLevel(itemInput.reorderLevel());
        }
        if (itemInput.returnable() != null) {
            itemToUpdate.setReturnable(itemInput.returnable());
        }

        // Update the updatedAt timestamp
        itemToUpdate.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        itemToUpdate.setUpdatedBy(updatedBy);

        return itemRepository.save(itemToUpdate);
    }

    public Item getItemById(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Item not found"));
    }

    public Item getItemByBarcode(String barcode) {
        return itemRepository.findByBarcodeNo(barcode);
    }

    public Item getItemByItemCode(String itemCode) {
        return itemRepository.findByItemCode(itemCode);
    }

    //get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
