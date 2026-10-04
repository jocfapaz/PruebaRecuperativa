package service

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

import model.Estacionamiento
import model.Estado
import model.Vehiculo


class GestionEstacionamientos {
    val capacidad = 5
    val estacionamientos: MutableList<Estacionamiento> = MutableList(capacidad){
        i ->
        Estacionamiento(i + 1)
    }
    //este garantiza que solo una corrutina se ejecute a las vez: por el uso de delay y suspend
    private val mutex = Mutex()

    fun ver(){
        for(estacionamiento in estacionamientos){
            println("${estacionamiento.numero} - ${estacionamiento.vehiculo?.marca} ${estacionamiento.vehiculo?.patente} - ${estacionamiento.estado}")
        }
    }
    suspend fun registrarIngreso(vehiculo: Vehiculo){
        println("Intento de registrar ingreso del vehículo ${vehiculo.patente}")
        val estacionamientoLibre = mutex.withLock {
            val libre = estacionamientos.find { it.estado == Estado.Libre } ?: return
            libre.estado = Estado.Procesando
            libre
        }
        delay(1000)
        estacionamientoLibre.estado = Estado.Ocupado
        estacionamientoLibre.vehiculo = vehiculo

        println("Vehículo ${vehiculo.patente} registrado satisfactoriamente")
        ver()
    }
    suspend fun registrarSalida(numero: Int, minutos: Int){
        println("Intento de registrar salida estacionamiento número $numero")
        val estacionamientoQueSale = estacionamientos.find {it.numero == numero}
        if(estacionamientoQueSale == null){
            println("[ERROR] No existe un estacionamiento con este número.")
            return
        }
        if(estacionamientoQueSale.estado != Estado.Ocupado){
            println("[ERROR] El estacionamiento número $numero no está ocupado.")
            return
        }
        val vehiculoQueSale = estacionamientoQueSale.vehiculo
        estacionamientoQueSale.estado = Estado.Procesando
        delay(3000)
        val tarifa = vehiculoQueSale?.obtenerTarifa(minutos) ?: 0.0
        if(vehiculoQueSale != null){
            println("La tarifa por $minutos minutos de ${vehiculoQueSale.marca} ${vehiculoQueSale.patente} es de ${vehiculoQueSale.formatear(tarifa)}")
        }
        estacionamientoQueSale.vehiculo = null
        estacionamientoQueSale.estado = Estado.Libre
        println("Número ${numero} salió satisfactoriamente")
        ver()
    }
}
