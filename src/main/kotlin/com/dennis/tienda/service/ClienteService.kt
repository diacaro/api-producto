package com.dennis.tienda.service

import com.dennis.tienda.model.Client
import com.dennis.tienda.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import org.springframework.web.bind.annotation.RequestBody


@Service
class ClienteService {
    @Autowired
    lateinit var clienteRepository: ClienteRepository


    fun list(): List<Client> {

        return clienteRepository.findAll()
    }

    fun save(@RequestBody client: Client): Client {

        if (client.nombre.equals("") && client.ci.equals("")) {
            throw Exception()
        } else {
            return clienteRepository.save(client)
        }
    }

    fun update(@RequestBody client: Client): Client {
        if (client.nombre.equals("") && client.ci.equals("")) {
            throw Exception()
        } else {
            return clienteRepository.save(client)
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
        val response = clienteRepository.findById(client.id)
            ?: throw Exception()
        response.apply {
            this.nombre = client.nombre
        }
        return clienteRepository.save(response)
    }
    fun delete(id: Long): Boolean {
        clienteRepository.deleteById(id)
        return true
    }


}