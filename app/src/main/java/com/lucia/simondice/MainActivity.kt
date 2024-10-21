package com.lucia.simondice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlin.random.Random
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

// ToDo: Comenta con docs
// ToDo: Separar codigo en clases
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

/**
 * Gernera de manera randomizada una secuencia de 5 numeros correspondientes con colores de Colors (1-4)
 * @return Lista con la secuencia de numeros generados
 */
fun generarSecuencia(): List<Int> {
    val secuenciaRandomizada = mutableListOf<Int>()
    for (i in 0..4) {
        secuenciaRandomizada.add(Random.nextInt(4) + 1)
    }
    return secuenciaRandomizada
}

/**
 * Crea un Toast y un Logcat apartir de la secuencia dada
 * @param secuencia Secuencia que se va a mostrar
 * @param contexto ?¿?
 */
fun mostrarToast(secuencia : List<Int>, contexto : Context) {
    Log.d("BotonCrearClick",secuencia.toString())
    val toast = Toast.makeText(contexto, secuencia.toString(),Toast.LENGTH_LONG)
    toast.show()
}

fun compararSecuencias(secuenciaJugador: List<Int>, secuenciaJuego: MutableList<Int>) {
// ToDo: metodo para comparar secuencias
}

@Composable
fun simonDice() { // ToDo: Mover variables

    var secuenciaJugador = remember { mutableStateListOf<Int>() }
    var botonActual by remember { mutableStateOf("") }
    val record = remember { mutableStateOf(Record()) }
    val contexto = LocalContext.current
    var secuenciaGenerada by remember { mutableStateOf(emptyList<Int>()) }

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
            crearBotonColor(
                color = Colors.ROJO,
                secuenciaJugador = secuenciaJugador,
                record = record,
                onClick = { color -> botonActual = color.nom }
            )
            crearBotonColor(
                color = Colors.AZUL,
                secuenciaJugador = secuenciaJugador,
                record = record,
                onClick = { color -> botonActual = color.nom }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            crearBotonColor(
                color = Colors.VERDE,
                secuenciaJugador = secuenciaJugador,
                record = record,
                onClick = { color -> botonActual = color.nom }
            )
            crearBotonColor(
                color = Colors.AMARILLO,
                secuenciaJugador = secuenciaJugador,
                record = record,
                onClick = { color -> botonActual = color.nom }
            )

        }

        Text(
            text = "Boton: ${secuenciaJugador.lastOrNull()?.toString() ?: " "} - $botonActual",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally))

        TextButton(
            onClick = {
                secuenciaGenerada = generarSecuencia()
                mostrarToast(secuenciaGenerada,contexto);
            },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)) {
            Text(text = "Comenzar")
        }
    }
}

/**
 * Crea un boton apartir de su color correspondiente
 * @param color
 * @param secuenciaJugador
 * @param record
 * @param
 */
@Composable
fun crearBotonColor(color: Colors,secuenciaJugador: MutableList<Int>,record: MutableState<Record>,onClick: (Colors) -> Unit) {
    Button(
        onClick = {
            secuenciaJugador.add(color.num)
            onClick(color)
            record.value.numRondas += 1
            Log.d("BotonColorClick", color.nom)
        },
        colors = ButtonDefaults.buttonColors(
            when (color) {
                Colors.ROJO -> Color.Red
                Colors.VERDE -> Color.Green
                Colors.AMARILLO -> Color.Yellow
                Colors.AZUL -> Color.Blue
            }
        ),
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(5.dp)
    ) {}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimonDiceTheme {
        simonDice()
    }
}