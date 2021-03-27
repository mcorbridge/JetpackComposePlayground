package com.example.mycompose.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mycompose.R

/**
 * This is an extension of the demo created by Mitch 'Coding With Mitch'
 * where he illustrated (beautifully, I might add) how to use Jetpack Compose animation.
 * I figured I would try to prove I understood what was going on by adding some
 * additional function.  For example: would it be possible to stop the Rocket in flight and start
 * it again at the point it last stopped?
 * Probably.
 * It took a little effort to achieve the solution, and here it is.
 * Once again - this based on the original demo created by Mitch.  Please visit his channel and
 * support him as he creates great lessons.
 * (https://www.youtube.com/watch?v=hLERtWC1THw&t=159s&ab_channel=CodingWithMitch)
 *
 */

class RocketMan {

    companion object {

        @Composable
        fun LaunchRocket() {

            val animationState = remember { mutableStateOf(false) }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black)
            ) {
                Rocket(
                    isRocketEnabled = animationState.value,
                    maxWidth = maxWidth,
                    maxHeight = maxHeight
                )
                LaunchButton(
                    animationState = animationState.value,
                    onToggleAnimationState = {
                        animationState.value = !animationState.value
                    }
                )
            }
        }

        @Composable
        fun Rocket(
            isRocketEnabled: Boolean,
            maxWidth: Dp,
            maxHeight: Dp
        ) {
            val resource: Painter
            val modifier: Modifier
            val rocketSize = 200.dp
            val xPosInitialValue = remember { mutableStateOf(0f)}
            val posX = remember { mutableStateOf(0f)}
            val durMillis = remember { mutableStateOf(2000) }

            if (!isRocketEnabled) {
                xPosInitialValue.value =  posX.value

                // what's happening here
                // well... if the Rocket is stopped during its ascent, and then re-launched -
                // the remaining duration will be a fraction of the original 2000 ms - based on how
                // far the Rocket has progressed.  Make sense?
                if(!posX.value.equals(0f)){
                    durMillis.value = (2000 * (1 - posX.value)).toInt()
                }

                resource = painterResource(id = R.drawable.rocket_intial)
                modifier = Modifier.offset(
                    x = (maxWidth - rocketSize) * posX.value,
                    y = (maxHeight - rocketSize) - (maxHeight - rocketSize) * posX.value,
                )
            } else {

                val infiniteTransition = rememberInfiniteTransition()

                // *********************** the Rocket 'flame' animation *********************
                val engineState = infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 500,
                            easing = LinearEasing
                        )
                    )
                )
                // create the 'flame' illusion
                resource = if (engineState.value <= 0.5f) {
                    painterResource(id = R.drawable.rocket1)
                } else {
                    painterResource(id = R.drawable.rocket2)
                }

                // *********************** the Rocket movement (ascent) animation *********************
                val xPositionState = infiniteTransition.animateFloat(
                    initialValue = xPosInitialValue.value,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = durMillis.value,
                            easing = LinearEasing
                        )
                    )
                )
                // What is this?
                // So... whether the Rocket has been stopped during its progress, or not, the initialValue must be set back to
                // 0f when the Rocket finally reaches its apogee.
                if(xPositionState.value >= 0.99){
                    xPosInitialValue.value = 0f
                    durMillis.value = 2000
                }

                modifier = Modifier.offset(
                    x = (maxWidth - rocketSize) * xPositionState.value,
                    y = (maxHeight - rocketSize) - (maxHeight - rocketSize) * xPositionState.value,
                )

                // keep track of the Rocket progress so, if it is stopped en route, it knows where to continue
                posX.value = xPositionState.value

            }



            Image(
                modifier = modifier
                    .width(rocketSize)
                    .height(rocketSize),
                painter = resource,
                contentDescription = "A Rocket",
            )
        } // end Rocket


        @Composable
        fun LaunchButton(
            animationState: Boolean,
            onToggleAnimationState: () -> Unit,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (animationState) {
                    Button(
                        onClick = onToggleAnimationState,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red,
                            contentColor = Color.White
                        )
                    ) {
                        Text("STOP")
                    }
                } else {
                    Button(
                        onClick = onToggleAnimationState,
                    ) {
                        Text("LAUNCH")
                    }
                }
            }
        } // end LaunchButton
    } //end companion object

} // end class