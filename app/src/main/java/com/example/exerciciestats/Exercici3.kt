package com.example.exerciciestats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NumeroSecret(modifier: Modifier = Modifier) {
    var numeroUsuari by rememberSaveable { mutableStateOf("") }
    var numeroSecreto by rememberSaveable { mutableIntStateOf((Math.random() * 100).roundToInt()) }
    var resultat by rememberSaveable { mutableStateOf("") }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var enableButton by rememberSaveable { mutableStateOf(false) }
    var intents by rememberSaveable { mutableIntStateOf(0) }
    var trobat by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            """
            NUMERO SECRETO 
                ENTRE 1 y 100""".trimIndent(), fontWeight = FontWeight.SemiBold, fontSize = 30.sp
        )
        Spacer(Modifier.height(30.dp))
        TextField(
            value = numeroUsuari,
            onValueChange = {
                numeroUsuari = it
                enableButton = habilitarBoton3(numeroUsuari)
            },
            label = { Text("Numero: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(20.dp))
        if (!trobat) {
            MyButton("Intentar", enableButton) {
                showResult = true
                if (numeroUsuari.toInt() < numeroSecreto) {
                    resultat = "El número que busques és més gran"
                    intents++
                } else if (numeroUsuari.toInt() > numeroSecreto) {
                    resultat =
                        "El número que busques és més petit"
                    intents++
                } else if (numeroUsuari.toInt() == numeroSecreto.toInt()) {
                    resultat = "Has encertat!"
                    trobat = true
                }
            }
        } else {
            MyButton("Tornar a jugar", true) {
                intents = 0
                trobat = false
                numeroSecreto = (Math.random() * 100).roundToInt()
            }
        }
        if (showResult) {
            Text("$resultat", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            Text("Intents : $intents")

        }
    }
}

@Composable
fun MyButton(text: String, enable: Boolean,accio: () -> Unit) {
    Button(onClick = accio, enabled = enable) {
        Text(text)
    }
}

fun habilitarBoton3(num: String): Boolean {
    var resultat = false
    if (num.isNotEmpty()) {
        resultat = true
    }

    return resultat
}