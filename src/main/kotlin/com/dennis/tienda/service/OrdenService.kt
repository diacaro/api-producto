package com.dennis.tienda.service

import com.dennis.tienda.model.Orden
import com.dennis.tienda.repository.ClienteRepository
import com.dennis.tienda.repository.OrdenRepository
import com.dennis.tienda.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service

class OrdenService {
    @Autowired
    lateinit var ordenRepository: OrdenRepository
    @Autowired
    lateinit var clienteRepository: ClienteRepository
    @Autowired
    lateinit var productRepository: ProductRepository


    fun list(): List<Orden> {
        return ordenRepository.findAll()
    }

    fun save(@RequestBody orden: Orden): Orden {
        try {
            val response = clienteRepository.findById(orden.id_cliente)
                ?: throw Exception("El ID ${orden.id_cliente} del cleinte no existe")

            val response1 = productRepository.findById(orden.id_producto)
                ?: throw Exception("El ID ${orden.id_producto} del producto no existe")


                return ordenRepository.save(orden)

        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update (@RequestBody orden: Orden):Orden{
        try {
            val response = clienteRepository.findById(orden.id_cliente)
                ?: throw Exception("El ID ${orden.id_cliente} del cliente no existe")

            val response1 = productRepository.findById(orden.id_producto)
                ?: throw Exception("El ID ${orden.id_producto} del producto no existe")

            return ordenRepository.save(orden)

        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete(id: Long): Boolean {
        ordenRepository.deleteById(id)
        return true
    }

}