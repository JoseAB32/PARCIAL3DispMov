package com.example.parcial3.FormEnvioSim

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEnvioSimScreen(FormVM: FormVM = hiltViewModel(), onBackPressed: () -> Unit) {
    val scrollState = rememberScrollState()
    val salmon = Color(red = 79, green = 36, blue = 119, alpha = 255)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Donde enviaremos tu SIM")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackPressed,
                        content = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )

                }
            )
        },
        content = {
                paddingValues ->  Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 2.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var numeroRef by remember { mutableStateOf("") }

            val ubicacion by FormVM.ubicacion.collectAsState()

            val latitud = ubicacion?.latitude?.toString() ?: ""
            val longitud = ubicacion?.longitude?.toString() ?: ""

            Column(modifier = Modifier.padding(15.dp, 5.dp, 15.dp, 5.dp)) {
                Text(
                    modifier = Modifier
                        .padding(0.dp, 5.dp, 0.dp, 10.dp),
                    text = "Ingrese los datos correspondientes",
                    fontSize = 20.sp
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = numeroRef,
                    onValueChange = {
                        numeroRef = it
                    },
                    label = {
                        Text(
                            text = "Número de referencia"
                        )
                    }
                )

                val marker = LatLng(-17.3895, -66.1568)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(marker, 15f)
                }
                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(0.dp, 10.dp, 0.dp, 10.dp),
                    cameraPositionState = cameraPositionState,
                    onMapClick = { latLng ->
                        FormVM.obtenerLatLong(latLng)
                    }
                ) {
                    ubicacion?.let {
                        Marker(position = it)
                    }
                }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = latitud,
                    onValueChange = {
//                        fecRec = it
                    },
                    label = {
                        Text(
                            text = "Latitud"
                        )
                    },
                    readOnly = true
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = longitud,
                    onValueChange = {
//                        fecRec = it
                    },
                    label = {
                        Text(
                            text = "Longitud"
                        )
                    },
                    readOnly = true
                )
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        FormVM.enviarSMS(numeroRef)
                    }
                ) {
                    Text(
                        text = "Enviar"
                    )
                }
            }
        }
        }
    )
}