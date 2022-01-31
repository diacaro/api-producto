package com.dennis.tienda.Service

import com.dennis.tienda.model.Client
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
class ClienteServiceEVA {

    @InjectMocks
    lateinit var clienteService: ClienteService

    @Mock
    lateinit var clienteRepository: ClienteService

    val returnObject: Client = Client().apply {
        id= 9
        cliente="Jose"
    }
    val newObject: Client = Client().apply {
        id= 9
        cliente="Jose"
    }

    val jsonString = File("./src/test/resources/Client/crearCliente.json").readText(Charsets.UTF_8)
    val categoriasMock = Gson().fromJson(jsonString, Client::class.java)

    @Test
    fun updateClienteIsIdCorrect(){

        Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
        val response = clienteService.save(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.cliente, newObject.cliente)
    }

    @Test
    fun updateClienteIsIdNotExist() {
        Assertions.assertThrows(Exception::class.java){
            Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(null)
            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
            clienteService.update(newObject)
        }
    }

    @Test
    fun updateClienteIsListCorrect(){
        Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(returnObject)
        Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(returnObject)
        val response = clienteService.update(newObject)
        Assertions.assertEquals(response.id, newObject.id)
        Assertions.assertEquals(response.cliente, newObject.cliente)
    }

    @Test
    fun updateClienteIsListIncorrect(){
        Assertions.assertThrows(Exception::class.java) {
            clienteMock.apply { cliente= " "}
            Mockito.`when`(clienteRepository.findById(returnObject.id)).thenReturn(clienteMock)
            Mockito.`when`(clienteRepository.save(Mockito.any(Client::class.java))).thenReturn(clienteMock)
            clienteService.update(clienteMock)
        }
    }
}
