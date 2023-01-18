package com.example.demospringmvc.controller;

import com.example.demospringmvc.model.Category;
import com.example.demospringmvc.model.Product;
import com.example.demospringmvc.service.impl.CategoryService;
import com.example.demospringmvc.service.impl.ProductService;
import jdk.internal.icu.text.NormalizerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("category")
    public List<Category> categoryList() {
        return (List<Category>) categoryService.findAll();
    }

    @GetMapping("home")
    public ModelAndView getAllProduct() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }




    @PostMapping("create")
    public ModelAndView saveCreateProduct(@Valid Product product, BindingResult bindingResult, @Param("idCategory") Long idCategory) {
        ModelAndView modelAndView;

        if (bindingResult.hasFieldErrors()) {
            modelAndView = new ModelAndView("redirect:/create");
        } else {
            product.setCategory(categoryService.findById(idCategory).get());
            productService.save(product);
            modelAndView = new ModelAndView("redirect:/home");
        }
        return modelAndView;
    }

//    @GetMapping("edit/{id}")
//    public ModelAndView editProduct(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("edit");
//        modelAndView.addObject("product", productService.findById(id));
//        modelAndView.addObject("category", categoryService.findAll());
//        return modelAndView;
//    }
//
//    @PostMapping("edit")
//    public ModelAndView saveEditProduct(Product product, @Param("idCategory") Long idCategory) {
//        Category category = categoryService.findById(idCategory).get();
//        product.setCategory(category);
//        productService.save(product);
//        ModelAndView modelAndView = new ModelAndView("redirect:/home");
//        return modelAndView;
//    }

    @GetMapping("edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", productService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("edit")
    public ModelAndView saveEditProduct(Product product, @Param("idCategory") Long idCategory) {
        product.setCategory(categoryService.findById(idCategory).get());
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @PostMapping("search")
    public ModelAndView searchProductByName(@Param("search") String search){
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("products", productService.findAllByName(search));
        return modelAndView;
    }
}
