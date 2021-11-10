package com.dennis.tienda.controller

import com.dennis.tienda.model.Client
import com.dennis.tienda.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class ClientController {
    @Autowired
    lateinit var clientService: ClientService

    @GetMapping
    fun list(): List<Client> {
        return clientService.list()
    }

}