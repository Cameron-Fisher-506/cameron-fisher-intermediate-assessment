package com.example.composecorelib.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(
    modifier: Modifier = Modifier,
    title: String,
    shape: Shape = RectangleShape,
    onClick: () -> Unit
) {
    Surface(modifier.wrapContentSize()) {
        Button(
            { onClick() },
            modifier
                .padding(20.dp, 10.dp)
                .fillMaxWidth(1f)
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            shape = shape,
        ) {
            Text(title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonView() {
    ButtonView(title = "Button") {}
}