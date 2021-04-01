package com.example.mycompose.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mycompose.R


/**
 * Keywords: Jetpack, Compose, Animation, Start, Stop, infiniteTransition, animateFloat
 *
 * I have seen comments regarding the newest (will it last) Jetpack Compose Animation API, wondering
 * how to Stop / Start an animation.
 *
 * Here is one solution.  Maybe not what the Compose engineers had envisioned, and I would be very
 * interested if a better one exists.
 *
 * Usage: Just create an activity and drop 'SpinningMan.DoTheSpin()' into it.  Clicking on a gear
 * will stop it.  Clicking on it again will start it.  Have fun!!
 *
 * I have included ic_cogwheel.xml for use in this demo.  
 */

// TODO when any one spinner is stopped, the rest will stop
class SpinningMan {

    companion object {

        @Composable
        fun DoTheSpin() {

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {

                //spinner one
                Spinner(
                    maxWidth = maxWidth,
                    maxHeight = maxHeight,
                    offsetPair = Pair((-47).dp, (-143).dp)
                )

                //spinner two
                Spinner(
                    maxWidth = maxWidth,
                    maxHeight = maxHeight,
                    spinnerDirection = SpinDirection.CounterClockwise.degree,
                    offsetPair = Pair((-30).dp, (-30).dp)
                )

                //spinner three
                Spinner(
                    maxWidth = maxWidth,
                    maxHeight = maxHeight,
                    offsetPair = Pair(50.dp, 50.dp)
                )

            }
        }
    }
}

@Composable
fun Spinner(
    maxWidth: Dp,
    maxHeight: Dp,
    spinnerDirection: Int = 360,
    offsetPair: Pair<Dp, Dp> = Pair(0.dp, 0.dp)
) {
    val spinnerSize = 150.dp
    val resource: Painter = painterResource(id = R.drawable.ic_cogwheel)
    val isSpinning = remember { mutableStateOf(true) }
    val spinz = remember { mutableStateOf(0f) }
    val initialValue = remember { mutableStateOf(0f) }
    val isStopStart = remember { mutableStateOf(false) }
    val anotherSpinMaster = SpinMaster()
    val spinDirection = remember { mutableStateOf(360) }

    if (spinnerDirection != SpinDirection.Clockwise.degree) {
        spinDirection.value = SpinDirection.CounterClockwise.degree
    }

    if (spinz.value >= 0.99f) {
        initialValue.value = 0f
    }

    if (isSpinning.value) {
        anotherSpinMaster.DoSpin(isStopStart.value, initialValue.value) {
            spinz.value = it
        }
        isStopStart.value = false
    } else {
        initialValue.value = spinz.value
    }

    Image(
        modifier = Modifier
            .width(spinnerSize)
            .height(spinnerSize)
            .offset(
                x = ((maxWidth - spinnerSize) / 2) + offsetPair.first,
                y = (((maxHeight - spinnerSize) - (maxHeight - spinnerSize) / 2)) + offsetPair.second,
            )
            .rotate(spinz.value * spinDirection.value)
            .clickable {
                isSpinning.value = !isSpinning.value
                isStopStart.value = true
            },
        painter = resource,
        contentDescription = "A Spinner"
    )
}

class SpinMaster {
    @Composable
    fun DoSpin(stopStart: Boolean, initialValue: Float, callback: (arg: Float) -> Unit) {

        val durationMillis = remember { mutableStateOf(10000) }

        val infiniteTransition = rememberInfiniteTransition()

        if (stopStart) {
            durationMillis.value = (durationMillis.value * (1 - initialValue)).toInt()
        } else {
            if (initialValue == 0f) {
                durationMillis.value = 10000
            }
        }

        val spinState = infiniteTransition.animateFloat(
            initialValue = initialValue,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = durationMillis.value,
                    easing = LinearEasing,
                    delayMillis = 0
                )
            )
        )
        callback(spinState.value)
    }
}

enum class SpinDirection(val degree: Int) {
    Clockwise(360),
    CounterClockwise(-360)
}
