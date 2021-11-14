package com.dennis.tienda.controller

import com.dennis.tienda.model.Client
import com.dennis.tienda.service.ClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class ClienteController {
    @Autowired
    lateinit var clienteService: ClienteService

    @GetMapping
    fun list(): List<Client> {
        return clienteService.list()
    }

    @PostMapping
    fun save (@RequestBody client:Client): Client{
        return clienteService.save(client)
    }

    @PutMapping
    fun update (@RequestBody client: Client): Client {
        return clienteService.update(client)
    }

    @PatchMapping
    fun updateCedula (@RequestBody client: Client): Client {
        return clienteService.updateCedula(client)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return clienteService.delete(id)
    }

}