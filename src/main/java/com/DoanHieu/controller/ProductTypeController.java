package com.DoanHieu.controller;

import com.DoanHieu.model.Product;
import com.DoanHieu.model.ProductType;
import com.DoanHieu.repository.ProductRepository;
import com.DoanHieu.service.ProductService;
import com.DoanHieu.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ProductTypeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;

    //hien thi tinh
    @GetMapping("/productTypes")
    public ModelAndView listProductType() {
        Iterable<ProductType> productTypes = productTypeService.findAll();
        ModelAndView modelAndView = new ModelAndView("/productType/list");
        modelAndView.addObject("productTypes", productTypes);
        return modelAndView;
    }

//    // them moi khach hang
//    //hien thi form them moi
    @GetMapping("/create-productType")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/productType/create");
        modelAndView.addObject("productType", new ProductType());
        return modelAndView;
    }

    @PostMapping("/create-productType")
    public ModelAndView saveProductType(@ModelAttribute("productType") ProductType productType) {
        productTypeService.save(productType);

        ModelAndView modelAndView = new ModelAndView("/productType/create");
        modelAndView.addObject("productType", new ProductType());
        modelAndView.addObject("message", "New ProductPro created successfully");
        return modelAndView;
    }
}
