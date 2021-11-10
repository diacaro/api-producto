package com.dennis.tienda.repository

import com.dennis.tienda.model.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository: JpaRepository<Client, Long> {
}