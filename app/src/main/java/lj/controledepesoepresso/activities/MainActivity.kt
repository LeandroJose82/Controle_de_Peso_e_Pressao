package lj.controledepesoepresso.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNovoPeso:Button = findViewById(R.id.btn_AdicionarNovoPeso)
        val textoPesoAtual : TextView = findViewById((R.id.pesoTelaPrincipal))

        val db =   ControleDatabase.getDatabase(this)

        runBlocking {
                val pesoRecente : String = db.controleDAO().pesoMaisRecente().toString()
            textoPesoAtual.text = "$pesoRecente KG"
        }



        btnNovoPeso.setOnClickListener {
            val intent =Intent (this, FormularioNovoPeso::class.java)
            startActivity(intent)
        }

    }

}

