package com.dennis.tienda.repository

import com.dennis.tienda.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findById (id: Long?): User?
    @Query(value = "SELECT * FROM USERS u WHERE u.username = :username", nativeQuery = true)
    fun findByUsername (username: String?): User?
}