package za.co.todoapp.presentation.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecorelib.buttons.ButtonView
import com.example.composecorelib.buttons.CustomInputView
import za.co.todoapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    taskName: MutableState<String>,
    taskDescription: MutableState<String>,
    taskNameErrorMessage: MutableState<String>,
    taskDescriptionErrorMessage: MutableState<String>,
    snackbarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    onTaskNameValueChanged: (value: String) -> Unit,
    onTaskDescriptionValueChanged: (value: String) -> Unit,
    onSaveClicked: () -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier.wrapContentSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.todo_new_task))
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateUp()
                        }
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "ArrowBack")
                    }
                }
            )
        }) { padding ->
        Surface(modifier = modifier.padding(padding)) {
            Column(modifier = modifier.fillMaxSize()) {
                CustomInputView(
                    title = stringResource(R.string.todo_task),
                    placeholder = stringResource(R.string.todo_task_hint),
                    value = taskName.value,
                    errorMessage = taskNameErrorMessage.value
                ) { value ->
                    onTaskNameValueChanged(value)
                }
                CustomInputView(
                    title = stringResource(R.string.todo_description),
                    placeholder = stringResource(R.string.todo_description_hint),
                    description = stringResource(R.string.todo_provide_a_brief_description),
                    value = taskDescription.value,
                    errorMessage = taskDescriptionErrorMessage.value
                ) { value ->
                    onTaskDescriptionValueChanged(value)
                }
                Spacer(modifier.weight(1f))
                ButtonView(
                    modifier.padding(bottom = 8.dp),
                    title = stringResource(R.string.todo_save)
                ) {
                    onSaveClicked()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview() {
    TaskScreen(
        taskName = remember { mutableStateOf("") },
        taskDescription = remember { mutableStateOf("") },
        taskNameErrorMessage = remember { mutableStateOf("") },
        taskDescriptionErrorMessage = remember { mutableStateOf("") },
        snackbarHostState = SnackbarHostState(),
        onNavigateUp = {},
        onTaskNameValueChanged = {},
        onTaskDescriptionValueChanged = {}
    ) {
    }
}