package com.dennis.tienda.service

import com.dennis.tienda.model.Orden
import com.dennis.tienda.repository.OrdenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@Service

class OrdenService {
    @Autowired
    lateinit var ordenRepository: OrdenRepository


    fun list(): List<Orden> {

        return ordenRepository.findAll()
    }

    fun save(@RequestBody orden: Orden): Orden {
        try {

            if (orden.idProducto.equals("") || orden.idClient.equals("")) {
                throw Exception()
            } else {
                return ordenRepository.save(orden)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NO_CONTENT, "Uno de los campos no validos", ex)
        }
    }

    fun update(@RequestBody orden: Orden): Orden {
        try {
            if (orden.idProducto.equals("") || orden.idClient.equals("")) {
                throw Exception()
            } else {
                return ordenRepository.save(orden)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NO_CONTENT, "Uno de los campos no validos", ex)
        }
    }

    fun updateNombre(orden: Orden): Orden {
        try {
            val response = ordenRepository.findById(orden.id)
                ?: throw Exception()
            response.apply {
                this.idClient = orden.idClient
            }
            return ordenRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cliente No Encontrado", ex)
        }
    }
    fun delete(id: Long): Boolean {
        ordenRepository.deleteById(id)
        return true
    }

}