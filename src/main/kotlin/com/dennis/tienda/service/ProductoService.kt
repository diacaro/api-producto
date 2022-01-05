package com.dennis.tienda.service

import com.dennis.tienda.model.Product
import com.dennis.tienda.repository.ProductRepository
import com.dennis.tienda.repository.ProveedorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service
class ProductoService {
    @Autowired
    lateinit var productRepository: ProductRepository

    fun list(): List<Product> {

        return productRepository.findAll()
    }
    fun save(product:Product): Product {
        try {
            if (product.description.equals("") || product.details.equals("")) {
                throw Exception("Campo DESCRIPTION o DETAILS no validos")
            } else {
                return productRepository.save(product)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(@RequestBody product: Product): Product {
        try {
            val response = productRepository.findById(product.id)
                ?: throw Exception("El ID ${product.id} del producto no existe")
            if (product.description.equals("") || product.details.equals("")) {
                throw Exception("Campo DESCRIPTION o DETAILS no validos")
            } else {
                return productRepository.save(product)
            }
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun updateDescription (product: Product): Product {
        try {
            val response = productRepository.findById(product.id)
                ?: throw Exception("Producto No Encontrado")
            response.apply {
                this.details = product.details
            }
            return productRepository.save(response)
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (id:Long): Boolean{
        productRepository.deleteById(id)
        return true
    }

}