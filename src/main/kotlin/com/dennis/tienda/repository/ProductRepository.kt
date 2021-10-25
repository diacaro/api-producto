package com.dennis.tienda.repository

import com.dennis.tienda.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository:JpaRepository <Product,Long> {

}