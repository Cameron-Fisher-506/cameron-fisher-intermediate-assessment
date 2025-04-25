package za.co.todoapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecorelib.buttons.CustomCardView
import za.co.todoapp.R
import za.co.todoapp.data.local.mockTaskList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState();
    val tabItems = listOf(
        TabItem(
            title = "To do",
            unselectedIcon = Icons.Outlined.Create,
            selectedIcon = Icons.Filled.Create
        ),
        TabItem(
            title = "Completed",
            unselectedIcon = Icons.Outlined.CheckCircle,
            selectedIcon = Icons.Filled.CheckCircle
        )
    )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val horizontalPagerState = rememberPagerState { tabItems.size }
    LaunchedEffect(selectedTabIndex) {
        horizontalPagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(horizontalPagerState.currentPage, horizontalPagerState.isScrollInProgress) {
        if (!horizontalPagerState.isScrollInProgress) {
            selectedTabIndex = horizontalPagerState.currentPage
        }
    }

    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                title = { Text("TODO") },
                actions = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(Icons.Rounded.Add, "Add")
                    }
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(Icons.Rounded.Menu, "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            )
        }, sheetContent = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.todo_weather),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        sheetPeekHeight = 90.dp,
        sheetShadowElevation = 10.dp,
        sheetSwipeEnabled = true,
        scaffoldState = scaffoldState
    ) { padding ->
        Surface(modifier = modifier.padding(padding)) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabItems.forEachIndexed { index, tabItem ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = {
                                selectedTabIndex = index
                            },
                            text = { Text(tabItem.title) },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedTabIndex) tabItem.selectedIcon else tabItem.unselectedIcon,
                                    contentDescription = tabItem.title
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    state = horizontalPagerState,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    userScrollEnabled = false
                ) { index ->
                    if (index == 0) {
                        //TODO: Handle To do task page - create a custom card view
                        val listState = rememberLazyListState()
                        LazyColumn(state = listState) {
                            items(
                                count = mockTaskList.size,
                                key = { mockTaskList[it].title }
                            ) {
                                mockTaskList.forEach { task ->
                                    CustomCardView(
                                        title = task.title,
                                        description = task.description,
                                        isComplete = task.isComplete
                                    )
                                }
                            }
                        }
                    } else {
                        //TODO: Handle completed task page - create a custom card view
                    }
                }
            }
        }
    }
}

private data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}