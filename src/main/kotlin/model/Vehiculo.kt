package model

import java.util.Locale

//creo la clase padre para las herencias
abstract class Vehiculo (
    val patente: String,
    val fecha: String,
    val marca: String,
    val tipoCliente: TipoCliente
){
    abstract fun obtenerTarifa(minutos: Int): Double

    fun formatear(monto: Double): String = String.format(Locale.US, "%.2f", monto)
}
//Sealed class por los tipos de cliente
sealed class TipoCliente(){
    object Regular : TipoCliente()
    object Abonado : TipoCliente()
    object Discapacitado : TipoCliente()
}
class Particular(
    patente: String,
    marca: String,
    fecha: String,
    tipoCliente: TipoCliente
    //herencia;
): Vehiculo(patente, fecha, marca, tipoCliente)
{
    //funcion para obtener tarifa mediante iva
    override fun obtenerTarifa(minutos: Int): Double {
        var total = 1500 * minutos / 60.0
        var iva = 0.19
        if(tipoCliente == TipoCliente.Abonado){
            total *= 0.8
        }
        if(tipoCliente == TipoCliente.Discapacitado){
            iva = 0.19 / 2
        }
        total *= (1 + iva)
        return total
    }
}
//clase hija:
class Moto(
    patente: String,
    fecha: String,
    marca: String,
    tipoCliente: TipoCliente
    //herencia
): Vehiculo(patente, fecha, marca, tipoCliente){
    //funcion para obtener tarifa mediante iva
    override fun obtenerTarifa(minutos: Int): Double {
        var total = 800 * minutos / 60.0
        var iva = 0.19
        if(minutos < 15){
            total = 0.0
        }
        if(tipoCliente == TipoCliente.Discapacitado){
            iva = 0.19 / 2
        }
        total *= (1 + iva)
        return total
    }
}
//clase hija
class Camioneta(
    patente: String,
    fecha: String,
    marca: String,
    val cargaPesada: Boolean,
    tipoCliente: TipoCliente
//herencia
): Vehiculo(patente, fecha, marca, tipoCliente){
    //funcion para obtener tarifa mediante iva
    override fun obtenerTarifa(minutos: Int): Double {
        var total = 2500 * minutos / 60.0
        var iva = 0.19
        if(cargaPesada){
            total *= 1.3
        }
        if(tipoCliente == TipoCliente.Discapacitado){
            iva = 0.19 / 2
        }
        total *= (1 + iva)
        return total
    }
}
