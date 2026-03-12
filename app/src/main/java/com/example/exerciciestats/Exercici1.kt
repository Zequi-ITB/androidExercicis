package com.example.exerciciestats

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


@Composable
fun CalculadoraPropina(modifier: Modifier = Modifier) {
    var preuMenu by rememberSaveable { mutableStateOf("") }
    var propina by rememberSaveable { mutableStateOf("") }
    var preuTotal by rememberSaveable { mutableStateOf(0.0) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var enableButton by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = preuMenu,
            onValueChange = {
                preuMenu = it
                enableButton = habilitarBoton(preuMenu, propina)
            },
            label = { Text("Preu del menu") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = propina,
            onValueChange = {
                propina = it
                enableButton = habilitarBoton(preuMenu, propina)
            },
            label = { Text("Propina") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(50.dp))
        Button(onClick = {
            showResult = true
            preuTotal = preuMenu.toDouble() + (preuMenu.toDouble() * propina.toDouble())

        }, enabled = enableButton) {
            Text("Calcular preu total")
        }
        if (showResult) {
            Text("Preu total: $preuTotal", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

fun habilitarBoton(preuMenu: String, propina: String): Boolean {
    var resultat = false
    if (preuMenu.isNotEmpty() && propina.isNotEmpty()) {
        resultat = true
    }

    return resultat
}