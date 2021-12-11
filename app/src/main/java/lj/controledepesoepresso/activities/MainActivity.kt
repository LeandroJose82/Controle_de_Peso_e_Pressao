package lj.controledepesoepresso.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Peso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textoPesoAtual : TextView = findViewById((R.id.pesoTelaPrincipal))
        val sliderPeso : Slider = findViewById(R.id.sliderPeso)
        val btnSalvarNovoPeso : Button = findViewById(R.id.salvarNovoPeso)
        val db =   ControleDatabase.getDatabase(this)

        exibirPesoMaisRecente(db, textoPesoAtual)

//        sliderPeso.addOnChangeListener { slider, value, fromUser ->
//            // Responds to when slider's value is changed
//            textoPesoAtual.text = "${sliderPeso.value} KG"
//        }

        btnSalvarNovoPeso.setOnClickListener {
            salvarPeso(sliderPeso, db)
            exibirPesoMaisRecente(db, textoPesoAtual)
        }

    }

    private fun salvarPeso(
        sliderPeso: Slider,
        db: ControleDatabase
    ) {
        val verificarPeso = sliderPeso.value.toString()
        if (verificarPeso.isEmpty()) {
            Toast.makeText(this, R.string.insiraPeso, Toast.LENGTH_SHORT).show()
        } else {
            runBlocking {
                val novoPeso = Peso(peso = verificarPeso.toDouble())
                db.controleDAO().inserirPeso(novoPeso)
            }
        }
    }

    private fun exibirPesoMaisRecente(
        db: ControleDatabase,
        textoPesoAtual: TextView
    ) {
        runBlocking {
            val pesoRecente: String = db.controleDAO().pesoMaisRecente().toString()
            textoPesoAtual.text = "$pesoRecente KG"
        }
    }

}

