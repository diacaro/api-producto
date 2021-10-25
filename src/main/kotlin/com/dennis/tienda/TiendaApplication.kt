package com.dennis.tienda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TiendaApplication

fun main(args: Array<String>) {
	runApplication<TiendaApplication>(*args)
}
