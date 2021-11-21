package com.dennis.tienda.repository


import com.dennis.tienda.model.Proveedor
import org.springframework.data.jpa.repository.JpaRepository

interface ProveedorRepository: JpaRepository<Proveedor, Long> {
    fun findById (id: Long?): Proveedor?
}