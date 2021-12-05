package com.dennis.tienda.service

import com.dennis.tienda.model.Client
import com.dennis.tienda.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException


@Service
class ClienteService {
    @Autowired
    lateinit var clienteRepository: ClienteRepository


    fun list(): List<Client> {

        return clienteRepository.findAll()
    }

    fun save(@RequestBody client: Client): Client {
        try {

            if (client.nombre.equals("") || client.ci.equals("")) {
                throw Exception()
            } else {
                return clienteRepository.save(client)
            }
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NO_CONTENT, "Campo NOMBRE o CI no validos", ex)
        }
    }

    fun update(@RequestBody client: Client): Client {
    try {
        if (client.nombre.equals("") || client.ci.equals("")) {
            throw Exception()
        } else {
            return clienteRepository.save(client)
        }
    }
    catch (ex: Exception) {
        throw ResponseStatusException(
            HttpStatus.NO_CONTENT, "Campo NOMBRE o CI no validos", ex)
    }
    }
//
//    fun updateCedula(client: Client): Client {
//        val response = clienteRepository.findById(client.id)
//            ?: throw Exception()
//        response.apply {
//            this.ci = client.ci
//        }
//        return clienteRepository.save(response)
//
//        if (client.ci.equals("")) {
//
//        } else {
//            return clienteRepository.save(client)
//        }
//    }

    fun updateNombre(client: Client): Client {
        try {
            val response = clienteRepository.findById(client.id)
                ?: throw Exception()
            response.apply {
                this.nombre = client.nombre
            }
            return clienteRepository.save(response)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cliente No Encontrado", ex)
        }
    }
    fun delete(id: Long): Boolean {
        clienteRepository.deleteById(id)
        return true
    }


}