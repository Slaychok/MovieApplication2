package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.testtask.movieapplication.domain.models.Movie
import com.testtask.movieapplication.presentation.ui.components.ClearFocusContainer
import com.testtask.movieapplication.presentation.ui.components.CustomText
import com.testtask.movieapplication.presentation.ui.components.CustomTextField
import com.testtask.movieapplication.presentation.ui.components.NewMovieCard
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont

@Composable
fun FeaturedScreen(navController: NavController) {
    // Состояние для списка фильмов
    val (movies, setMovies) = remember {
        mutableStateOf(
            listOf(
                Movie(
                    id = 1,
                    title = "The Shawshank Redemption",
                    poster = "https://moviesapi.ir/images/tt0111161_poster.jpg",
                    year = "1994",
                    country = "USA",
                    imdb_rating = "9.3",
                    genres = listOf("Crime", "Drama"),
                    images = listOf(
                        "https://moviesapi.ir/images/tt0111161_screenshot1.jpg",
                        "https://moviesapi.ir/images/tt0111161_screenshot2.jpg",
                        "https://moviesapi.ir/images/tt0111161_screenshot3.jpg"
                    ),
                    isFavourite = true
                ),
                Movie(
                    id = 2,
                    title = "The Godfather",
                    poster = "https://moviesapi.ir/images/tt0068646_poster.jpg",
                    year = "1972",
                    country = "USA",
                    imdb_rating = "9.2",
                    genres = listOf("Crime", "Drama"),
                    images = listOf(
                        "https://moviesapi.ir/images/tt0068646_screenshot1.jpg",
                        "https://moviesapi.ir/images/tt0068646_screenshot2.jpg",
                        "https://moviesapi.ir/images/tt0068646_screenshot3.jpg"
                    ),
                    isFavourite = true
                )
            )
        )
    }

    // Обработчик изменения избранного
    fun toggleFavorite(movieId: Int) {
        setMovies(movies.map { movie ->
            if (movie.id == movieId) movie.copy(isFavourite = !movie.isFavourite!!!!)
            else movie
        })
    }

    ClearFocusContainer(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground)
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomTextField(
                text = "",
                onTextChange = {},
                placeholder = {
                    CustomText(
                        text = "Поиск избранного",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = GrayForFont
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = movies,
                    key = { movie -> movie.id }
                ) { movie ->
                    movie.isFavourite?.let {
                        NewMovieCard(
                            movie = movie,
                            onClick = {
                                // Обработка клика по карточке
                                navController.navigate("movie_details/${movie.id}")
                            },
                            onFavoriteClick = { toggleFavorite(movie.id) },
                            isFavorite = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        )
                    }
                }
            }
        }
    }
}