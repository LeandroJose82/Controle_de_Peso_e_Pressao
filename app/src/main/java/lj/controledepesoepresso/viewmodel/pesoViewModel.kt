package lj.controledepesoepresso.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lj.controledepesoepresso.repository.pesoRepository


class pesoViewModel (context:Context) : ViewModel(){

    private val repository = pesoRepository (context)

    fun salvarPeso (peso:String) {
        repository.salvarPeso(peso,repository.context)
    }

    fun pesoAtual () : LiveData<Double> {
        return repository.pesoAtual()
    }



}