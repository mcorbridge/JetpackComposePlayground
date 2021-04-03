package com.example.mycompose.dragdrop

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class TestDragDrop {

    companion object {

        @Composable
        fun DoDragDrop() {

            var fooColor = Color(red = 0f, green = 0f, blue = 1f, alpha = 0.4f)
            var redColor = Color(red = 1f, green = 0f, blue = 0f, alpha = 1f)
            var blueColor = Color(red = 0f, green = 0f, blue = 1f, alpha = 1f)
            var blackColor = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f)
            var yellowColor = Color(0xFFFEFE33)

            var landingColor0 = remember { mutableStateOf(fooColor) }
            var landingColor1 = remember { mutableStateOf(fooColor) }
            var landingColor2 = remember { mutableStateOf(fooColor) }
            var landingColor3 = remember { mutableStateOf(fooColor) }

            val dragDropTotal = remember { mutableStateOf(0)}

            val cryptoSeed = remember { mutableStateOf("") }
            
            


            fun whoMoved(dropData: DropData) {

                dragDropTotal.value++

                when (dropData.position) {
                    "TOP_LEFT" -> {
                        //println("${ dropData.color } | ${ dropData.position } | ${ dropData.isDropped } | ${ dropData.num }")
                        cryptoSeed.value += "${dropData.color}${dropData.position}${dropData.num}"
                        when (dropData.color) {
                            "black" -> landingColor0.value = blackColor
                            "red" -> landingColor0.value = redColor
                            "blue" -> landingColor0.value = blueColor
                            "yellow" -> landingColor0.value = yellowColor
                        }
                    }
                    "TOP_RIGHT" -> {
                        //println("${ dropData.color } | ${ dropData.position } | ${ dropData.isDropped } | ${ dropData.num }")
                        cryptoSeed.value += "${dropData.color}${dropData.position}${dropData.num}"
                        when (dropData.color) {
                            "black" -> landingColor1.value = blackColor
                            "red" -> landingColor1.value = redColor
                            "blue" -> landingColor1.value = blueColor
                            "yellow" -> landingColor1.value = yellowColor
                        }
                    }
                    "BOTTOM_LEFT" -> {
                        //println("${ dropData.color } | ${ dropData.position } | ${ dropData.isDropped } | ${ dropData.num }")
                        cryptoSeed.value += "${dropData.color}${dropData.position}${dropData.num}"
                        when (dropData.color) {
                            "black" -> landingColor2.value = blackColor
                            "red" -> landingColor2.value = redColor
                            "blue" -> landingColor2.value = blueColor
                            "yellow" -> landingColor2.value = yellowColor
                        }
                    }
                    "BOTTOM_RIGHT" -> {
                        //println("${ dropData.color } | ${ dropData.position } | ${ dropData.isDropped } | ${ dropData.num }")
                        cryptoSeed.value += "${dropData.color}${dropData.position}${dropData.num}"
                        when (dropData.color) {
                            "black" -> landingColor3.value = blackColor
                            "red" -> landingColor3.value = redColor
                            "blue" -> landingColor3.value = blueColor
                            "yellow" -> landingColor3.value = yellowColor
                        }
                    }
                }
            }

            //target matrix
            var offsetXt = remember { mutableStateOf(50.dp) }
            var offsetYt = remember { mutableStateOf(350.dp) }

            //BlackBoxes
            var offsetX0_0 = remember { mutableStateOf(0f) }
            var offsetY0_0 = remember { mutableStateOf(0f) }

            var offsetX0_1 = remember { mutableStateOf(0f) }
            var offsetY0_1 = remember { mutableStateOf(0f) }

            var offsetX0_2 = remember { mutableStateOf(0f) }
            var offsetY0_2 = remember { mutableStateOf(0f) }

            var offsetX0_3 = remember { mutableStateOf(0f) }
            var offsetY0_3 = remember { mutableStateOf(0f) }

            //RedBoxes
            var offsetX1_0 = remember { mutableStateOf(0f) }
            var offsetY1_0 = remember { mutableStateOf(0f) }

            var offsetX1_1 = remember { mutableStateOf(0f) }
            var offsetY1_1 = remember { mutableStateOf(0f) }

            var offsetX1_2 = remember { mutableStateOf(0f) }
            var offsetY1_2 = remember { mutableStateOf(0f) }

            var offsetX1_3 = remember { mutableStateOf(0f) }
            var offsetY1_3 = remember { mutableStateOf(0f) }

            //BlueBoxes
            var offsetX2_0 = remember { mutableStateOf(0f) }
            var offsetY2_0 = remember { mutableStateOf(0f) }

            var offsetX2_1 = remember { mutableStateOf(0f) }
            var offsetY2_1 = remember { mutableStateOf(0f) }

            var offsetX2_2 = remember { mutableStateOf(0f) }
            var offsetY2_2 = remember { mutableStateOf(0f) }

            var offsetX2_3 = remember { mutableStateOf(0f) }
            var offsetY2_3 = remember { mutableStateOf(0f) }

            //YellowBoxes
            var offsetX3_0 = remember { mutableStateOf(0f) }
            var offsetY3_0 = remember { mutableStateOf(0f) }

            var offsetX3_1 = remember { mutableStateOf(0f) }
            var offsetY3_1 = remember { mutableStateOf(0f) }

            var offsetX3_2 = remember { mutableStateOf(0f) }
            var offsetY3_2 = remember { mutableStateOf(0f) }

            var offsetX3_3 = remember { mutableStateOf(0f) }
            var offsetY3_3 = remember { mutableStateOf(0f) }

            fun doReset(){
                dragDropTotal.value = 0
                cryptoSeed.value = ""
                landingColor0.value = fooColor
                landingColor1.value = fooColor
                landingColor2.value = fooColor
                landingColor3.value = fooColor

                offsetX0_0.value =  0f
                offsetY0_0.value =  0f

                offsetX0_1.value =  0f
                offsetY0_1.value =  0f

                offsetX0_2.value =  0f
                offsetY0_2.value =  0f

                offsetX0_3.value =  0f
                offsetY0_3.value =  0f

                //RedBoxes
                offsetX1_0.value =  0f
                offsetY1_0.value =  0f

                offsetX1_1.value =  0f
                offsetY1_1.value =  0f

                offsetX1_2.value =  0f
                offsetY1_2.value =  0f

                offsetX1_3.value =  0f
                offsetY1_3.value =  0f

                //BlueBoxes
                offsetX2_0.value =  0f
                offsetY2_0.value =  0f

                offsetX2_1.value =  0f
                offsetY2_1.value =  0f

                offsetX2_2.value =  0f
                offsetY2_2.value =  0f

                offsetX2_3.value =  0f
                offsetY2_3.value =  0f

                //YellowBoxes
                offsetX3_0.value =  0f
                offsetY3_0.value =  0f

                offsetX3_1.value =  0f
                offsetY3_1.value =  0f

                offsetX3_2.value =  0f
                offsetY3_2.value =  0f

                offsetX3_3.value =  0f
                offsetY3_3.value =  0f
            }

            @Composable
            fun BlackBox(num: Int, offsets: Pair<MutableState<Float>, MutableState<Float>>) {
                Box(
                    Modifier
                        .offset {
                            IntOffset(
                                offsets.first.value.roundToInt(),
                                offsets.second.value.roundToInt()
                            )
                        }
                        .background(blackColor)
                        .size(100.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(onDragStart = {
                                println("BlackBox drag started [x=${offsets.first.value}] [y=${offsets.second.value}]")
                            },
                                onDragEnd = {
                                    //println("BlackBox drag ended [x=${offsetX0.value}] [y=${offsetY0.value}]")
                                    val findDropTarget = FindDropTarget.blackDropTarget(
                                        offsets.first.value,
                                        offsets.second.value,
                                        num
                                    )
                                    if (findDropTarget.isDropped) {
                                        whoMoved(findDropTarget)
                                        offsets.first.value = 2000f
                                        offsets.second.value = 2000f
                                    } else {
                                        offsets.first.value = 0.0f
                                        offsets.second.value = 0.0f
                                    }
                                },
                                onDragCancel = {
                                    println("drag canceled")
                                },
                                onDrag = { pointerInputChange: PointerInputChange, offset: Offset ->
                                    if(dragDropTotal.value != 4){
                                        pointerInputChange.consumeAllChanges()
                                        offsets.first.value += (pointerInputChange.position.x) - 75
                                        offsets.second.value += (pointerInputChange.position.y) - 75
                                    }

                                })
                        }
                )
            }

            @Composable
            fun RedBox(num: Int, offsets: Pair<MutableState<Float>, MutableState<Float>>) {
                Box(
                    Modifier
                        .offset {
                            IntOffset(
                                offsets.first.value.roundToInt(),
                                offsets.second.value.roundToInt()
                            )
                        }
                        .background(redColor)
                        .size(100.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(onDragStart = {
                                println("RedBox drag started [x=${offsets.first.value}] [y=${offsets.second.value}]")
                            },
                                onDragEnd = {
                                    val findDropTarget =
                                        FindDropTarget.redDropTarget(
                                            offsets.first.value,
                                            offsets.second.value,
                                            num
                                        )
                                    if (findDropTarget.isDropped) {
                                        whoMoved(findDropTarget)
                                        offsets.first.value = 2000f
                                        offsets.second.value = 2000f
                                    } else {
                                        offsets.first.value = 0.0f
                                        offsets.second.value = 0.0f
                                    }
                                },
                                onDragCancel = {
                                    println("drag canceled")
                                },
                                onDrag = { pointerInputChange: PointerInputChange, offset: Offset ->
                                    if(dragDropTotal.value != 4) {
                                        pointerInputChange.consumeAllChanges()
                                        offsets.first.value += (pointerInputChange.position.x) - 75
                                        offsets.second.value += (pointerInputChange.position.y) - 75
                                    }
                                })
                        }
                )
            }

            @Composable
            fun BlueBox(num: Int, offsets: Pair<MutableState<Float>, MutableState<Float>>) {
                Box(
                    Modifier
                        .offset {
                            IntOffset(
                                offsets.first.value.roundToInt(),
                                offsets.second.value.roundToInt()
                            )
                        }
                        .background(blueColor)
                        .size(100.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(onDragStart = {
                                println("BlueBox drag started [x=${offsets.first.value}] [y=${offsets.second.value}]")
                            },
                                onDragEnd = {
                                    val findDropTarget = FindDropTarget.blueDropTarget(
                                        offsets.first.value,
                                        offsets.second.value,
                                        num
                                    )
                                    if (findDropTarget.isDropped) {
                                        whoMoved(findDropTarget)
                                        offsets.first.value = 2000f
                                        offsets.second.value = 2000f
                                    } else {
                                        offsets.first.value = 0.0f
                                        offsets.second.value = 0.0f
                                    }
                                },
                                onDragCancel = {
                                    println("drag canceled")
                                },
                                onDrag = { pointerInputChange: PointerInputChange, offset: Offset ->
                                    if(dragDropTotal.value != 4) {
                                        pointerInputChange.consumeAllChanges()
                                        offsets.first.value += (pointerInputChange.position.x) - 75
                                        offsets.second.value += (pointerInputChange.position.y) - 75
                                    }
                                })
                        }
                )
            }

            @Composable
            fun YellowBox(num: Int, offsets: Pair<MutableState<Float>, MutableState<Float>>) {
                Box(
                    Modifier
                        .offset {
                            IntOffset(
                                offsets.first.value.roundToInt(),
                                offsets.second.value.roundToInt()
                            )
                        }
                        .background(yellowColor)
                        .size(100.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(onDragStart = {
                                println("YellowBox drag started [x=${offsets.first.value}] [y=${offsets.second.value}]")
                            },
                                onDragEnd = {
                                    val findDropTarget = FindDropTarget.yellowDropTarget(
                                        offsets.first.value,
                                        offsets.second.value,
                                        num
                                    )
                                    if (findDropTarget.isDropped) {
                                        whoMoved(findDropTarget)
                                        offsets.first.value = 2000f
                                        offsets.second.value = 2000f
                                    } else {
                                        offsets.first.value = 0.0f
                                        offsets.second.value = 0.0f
                                    }
                                },
                                onDragCancel = {
                                    println("drag canceled")
                                },
                                onDrag = { pointerInputChange: PointerInputChange, offset: Offset ->
                                    if(dragDropTotal.value != 4) {
                                        pointerInputChange.consumeAllChanges()
                                        offsets.first.value += (pointerInputChange.position.x) - 75
                                        offsets.second.value += (pointerInputChange.position.y) - 75
                                    }
                                })
                        }
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.offset(offsetXt.value, offsetYt.value)) {

                    Row() {
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .height(110.dp)
                                .width(110.dp)
                                .border(2.dp, color = Color.Black)
                                .background(landingColor0.value)
                        )
                        {
                            //Text("TOP_LEFT")
                        }
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .height(110.dp)
                                .width(110.dp)
                                .border(2.dp, color = Color.Black)
                                .background(landingColor1.value)
                        )
                        {
                            //Text("TOP_RIGHT")
                        }
                    }

                    Row() {
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .height(110.dp)
                                .width(110.dp)
                                .border(2.dp, color = Color.Black)
                                .background(landingColor2.value)
                        )
                        {
                            //Text("BOTTOM_LEFT")
                        }
                        Box(
                            modifier = Modifier
                                .padding(6.dp)
                                .height(110.dp)
                                .width(110.dp)
                                .border(2.dp, color = Color.Black)
                                .background(landingColor3.value)
                        )
                        {
                            //Text("BOTTOM_RIGHT")
                        }
                    }
                }

                Column ( modifier = Modifier.offset{IntOffset(0, 400) }){
                    Text("${cryptoSeed.value}",)
                    Button(onClick = { doReset() }){
                        Text("Reset")
                    }
                }


                Row {
                    Box {
                        BlackBox(3, Pair(offsetX0_3, offsetY0_3))
                        BlackBox(2, Pair(offsetX0_2, offsetY0_2))
                        BlackBox(1, Pair(offsetX0_1, offsetY0_1))
                        BlackBox(0, Pair(offsetX0_0, offsetY0_0))
                    }
                    Box {
                        RedBox(3, Pair(offsetX1_3, offsetY1_3))
                        RedBox(2, Pair(offsetX1_2, offsetY1_2))
                        RedBox(1, Pair(offsetX1_1, offsetY1_1))
                        RedBox(0, Pair(offsetX1_0, offsetY1_0))
                    }
                    Box {
                        BlueBox(3, Pair(offsetX2_3, offsetY2_3))
                        BlueBox(2, Pair(offsetX2_2, offsetY2_2))
                        BlueBox(1, Pair(offsetX2_1, offsetY2_1))
                        BlueBox(0, Pair(offsetX2_0, offsetY2_0))
                    }
                    Box {
                        YellowBox(3, Pair(offsetX3_3, offsetY3_3))
                        YellowBox(2, Pair(offsetX3_2, offsetY3_2))
                        YellowBox(1, Pair(offsetX3_1, offsetY3_1))
                        YellowBox(0, Pair(offsetX3_0, offsetY3_0))
                    }
                }

            }

        }


    } // end companion object
} // end class

