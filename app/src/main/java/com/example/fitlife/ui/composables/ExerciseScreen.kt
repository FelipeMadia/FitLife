package com.example.fitlife.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlife.model.FitLifeItemEntity
import com.example.fitlife.ui.MainViewModel
import com.example.fitlife.ui.theme.FitLifeBlue100
import com.example.fitlife.ui.theme.FitLifeBlue80
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun ExerciseScreen(navController: NavController, viewModel: MainViewModel) {
    Surface(
        color = FitLifeBlue80,
        modifier = Modifier
            .fillMaxSize()
            .background(FitLifeBlue80)
    ) {
        var name by remember { mutableStateOf("") }
        var duration by remember { mutableStateOf("") }
        var calories by remember { mutableStateOf("") }
        var obs by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text("Exercício", color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do Exercício") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = duration,
                onValueChange = { duration = it },
                label = { Text("Tempo de Duração") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = calories,
                onValueChange = { calories = it },
                label = { Text("Calorias Gastas") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = obs,
                onValueChange = { obs = it },
                label = { Text("Observações") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = VisualTransformation.None,
                minLines = 3,
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        navController.navigateUp()
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Cancelar", color = Color.Black)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
                        val date = simpleDateFormat.format(Date())
                        val fitLifeItem = FitLifeItemEntity(
                            type = 1,
                            title = name,
                            duration = duration,
                            calories = calories.toLong(),
                            obs = obs,
                            date = date
                        )
                        viewModel.addItem(fitLifeItem)
                        navController.navigateUp()
                    }, colors = ButtonDefaults.buttonColors(containerColor = FitLifeBlue100)
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}