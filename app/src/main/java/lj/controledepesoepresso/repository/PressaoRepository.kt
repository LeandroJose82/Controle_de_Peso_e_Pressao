package lj.controledepesoepresso.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Pressao

class PressaoRepository (private val context: Context) {

    private val db =   ControleDatabase.getDatabase(context)

    private val pressaoSistolicaLivedata = MutableLiveData<Int>()
    private val pressaoDiastolicaLiveData = MutableLiveData<Int>()

    fun salvarPressao(
        novaPressaoSistolica: Int,
        novaPressaoDiastolica : Int
    ) : LiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()

        runBlocking {
            val novaPressao = Pressao(pressaoSistolica = novaPressaoSistolica , pressaoDiastolica = novaPressaoDiastolica)
            db.pressaoDAO().inserirPressao(novaPressao)
            Toast.makeText(context, R.string.InfoPressaoSalva, Toast.LENGTH_SHORT).show()
            liveData.value = true
        }
        return liveData
    }


     fun pressaoSistolicaAtual() : LiveData<Int> {
        return  runBlocking {
         pressaoSistolicaLivedata.value = db.pressaoDAO().pressaoSistolicaMaisRecente()
            pressaoSistolicaLivedata
        }
    }

    fun pressaoDiastolicaAtual() : LiveData<Int> {
        return  runBlocking {
           pressaoDiastolicaLiveData.value = db.pressaoDAO().pressaoDiastolicaMaisRecente()
            pressaoDiastolicaLiveData
        }
    }


}