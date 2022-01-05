package lj.controledepesoepresso.repository

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Peso

class pesoRepository (val context: Context) {

    val db =   ControleDatabase.getDatabase(context)


    fun salvarPeso(
        novoPeso: String,
        context:Context
    ) {
        if (novoPeso.isEmpty() || novoPeso.toDouble() == 0.0) {
            Toast.makeText(context, R.string.insiraPeso, Toast.LENGTH_SHORT).show()
        } else {
            runBlocking {
                val peso = Peso(peso = novoPeso.toDouble())
                db.pesoDAO().inserirPeso(peso)
                Toast.makeText(context,R.string.InfoPesoSalvo,Toast.LENGTH_SHORT).show()

            }
        }
    }

     fun pesoAtual() : Double {
        return runBlocking {
             db.pesoDAO().pesoMaisRecente()
                   }
     }

}