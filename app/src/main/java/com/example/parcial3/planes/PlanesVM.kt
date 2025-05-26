package com.example.parcial3.planes

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.example.domain.Card
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class PlanesVM @Inject constructor(
    @ApplicationContext val context: Context
): ViewModel(){
    private val _Cards = listOf(
        Card("Plan FLEX 5", "$270", "$199", "5GB"),
        Card("Plan FLEX 8", "$370", "$299", "8GB"),
        Card("Plan FLEX 10", "$470", "$399", "10GB")
    )

    private val _indiceActual = MutableStateFlow<Int>(0)
    val indiceAc: StateFlow<Int> = _indiceActual

    val CardAc: StateFlow<Card> = _indiceActual
        .map { _Cards[it] }
        .stateIn(viewModelScope, SharingStarted.Eagerly, _Cards[0])

    fun onNext() {
        if (_indiceActual.value < _Cards.lastIndex) {
            _indiceActual.value++
        }
    }

    fun onPrevious() {
        if (_indiceActual.value > 0) {
            _indiceActual.value--
        }
    }

    fun enviarWhatsApp(context: Context) {
        val numero = "59170776593"
        val mensaje = "Hola, UCB mobile, preciso su ayuda"
        val url = "https://wa.me/$numero?text=${Uri.encode(mensaje)}"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No se encontró una app para abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
}