class FindDropTarget {

    companion object {

        fun blackDropTarget(offsetX: Float, offsetY: Float, num: Int): DropData {

            var isDropTarget = false
            val color = "black"
            var position = ""

            if (
                (offsetX > BlackBoxTarget.TOP_LEFT.landingArea.first.first && offsetY > BlackBoxTarget.TOP_LEFT.landingArea.first.second)
                &&
                (offsetX < BlackBoxTarget.TOP_LEFT.landingArea.second.first && offsetY < BlackBoxTarget.TOP_LEFT.landingArea.second.second)
            ) {
                position = "TOP_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > BlackBoxTarget.TOP_RIGHT.landingArea.first.first && offsetY > BlackBoxTarget.TOP_RIGHT.landingArea.first.second)
                &&
                (offsetX < BlackBoxTarget.TOP_RIGHT.landingArea.second.first && offsetY < BlackBoxTarget.TOP_RIGHT.landingArea.second.second)
            ) {
                position = "TOP_RIGHT"
                isDropTarget = true
            } else if (
                (offsetX > BlackBoxTarget.BOTTOM_LEFT.landingArea.first.first && offsetY > BlackBoxTarget.BOTTOM_LEFT.landingArea.first.second)
                &&
                (offsetX < BlackBoxTarget.BOTTOM_LEFT.landingArea.second.first && offsetY < BlackBoxTarget.BOTTOM_LEFT.landingArea.second.second)
            ) {
                position = "BOTTOM_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > BlackBoxTarget.BOTTOM_RIGHT.landingArea.first.first && offsetY > BlackBoxTarget.BOTTOM_RIGHT.landingArea.first.second)
                &&
                (offsetX < BlackBoxTarget.BOTTOM_RIGHT.landingArea.second.first && offsetY < BlackBoxTarget.BOTTOM_RIGHT.landingArea.second.second)
            ) {
                position = "BOTTOM_RIGHT"
                isDropTarget = true
            }

            return DropData(position, color, isDropTarget, num)
        }

        fun redDropTarget(offsetX: Float, offsetY: Float, num: Int): DropData {

            var isDropTarget = false
            val color = "red"
            var position = ""

            if (
                (offsetX > RedBoxTarget.TOP_LEFT.landingArea.first.first && offsetY > RedBoxTarget.TOP_LEFT.landingArea.first.second)
                &&
                (offsetX < RedBoxTarget.TOP_LEFT.landingArea.second.first && offsetY < RedBoxTarget.TOP_LEFT.landingArea.second.second)
            ) {
                position = "TOP_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > RedBoxTarget.TOP_RIGHT.landingArea.first.first && offsetY > RedBoxTarget.TOP_RIGHT.landingArea.first.second)
                &&
                (offsetX < RedBoxTarget.TOP_RIGHT.landingArea.second.first && offsetY < RedBoxTarget.TOP_RIGHT.landingArea.second.second)
            ) {
                position = "TOP_RIGHT"
                isDropTarget = true
            } else if (
                (offsetX > RedBoxTarget.BOTTOM_LEFT.landingArea.first.first && offsetY > RedBoxTarget.BOTTOM_LEFT.landingArea.first.second)
                &&
                (offsetX < RedBoxTarget.BOTTOM_LEFT.landingArea.second.first && offsetY < RedBoxTarget.BOTTOM_LEFT.landingArea.second.second)
            ) {
                position = "BOTTOM_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > RedBoxTarget.BOTTOM_RIGHT.landingArea.first.first && offsetY > RedBoxTarget.BOTTOM_RIGHT.landingArea.first.second)
                &&
                (offsetX < RedBoxTarget.BOTTOM_RIGHT.landingArea.second.first && offsetY < RedBoxTarget.BOTTOM_RIGHT.landingArea.second.second)
            ) {
                position = "BOTTOM_RIGHT"
                isDropTarget = true
            }

            return DropData(position, color, isDropTarget, num)
        }

        fun blueDropTarget(offsetX: Float, offsetY: Float, num: Int): DropData {

            var isDropTarget = false
            val color = "blue"
            var position = ""


            if (
                (offsetX > BlueBoxTarget.TOP_LEFT.landingArea.first.first && offsetY > BlueBoxTarget.TOP_LEFT.landingArea.first.second)
                &&
                (offsetX < BlueBoxTarget.TOP_LEFT.landingArea.second.first && offsetY < BlueBoxTarget.TOP_LEFT.landingArea.second.second)
            ) {
                position = "TOP_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > BlueBoxTarget.TOP_RIGHT.landingArea.first.first && offsetY > BlueBoxTarget.TOP_RIGHT.landingArea.first.second)
                &&
                (offsetX < BlueBoxTarget.TOP_RIGHT.landingArea.second.first && offsetY < BlueBoxTarget.TOP_RIGHT.landingArea.second.second)
            ) {
                position = "TOP_RIGHT"
                isDropTarget = true
            } else if (
                (offsetX > BlueBoxTarget.BOTTOM_LEFT.landingArea.first.first && offsetY > BlueBoxTarget.BOTTOM_LEFT.landingArea.first.second)
                &&
                (offsetX < BlueBoxTarget.BOTTOM_LEFT.landingArea.second.first && offsetY < BlueBoxTarget.BOTTOM_LEFT.landingArea.second.second)
            ) {
                position = "BOTTOM_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > BlueBoxTarget.BOTTOM_RIGHT.landingArea.first.first && offsetY > BlueBoxTarget.BOTTOM_RIGHT.landingArea.first.second)
                &&
                (offsetX < BlueBoxTarget.BOTTOM_RIGHT.landingArea.second.first && offsetY < BlueBoxTarget.BOTTOM_RIGHT.landingArea.second.second)
            ) {
                position = "BOTTOM_RIGHT"
                isDropTarget = true
            }

            return DropData(position, color, isDropTarget, num)
        }

        fun yellowDropTarget(offsetX: Float, offsetY: Float, num: Int): DropData {

            var isDropTarget = false
            val color = "yellow"
            var position = ""

            if (
                (offsetX > YellowBoxTarget.TOP_LEFT.landingArea.first.first && offsetY > YellowBoxTarget.TOP_LEFT.landingArea.first.second)
                &&
                (offsetX < YellowBoxTarget.TOP_LEFT.landingArea.second.first && offsetY < YellowBoxTarget.TOP_LEFT.landingArea.second.second)
            ) {
                position = "TOP_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > YellowBoxTarget.TOP_RIGHT.landingArea.first.first && offsetY > YellowBoxTarget.TOP_RIGHT.landingArea.first.second)
                &&
                (offsetX < YellowBoxTarget.TOP_RIGHT.landingArea.second.first && offsetY < YellowBoxTarget.TOP_RIGHT.landingArea.second.second)
            ) {
                position = "TOP_RIGHT"
                isDropTarget = true
            } else if (
                (offsetX > YellowBoxTarget.BOTTOM_LEFT.landingArea.first.first && offsetY > YellowBoxTarget.BOTTOM_LEFT.landingArea.first.second)
                &&
                (offsetX < YellowBoxTarget.BOTTOM_LEFT.landingArea.second.first && offsetY < YellowBoxTarget.BOTTOM_LEFT.landingArea.second.second)
            ) {
                position = "BOTTOM_LEFT"
                isDropTarget = true
            } else if (
                (offsetX > YellowBoxTarget.BOTTOM_RIGHT.landingArea.first.first && offsetY > YellowBoxTarget.BOTTOM_RIGHT.landingArea.first.second)
                &&
                (offsetX < YellowBoxTarget.BOTTOM_RIGHT.landingArea.second.first && offsetY < YellowBoxTarget.BOTTOM_RIGHT.landingArea.second.second)
            ) {
                position = "BOTTOM_RIGHT"
                isDropTarget = true
            }

            return DropData(position, color, isDropTarget, num)
        }


    }


}

