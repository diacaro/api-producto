package com.dennis.tienda.Service

import com.dennis.tienda.model.Client
import com.dennis.tienda.model.Orden
import com.dennis.tienda.model.Product
import com.dennis.tienda.repository.ClienteRepository
import com.dennis.tienda.repository.OrdenRepository
import com.dennis.tienda.repository.ProductRepository
import com.dennis.tienda.service.OrdenService
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class OrdenServiceTest {
    @InjectMocks
    lateinit var ordenService: OrdenService

    @Mock
    lateinit var ordenRepository: OrdenRepository

    @Mock
    lateinit var clienteRepository: ClienteRepository

    @Mock
    lateinit var productRepository: ProductRepository

    val returnObject: Orden = Orden().apply {
        id = 1
        id_cliente = 1
        id_producto = 1
    }
    val newObject: Orden = Orden().apply {
        id = 1
        id_cliente = 1
        id_producto = 1
    }

    //SAVE

    @Test
    fun saveIsCorrect() {
        Mockito.`when`(clienteRepository.findById(ordenMocko.id_cliente)).thenReturn(clientMocko)
        Mockito.`when`(productRepository.findById(ordenMocko.id_producto)).thenReturn(productoMocko)

        Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(returnObject)
        val response = ordenService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.id_cliente, newObject.id_cliente)
        Assertions.assertEquals(response.id_producto, newObject.id_producto)
    }

    val jsonString = File("./src/test/resources/orden/crearOrden.json").readText(Charsets.UTF_8)
    val ordenMocko = Gson().fromJson(jsonString, Orden::class.java)

    val jsonString1 = File("./src/test/resources/client/crearCliente.json").readText(Charsets.UTF_8)
    val clientMocko = Gson().fromJson(jsonString1, Client::class.java)

    val jsonString2 = File("./src/test/resources/product/crearProduct.json").readText(Charsets.UTF_8)
    val productoMocko = Gson().fromJson(jsonString2, Product::class.java)

    @Test
    fun saveOrdenFailedIdCliente() {
        Assertions.assertThrows(Exception::class.java) {
            ordenMocko.apply { id_cliente = 0 }

            Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(ordenMocko)
            ordenService.save(ordenMocko)
        }
    }

    @Test
    fun saveOrdenFailedIdProducto() {
        Assertions.assertThrows(Exception::class.java) {
            ordenMocko.apply { id_producto = 0 }

            Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(ordenMocko)
            ordenService.save(ordenMocko)
        }
    }

    //UPDATE

    @Test
    fun updateOrdenIsIdCorrect() {

        Mockito.`when`(clienteRepository.findById(ordenMocko.id_cliente)).thenReturn(clientMocko)
        Mockito.`when`(productRepository.findById(ordenMocko.id_producto)).thenReturn(productoMocko)
        Mockito.`when`(ordenRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(returnObject)
        val response = ordenService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.id_cliente, newObject.id_cliente)
        Assertions.assertEquals(response.id_producto, newObject.id_producto)
    }

    @Test
    fun updateOrdenIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(productRepository.findById(ordenMocko.id_producto)).thenReturn(productoMocko)
            Mockito.`when`(ordenRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(returnObject)
            ordenService.update(newObject)
        }
    }

    @Test
    fun updateOrdenFailedIdCliente() {
        Assertions.assertThrows(Exception::class.java) {
            ordenMocko.apply { id_cliente = 0 }
            Mockito.`when`(ordenRepository.findById(returnObject.id)).thenReturn(ordenMocko)
            Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(ordenMocko)
            ordenService.update(ordenMocko)
        }
    }

    @Test
    fun updateOrdenFailedIdProducto() {
        Assertions.assertThrows(Exception::class.java) {
            ordenMocko.apply { id_producto = 0 }
            Mockito.`when`(ordenRepository.findById(returnObject.id)).thenReturn(ordenMocko)
            Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(ordenMocko)
            ordenService.update(ordenMocko)
        }
    }

    //DELETE

    @Test
    fun deleteOrdenCorrect() {
        Mockito.`when`(ordenRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(returnObject)
        val response = ordenService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteOrdenIsFailed() {
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(ordenRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(ordenRepository.save(Mockito.any(Orden::class.java))).thenReturn(returnObject)
            val response = ordenService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}