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

                proveedor.nombre?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("El campo 'NOMBRE' no puede estar vacio")

                proveedor.telefono?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("El campo 'CI' no puede estar vacio")

                proveedor.categoria?.takeIf { it.trim().isNotEmpty() }
                    ?: throw Exception("El campo 'CI' no puede estar vacio")
                return proveedorRepository.save(proveedor)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(@RequestBody proveedor: Proveedor): Proveedor {
        try {
            val response = proveedorRepository.findById(proveedor.id)
                ?: throw Exception("El ID ${proveedor.id} del cliente no existe")

            proveedor.nombre?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'NOMBRE' no puede estar vacio")

            proveedor.telefono?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'CI' no puede estar vacio")

            proveedor.categoria?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'CI' no puede estar vacio")
                return proveedorRepository.save(proveedor)
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

            proveedor.telefono?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo 'CI' no puede estar vacio")

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

    fun delete (id:Long?): Boolean{
        try {
            proveedorRepository.findById(id)
                ?: throw Exception("NO existe el ID")
            proveedorRepository.deleteById(id!!)
            return true
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
}