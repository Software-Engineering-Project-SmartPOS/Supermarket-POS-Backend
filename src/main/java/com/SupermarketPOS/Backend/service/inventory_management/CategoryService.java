package com.SupermarketPOS.Backend.service.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.CategoryInput;
import com.SupermarketPOS.Backend.model.employee_management.Employee;
import com.SupermarketPOS.Backend.model.inventory_management.Category;
import com.SupermarketPOS.Backend.repository.inventory_management.CategoryRepository;
import com.SupermarketPOS.Backend.service.common_services.DateTimeService;
import com.SupermarketPOS.Backend.service.employee_management.EmployeeService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final EmployeeService employeeService;
    private final DateTimeService dateTimeService;

    // Inject dependencies
    public CategoryService(CategoryRepository categoryRepository, EmployeeService employeeService, DateTimeService dateTimeService) {
        this.categoryRepository = categoryRepository;
        this.employeeService = employeeService;
        this.dateTimeService = dateTimeService;
    }

    public Category AddCategory(CategoryInput categoryInput, Principal principal) {
        Employee updatingUser = employeeService.getByEmail(principal.getName());

        // Check if the category already exists
        boolean isCategoryAlreadyAvailable = IsCategoryAvailable(categoryInput.description());
        if (isCategoryAlreadyAvailable) {
            return null;
        } else {
            Timestamp createdAt = new Timestamp(System.currentTimeMillis());
            Category newCategory = new Category(
                    categoryInput.name(),
                    categoryInput.description(),
                    createdAt,
                    updatingUser
            );
            return categoryRepository.save(newCategory);
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category UpdateCategory(CategoryInput categoryInput, Principal principal) {
        Employee updatedBy = employeeService.getByEmail(principal.getName()); // set the updating user
        Optional<Category> categoryOptional = categoryRepository.findById(categoryInput.id());
        if (categoryOptional.isEmpty()) {
            throw new UsernameNotFoundException("Category id not found");
        }
        //update the name and the description
        Category category = categoryOptional.get();
        if (categoryInput.name() != null){
            category.setName(categoryInput.name());
        }
        if (categoryInput.description() != null) {
            category.setDescription(categoryInput.description());
        }
        category.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        category.setUpdatedEmployee(updatedBy);
        return categoryRepository.save(category); // save and return the updated category
    }

    public boolean IsCategoryAvailable(String name) {
        return categoryRepository.existsByName(name);
    }

    public Category findCategoryByName(String name){
        return categoryRepository.findByName(name);
    }
}
