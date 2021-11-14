package com.dennis.tienda.service

import com.dennis.tienda.model.Client
import com.dennis.tienda.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(): List<Client> {

        return clientRepository.findAll()
    }
    fun save(@RequestBody client:Client): Client{
        return clientRepository.save(client)
    }

    fun update(@RequestBody client: Client): Client {
        return clientRepository.save(client)
    }

    fun updateCedula (client: Client): Client {
        val response = clientRepository.findById(client.id)
            ?: throw Exception()
        response.apply {
            this.ci=client.ci
        }
        return clientRepository.save(response)
    }

    fun delete (id:Long): Boolean{
        clientRepository.deleteById(id)
        return true
    }
}