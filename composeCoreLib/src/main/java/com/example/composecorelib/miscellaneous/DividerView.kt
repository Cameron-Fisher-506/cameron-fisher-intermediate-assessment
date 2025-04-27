package com.example.composecorelib.miscellaneous

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DividerView(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray
) {
    HorizontalDivider(
        modifier
            .fillMaxWidth()
            .width(1.dp)
            .padding(top = 5.dp, bottom = 5.dp),
        color = color
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewDividerView() {
    DividerView()
}