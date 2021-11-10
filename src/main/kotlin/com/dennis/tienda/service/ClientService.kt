package com.dennis.tienda.service

import com.dennis.tienda.model.Client
import com.dennis.tienda.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService {
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun list(): List<Client> {

        return clientRepository.findAll()
    }
}