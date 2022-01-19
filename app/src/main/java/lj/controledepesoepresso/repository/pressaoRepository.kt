package lj.controledepesoepresso.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Pressao

class pressaoRepository (val context: Context) {

    val db =   ControleDatabase.getDatabase(context)

    private val pressaoSistolicaLivedata = MutableLiveData<Int>()
    private val pressaoDiastolicaLiveData = MutableLiveData<Int>()

    fun salvarPressao(
        novaPressaoSistolica: Int,
        novaPressaoDiastolica : Int
    ) {
        runBlocking {
            val novaPressao = Pressao(pressaoSistolica = novaPressaoSistolica , pressaoDiastolica = novaPressaoDiastolica)
            db.pressaoDAO().inserirPressao(novaPressao)
            Toast.makeText(context, R.string.InfoPressaoSalva, Toast.LENGTH_SHORT).show()
        }
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