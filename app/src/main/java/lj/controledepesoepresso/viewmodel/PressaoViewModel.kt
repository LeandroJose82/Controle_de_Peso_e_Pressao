package lj.controledepesoepresso.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lj.controledepesoepresso.repository.PressaoRepository

class PressaoViewModel(context: Context): ViewModel() {


    private val repository = PressaoRepository (context)

    fun salvarPressao (pressaoDiastolica:Int,pressaoSistolica: Int) : LiveData<Boolean> {
      return  repository.salvarPressao(pressaoDiastolica,pressaoSistolica)
    }

    fun pressaoDiastolicaAtual() : LiveData<Int> {
       return repository.pressaoDiastolicaAtual()
    }

    fun pressaoSistolicaAtual() : LiveData<Int> {
        return repository.pressaoSistolicaAtual()
    }

}