package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.testtask.movieapplication.presentation.ui.components.CustomText
import com.testtask.movieapplication.presentation.ui.components.CustomTextField
import com.testtask.movieapplication.presentation.ui.components.MovieCard
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont
import com.testtask.movieapplication.presentation.viewmodels.MovieListViewModel

//@Composable
//fun MovieListScreen(
//    navController: NavController,
//    viewModel: MovieListViewModel = hiltViewModel()
//) {
//    val listState = rememberLazyListState()
//    val movies = viewModel.movies.collectAsState()
//    val isLoading = viewModel.isLoading.value
//    val searchQuery by viewModel.searchQuery.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.loadNextPage()
//    }
//
//    LaunchedEffect(listState.firstVisibleItemIndex, listState.layoutInfo.totalItemsCount) {
//        val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
//        if (lastVisibleItem != null && lastVisibleItem.index >= movies.value.lastIndex - 2) {
//            viewModel.loadNextPage()
//        }
//    }
//
//    ClearFocusContainer(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BlackForBackground)
//            .padding(horizontal = 16.dp)
//    ) {
//        Column(modifier = Modifier.fillMaxSize()) {
//
//            CustomTextField(
//                text = searchQuery,
//                onTextChange = { viewModel.onSearchQueryChanged(it) },
//                placeholder = {
//                    CustomText(
//                        text = "Поиск фильмов",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.W400,
//                        color = GrayForFont
//                    )
//                },
//                modifier = Modifier.fillMaxWidth(),
//                trailingIcon = {
//                    if (searchQuery.isNotBlank()) {
//                        IconButton(onClick = {
//                            viewModel.clearSearch()
//                        }) {
//                            Icon(Icons.Default.Close, contentDescription = "Очистить", tint = GrayForFont)
//                        }
//                    }
//                }
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)) {
//
//                if (!isLoading && movies.value.isEmpty()) {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "Ничего не найдено",
//                            color = Color.White,
//                            fontSize = 16.sp
//                        )
//                    }
//                } else {
//                    LazyColumn(
//                        state = listState,
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        items(movies.value) { movie ->
//                            MovieCard(
//                                movie = movie,
//                                onClick = { navController.navigate("movieDetail/${movie.id}") },
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                            )
//                        }
//
//                        if (isLoading) {
//                            item {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(16.dp),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    CircularProgressIndicator()
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}


