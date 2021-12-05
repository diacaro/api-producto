package com.dennis.tienda.model

import javax.persistence.*

@Entity
@Table(name = "orden")
class Orden {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var idProducto: String? = null
    var idClient: String? = null
}