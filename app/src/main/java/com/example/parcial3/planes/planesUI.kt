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
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanesScreen(viewModel: PlanesVM = hiltViewModel(), onSuccess: () -> Unit) {
    val cardAc by viewModel.CardAc.collectAsState()
    val indiceAc by viewModel.indiceAc.collectAsState()

    val accentPurple = Color(0xFF4E3A87)
    val grayDark = Color(0xFF696969)
    val redDark = Color(0xFFB00000)
    val greenAccent = Color(0xFF71C31B)

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = "Nuestros planes móviles",
            fontSize = 28.sp,
            color = accentPurple,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "La mejor cobertura, increíbles beneficios y un compromiso con el planeta.",
            fontSize = 18.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Card(
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                border = BorderStroke(2.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = cardAc.titulo,
                        fontSize = 28.sp,
                        color = accentPurple,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("Antes ", color = Color.Gray)

                        Text(
                            text = cardAc.costoAn,
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .drawWithContent {
                                    drawContent()
                                    drawLine(
                                        color = redDark,
                                        start = Offset(0f, size.height / 2),
                                        end = Offset(size.width, size.height / 2),
                                        strokeWidth = 4f
                                    )
                                },
                            fontSize = 26.sp,
                            color = grayDark,
                            fontWeight = FontWeight.Bold
                        )

                        Text("/mes", color = Color.Gray)
                    }

                    Row(verticalAlignment = Alignment.Bottom) {
                        Text("Ahora ", color = Color.Black)

                        Text(
                            text = cardAc.costoAc,
                            modifier = Modifier.padding(horizontal = 6.dp),
                            fontSize = 34.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text("/mes", color = Color.Black)
                    }

                    Text(
                        text = cardAc.tamano,
                        fontSize = 20.sp,
                        color = grayDark,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(14.dp))

                    val caracteristicas = listOf(
                        "Llamadas y SMS ilimitados",
                        "Hotspot. Comparte tus datos",
                        "Redes Sociales ilimitadas incluidas",
                        "Arma tu plan con más apps ilimitadas",
                        "CO2 Negativo"
                    )

                    caracteristicas.forEach {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 3.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = accentPurple)
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = it,
                                fontSize = 15.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }

                    Spacer(Modifier.height(18.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        repeat(6) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = null,
                                tint = accentPurple,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(25.dp))

                    Button(
                        onClick = onSuccess,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = accentPurple,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Quiero este plan")
                    }
                }
            }

            FloatingActionButton(
                onClick = { viewModel.enviarWhatsApp(context) },
                containerColor = greenAccent,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(52.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "WhatsApp",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            FloatingActionButton(
                onClick = { viewModel.onPrevious() },
                containerColor = accentPurple,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(44.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Anterior",
                    tint = Color.White
                )
            }

            FloatingActionButton(
                onClick = { viewModel.onNext() },
                containerColor = accentPurple,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(44.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Siguiente",
                    tint = Color.White
                )
            }
        }
    }
}
