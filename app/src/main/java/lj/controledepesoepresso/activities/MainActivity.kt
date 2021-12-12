package lj.controledepesoepresso.activities

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import kotlinx.coroutines.runBlocking
import lj.controledepesoepresso.R
import lj.controledepesoepresso.database.ControleDatabase
import lj.controledepesoepresso.models.Peso
import lj.controledepesoepresso.models.Pressao

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textoPesoAtual : TextView = findViewById((R.id.pesoTelaPrincipal))
        val textoPressaoAtual : TextView = findViewById(R.id.pressaoTelaPrincipal)
        val sliderPeso : Slider = findViewById(R.id.sliderPeso)
        val btnSalvarPeso : Button = findViewById(R.id.salvarNovoPeso)
        val btnSalvarPressao : Button = findViewById(R.id.btnSalvarPressao)
        val db =   ControleDatabase.getDatabase(this)
        val pickerPressaoSistolica : NumberPicker = findViewById(R.id.pickerPressaoSistolica)
        val pickerPressaoDiastolica : NumberPicker = findViewById(R.id.pickerPressaoDiastolica)


        configuracaoNumberPickerPressao(pickerPressaoSistolica, pickerPressaoDiastolica)

        exibirPeso(db, textoPesoAtual)
        exibirPressao(db, textoPressaoAtual)

        btnSalvarPeso.setOnClickListener {
            salvarPeso(sliderPeso, db)
            exibirPeso(db, textoPesoAtual)
        }

        btnSalvarPressao.setOnClickListener {
            salvarPressao(pickerPressaoSistolica,pickerPressaoDiastolica,db)
            exibirPressao(db, textoPressaoAtual)
        }

    }

    private fun exibirPressao(
        db: ControleDatabase,
        textoPressaoAtual: TextView
    ) {
        runBlocking {
            val pressaoSistolicaRecente: String =
                db.pressaoDAO().pressaoSistolicaMaisRecente().toString()
            val pressaoDiastolicaRecente: String =
                db.pressaoDAO().pressaoDiastolicaMaisRecente().toString()

            textoPressaoAtual.text = getString(
                R.string.pressaoRecente,
                pressaoSistolicaRecente,
                pressaoDiastolicaRecente
            )
        }
    }


    private fun exibirPeso(
        db: ControleDatabase,
        textoPesoAtual: TextView
    ) {
        runBlocking {
            val pesoRecente: String = db.pesoDAO().pesoMaisRecente().toString()
            textoPesoAtual.text = getString(R.string.pesoRecente, pesoRecente)
        }
    }


    private fun configuracaoNumberPickerPressao(
        pickerPressaoSistolica: NumberPicker,
        pickerPressaoDiastolica: NumberPicker
    ) {
        pickerPressaoSistolica.minValue = 0
        pickerPressaoSistolica.maxValue = 300
        pickerPressaoSistolica.value = 120
        pickerPressaoDiastolica.minValue = 0
        pickerPressaoDiastolica.maxValue = 300
        pickerPressaoDiastolica.value = 80
    }

    private fun salvarPeso(
        sliderPeso: Slider,
        db: ControleDatabase
    ) {
        val verificarPeso = sliderPeso.value.toString()
        if (verificarPeso.isEmpty() || sliderPeso.value.toDouble() == 0.0) {
            Toast.makeText(this, R.string.insiraPeso, Toast.LENGTH_SHORT).show()
        } else {
            runBlocking {
                val novoPeso = Peso(peso = verificarPeso.toDouble())
                db.pesoDAO().inserirPeso(novoPeso)
                Toast.makeText(applicationContext,getString(R.string.InfoPesoSalvo),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun salvarPressao(
        pickerPressaoSistolica: NumberPicker,
        pickerPressaoDiastolica: NumberPicker,
        db: ControleDatabase
    ) {
        val novaPressaoSistolica = pickerPressaoSistolica.value
        val novaPressaoDiastolica = pickerPressaoDiastolica.value

            runBlocking {
                val novaPressao = Pressao(pressaoSistolica = novaPressaoSistolica, pressaoDiastolica = novaPressaoDiastolica)
                db.pressaoDAO().inserirPressao(novaPressao)
                Toast.makeText(applicationContext,getString(R.string.InfoPressaoSalva),Toast.LENGTH_SHORT).show()
            }
        }
    }






