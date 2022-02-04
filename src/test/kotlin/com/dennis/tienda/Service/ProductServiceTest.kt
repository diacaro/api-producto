package com.dennis.tienda.Service

import com.dennis.tienda.model.Product
import com.dennis.tienda.repository.ProductRepository
import com.dennis.tienda.service.ProductoService
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ProductServiceTest {
    @InjectMocks
    lateinit var productoService: ProductoService

    @Mock
    lateinit var productRepository: ProductRepository

    val returnObject: Product = Product().apply {
        id= 1
        description= "cola"
        details= "bebida"
    }
    val newObject: Product = Product().apply {
        id= 1
        description= "cola"
        details= "bebida"
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(returnObject)
        val response = productoService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.description, newObject.description)
        Assertions.assertEquals(response.details, newObject.details)
    }

    val jsonString = File("./src/test/resources/product/crearProduct.json").readText(Charsets.UTF_8)
    val productoMocko = Gson().fromJson(jsonString, Product::class.java)

    @Test
    fun saveProductFailedDescription(){
        Assertions.assertThrows(Exception::class.java) {
            productoMocko.apply { description= " "}

            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productoMocko)
            productoService.save(productoMocko)
        }
    }
    @Test
    fun saveProductFailedDetails(){
        Assertions.assertThrows(Exception::class.java) {
            productoMocko.apply { details= " "}

            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productoMocko)
            productoService.save(productoMocko)
        }
    }

    //UPDATE

    @Test
    fun updateProductIsIdCorrect() {

        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(returnObject)
        val response = productoService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.description, newObject.description)
        Assertions.assertEquals(response.details, newObject.details)
    }

    @Test
    fun updateProductIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(productRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(returnObject)
            productoService.update(newObject)
        }
    }

    @Test
    fun updateProductFailedDescription(){
        Assertions.assertThrows(Exception::class.java) {
            productoMocko.apply { description= " "}
            Mockito.`when`(productRepository.findById(returnObject.id)).thenReturn(productoMocko)
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productoMocko)
            productoService.update(productoMocko)
        }
    }
    @Test
    fun updateProductFailedDetails(){
        Assertions.assertThrows(Exception::class.java) {
            productoMocko.apply { details= " "}
            Mockito.`when`(productRepository.findById(returnObject.id)).thenReturn(productoMocko)
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(productoMocko)
            productoService.update(productoMocko)
        }
    }

    //DELETE

    @Test
    fun deleteProductCorrect(){
        Mockito.`when`(productRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(returnObject)
        val response = productoService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteProductIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(productRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(productRepository.save(Mockito.any(Product::class.java))).thenReturn(returnObject)
            val response = productoService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}