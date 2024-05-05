package com.example.fitlife.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.outlined.FoodBank
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitlife.ui.EXERCISE_SCREEN
import com.example.fitlife.ui.HISTORIC_SCREEN
import com.example.fitlife.ui.NUTRITION_SCREEN
import com.example.fitlife.ui.theme.FitLifeBlue40
import com.example.fitlife.ui.theme.FitLifeBlue60
import com.example.fitlife.ui.theme.FitLifeBlue80
import com.example.fitlife.ui.theme.FitLifeTheme

@Composable
fun FitLifeMainScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(FitLifeBlue80),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "FitLife", style = TextStyle(color = Color.White, fontSize = 62.sp))
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Seja bem-vindo!",
                        style = TextStyle(color = FitLifeBlue80, fontSize = 42.sp)
                    )
                }
                MainScreenButtonsSection(navController)
            }
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(color = FitLifeBlue40, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MedicalServices,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun MainScreenButtonsSection(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(FitLifeBlue80),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MainScreenIconButton(
            text = "Exercícios",
            icon = Icons.Filled.DirectionsRun,
            description = "Exercises",
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            navController = navController,
            route = EXERCISE_SCREEN
        )
        MainScreenIconButton(
            text = "Nutrição",
            icon = Icons.Outlined.FoodBank,
            description = "Nutrition",
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(
                    FitLifeBlue60
                ),
            navController = navController,
            route = NUTRITION_SCREEN
        )
        MainScreenIconButton(
            text = "Histórico",
            icon = Icons.Filled.History,
            description = "Historic",
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            navController = navController,
            route = HISTORIC_SCREEN
        )
    }
}

@Composable
fun MainScreenIconButton(
    text: String,
    icon: ImageVector,
    description: String,
    modifier: Modifier,
    navController: NavController,
    route: String
) {
    IconButton(onClick = { navController.navigate(route) }, modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = description, modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = TextStyle(color = Color.Black, fontSize = 14.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    FitLifeTheme {
        val navController = rememberNavController()
        FitLifeMainScreen(navController)
    }
}