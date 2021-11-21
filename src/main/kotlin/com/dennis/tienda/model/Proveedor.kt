package com.dennis.tienda.model

import javax.persistence.*

@Entity
@Table(name = "proveedor")

class Proveedor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var telefono: String? = null
    var categoria: String? = null
}