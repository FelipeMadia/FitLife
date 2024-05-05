package com.example.fitlife.ui.composables

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitlife.model.FitLifeItemEntity
import com.example.fitlife.ui.MainViewModel
import com.example.fitlife.ui.theme.FitLifeBlue100
import com.example.fitlife.ui.theme.FitLifeBlue80
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun NutritionScreen(navController: NavController, viewModel: MainViewModel) {
    Surface(
        color = FitLifeBlue80,
        modifier = Modifier
            .fillMaxSize()
            .background(FitLifeBlue80)
    ) {
        var name by remember { mutableStateOf("") }
        var aliments by remember { mutableStateOf("") }
        var calories by remember { mutableStateOf("") }
        var obs by remember { mutableStateOf("") }
        var capturedImage: Bitmap? by remember { mutableStateOf(null) }

        val pickMedia =
            rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
                it?.let { capturedBitmap ->
                    capturedImage = capturedBitmap
                }
            }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text("Nutrição", color = Color.White, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome da Refeição") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = aliments,
                onValueChange = { aliments = it },
                label = { Text("Alimentos Consumidos") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = calories,
                onValueChange = { calories = it },
                label = { Text("Calorias Cosumidas") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = obs,
                onValueChange = { obs = it },
                label = { Text("Observações") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = VisualTransformation.None,
                minLines = 4,
                maxLines = 4
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (capturedImage != null) {
                    Image(
                        bitmap = capturedImage!!.asImageBitmap(),
                        contentDescription = "Imagem Capturada",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(bottom = 8.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Tirar Foto",
                    color = FitLifeBlue100,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(
                    onClick = {
                        pickMedia.launch()
                    }, modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(color = FitLifeBlue100)
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Camera",
                        tint = Color.White
                    )
                }
            }
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
                            type = 2,
                            title = name,
                            aliments = aliments,
                            calories = calories.toLong(),
                            obs = obs,
                            image = capturedImage,
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