package lj.controledepesoepresso.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lj.controledepesoepresso.repository.pressaoRepository

class pressaoViewModel(context: Context): ViewModel() {


    private val repository = pressaoRepository (context)

    fun salvarPressao (pressaoDiastolica:Int,pressaoSistolica: Int) {
        repository.salvarPressao(pressaoDiastolica,pressaoSistolica)
    }

    fun pressaoDiastolicaAtual() : LiveData<Int> {
       return repository.pressaoDiastolicaAtual()
    }

    fun pressaoSistolicaAtual() : LiveData<Int> {
        return repository.pressaoSistolicaAtual()
    }

}