package com.SupermarketPOS.Backend.controller.inventory_management;

import com.SupermarketPOS.Backend.dto.inventory_management.CategoryInput;
import com.SupermarketPOS.Backend.model.inventory_management.Category;
import com.SupermarketPOS.Backend.service.inventory_management.CategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_STORE_MANAGER"})
    @MutationMapping
    public Category CreateCategory(@Argument CategoryInput categoryInput, Principal principal) {
        return categoryService.AddCategory(categoryInput, principal);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_OWNER","ROLE_STORE_MANAGER"})
    @MutationMapping
    public Category UpdateCategory(@Argument CategoryInput categoryInput, Principal principal) {
        return categoryService.UpdateCategory(categoryInput, principal);
    }

    @QueryMapping
    public Category GetCategoryById(@Argument Integer id) {
        return categoryService.findCategoryById(id);
    }

    @QueryMapping
    public List<Category> GetAllCategories() {
        return categoryService.getAllCategories();
    }
    @QueryMapping
    public Category GetCategoryByName(@Argument String name){
        return categoryService.findCategoryByName(name);
    }
}
