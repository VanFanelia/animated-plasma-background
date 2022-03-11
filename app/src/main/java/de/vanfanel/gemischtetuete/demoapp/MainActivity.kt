package de.vanfanel.gemischtetuete.demoapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.vanfanel.gemischtetuete.demoapp.NavigationDestinations.MAIN_SCREEN
import de.vanfanel.gemischtetuete.demoapp.NavigationDestinations.PLASMA_BACKGROUND
import de.vanfanel.gemischtetuete.demoapp.screens.PlasmaBackgroundScreen
import de.vanfanel.gemischtetuete.demoapp.ui.theme.AnimatedPlasmaBackgroundTheme

enum class NavigationDestinations(val path: String) {
    MAIN_SCREEN("main"),
    PLASMA_BACKGROUND("plasmaBackground"),
    AUTOCOMPLETE_SELECT_BOX("autocompleteSelectBox")
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN.path
    ) {
        composable(MAIN_SCREEN.path) {
            MainScreen(navController = navController)
        }
        composable(PLASMA_BACKGROUND.path) {
            PlasmaBackgroundScreen()
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val appContext = LocalContext.current.applicationContext

    AnimatedPlasmaBackgroundTheme {
        BoxWithConstraints {
            val thirdWidth = (this.maxWidth / 3) - 16.dp
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .padding(top = 32.dp, bottom = 48.dp, start = 0.dp, end = 0.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), text = "Choose a Component",
                    color = MaterialTheme.colors.onBackground
                )

                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .sizeIn(maxWidth = thirdWidth)
                            .wrapContentHeight(),
                        colors = buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        onClick = {
                            navController.navigate(PLASMA_BACKGROUND.path)
                        }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "\uD83C\uDF0B"
                            )
                            Text(
                                text = "Plasma Background",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Button(
                        modifier = Modifier
                            .sizeIn(maxWidth = thirdWidth)
                            .wrapContentHeight(),
                        colors = buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        onClick = {
                            Toast.makeText(appContext, "goto", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "\uD83D\uDDC3"
                            )
                            Text(
                                text = "Auto\u200BComplete Select\u200BBox",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Button(
                        modifier = Modifier
                            .sizeIn(maxWidth = thirdWidth)
                            .wrapContentHeight(),
                        colors = buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        onClick = {
                            Toast.makeText(appContext, "goto", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "?"
                            )
                            Text(
                                text = "Next component",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    MainScreen(navController = navController)
}
