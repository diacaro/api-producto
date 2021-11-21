package com.dennis.tienda.controller


import com.dennis.tienda.model.Proveedor
import com.dennis.tienda.service.ProveedorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])

class ProveedorController {
    @Autowired
    lateinit var proveedorService: ProveedorService

    @GetMapping
    fun list(): List<Proveedor> {
        return proveedorService.list()
    }

    @PostMapping
    fun save (@RequestBody proveedor: Proveedor): Proveedor {
        return proveedorService.save(proveedor)
    }

    @PutMapping
    fun update (@RequestBody proveedor: Proveedor): Proveedor {
        return proveedorService.update(proveedor)
    }

    @PatchMapping
    fun updateTelefono (@RequestBody proveedor: Proveedor): Proveedor {
        return proveedorService.updateTelefono(proveedor)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long): Boolean {
        return proveedorService.delete(id)
    }

}