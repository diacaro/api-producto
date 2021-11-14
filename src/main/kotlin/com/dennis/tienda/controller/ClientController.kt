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
    @PutMapping
    fun update (@RequestBody client: Client): Client {
        return clientService.update(client)
    }

    @PatchMapping
    fun updateCedula (@RequestBody client: Client): Client {
        return clientService.updateCedula(client)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable ("id") id: Long): Boolean {
        return clientService.delete(id)
    }

}