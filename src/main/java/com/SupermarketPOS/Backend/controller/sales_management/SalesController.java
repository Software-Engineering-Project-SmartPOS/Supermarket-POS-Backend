package com.SupermarketPOS.Backend.controller.sales_management;

import com.SupermarketPOS.Backend.service.sales_management.SalesService;
import org.springframework.stereotype.Controller;

@Controller
public class SalesController {
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }
}
