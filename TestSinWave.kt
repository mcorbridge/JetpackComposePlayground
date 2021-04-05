package com.example.mycompose.sinWave

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


class TestSinWave {

    companion object {


        @Composable
        fun App() {

            val amplitude = remember { mutableStateOf(1.0) }
            val length = remember { mutableStateOf(0.0) }
            val geom = remember { mutableStateOf("SINE")}
            val freq = remember { mutableStateOf(0f)}

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(1f)
            ) {
                
                TrigWave(amplitude.value, length.value, freq.value, geom.value)

                Row{
                    Button(onClick = {
                        amplitude.value += 10.0
                    }){
                        Text("amplitude+")
                    }
                    Button(onClick = {
                        amplitude.value -= 10.0
                    }){
                        Text("amplitude-")
                    }
                }
                Row{
                    Button(onClick = {
                        length.value += 0.001
                    }){
                        Text("length-")
                    }
                    Button(onClick = {
                        length.value -= 0.001
                    }){
                        Text("length+")
                    }
                }
                Row{
                    Button(onClick = {
                        freq.value += 0.1f
                    }){
                        Text("frequency-")
                    }
                    Button(onClick = {
                        freq.value -= 0.1f
                    }){
                        Text("frequency+")
                    }
                }
                Row{
                    Button(onClick = {
                        var label = ""
                        if(geom.value == "SINE"){
                            label = "COSINE"
                            geom.value = "COSINE"
                        }else{
                            label = "SINE"
                            geom.value = "SINE"
                        }
                    }){
                        Text(geom.value)
                    }
                }
            }
        }

        @Composable
        fun TrigWave(chgAmplitude:Double, chgLength:Double, chgFreq:Float, geom:String) {

            val amplitude = remember { mutableStateOf(200.0) }
            val length = remember { mutableStateOf(0.02) }
            val waveColor = remember { mutableStateOf(Color.Black) }

            val t = remember { mutableStateOf(0f) }
            var waveMaster = WaveMaster()

            waveMaster.DoWave {
               t.value = it * (100 + chgFreq)
            }

            Canvas(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(250.dp)
                    .background(Color.White)
            ) {
                var path:Path
                if(geom == "COSINE"){
                    waveColor.value = Color.Red
                    path = drawCosineWave(size, (length.value + chgLength), (amplitude.value + chgAmplitude), t.value)
                }else{
                    waveColor.value = Color.Black
                    path = drawSineWave(size, (length.value + chgLength), (amplitude.value + chgAmplitude), t.value)
                }

                drawPath(
                    path = path,
                    color = waveColor.value,
                    style = Stroke(width = 10.dp.toPx())
                )
            }
        }

        fun drawSineWave(size: Size, length: Double, amplitude: Double, frequency: Float): Path {
            var x = 0.0
            var y: Double

            return Path().apply {

                moveTo(0f, size.height / 2)

                while (x < size.width) {
                    y = ((size.height / 2) + sin(x * length + frequency) * amplitude)
                    lineTo(x.toFloat(), y.toFloat())
                    x += 1
                }
            }
        }

        fun drawCosineWave(size: Size, length: Double, amplitude: Double, frequency: Float): Path {
            var x = 0.0
            var y: Double

            return Path().apply {

                moveTo(0f, size.height / 2)

                while (x < size.width) {
                    y = ((size.height / 2) + cos(x * length + frequency) * amplitude)
                    lineTo(x.toFloat(), y.toFloat())
                    x += 1
                }
            }
        }
    }// end companion object


} // end class


class WaveMaster {
    @Composable
    fun DoWave(callback: (arg: Float) -> Unit) {

        val infiniteTransition = rememberInfiniteTransition()

        var nextInt = remember { mutableStateOf(0)}

        val sineState = infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 60f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 5000,
                    easing = LinearEasing,
                    delayMillis = 0
                )
            )
        )

        if(sineState.value.toInt() == nextInt.value){
            callback(nextInt.value.toFloat())
            if(nextInt.value == 59){
                nextInt.value = 0
            }else{
                nextInt.value++
            }
        }
    }
}







