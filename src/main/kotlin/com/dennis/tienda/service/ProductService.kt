package com.dennis.tienda.service

import com.dennis.tienda.model.Product
import com.dennis.tienda.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {
    @Autowired
    lateinit var productRepository: ProductRepository


    fun list(): List<Product> {

        return productRepository.findAll()
    }
}