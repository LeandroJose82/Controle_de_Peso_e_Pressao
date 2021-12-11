package lj.controledepesoepresso.activities

import android.content.Intent
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

class FormularioNovoPeso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_novo_peso)

        val btnHome: Button = findViewById(R.id.retornarHome)
        val btnSalvarNovoPeso: Button = findViewById(R.id.salvarNovoPeso)
        val sliderPeso: Slider = findViewById(R.id.sliderPeso)
        val txtResuldadoPeso: TextView = findViewById(R.id.resultadoPeso)

        val db = ControleDatabase.getDatabase(this)

        sliderPeso.addOnChangeListener { slider, value, fromUser ->
            // Responds to when slider's value is changed
            txtResuldadoPeso.text = sliderPeso.value.toString()
        }

        btnSalvarNovoPeso.setOnClickListener {
            val verificarPeso = sliderPeso.value.toString()
            if (verificarPeso.isEmpty()) {
                Toast.makeText(this, R.string.insiraPeso, Toast.LENGTH_SHORT).show()
            } else {
                runBlocking {
                    val novoPeso = Peso(peso = verificarPeso.toDouble())
                    db.controleDAO().inserirPeso(novoPeso)
                }
                retornarHome()
            }
        }

        btnHome.setOnClickListener {
            retornarHome()
        }


    }
    private fun retornarHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}