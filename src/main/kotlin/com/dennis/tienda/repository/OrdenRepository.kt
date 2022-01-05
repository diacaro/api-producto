package com.dennis.tienda.repository

import com.dennis.tienda.model.Orden
import org.springframework.data.jpa.repository.JpaRepository

interface OrdenRepository: JpaRepository<Orden, Long> {
    fun findById (id: Long?): Orden?
}