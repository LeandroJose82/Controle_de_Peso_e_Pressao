package lj.controledepesoepresso.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Peso

class PesoRepository (val context: Context) {
    private val db =   ControleDatabase.getDatabase(context)
    private val pesoLiveData = MutableLiveData<Double>()

    fun salvarPeso(
        novoPeso: String,
        context:Context
    ) : LiveData<Boolean>{

        val liveData = MutableLiveData<Boolean>()

        if (novoPeso.isEmpty() || novoPeso.toDouble() == 0.0) {
            Toast.makeText(context, R.string.insiraPeso, Toast.LENGTH_SHORT).show()
            liveData.value = false
        } else {
            runBlocking {
                val peso = Peso(peso = novoPeso.toDouble())
                db.pesoDAO().inserirPeso(peso)
                Toast.makeText(context,R.string.InfoPesoSalvo,Toast.LENGTH_SHORT).show()
                liveData.value = true
            }
        }
        return liveData
    }

    fun pesoAtual() : LiveData<Double> {
        return runBlocking {
            pesoLiveData.value = db.pesoDAO().pesoMaisRecente()
            pesoLiveData
        }
    }

}