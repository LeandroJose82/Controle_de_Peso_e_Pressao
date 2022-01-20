package lj.controledepesoepresso.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lj.controledepesoepresso.repository.PesoRepository


class PesoViewModel (context:Context) : ViewModel(){

    private val repository = PesoRepository (context)

    fun salvarPeso (peso:String) : LiveData<Boolean> {
        return repository.salvarPeso(peso,repository.context)
    }

    fun pesoAtual () : LiveData<Double> {
        return repository.pesoAtual()
    }

}