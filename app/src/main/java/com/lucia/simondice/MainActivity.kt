package com.lucia.simondice

import android.os.Bundle
import android.util.Log
import kotlin.random.Random
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucia.simondice.ui.theme.Record
import com.lucia.simondice.ui.theme.SimonDiceTheme

class MainActivity : ComponentActivity() {

    private val secuencia = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimonDiceTheme {
            }
        }
    }
}

fun ronda() {

}

@Composable
fun simonDice() {
    var secuenciaJugador = remember { mutableStateListOf<Int>() }
    val record = Record(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = secuenciaJugador.lastOrNull()?.toString() ?: " ")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.ROJO.num)
                    Log.d("BotonClick",Colors.ROJO.nom)
                    record},
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.AZUL.num)
                    Log.d("BotonClick",Colors.AZUL.nom)},
                colors = ButtonDefaults.buttonColors(Color.Blue),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.VERDE.num)
                    Log.d("BotonClick",Colors.VERDE.nom)},
                colors = ButtonDefaults.buttonColors(Color.Green),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.AMARILLO.num)
                    Log.d("BotonClick",Colors.AMARILLO.nom)},
                colors = ButtonDefaults.buttonColors(Color.Yellow),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceTheme {
        simonDice()
    }
}