class DropData(val position: String, val color: String, val isDropped: Boolean, val num: Int) {}


enum class BlackBoxTarget(val landingArea: Pair<Pair<Float, Float>, Pair<Float, Float>>) {
    TOP_LEFT(
        Pair(
            Pair(168f, 990f),
            Pair(418f, 1241f)
        )
    ),//[x=168.78203] [y=990.7778] | [x=418.32654] [y=1241.187]
    TOP_RIGHT(
        Pair(
            Pair(512f, 997f),
            Pair(762f, 1244f)
        )
    ),//[x=512.3176] [y=997.23364] | [x=762.72156] [y=1244.0653]
    BOTTOM_LEFT(
        Pair(
            Pair(174f, 1337f),
            Pair(418f, 1590f)
        )
    ),//[x=174.43262] [y=1337.895] | [x=418.03366] [y=1590.2012]
    BOTTOM_RIGHT(
        Pair(
            Pair(512f, 1332f),
            Pair(744f, 1583f)
        )
    ) //x=512.417] [y=1332.4137] | [x=744.87933] [y=1583.2059]
}

enum class RedBoxTarget(val landingArea: Pair<Pair<Float, Float>, Pair<Float, Float>>) {
    TOP_LEFT(
        Pair(
            Pair(-106f, 1002f),
            Pair(144f, 1244f)
        )
    ),// [x=-106.46811] [y=1002.5892]|[x=144.41917] [y=1244.0488]
    TOP_RIGHT(
        Pair(
            Pair(249f, 1010f),
            Pair(491f, 1248f)
        )
    ),// [x=249.08759] [y=1010.87085]|[x=491.9361] [y=1248.3658]
    BOTTOM_LEFT(
        Pair(
            Pair(-99f, 1332f),
            Pair(144f, 1586f)
        )
    ),//[x=-99.111206] [y=1332.9587] |[x=144.14116] [y=1586.4225]
    BOTTOM_RIGHT(
        Pair(
            Pair(238f, 1343f),
            Pair(486f, 1583f)
        )
    )// [x=238.30766] [y=1343.2527] |[x=486.60815] [y=1583.3839]
}

