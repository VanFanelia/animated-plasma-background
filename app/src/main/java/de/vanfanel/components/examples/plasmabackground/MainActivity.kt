package de.vanfanel.components.examples.plasmabackground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.vanfanel.components.examples.plasmabackground.ui.theme.AnimatedPlasmaBackgroundTheme
import de.vanfanel.components.examples.plasmabackground.ui.theme.TRANSPARENT_GREY
import de.vanfanel.components.plasmabackground.PlasmaBackground

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedPlasmaBackgroundTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    var showMenuOverlay by remember { mutableStateOf(false) }
                    val showFPS = remember { mutableStateOf(false) }

                    PlasmaBackground(
                        debugShowFPS = showFPS.value
                    )

                    AnimatedVisibility(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .align(Alignment.BottomCenter),
                        visible = showMenuOverlay,
                        enter = slideInVertically({ it }),
                        exit = slideOutVertically({ it })
                    ) {
                        Surface(
                            color = TRANSPARENT_GREY
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.clickable {
                                        showFPS.value = !showFPS.value
                                    }
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
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimatedPlasmaBackgroundTheme {
        Greeting("Android")
    }
}
