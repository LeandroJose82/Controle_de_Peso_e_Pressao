package lj.controledepesoepresso.activities

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider
import lj.controledepesoepresso.R
import lj.controledepesoepresso.viewmodel.PesoViewModel
import lj.controledepesoepresso.viewmodel.PressaoViewModel

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textoPesoAtual: TextView = findViewById((R.id.pesoTelaPrincipal))
        val textoPressaoAtual: TextView = findViewById(R.id.pressaoTelaPrincipal)
        val sliderPeso: Slider = findViewById(R.id.sliderPeso)
        val btnSalvarPeso: Button = findViewById(R.id.salvarNovoPeso)
        val btnSalvarPressao: Button = findViewById(R.id.btnSalvarPressao)
        val pickerPressaoSistolica: NumberPicker = findViewById(R.id.pickerPressaoSistolica)
        val pickerPressaoDiastolica: NumberPicker = findViewById(R.id.pickerPressaoDiastolica)


        val pressaoViewModel = PressaoViewModel(this)
        val pesoViewModel = PesoViewModel(this)

        configuracaoNumberPickerPressao(pickerPressaoSistolica, pickerPressaoDiastolica)


        val pressaoSistolicaAtual = pressaoViewModel.pressaoSistolicaAtual().value.toString()
        val pressaoDiastolicaAtual = pressaoViewModel.pressaoDiastolicaAtual().value.toString()
        val pesoAtual = pesoViewModel.pesoAtual().value.toString()



        sliderPeso.value = pesoAtual.toFloat()
        pickerPressaoSistolica.value = pressaoSistolicaAtual.toInt()
        pickerPressaoDiastolica.value = pressaoDiastolicaAtual.toInt()


        textoPesoAtual.text = getString(R.string.pesoRecente, pesoAtual)
        textoPressaoAtual.text = getString(
            R.string.pressaoRecente,
            pressaoSistolicaAtual,
            pressaoDiastolicaAtual
        )

        btnSalvarPeso.setOnClickListener {
            val pesoNovo = sliderPeso.value.toString()
            pesoViewModel.salvarPeso(pesoNovo)
            textoPesoAtual.text = getString(R.string.pesoRecente, pesoNovo)

        }

        btnSalvarPressao.setOnClickListener {
            val novaPressaoSistolica = pickerPressaoSistolica.value
            val novaPressaoDiastolica = pickerPressaoDiastolica.value
            pressaoViewModel.salvarPressao(novaPressaoSistolica, novaPressaoDiastolica)
            textoPressaoAtual.text = getString(
                R.string.pressaoRecente,
                novaPressaoSistolica.toString(),
                novaPressaoDiastolica.toString()
            )
        }

    }


    private fun configuracaoNumberPickerPressao(
        pickerPressaoSistolica: NumberPicker,
        pickerPressaoDiastolica: NumberPicker
    ) {
        pickerPressaoSistolica.minValue = 0
        pickerPressaoSistolica.maxValue = 300
        pickerPressaoDiastolica.minValue = 0
        pickerPressaoDiastolica.maxValue = 300
        pickerPressaoSistolica.value = 120
        pickerPressaoDiastolica.value = 80
    }


}






