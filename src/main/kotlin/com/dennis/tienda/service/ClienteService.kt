package com.dennis.tienda.service

import com.dennis.tienda.model.Client
import com.dennis.tienda.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class ClienteService {
    @Autowired
    lateinit var clienteRepository: ClienteRepository
    val listaCliente= listOf<String>("Jose","Carlos","Luis")


    fun list(): List<Client> {
        return clienteRepository.findAll()
    }


    fun save(@RequestBody client: Client): Client {
        try {
            if (client.nombre.equals("") || client.ci.equals("")) {
                throw Exception("Campo NOMBRE o CI no validos")
            } else {
                return clienteRepository.save(client)
            }
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(@RequestBody client: Client): Client {
    try {
        val response = clienteRepository.findById(client.id)
            ?: throw Exception("El ID ${client.id} de la orden no existe")
        if (client.nombre.equals("") || client.ci.equals("")) {
            throw Exception("Campo NOMBRE o CI no validos")
        } else {
            return clienteRepository.save(client)
        }
    }
    catch(ex: Exception){
        throw ResponseStatusException(
            HttpStatus.NOT_FOUND, ex.message, ex)
    }
    }

    fun updateNombre(client: Client): Client {
        try {
            val response = clienteRepository.findById(client.id)
                ?: throw Exception("Cliente No Encontrado")
            response.apply {
                this.nombre = client.nombre
            }
            return clienteRepository.save(response)
        }
        catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }
    fun delete(id: Long): Boolean {
        try{
            clienteRepository.deleteById(id)
                ?: throw Exception("No existe el ID")
            clienteRepository.deleteById(id!!)
            return true
        }
        catch (ex: Exception) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }

    }
    fun validarClientes(tipo: String): Boolean {
        for (i in listaCliente){
            if (tipo == i){
                return true
            }
        }
        return false
    }


}