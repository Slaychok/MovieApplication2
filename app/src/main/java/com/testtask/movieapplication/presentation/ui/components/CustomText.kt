package com.testtask.movieapplication.presentation.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.theme.SkModernist

@Composable
fun CustomText(
    text: String,
    fontSize: TextUnit,
    color: Color = BlackForBackground,
    fontWeight: FontWeight,
    contentAlignment: Alignment? = null,
    textAlignment: Alignment? = null,
    textDecoration: TextDecoration? = null,
) {

    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        style = TextStyle(
            fontFamily = SkModernist,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color,
            textDecoration = textDecoration
        ),
    )
}