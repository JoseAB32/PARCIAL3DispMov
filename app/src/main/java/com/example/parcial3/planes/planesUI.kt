package com.example.parcial3.planes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue

@OptIn
@Composable
fun PlanesScreen(viewModel: PlanesVM = hiltViewModel(), onSuccess: () -> Unit) {
    val cardAc by viewModel.CardAc.collectAsState()
    val indiceAc by viewModel.indiceAc.collectAsState()

    val salmon = Color(red = 102, green = 51, blue = 143, alpha = 255)
    val plomoOscuro = Color(red = 105, green = 105, blue = 105)
    val rojoOscuro = Color(red = 190, green = 0, blue = 0)
    val verde = Color(red = 113, green = 195, blue = 27)

    val context = LocalContext.current
    var indiceAnterior = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 40.dp, 20.dp, 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(0.dp, 15.dp, 0.dp, 20.dp),
            text = "Nuestros planes móviles",
            fontSize = 30.sp,
            color = salmon,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "La mejor cobertura, increíbles beneficios y un compromiso con el planeta.",
            fontSize = 20.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                border = BorderStroke(2.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text= cardAc.titulo,
                        fontSize = 30.sp,
                        color = salmon,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(20.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "Antes ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Text(
                            text = cardAc.costoAn,
                            modifier = Modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                                .drawWithContent {
                                    drawContent()
                                    val y = size.height / 2  // línea en la mitad del texto
                                    drawLine(
                                        color = rojoOscuro,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth = 6f
                                    )
                                },
                            fontSize = 30.sp,
                            color = plomoOscuro,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "/mes",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }

                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "Antes ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                        Text(
                            text = cardAc.costoAc,
                            modifier = Modifier
                                .padding(8.dp, 0.dp, 8.dp, 0.dp),
                            fontSize = 40.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "/mes",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }

                    Text(
                        text = cardAc.tamano,
                        fontSize = 25.sp,
                        color = plomoOscuro,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(Modifier.height(12.dp))

                    val caracteristicas = listOf(
                        "Llamadas y SMS ilimitados",
                        "Hotspot. Comparte tus datos",
                        "Redes Sociales ilimitadas incluidas",
                        "Arma tu plan con más apps ilimitadas",
                        "CO2 Negativo"
                    )

                    caracteristicas.forEach {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                .fillMaxWidth()
                        ) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.Black)
                            Spacer(Modifier.width(15.dp))
                            Text(
                                text = it,
                                textAlign = TextAlign.Left,
                                fontSize = 15.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .width(150.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(15.dp))

                    // Simulación de íconos sociales
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        repeat(6) {
                            Icon(Icons.Default.Favorite, contentDescription = "Icono Social", modifier = Modifier.padding(4.dp))
                        }
                    }

                    Spacer(Modifier.height(25.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        onClick = {
                            onSuccess()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = salmon, contentColor = Color.White)
                    ) {
                        Text("Quiero este plan", color = Color.White)
                    }
                }
            }

            FloatingActionButton(
                onClick = {
                    viewModel.enviarWhatsApp(context)
                },
                containerColor = verde,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(50.dp),
                shape = CircleShape,
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = Icons.Default.Call,
                    contentDescription = "WhatsApp",
                    tint = Color.White
                )
            }

            FloatingActionButton(
                onClick = {
                    viewModel.onPrevious()
                },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(40.dp),
                containerColor = salmon,
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Anterior",
                    tint = Color.White
                )
            }

            FloatingActionButton(
                onClick = {
                    viewModel.onNext()
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(40.dp),
                containerColor = salmon,
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Siguiente",
                    tint = Color.White
                )
            }
        }
    }
}