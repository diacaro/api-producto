package com.dennis.tienda.service

import com.dennis.tienda.model.Product
import com.dennis.tienda.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ProductoService {
    @Autowired
    lateinit var productRepository: ProductRepository


    fun list(): List<Product> {

        return productRepository.findAll()
    }
    fun save(product:Product): Product {

        return productRepository.save(product)
    }

    fun update(@RequestBody product: Product): Product {
        return productRepository.save(product)
    }

    fun updateDescription (product: Product): Product {
        val response = productRepository.findById(product.id)
            ?: throw Exception()
        response.apply {
            this.details=product.details
        }
        return productRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        productRepository.deleteById(id)
        return true
    }

}