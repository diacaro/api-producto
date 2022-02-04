package com.dennis.tienda.Service

import com.dennis.tienda.model.Client
import com.dennis.tienda.repository.ClienteRepository
import com.dennis.tienda.service.ClienteService
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class ClientServiceTest {
    @InjectMocks
    lateinit var clienteService: ClienteService

    @Mock
    lateinit var clienteRepository: ClienteRepository

    val returnObject: Client = Client().apply {
        id= 1
        nombre= "Jose"
        ci= "010254512"
    }
    val newObject: Client = Client().apply {
        id= 1
        nombre= "Jose"
        ci= "010254512"
    }

    //SAVE

    @Test
    fun saveIsCorrect(){
        Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
        val response = clienteService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.ci, newObject.ci)
    }

    val jsonString = File("./src/test/resources/client/crearCliente.json").readText(Charsets.UTF_8)
    val clientMocko = Gson().fromJson(jsonString, Client::class.java)

    @Test
    fun saveClient(){
        //Para Actualizar

        Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMocko)
        val response = clienteService.save(clientMocko)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.ci, newObject.ci)
    }

    @Test
    fun saveClientFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            clientMocko.apply { nombre= " "}

            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMocko)
            clienteService.save(clientMocko)
        }
    }
    @Test
    fun saveClientFailedCI(){
        Assertions.assertThrows(Exception::class.java) {
            clientMocko.apply { ci= " "}

            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMocko)
            clienteService.save(clientMocko)
        }
    }

    //UPDATE

    @Test
    fun updateClientIsIdCorrect() {

        Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
        val response = clienteService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.nombre, newObject.nombre)
        Assertions.assertEquals(response.ci, newObject.ci)
    }

    @Test
    fun updateClientIsIdFailedWhenIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
            clienteService.update(newObject)
        }
    }

    @Test
    fun updateClientFailedNombre(){
        Assertions.assertThrows(Exception::class.java) {
            clientMocko.apply { nombre= " "}
            Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(clientMocko)
            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMocko)
            clienteService.update(clientMocko)
        }
    }
    @Test
    fun updateClientFailedCI(){
        Assertions.assertThrows(Exception::class.java) {
            clientMocko.apply { ci= " "}
            Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(clientMocko)
            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(clientMocko)
            clienteService.update(clientMocko)
        }
    }

    //DELETE

    @Test
    fun deleteClientCorrect(){
        Mockito.`when`(clienteRepository.findById(newObject.id)).thenReturn(returnObject)
        Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
        val response = clienteService.delete(newObject.id)
        Assertions.assertEquals(response, true)
    }

    @Test
    fun deleteClientIsFailed(){
        Assertions.assertThrows(Exception::class.java) {
            Mockito.`when`(clienteRepository.findById(newObject.id)).thenReturn(null)
            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
            val response = clienteService.delete(newObject.id)
            Assertions.assertEquals(response, true)
        }
    }
}

