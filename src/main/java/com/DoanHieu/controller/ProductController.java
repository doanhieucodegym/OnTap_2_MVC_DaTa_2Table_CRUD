package com.DoanHieu.controller;

import com.DoanHieu.model.Product;
import com.DoanHieu.model.ProductType;
import com.DoanHieu.service.ProductService;
import com.DoanHieu.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    //tao doi tuong cho province
    @Autowired
    private ProductTypeService productTypeService;
    // boo sung danh sach cho nguoi dung  lua chon tinh.@ModelAttribute laf cach de gan danh sach tat ca model cua view
// cai này la tao sang file html
    @ModelAttribute("productTypes")
    public Iterable<ProductType> productTypes(){
        return productTypeService.findAll();
    }

    //hien thi sp
    @GetMapping("/products")
    public ModelAndView listProduct(@RequestParam("name")Optional<String> name , Pageable pageable){
        Page<Product> products;
        if(name.isPresent()){
            products = productService.findAllByProductTypeName(name.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    //tao mơi
    // tao  moi  khach hang
    //show man hinh
    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView =new ModelAndView("/product/create");
        modelAndView.addObject("product",new Product());
        return modelAndView;
    }
    //tra ve save
    @PostMapping("/create-product")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("product") Product product, BindingResult result){
        if (result.hasFieldErrors()) {
            System.out.println("Result Error Occured" + result.getAllErrors());
            ModelAndView modelAndView= new ModelAndView("product/create");
            modelAndView.addObject("product", product);
            return modelAndView;
        }
        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }


    //sua sp
    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product!=null){
            ModelAndView modelAndView =new ModelAndView("/product/edit");
            //link vao  view
            modelAndView.addObject("product",product);
            //tao dooi tuong truyen vao
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-product")
    public  ModelAndView updateCustomer(@ModelAttribute("product")Product product){
        productService.save(product);
        ModelAndView modelAndView =new ModelAndView("/product/edit");
        modelAndView.addObject("product",product);
        modelAndView.addObject("message","Product update successfully!");
        return modelAndView;

    }
    //Xóa sp
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product =productService.findById(id);
        if(product!=null){
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product" ,product);
            return modelAndView;
        }else {
            ModelAndView modelAndView =new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    //xoa
    @PostMapping("/delete-product")
    public String deleteCustomer(@ModelAttribute("product")Product product){
        productService.remove(product.getId());
        return "redirect:products";
    }

}
