package com.dennis.tienda.model

import javax.persistence.*

@Entity
@Table(name = "orden")
class Orden {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @Column(name="id_cliente")
    var id_cliente: Long? = null
    @Column(name="id_producto")
    var id_producto: Long? = null
}