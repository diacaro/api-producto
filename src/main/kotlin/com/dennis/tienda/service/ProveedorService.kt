package com.dennis.tienda.service

import com.dennis.tienda.model.Proveedor
import com.dennis.tienda.repository.ProveedorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service

class ProveedorService {

    @Autowired
    lateinit var proveedorRepository: ProveedorRepository


    fun list(): List<Proveedor> {

        return proveedorRepository.findAll()
    }

    fun save (@RequestBody proveedor: Proveedor): Proveedor {
        try {

            if (proveedor.nombre.equals("") || proveedor.telefono.equals("") || proveedor.categoria.equals("")) {
                throw Exception("Uno de los campos no es valido")
            } else {
                return proveedorRepository.save(proveedor)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(@RequestBody proveedor: Proveedor): Proveedor {
        try {
            if (proveedor.nombre.equals("") || proveedor.telefono.equals("") || proveedor.categoria.equals("")) {
                throw Exception("Uno de los campos no es valido")
            } else {
                return proveedorRepository.save(proveedor)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateTelefono (proveedor: Proveedor): Proveedor {
        try {
            val response = proveedorRepository.findById(proveedor.id)
                ?: throw Exception("Proveedor No Encontrado")
            response.apply {
                this.telefono = proveedor.telefono
            }
            return proveedorRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete (id:Long): Boolean{
        proveedorRepository.deleteById(id)
        return true
    }
}