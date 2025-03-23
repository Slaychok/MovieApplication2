package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.testtask.movieapplication.presentation.ui.components.MovieCard
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.viewmodels.MovieViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {

    val listState = rememberLazyListState()
    val movies = viewModel.movies.collectAsState()
    val isLoading = viewModel.isLoading.value
    val searchText = rememberSaveable { mutableStateOf("") }

    LaunchedEffect (Unit){
        viewModel.loadNextPage()
    }

    LaunchedEffect(listState.firstVisibleItemIndex, listState.layoutInfo.totalItemsCount) {
        val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
        if (lastVisibleItem != null && lastVisibleItem.index >= movies.value.lastIndex - 2) {
            viewModel.loadNextPage()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            placeholder = { Text("Поиск фильмов") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (searchText.value.isNotBlank()) {
                    viewModel.searchMovies(searchText.value)
                }
            }
        ) {
            Text("Поиск")
        }

        Button(
            onClick = {
                searchText.value = ""
                viewModel.clearSearch() // метод, который сбрасывает currentQuery = null и заново вызывает loadNextPage()
            }
        ) {
            Text("Сброс")
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(movies.value) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = { navController.navigate("movieDetail/${movie.id}") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}