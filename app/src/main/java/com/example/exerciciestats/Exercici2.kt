package com.example.exerciciestats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Year
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntrenadorPersonal(modifier: Modifier = Modifier) {
    var nom by rememberSaveable { mutableStateOf("") }
    var anyNeixement by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var pes by rememberSaveable { mutableStateOf("") }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var enableButton by rememberSaveable { mutableStateOf(false) }
    var edat by rememberSaveable { mutableStateOf(0) }
    var imc by rememberSaveable { mutableStateOf(0.0) }
    var valoracio by rememberSaveable { mutableStateOf("") }

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("TRAINER 3001", fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
        Spacer(Modifier.height(30.dp))
        TextField(
            value = nom,
            onValueChange = {
                nom = it
                enableButton = habilitarBoton2(nom, anyNeixement, altura, pes)
            },
            label = { Text("Nom: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        TextField(
            value = anyNeixement,
            onValueChange = {
                anyNeixement = it
                enableButton = habilitarBoton2(nom, anyNeixement, altura, pes)
            },
            label = { Text("Any de naixement: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = altura,
            onValueChange = {
                altura = it
                enableButton = habilitarBoton2(nom, anyNeixement, altura, pes)
            },
            label = { Text("Alçada: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = pes,
            onValueChange = {
                pes = it
                enableButton = habilitarBoton2(nom, anyNeixement, altura, pes)
            },
            label = { Text("Pes: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(50.dp))
        Button(onClick = {
            showResult = true
            edat = java.time.LocalDate.now().year - anyNeixement.toInt()
            imc = pes.toDouble() / (Math.pow(altura.toDouble(),2.0))

            if (imc < 18.5) valoracio = "INSUFICIENT"
            else if (imc > 18.5 && imc < 24.9) valoracio = "NORMAL"
            else valoracio = "SOBREPES"

        }, enabled = enableButton) {
            Text("Calcular preu total")
        }
        if (showResult) {
            Text("NOM: $nom", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text("EDAT: $edat", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text("IMC: $imc", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Text("$valoracio", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

fun habilitarBoton2(nom: String, anyNaixement: String, altura: String, pes: String): Boolean {
    var resultat = false
    if (nom.isNotEmpty() && anyNaixement.isNotEmpty() && altura.isNotEmpty() && pes.isNotEmpty()) {
        resultat = true
    }

    return resultat
}