package edu.gustavo.livedataexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DatosViewModel : ViewModel() {
    var datoObservable = MutableLiveData<Int>()
    private var cadena : String? = null
    init {
        setCadena("Dato inicial")
    }

    fun getCadena() : String {
        return cadena!!
    }
    fun setCadena(value:String) {
        cadena = value
        datoObservable.value = 1
    }

    override fun toString(): String {
        return "${cadena} ${datoObservable.value}"
    }

    fun up() {
        datoObservable.value = datoObservable.value!! + 1
    }
}