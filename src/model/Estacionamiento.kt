package model
// Esta calse es para los datos principales de Estacionamiento
data class Estacionamiento (
    var numero: Int,
    var vehiculo: Vehiculo? = null,
    var estado: Estado = Estado.Libre
)
//Estado del estacionamiento:
sealed class Estado(){
    data object Libre : Estado()
    data object Ocupado : Estado()
    data object Procesando : Estado()
    data object FueraDeServicio : Estado()
}