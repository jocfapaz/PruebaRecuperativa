import service.GestionEstacionamientos
import kotlinx.coroutines.coroutineScope
import model.Camioneta
import model.Moto
import model.Particular
import model.TipoCliente

suspend fun main() = coroutineScope {

    val gestor = GestionEstacionamientos()

    val auto = Particular("SGAK-14","FIAT","14-09-26", TipoCliente.Regular)
    val moto = Moto("ERAF-07", "CHERY", "10-08-26", TipoCliente.Abonado)
    val camioneta = Camioneta("PQWJ-55","21-08-26","TOYOTA", true, TipoCliente.Discapacitado)

    try {
        gestor.registrarIngreso(auto)
        gestor.registrarIngreso(moto)
        gestor.registrarSalida(2, 20)
        gestor.registrarIngreso(camioneta)
        gestor.registrarSalida(2, 20)
    }
    catch (ex: Exception) {
        println("ERROR: ${ex.message}")
    }
}