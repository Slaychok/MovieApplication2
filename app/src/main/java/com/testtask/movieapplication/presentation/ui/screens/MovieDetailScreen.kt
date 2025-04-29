package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.viewmodels.MovieDetailViewModel

@Composable
fun MovieDetailScreen(movieId: Int) {
    val viewModel: MovieDetailViewModel = hiltViewModel()

    // Load movie details on screen launch
    LaunchedEffect(key1 = movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    val movieDetails by viewModel.movieDetails.collectAsState()

    // Observe the state and display the UI accordingly
    if (movieDetails == null) {
        // Loading state
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Display the movie details
        MovieDetailContent(movieDetails = movieDetails!!) // Non-null assertion is safe here because of the null check above
    }
}

@Composable
fun MovieDetailContent(movieDetails: com.testtask.movieapplication.domain.models.MovieDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(BlackForBackground)
    ) {
        // Title
        Text(
            text = movieDetails.title ?: "No Title Available",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Poster
        AsyncImage(
            model = movieDetails.poster ?: "", // Handle the case where the poster URL is null/empty
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp), // Set the desired height for the poster
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // Or other suitable ContentScale
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Year and Country
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Year: ${movieDetails.year ?: "N/A"}")
            Text(text = "Country: ${movieDetails.country ?: "N/A"}")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Rating and Runtime
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "IMDB Rating: ${movieDetails.imdbRating ?: "N/A"}")
            Text(text = "Runtime: ${movieDetails.runtime ?: "N/A"}")
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Genres
        Text(text = "Genres:", style = MaterialTheme.typography.titleMedium)
        Text(text = movieDetails.genres?.joinToString(", ") ?: "N/A")
        Spacer(modifier = Modifier.height(8.dp))

        // Plot
        Text(text = "Plot:", style = MaterialTheme.typography.titleMedium)
        Text(text = movieDetails.plot ?: "No plot available")
        Spacer(modifier = Modifier.height(16.dp))

        // Actors
        Text(text = "Actors:", style = MaterialTheme.typography.titleMedium)
        Text(text = movieDetails.actors ?: "N/A")
        Spacer(modifier = Modifier.height(8.dp))

        // Director
        Text(text = "Director:", style = MaterialTheme.typography.titleMedium)
        Text(text = movieDetails.director ?: "N/A")
    }
}