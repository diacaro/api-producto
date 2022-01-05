package com.dennis.tienda.controller

import com.dennis.tienda.model.Product
import com.dennis.tienda.service.ProductoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class ProductoController {
    @Autowired
    lateinit var productoService: ProductoService

    @GetMapping
    fun list(): List<Product>{
        return productoService.list()
    }
    @PostMapping
    fun save (@RequestBody product:Product):Product {
        return productoService.save(product)

    }
    @PutMapping
    fun update (@RequestBody product: Product): Product {
        return productoService.update(product)
    }

    @PatchMapping
    fun updateDescription (@RequestBody product: Product): Product{
        return productoService.updateDescription(product)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable ("id") id: Long): Boolean {
        return productoService.delete(id)
    }

}