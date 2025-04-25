package com.example.composecorelib.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomCardView(
    modifier: Modifier = Modifier,
    title: String,
    description: String = "",
    isComplete: Boolean = false

) {
    Card(
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            Button(
                modifier = modifier
                    .height(80.dp)
                    .width(8.dp),
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isComplete) Color.Green else Color.DarkGray
                ),
                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp),
            ) { }
            Column(modifier = modifier.wrapContentSize()) {
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@Preview
@Composable
fun CustomCardItemViewPreview() {
    CustomCardView(
        title = "Title",
        description = "Description"
    )
}