package lj.controledepesoepresso.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.slider.Slider
import lj.controledepesoepresso.R
import lj.controledepesoepresso.viewmodel.PesoViewModel

class PesoFragment : Fragment()  {

    val pesoViewModel by lazy {
        context?.let {
            PesoViewModel(it)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_peso,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textoPesoAtual: TextView = view.findViewById((R.id.pesoTelaPrincipal))
        val sliderPeso: Slider = view.findViewById(R.id.sliderPeso)
        val btnSalvarPeso: Button = view.findViewById(R.id.salvarNovoPeso)


        val pesoAtual = pesoViewModel?.pesoAtual()?.value?.toString()

        if (pesoAtual != null) {
            sliderPeso.value = pesoAtual.toFloat()
        }
        textoPesoAtual.text = getString(R.string.pesoRecente, pesoAtual)

        btnSalvarPeso.setOnClickListener {
            val pesoNovo = sliderPeso.value.toString()
            pesoViewModel?.salvarPeso(pesoNovo)
            textoPesoAtual.text = getString(R.string.pesoRecente, pesoNovo)

        }
    }
}