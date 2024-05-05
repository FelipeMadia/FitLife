package com.example.fitlife.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitlife.ui.composables.ExerciseScreen
import com.example.fitlife.ui.composables.FitLifeMainScreen
import com.example.fitlife.ui.composables.HistoricScreen
import com.example.fitlife.ui.composables.NutritionScreen
import com.example.fitlife.ui.theme.FitLifeTheme
import dagger.hilt.android.AndroidEntryPoint

const val FITLIFE_SCREEN = "fitlife_screen"
const val EXERCISE_SCREEN = "exercise_screen"
const val NUTRITION_SCREEN = "nutrition_screen"
const val HISTORIC_SCREEN = "historic_screen"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitLifeTheme {
                FitLifeApp()
            }
        }
    }
}

@Composable
fun FitLifeApp(viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = FITLIFE_SCREEN) {
        composable(FITLIFE_SCREEN) {
            FitLifeMainScreen(navController)
        }
        composable(EXERCISE_SCREEN)
        {
            ExerciseScreen(navController, viewModel)
        }
        composable(NUTRITION_SCREEN)
        {
            NutritionScreen(navController, viewModel)
        }
        composable(HISTORIC_SCREEN)
        {
            HistoricScreen(navController, viewModel)
        }
    }
}
