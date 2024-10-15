package com.lucia.simondice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlin.random.Random
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
                simonDice()
            }
        }
    }
}

fun generarSecuencia(): List<Int> {
    val secuenciaRandomizada = mutableListOf<Int>()
    for (i in 0..4) {
        secuenciaRandomizada.add(Random.nextInt(4) + 1)
    }
    return secuenciaRandomizada
}

@Composable
fun simonDice() {
    var secuenciaJugador = remember { mutableStateListOf<Int>() }
    var botonActual by remember { mutableStateOf("") }
    val record = remember { mutableStateOf(Record()) }
    val contexto = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

        Text(
            text = "Record: ${record.value.numRondas}",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.ROJO.num)
                    botonActual = Colors.ROJO.nom
                    Log.d("BotonColorClick",Colors.ROJO.nom)
                    record.value.numRondas +=1},
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.AZUL.num)
                    botonActual = Colors.AZUL.nom
                    Log.d("BotonColorClick",Colors.AZUL.nom)
                    record.value.numRondas +=1},
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
                    botonActual = Colors.VERDE.nom
                    Log.d("BotonColorClick",Colors.VERDE.nom)
                    record.value.numRondas +=1},
                colors = ButtonDefaults.buttonColors(Color.Green),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
            Button(
                onClick = {
                    secuenciaJugador.add(Colors.AMARILLO.num)
                    botonActual = Colors.AMARILLO.nom
                    Log.d("BotonColorClick",Colors.AMARILLO.nom)
                    record.value.numRondas +=1},
                colors = ButtonDefaults.buttonColors(Color.Yellow),
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(3f)
                    .padding(5.dp)
            ) {}
        }

        Text(
            text = "Boton: ${secuenciaJugador.lastOrNull()?.toString() ?: " "} $botonActual",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally))

        TextButton(
            onClick = {
                val secuencia = generarSecuencia()
                Log.d("BotonCrearClick",secuencia.toString())
                val toast = Toast.makeText(contexto, secuencia.toString(),Toast.LENGTH_LONG)
                toast.show()
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)) {
            Text(text = "Comenzar")
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