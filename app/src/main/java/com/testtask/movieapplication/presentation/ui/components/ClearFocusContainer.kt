package com.testtask.movieapplication.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun ClearFocusContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                focusManager.clearFocus()
            }
    ) {
        content()
    }
}
