package com.testtask.movieapplication.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.testtask.movieapplication.R
import com.testtask.movieapplication.domain.models.Movie
import com.testtask.movieapplication.presentation.ui.theme.GrayForContainer

@Composable
fun NewMovieCard(
    movie: Movie,
    onClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit, // Новый параметр для обработки клика по избранному
    isFavorite: Boolean, // Состояние: добавлено ли в избранное
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = GrayForContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                AsyncImage(
                    model = movie.poster,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )

                // Кнопка "Избранное" в правом верхнем углу
                IconButton(
                    onClick = {
                        // Предотвращаем всплытие события на карточку
                        onFavoriteClick(!isFavorite)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite)
                            "Удалить из избранного"
                        else
                            "Добавить в избранное",
                        tint = if (isFavorite) Color.Red else Color.White
                    )
                }
            }

            // Название и рейтинг
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = movie.title ?: stringResource(R.string.no_name),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (!movie.imdb_rating.isNullOrBlank()) {
                    Text(
                        text = "Rating: ${movie.imdb_rating}",
                        fontSize = 12.sp,
                        color = Color(0xFFAAAAAA),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}