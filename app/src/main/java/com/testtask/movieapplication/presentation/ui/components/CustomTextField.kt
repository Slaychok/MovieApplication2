package com.testtask.movieapplication.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.graphics.SolidColor
import com.testtask.movieapplication.presentation.ui.theme.GrayForContainer
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont
import com.testtask.movieapplication.presentation.ui.theme.MyYellow

@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(15.dp),
    backgroundColor: Color = GrayForContainer,
    textColor: Color = Color.White,
    borderColor: Color = Color.Transparent,
    focusedBorderColor: Color = MyYellow,
    cursorColor: Color = GrayForFont,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isFocused) focusedBorderColor else borderColor,
        animationSpec = tween(durationMillis = 330),
        label = "Border Color Animation"
    )

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 2.dp,
                    color = animatedBorderColor,
                    shape = shape
                )
                .background(backgroundColor, shape)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = onTextChange,
                    textStyle = TextStyle(
                        color = textColor,
                        fontSize = 16.sp
                    ),
                    cursorBrush = SolidColor(cursorColor),
                    interactionSource = interactionSource,
                    singleLine = true,
                    visualTransformation = visualTransformation,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                trailingIcon?.let {
                    it()
                }
            }

            if (text.isEmpty() && !isFocused) {
                placeholder?.let {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        it()
                    }
                }
            }
        }
    }
}

