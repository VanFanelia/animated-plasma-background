package de.vanfanel.gemischtetuete.demoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.vanfanel.gemischtetuete.demoapp.ui.theme.AnimatedPlasmaBackgroundTheme
import de.vanfanel.gemischtetuete.demoapp.ui.theme.TRANSPARENT_GREY
import de.vanfanel.gemischtetuete.plasmabackground.DEFAULT_FPS
import de.vanfanel.gemischtetuete.plasmabackground.PlasmaBackground

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContext = this.applicationContext

        setContent {
            AppContent(fpsNumberInvalid = {
                Toast.makeText(
                    appContext,
                    "FPS must between 1 and 60",
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
    }
}

@Composable
@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalComposeUiApi::class)
fun AppContent(fpsNumberInvalid: () -> Unit = {}) {
    AnimatedPlasmaBackgroundTheme {
        // A surface container using the 'background' color from the theme
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            var showMenuOverlay by remember { mutableStateOf(false) }
            val showFPS = remember { mutableStateOf(true) }
            val useMaterialColors = remember { mutableStateOf(true) }
            val debugDoNotScale = remember { mutableStateOf(false) }
            val fpsValue = remember { mutableStateOf(TextFieldValue("20")) }
            var newFPSValue by remember { mutableStateOf<Int?>(DEFAULT_FPS) }

            if (useMaterialColors.value) {
                PlasmaBackground(
                    maxFPS = newFPSValue ?: DEFAULT_FPS,
                    debugShowFPS = showFPS.value,
                    debugDoNotScale = debugDoNotScale.value
                )
            } else {
                PlasmaBackground(
                    colors = arrayOf(
                        Color(0xFF227c9d),
                        Color(0xFF17c3b2),
                        Color(0xFFffcb77),
                        Color(0xfffef9ef),
                        Color(0xfffe6d73)
                ),
                    maxFPS = newFPSValue ?: DEFAULT_FPS,
                    debugShowFPS = showFPS.value,
                    debugDoNotScale = debugDoNotScale.value
                )
            }

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter),
                visible = showMenuOverlay,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                Surface(
                    color = TRANSPARENT_GREY
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.clickable {
                                showFPS.value = !showFPS.value
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = showFPS.value,
                                onCheckedChange = { showFPS.value = it }
                            )
                            Text(
                                modifier = Modifier.padding(start = 20.dp),
                                text = "Show FPS"
                            )
                        }
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val keyboardController = LocalSoftwareKeyboardController.current
                            val focusManager = LocalFocusManager.current
                            TextField(
                                modifier = Modifier.width(64.dp),
                                value = fpsValue.value,
                                onValueChange = { textFieldValue ->
                                    try {
                                        if (textFieldValue.text.isEmpty()) {
                                            fpsValue.value = textFieldValue
                                            return@TextField
                                        }
                                        val intValue =
                                            textFieldValue.text.filter { it.isDigit() }
                                                .toInt()
                                        if (intValue < 1) {
                                            fpsValue.value = TextFieldValue("0")
                                        }
                                        if (intValue in 1..60) {
                                            fpsValue.value = textFieldValue
                                        } else {
                                            fpsNumberInvalid()
                                        }
                                    } catch (e: NumberFormatException) {
                                        fpsNumberInvalid()
                                    }
                                },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        keyboardController?.hide()
                                        focusManager.clearFocus()
                                        newFPSValue = fpsValue.value.text.toInt()
                                    }
                                )
                            )
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "Set maximum FPS (Optimum is $DEFAULT_FPS)"
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable {
                                    debugDoNotScale.value = !debugDoNotScale.value
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = debugDoNotScale.value,
                                onCheckedChange = { debugDoNotScale.value = it }
                            )
                            Text(
                                modifier = Modifier.padding(start = 20.dp),
                                text = "Do not scale image (debug)"
                            )
                        }
                        Row(
                            modifier = Modifier.clickable {
                                useMaterialColors.value = !useMaterialColors.value
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = useMaterialColors.value,
                                onCheckedChange = { useMaterialColors.value = it }
                            )
                            Text(
                                modifier = Modifier.padding(start = 20.dp),
                                text = "Use Material UI Colors)"
                            )
                        }
                    }
                }
            }

            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(width = 48.dp, height = 48.dp),
                shape = RoundedCornerShape(99.dp),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = TRANSPARENT_GREY),
                onClick = {
                    showMenuOverlay = !showMenuOverlay
                },
            ) {
                val image: Painter = painterResource(id = R.drawable.ic_menu)
                Image(painter = image, contentDescription = "")
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DefaultPreview() {
    AppContent()
}
