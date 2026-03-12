package com.example.exerciciestats

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.exerciciestats.ui.theme.ExerciciEstatsTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var modifier = Modifier.background(Color.LightGray)
            ExerciciEstatsTheme {
                Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
                    //CalculadoraPropina(Modifier.padding(innerPadding))
                    //EntrenadorPersonal(Modifier.padding(innerPadding))
                    //NumeroSecret(modifier.padding(innerPadding))
                    Calculadora(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExerciciEstatsTheme {
        Greeting("Android")
    }
}