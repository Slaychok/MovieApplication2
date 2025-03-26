package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.testtask.movieapplication.presentation.ui.components.MovieCard
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.viewmodels.MovieViewModel

@Composable
fun SearchScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {
    val movies = viewModel.movies.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (movies.value.isEmpty()) {
            // Показываем сообщение, если список пуст
            Text(
                text = "No movies found",
                color = Color.White,
                fontSize = 18.sp
            )
        } else {
            // Отображаем список фильмов
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movies.value) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = {
                            // Обработчик клика по карточке
                            navController.navigate("movieDetail/${movie.id}")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}