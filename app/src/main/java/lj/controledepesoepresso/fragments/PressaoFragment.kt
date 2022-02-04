package lj.controledepesoepresso.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import lj.controledepesoepresso.R
import lj.controledepesoepresso.viewmodel.PressaoViewModel

class PressaoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pressao,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pressaoViewModel by lazy {
          context?.let {
              PressaoViewModel(it)
          }
        }


        val textoPressaoAtual: TextView = view.findViewById(R.id.pressaoTelaPrincipal)
        val btnSalvarPressao: Button = view.findViewById(R.id.btnSalvarPressao)
        val pickerPressaoSistolica: NumberPicker = view.findViewById(R.id.pickerPressaoSistolica)
        val pickerPressaoDiastolica: NumberPicker = view.findViewById(R.id.pickerPressaoDiastolica)
        configuracaoNumberPickerPressao(pickerPressaoSistolica, pickerPressaoDiastolica)


        val pressaoSistolicaAtual = pressaoViewModel?.pressaoSistolicaAtual()?.value.toString()
        val pressaoDiastolicaAtual = pressaoViewModel?.pressaoDiastolicaAtual()?.value.toString()



        pickerPressaoSistolica.value = pressaoSistolicaAtual.toInt()
        pickerPressaoDiastolica.value = pressaoDiastolicaAtual.toInt()



        textoPressaoAtual.text = getString(
            R.string.pressaoRecente,
            pressaoSistolicaAtual,
            pressaoDiastolicaAtual
        )



        btnSalvarPressao.setOnClickListener {
            val novaPressaoSistolica = pickerPressaoSistolica.value
            val novaPressaoDiastolica = pickerPressaoDiastolica.value
            pressaoViewModel?.salvarPressao(novaPressaoSistolica, novaPressaoDiastolica)
            textoPressaoAtual.text = getString(
                R.string.pressaoRecente,
                novaPressaoSistolica.toString(),
                novaPressaoDiastolica.toString()
            )
        }

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

