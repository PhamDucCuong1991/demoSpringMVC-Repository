package com.example.demospringmvc.service;

import com.example.demospringmvc.model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product>{
    List<Product> findAllByName(String name);
}
