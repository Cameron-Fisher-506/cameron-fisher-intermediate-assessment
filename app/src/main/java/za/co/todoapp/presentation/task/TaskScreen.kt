package za.co.todoapp.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecorelib.buttons.ButtonView
import com.example.composecorelib.buttons.CustomInputView
import za.co.todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.wrapContentSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.todo_new_task))
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        }) { padding ->
        Surface(modifier = modifier.padding(padding)) {
            Column(modifier = modifier.fillMaxWidth()) {
                CustomInputView(
                    title = stringResource(R.string.todo_task),
                    placeholder = stringResource(R.string.todo_task_hint)
                ) {

                }
                CustomInputView(
                    title = stringResource(R.string.todo_description),
                    placeholder = stringResource(R.string.todo_description_hint),
                    description = stringResource(R.string.todo_provide_a_brief_description)
                ) {

                }
                Spacer(modifier.weight(1f))
                ButtonView(
                    modifier.padding(bottom = 8.dp),
                    title = stringResource(R.string.todo_save)
                ) {
                    //TODO:Cache the task in RoomDB
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}