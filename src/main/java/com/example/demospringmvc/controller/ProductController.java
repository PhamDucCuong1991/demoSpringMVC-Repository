package com.example.demospringmvc.controller;

import com.example.demospringmvc.model.Product;
import com.example.demospringmvc.service.impl.CategoryService;
import com.example.demospringmvc.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("home")
    public ModelAndView getAllProduct() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("category", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView saveCreateProduct(Product product, @Param("idCategory") Long idCategory) {
        product.setCategory(categoryService.findById(idCategory).get());
        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }
}
