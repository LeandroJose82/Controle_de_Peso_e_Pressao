package lj.controledepesoepresso.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lj.controledepesoepresso.repository.pesoRepository


class pesoViewModel (context:Context) : ViewModel(){

    private val repository = pesoRepository (context)

    private val pesoLiveData : LiveData <Double> = TODO()


    fun salvarPeso (peso:String) {
        repository.salvarPeso(peso,repository.context)
    }

    fun pesoAtual () : Double {
        return repository.pesoAtual()
    }

}