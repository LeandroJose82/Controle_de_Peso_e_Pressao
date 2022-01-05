package lj.controledepesoepresso.repository

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Pressao

class pressaoRepository (val context: Context) {

    val db =   ControleDatabase.getDatabase(context)

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


     fun pressaoSistolicaAtual() : Int {
        return  runBlocking {
          db.pressaoDAO().pressaoSistolicaMaisRecente()
        }
    }

    fun pressaoDiastolicaAtual() : Int {
        return  runBlocking {
            db.pressaoDAO().pressaoDiastolicaMaisRecente()
        }
    }


}