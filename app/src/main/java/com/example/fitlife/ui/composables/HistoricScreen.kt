package com.example.fitlife.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitlife.model.FitLifeItemEntity
import com.example.fitlife.ui.MainViewModel
import com.example.fitlife.ui.theme.FitLifeBlue80

@Composable
fun HistoricScreen(
    navController: NavController, viewModel: MainViewModel
) {
    val data = viewModel.allItems.collectAsState(initial = emptyList())

    Surface(color = FitLifeBlue80, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(FitLifeBlue80),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text("Histórico", color = Color.White, style = MaterialTheme.typography.headlineMedium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            HistoricListSection(
                data,
                onItemClick = {
                    viewModel.removeItem(it)
                })
        }
    }
}

@Composable
fun HistoricListSection(
    list: State<List<FitLifeItemEntity>>,
    onItemClick: (FitLifeItemEntity) -> Unit
) {
    if (list.value.isEmpty()) {
        Text(
            "Lista Vazia",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge
        )
    } else {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(list.value) { item ->
                ItemCard(item, onItemClick)
            }

        }

    }
}

@Composable
fun ItemCard(
    item: FitLifeItemEntity,
    onItemClick: (FitLifeItemEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .graphicsLayer(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 8F
            )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            item.image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = if (item.type.toInt() == 1) "Exercício" else "Nutrição",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                )

                Text(
                    text = "${item.calories} Kcal",
                    color = if (item.type.toInt() == 1) Color(0xFF119943) else Color(0xFF9C0F0F),
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                )

                IconButton(onClick = {
                    onItemClick(item)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }

            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "${item.title} - ${item.date}",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                )

                (if (item.type.toInt() == 1) item.duration else item.aliments)?.let {
                    Text(
                        it,
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

    }
}