//@Composable
//fun MovieListScreen(
//    navController: NavController,
//    viewModel: MovieListViewModel = hiltViewModel(),
//    searchHistoryManager: Set<String> // Добавляем SearchHistoryManager
//) {
//    val listState = rememberLazyListState()
//    val movies = viewModel.movies.collectAsState()
//    val isLoading = viewModel.isLoading.value
//    val searchQuery by viewModel.searchQuery.collectAsState()
//    val searchHistory by viewModel.searchHistory.collectAsState()
//
//    // Состояние фокуса текстового поля
//    var isTextFieldFocused by remember { mutableStateOf(false) }
//
//    // Автоматическое управление видимостью истории
//    val showHistory = isTextFieldFocused && searchQuery.isEmpty() && searchHistory.isNotEmpty()
//
//    LaunchedEffect(Unit) {
//        viewModel.loadNextPage()
//    }
//
//    LaunchedEffect(listState.firstVisibleItemIndex, listState.layoutInfo.totalItemsCount) {
//        val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
//        if (lastVisibleItem != null && lastVisibleItem.index >= movies.value.lastIndex - 2) {
//            viewModel.loadNextPage()
//        }
//    }
//
//    ClearFocusContainer(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BlackForBackground)
//            .padding(horizontal = 16.dp)
//    ) {
//        Column(modifier = Modifier.fillMaxSize()) {
//
//            CustomTextField(
//                text = searchQuery,
//                onTextChange = { viewModel.onSearchQueryChanged(it) },
//                placeholder = {
//                    CustomText(
//                        text = "Поиск фильмов",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.W400,
//                        color = GrayForFont
//                    )
//                },
//                modifier = Modifier.fillMaxWidth(),
//                trailingIcon = {
//                    if (searchQuery.isNotBlank()) {
//                        IconButton(onClick = {
//                            viewModel.clearSearch()
//                            showHistory = true // Показываем историю
//                        }) {
//                            Icon(Icons.Default.Close, contentDescription = "Очистить", tint = GrayForFont)
//                        }
//                    }
//                }
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Отображение истории поиска
//            if (showHistory && searchHistory.isNotEmpty()) {
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(15000.dp) // Регулируй высоту по необходимости
//                ) {
//                    items(searchHistory.toList()) { query ->
//                        SearchHistoryItem(
//                            query = query,
//                            onItemClick = {
//                                viewModel.onSearchQueryChanged(query) // Устанавливаем запрос в поле поиска
//                                showHistory = false // Скрываем историю
//                            },
//                            onDeleteClick = {
//                                viewModel.deleteSearchQuery(query) // Удаляем запрос из истории
//                            }
//                        )
//                    }
//                }
//            }
//
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)) {
//
//                if (!isLoading && movies.value.isEmpty()) {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(
//                            text = "Ничего не найдено",
//                            color = Color.White,
//                            fontSize = 16.sp
//                        )
//                    }
//                } else {
//                    LazyColumn(
//                        state = listState,
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        items(movies.value) { movie ->
//                            MovieCard(
//                                movie = movie,
//                                onClick = { navController.navigate("movieDetail/${movie.id}") },
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                            )
//                        }
//
//                        if (isLoading) {
//                            item {
//                                Box(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(16.dp),
//                                    contentAlignment = Alignment.Center
//                                ) {
//                                    CircularProgressIndicator()
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SearchHistoryItem(query: String, onItemClick: () -> Unit, onDeleteClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onItemClick() }
//            .padding(8.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(text = query, color = Color.White)
//        IconButton(onClick = { onDeleteClick() }) {
//            Icon(Icons.Default.Close, contentDescription = "Удалить", tint = Color.Gray)
//        }
//    }
//}

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val movies by viewModel.movies.collectAsState()
    val isLoading by remember { viewModel.isLoading }
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchHistory by viewModel.searchHistory.collectAsState()

    // Состояние фокуса и управление им
    var isTextFieldFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    // Показывать историю только когда поле в фокусе И пустое И есть история
    val showHistory = isTextFieldFocused && searchQuery.isEmpty() && searchHistory.isNotEmpty()

    LaunchedEffect(Unit) {
        viewModel.loadNextPage()
        // Запросить фокус при первом открытии экрана
        focusRequester.requestFocus()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty()) {
                    val lastVisibleItem = visibleItems.last()
                    val totalItems = listState.layoutInfo.totalItemsCount

                    if (lastVisibleItem.index >= totalItems - 3 && !isLoading) {
                        viewModel.loadNextPage()
                    }
                }
            }
    }

    // Исправление навигации
    val navigateToDetail: (Int) -> Unit = { movieId ->
        focusManager.clearFocus()
        navController.navigate("movieDetail/$movieId")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground)
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomTextField(
                text = searchQuery,
                onTextChange = viewModel::onSearchQueryChanged,
                onFocusChange = { isTextFieldFocused = it },
                placeholder = {
                    CustomText(
                        text = "Поиск фильмов",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = GrayForFont
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = {
                            viewModel.clearSearch()
                            focusRequester.requestFocus()
                        }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "Очистить",
                                tint = GrayForFont
                            )
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // История поиска
            if (showHistory) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                        .background(Color.DarkGray)
                ) {
                    items(searchHistory.toList()) { query ->
                        SearchHistoryItem(
                            query = query,
                            onItemClick = {
                                viewModel.onSearchQueryChanged(query)
                                focusManager.clearFocus()
                            },
                            onDeleteClick = {
                                viewModel.deleteSearchQuery(query)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Список фильмов
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(movies) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = { navigateToDetail(movie.id) },
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

                if (!isLoading && movies.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Ничего не найдено",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchHistoryItem(query: String, onItemClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = query, color = Color.White)
        IconButton(onClick = { onDeleteClick() }) {
            Icon(Icons.Default.Close, contentDescription = "Удалить", tint = Color.Gray)
        }
    }
}