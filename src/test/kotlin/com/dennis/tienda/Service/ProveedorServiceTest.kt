package com.dennis.tienda.Service

import com.dennis.tienda.model.Client
import com.dennis.tienda.model.Proveedor
import com.dennis.tienda.repository.ProveedorRepository
import com.dennis.tienda.service.ProveedorService
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ProveedorServiceTest {

    @InjectMocks
    lateinit var proveedorService: ProveedorService

    @Mock
    lateinit var proveedorRepository: ProveedorRepository

    val returnObject: Proveedor = Proveedor().apply {
        id= 1
        nombre= "Carlos"
        telefono= "095214526"
        categoria= "bebida"
    }
    val newObject: Proveedor = Proveedor().apply {
        id= 1
        nombre= "Carlos"
        telefono= "095214526"
        categoria= "bebida"
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(returnObject)
        val response = proveedorService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.telefono, newObject.telefono)
        Assertions.assertEquals(response.categoria, newObject.categoria)
    }

    val jsonString = File("./src/test/resources/proveedor/crearProveedor.json").readText(Charsets.UTF_8)
    val proveedorMocko = Gson().fromJson(jsonString, Proveedor::class.java)

    @Test
    fun saveProveedor(){
        //Para Actualizar

        Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
        val response = proveedorService.save(proveedorMocko)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.telefono, newObject.telefono)
        Assertions.assertEquals(response.categoria, newObject.categoria)
    }

    @Test
    fun saveProveedorFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            proveedorMocko.apply { nombre= " "}

            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
            proveedorService.save(proveedorMocko)
        }
    }
    @Test
    fun saveProveedorFailedTelefono(){
        Assertions.assertThrows(Exception::class.java) {
            proveedorMocko.apply { telefono= " "}

            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
            proveedorService.save(proveedorMocko)
        }
    }
    @Test
    fun saveProveedorFailedCategoria(){
        Assertions.assertThrows(Exception::class.java) {
            proveedorMocko.apply { categoria= " "}

            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
            proveedorService.save(proveedorMocko)
        }
    }

    //UPDATE

    @Test
    fun updateProveedorIsIdCorrect() {

        Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(returnObject)
        val response = proveedorService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.telefono, newObject.telefono)
        Assertions.assertEquals(response.categoria, newObject.categoria)
    }

    @Test
    fun updateProveedorIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(proveedorRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(returnObject)
            proveedorService.update(newObject)
        }
    }

    @Test
    fun updateProveedorFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            proveedorMocko.apply { nombre= " "}
            Mockito.`when`(proveedorRepository.findById(returnObject.id)).thenReturn(proveedorMocko)
            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
            proveedorService.update(proveedorMocko)
        }
    }
    @Test
    fun updateProveedorFailedTelefono(){
        Assertions.assertThrows(Exception::class.java) {
            proveedorMocko.apply { telefono= " "}
            Mockito.`when`(proveedorRepository.findById(returnObject.id)).thenReturn(proveedorMocko)
            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
            proveedorService.update(proveedorMocko)
        }
    }
    @Test
    fun updateProveedorFledCategoria(){
        Assertions.assertThrows(Exception::class.java) {
            proveedorMocko.apply { categoria= " "}
            Mockito.`when`(proveedorRepository.findById(returnObject.id)).thenReturn(proveedorMocko)
            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(proveedorMocko)
            proveedorService.update(proveedorMocko)
        }
    }

    //DELETE

    @Test
    fun deleteProveedorCorrect(){
        Mockito.`when`(proveedorRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(returnObject)
        val response = proveedorService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteProveedorIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(proveedorRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(proveedorRepository.save(Mockito.any(Proveedor::class.java))).thenReturn(returnObject)
            val response = proveedorService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }

}