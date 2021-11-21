package com.dennis.tienda.service

import com.dennis.tienda.model.Proveedor
import com.dennis.tienda.repository.ProveedorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service

class ProveedorService {

    @Autowired
    lateinit var proveedorRepository: ProveedorRepository


    fun list(): List<Proveedor> {

        return proveedorRepository.findAll()
    }

    fun save (@RequestBody proveedor: Proveedor): Proveedor {
        return proveedorRepository.save(proveedor)
    }

    fun update(@RequestBody proveedor: Proveedor): Proveedor {
        return proveedorRepository.save(proveedor)
    }

    fun updateTelefono (proveedor: Proveedor): Proveedor {
        val response = proveedorRepository.findById(proveedor.id)
            ?: throw Exception()
        response.apply {
            this.telefono=proveedor.telefono
        }
        return proveedorRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        proveedorRepository.deleteById(id)
        return true
    }
}