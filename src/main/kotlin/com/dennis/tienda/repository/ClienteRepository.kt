package com.dennis.tienda.repository

import com.dennis.tienda.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository: JpaRepository<Client, Long> {

    fun findById (id: Long?): Client?

}