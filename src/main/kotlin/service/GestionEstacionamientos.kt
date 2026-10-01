package service
import kotlinx.coroutines.delay

import model.Estacionamiento
import model.Estado
import model.Vehiculo


class GestionEstacionamientos {
    val capacidad = 5
    val estacion = MutableList(capacidad){
        i ->
        Estacionamiento(i + 1)
    }
    fun ver(){
        for(estacionamiento in estacionamiento){
            println("${estacionamiento.numero} - ${estacionamiento.vehiculo?.marca} ${estacionamiento.vehiculo?.patente} - ${estacionamiento.estado}")
        }
    }
    suspend fun registrarIngreso(vehiculo: Vehiculo){
        println("Intento de registrar ingreso del vehículo ${vehiculo.patente}")
        var estacionamientoLibre = estacionamiento.find {
            it.estado == Estado.Libre
        }
        if (estacionamientoLibre == null){
            println("[ERROR] No hay estacionamientos disponibles.")
            return
        }
        estacionamientoLibre.estado = Estado.Procesando
        delay(1000)
        estacionamientoLibre.estado = Estado.Ocupado
        estacionamientoLibre.vehiculo = vehiculo

        println("Vehículo ${vehiculo.patente} registrado satisfactoriamente")
        ver()
    }
    suspend fun registrarSalida(numero: Int, minutos: Int){
        println("Intento de registrar salida estacionamiento número $numero")
        var estacionamientoQueSale = estacionamientos.find {it.numero == numero}
        if(estacionamientoQueSale == null){
            println("[ERROR] No existe un estacionamiento con este número.")
            return
        }
        estacionamientoQueSale.estado = Estado.Procesando
        delay(3000)
        estacionamientoQueSale.estado = Estado.Libre
        estacionamientoQueSale.vehiculo?.obtenerTarifa(minutos)
        estacionamientoQueSale.vehiculo = null
        println("Número ${numero} salió satisfactoriamente")
        ver()
    }
}