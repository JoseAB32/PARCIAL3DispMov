package com.example.parcial3.FormEnvioSim

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FormVM @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _ubicacion = MutableStateFlow<LatLng?>(null)
    val ubicacion : StateFlow<LatLng?> = _ubicacion

    fun obtenerLatLong(latLng: LatLng) {
        _ubicacion.value = latLng
    }

    fun enviarSMS(numeroReferencia: String) {
        val ubicacionActual = _ubicacion.value

        if (ubicacionActual == null || numeroReferencia.isBlank()) {
            Toast.makeText(context, "Faltan datos", Toast.LENGTH_SHORT).show()
            return
        }

        val latitud = ubicacionActual.latitude
        val longitud = ubicacionActual.longitude

        val mensaje = "Hola, solicito mi SIM. Ubicación: lat=$latitud, long=$longitud"

        // Crear intent para SMS
        val uri = Uri.parse("smsto:$numeroReferencia")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra("sms_body", mensaje)

        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No se pudo abrir la app de mensajes", Toast.LENGTH_SHORT).show()
        }
    }

}