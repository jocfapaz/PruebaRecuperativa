package model

abstract class Vehiculo (
    val patente: String,
    val fecha: String,
    val marca: String,
    val tipoCliente: TipoCliente
){
    abstract fun obtenerTarifa(minutos: Int)
}
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
): Vehiculo(patente, marca, fecha, tipoCliente)
{
    override fun obtenerTarifa(minutos: Int) {
        var total = 1500 * minutos /60.0
        var iva = 0.19
        if(tipoCliente == TipoCliente.Abonado){
            total *= 0.8
        }
        if(tipoCliente == TipoCliente.Discapacitado){
            iva = 0.19 / 2
        }
        total *= (1 + iva)
        println("La tarfia por $minutos de un particular es de $${String.format("%.2f", total)}")
    }
}
class Moto(
    patente: String,
    fecha: String,
    marca: String,
    tipoCliente: TipoCliente
    //herencia
): Vehiculo(patente, fecha, marca, tipoCliente){

    override fun obtenerTarifa(minutos: Int) {
        var total = 800 * minutos / 60.0
        var iva = 0.19
        if(minutos<15){
            total = 0.0
        }
        if(tipoCliente == TipoCliente.Discapacitado){
            iva = 0.19 / 2
        }
        total *= (1 + iva)
        println("La tarifa por $minutos minutos de una Moto es de $${String.format("%.2f", total)}")
    }
}

class Camioneta(
    patente: String,
    fecha: String,
    marca: String,
    val cargaPesada: Boolean,
    tipoCliente: TipoCliente
//herencia
): Vehiculo(patente, fecha, marca, tipoCliente){
    override fun obtenerTarifa(minutos: Int) {
        var total = 2500 * minutos / 60.0
        var iva = 0.19
        if(cargaPesada){
            total *= 1.3
        }
        if(tipoCliente == TipoCliente.Discapacitado){
            iva = 0.19 / 2
        }
        total *= (1 + iva)
        println("La tarifa por $minutos $minutos de una Moto es de ${String.format("%.2f", total)}")
    }
}
