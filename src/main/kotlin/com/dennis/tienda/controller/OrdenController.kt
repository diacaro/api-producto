package com.dennis.tienda.controller

import com.dennis.tienda.model.Orden
import com.dennis.tienda.service.OrdenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orden")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class OrdenController {
    @Autowired
    lateinit var ordenService: OrdenService

    @GetMapping
    fun list(): List<Orden> {
        return ordenService.list()
    }

    @PostMapping
    fun save (@RequestBody orden: Orden): Orden {
        return ordenService.save(orden)
    }

    @PutMapping
    fun update (@RequestBody orden: Orden): Orden {
        return ordenService.update(orden)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return ordenService.delete(id)
    }
}