enum class BlueBoxTarget(val landingArea: Pair<Pair<Float, Float>, Pair<Float, Float>>) {
    TOP_LEFT(
        Pair(
            Pair(-378f, 998f),
            Pair(-126f, 1246f)
        )
    ), // [x=-378.67233] [y=998.65295]|[x=-126.894745] [y=1246.9147]
    TOP_RIGHT(
        Pair(
            Pair(-36f, 996f),
            Pair(203f, 1242f)
        )
    ), // [x=-36.515533] [y=996.68994]| [x=203.16733] [y=1242.5089]
    BOTTOM_LEFT(
        Pair(
            Pair(-359f, 1341f),
            Pair(-134f, 1587f)
        )
    ), // [x=-359.98114] [y=1341.6536]|[x=-134.08069] [y=1587.5494]
    BOTTOM_RIGHT(
        Pair(
            Pair(-41f, 1330f),
            Pair(220f, 1589f)
        )
    ) // [x=-41.929962] [y=1330.4728]|[x=220.03336] [y=1589.111]
}

enum class YellowBoxTarget(val landingArea: Pair<Pair<Float, Float>, Pair<Float, Float>>) {
    TOP_LEFT(
        Pair(
            Pair(-653f, 998f),
            Pair(-401f, 1253f)
        )
    ), //[x=-653.92303] [y=998.4721] | [x=-401.22604] [y=1253.3844]
    TOP_RIGHT(
        Pair(
            Pair(-312f, 993f),
            Pair(-62f, 1246f)
        )
    ), //[x=-312.82602] [y=993.6763] |[x=-62.06363] [y=1246.3318]
    BOTTOM_LEFT(
        Pair(
            Pair(-651f, 1335f),
            Pair(-402f, 1588f)
        )
    ), //[x=-651.4158] [y=1335.5553] |[x=-402.16818] [y=1588.0938]
    BOTTOM_RIGHT(
        Pair(
            Pair(-317f, 1327f),
            Pair(-57f, 1595f)
        )
    ) // [x=-317.82755] [y=1327.6492]| [x=-57.840607] [y=1595.3987]
}