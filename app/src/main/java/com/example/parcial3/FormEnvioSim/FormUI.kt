package com.example.parcial3.FormEnvioSim

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
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
    val accentPurple = Color(0xFF4E3A87)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "¿Dónde enviaremos tu SIM?", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = accentPurple)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var numeroRef by remember { mutableStateOf("") }

                val ubicacion by FormVM.ubicacion.collectAsState()
                val latitud = ubicacion?.latitude?.toString() ?: ""
                val longitud = ubicacion?.longitude?.toString() ?: ""

                Text(
                    text = "Ingrese los datos correspondientes",
                    fontSize = 22.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    value = numeroRef,
                    onValueChange = { numeroRef = it },
                    label = { Text("Número de referencia") },
                    shape = RoundedCornerShape(12.dp)
                )

                val marker = LatLng(-17.3895, -66.1568)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(marker, 15f)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(vertical = 10.dp)
                        .nestedScroll(rememberNestedScrollInteropConnection()) // evita que al mover el mapa se mueva el scroll
                ) {
                    GoogleMap(
                        modifier = Modifier.matchParentSize(),
                        cameraPositionState = cameraPositionState,
                        onMapClick = { latLng ->
                            FormVM.obtenerLatLong(latLng)
                        }
                    ) {
                        ubicacion?.let {
                            Marker(position = it)
                        }
                    }
                }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    value = latitud,
                    onValueChange = {},
                    label = { Text("Latitud") },
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    value = longitud,
                    onValueChange = {},
                    label = { Text("Longitud") },
                    readOnly = true,
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedButton(
                    onClick = { FormVM.enviarSMS(numeroRef) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = accentPurple,
                        contentColor = Color.White
                    )
                ) {
                    Text("Enviar")
                }
            }
        }
    )
}

