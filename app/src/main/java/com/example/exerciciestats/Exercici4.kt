package com.example.exerciciestats


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Calculadora(modifier: Modifier = Modifier) {
    var operand1 by rememberSaveable { mutableStateOf("") }
    var operand2 by rememberSaveable { mutableStateOf("") }
    var resultat by rememberSaveable { mutableDoubleStateOf(0.0) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    var enableButton by rememberSaveable { mutableStateOf(false) }
    var operacio by rememberSaveable { mutableStateOf("") }

    Column(
        modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "CALCULADORA".trimIndent(), fontWeight = FontWeight.SemiBold, fontSize = 30.sp
        )
        Spacer(Modifier.height(30.dp))
        TextField(
            value = operand1,
            onValueChange = {
                operand1 = it
                enableButton = habilitarBoton3(operand1, operand2)
            },
            label = { Text("Operand: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = operand2,
            onValueChange = {
                operand2 = it
                enableButton = habilitarBoton3(operand1, operand2)
            },
            label = { Text("Operand: ") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        MyDropDownMenu(operacio) {
            operacio = it
        }
        Button(onClick = {
            when (operacio) {
                "SUMA" -> resultat = operand1.toDouble() + operand2.toDouble()
                "RESTA" -> resultat = operand1.toDouble() - operand2.toDouble()
                "MULTIPLICACIO" -> resultat = operand1.toDouble() * operand2.toDouble()
                "DIVISIO" -> {
                    if (operand2.toDouble() != 0.0) {
                        resultat = operand1.toDouble() / operand2.toDouble()
                    }
                    else Toast.makeText(LocalContext,"ERROR", Toast.LENGTH_SHORT)

                }
            }
            showResult = true
        }, enabled = enableButton) { Text("CALCULAR") }

        Spacer(Modifier.height(20.dp))


        if (showResult) {
            Text("$resultat", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}


fun habilitarBoton3(num: String, num2: String): Boolean {
    var resultat = false
    if (num.isNotEmpty() && num2.isNotEmpty()) {
        resultat = true
    }

    return resultat
}

@Composable
fun MyDropDownMenu(operacio: String, onValueChange: (String) -> Unit) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    val operacions = listOf("SUMA", "RESTA", "MULTIPLICACIO", "DIVISIO")

    Column(Modifier) {
        OutlinedTextField(
            value = operacio,
            onValueChange = { onValueChange(it) },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
        ) {
            operacions.forEach { seleccio ->
                DropdownMenuItem(text = { Text(text = seleccio) }, onClick = {
                    expanded = false
                    onValueChange(seleccio)
                })
            }
        }

    }
}
