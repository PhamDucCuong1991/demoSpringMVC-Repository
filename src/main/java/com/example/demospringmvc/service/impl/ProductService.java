package com.example.demospringmvc.service.impl;

import com.example.demospringmvc.model.Product;
import com.example.demospringmvc.repository.IProductRepository;
import com.example.demospringmvc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository iProductRepository;

    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByName(String name) {
        return iProductRepository.findAllByNameContaining(name);
    }
}
