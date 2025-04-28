package za.co.todoapp.presentation.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecorelib.buttons.CustomCardView
import za.co.todoapp.data.model.Task
import za.co.todoapp.data.model.currentWeather.CurrentWeatherResponse
import za.co.todoapp.data.model.currentWeather.dto.Astro
import za.co.todoapp.data.model.currentWeather.dto.Condition
import za.co.todoapp.data.model.currentWeather.dto.Current
import za.co.todoapp.data.model.currentWeather.dto.Day
import za.co.todoapp.data.model.currentWeather.dto.Forecast
import za.co.todoapp.data.model.currentWeather.dto.ForecastDay
import za.co.todoapp.data.model.currentWeather.dto.Location
import za.co.todoapp.presentation.home.HomeScreenViewModel.CurrentWeatherState
import za.co.todoapp.presentation.home.HomeScreenViewModel.TabItem
import za.co.todoapp.presentation.home.HomeScreenViewModel.TaskState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskState: State<TaskState>,
    currentWeatherState: State<CurrentWeatherState>,
    tabItemList: List<TabItem>,
    snackbarHostState: SnackbarHostState,
    onCreate: () -> Unit,
    onGetDeviceLocation: (isLocationPermissionGranted: Boolean) -> Unit,
    onDeleteTask: (task: Task) -> Unit,
    onCompleteTask: (task: Task) -> Unit,
    onUndoCompletedTask: (task: Task) -> Unit,
    onTaskTabClicked: (isComplete: Boolean) -> Unit,
    onNavigateToTaskScreenClicked: () -> Unit,
    onNavigateToMenuScreenClicked: () -> Unit
) {
    val locationPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isLocationPermissionGranted ->
            onGetDeviceLocation(isLocationPermissionGranted)
        }

    val scaffoldState = rememberBottomSheetScaffoldState();
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val horizontalPagerState = rememberPagerState { tabItemList.size }
    LaunchedEffect(Unit) {
        onCreate()
        locationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
    LaunchedEffect(selectedTabIndex) {
        horizontalPagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(horizontalPagerState.currentPage, horizontalPagerState.isScrollInProgress) {
        if (!horizontalPagerState.isScrollInProgress) {
            selectedTabIndex = horizontalPagerState.currentPage
        }
    }

    BottomSheetScaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("TODO") },
                actions = {
                    IconButton(
                        onClick = {
                            onNavigateToTaskScreenClicked()
                        }
                    ) {
                        Icon(Icons.Rounded.Add, "Add")
                    }
                    IconButton(
                        onClick = {
                            onNavigateToMenuScreenClicked()
                        }
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
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentWeatherState.value.currentWeatherResponse.location.name,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "Add",
                        modifier = modifier.padding(start = 8.dp)
                    )
                }

                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = "${currentWeatherState.value.currentWeatherResponse.current.temperatureCelsius}℃",
                    fontWeight = FontWeight.Bold,
                    fontSize = 64.sp,
                    style = MaterialTheme.typography.titleLarge
                )

                if (currentWeatherState.value.currentWeatherResponse.forecast.forecastDayList.isNotEmpty()) {
                    val forecastDay =
                        currentWeatherState.value.currentWeatherResponse.forecast.forecastDayList.first()
                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = "${forecastDay.day.maxTemperatureCelsius}℃ / ${forecastDay.day.minTemperatureCelsius}℃",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        modifier = modifier.padding(top = 8.dp),
                        text = "Sunrise ${forecastDay.astro.sunrise} / Sunset ${forecastDay.astro.sunset}",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = currentWeatherState.value.currentWeatherResponse.current.condition.text,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge
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
                    tabItemList.forEachIndexed { index, tabItem ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = {
                                selectedTabIndex = index
                                onTaskTabClicked(selectedTabIndex != 0)
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
                        .fillMaxWidth(),
                    userScrollEnabled = false
                ) { index ->
                    if (index == 0) {
                        val listState = rememberLazyListState()
                        LazyColumn(state = listState) {
                            itemsIndexed(
                                items = taskState.value.taskList,
                                key = { _, task ->
                                    task.id
                                }
                            ) { index, item ->
                                val state = rememberSwipeToDismissBoxState(
                                    confirmValueChange = {
                                        when (it) {
                                            SwipeToDismissBoxValue.StartToEnd -> onCompleteTask(item)
                                            SwipeToDismissBoxValue.EndToStart -> onDeleteTask(item)
                                            SwipeToDismissBoxValue.Settled -> {}
                                        }
                                        true
                                    }
                                )

                                SwipeToDismissBox(
                                    state = state,
                                    backgroundContent = {
                                        val color = when (state.dismissDirection) {
                                            SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.secondaryContainer
                                            SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.primaryContainer
                                            SwipeToDismissBoxValue.Settled -> Color.Transparent
                                        }
                                        Box(
                                            modifier = modifier
                                                .fillMaxSize()
                                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                                .background(color)
                                        ) {
                                            Icon(
                                                modifier = modifier
                                                    .padding(end = 16.dp)
                                                    .align(Alignment.CenterEnd),
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete"
                                            )
                                            Icon(
                                                modifier = modifier
                                                    .padding(start = 16.dp)
                                                    .align(Alignment.CenterStart),
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "CheckCircle"
                                            )
                                        }
                                    }
                                ) {
                                    CustomCardView(
                                        title = item.title,
                                        description = item.description,
                                        isComplete = item.isComplete
                                    )
                                }
                            }
                        }
                    } else {
                        val listState = rememberLazyListState()
                        LazyColumn(state = listState) {
                            itemsIndexed(
                                items = taskState.value.taskList,
                                key = { _, task ->
                                    task.id
                                }
                            ) { index, item ->
                                val state = rememberSwipeToDismissBoxState(
                                    confirmValueChange = {
                                        when (it) {
                                            SwipeToDismissBoxValue.StartToEnd -> onUndoCompletedTask(
                                                item
                                            )

                                            SwipeToDismissBoxValue.EndToStart -> onDeleteTask(item)
                                            SwipeToDismissBoxValue.Settled -> {}
                                        }
                                        true
                                    }
                                )

                                SwipeToDismissBox(
                                    state = state,
                                    backgroundContent = {
                                        val color = when (state.dismissDirection) {
                                            SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.secondaryContainer
                                            SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.primaryContainer
                                            SwipeToDismissBoxValue.Settled -> Color.Transparent
                                        }
                                        Box(
                                            modifier = modifier
                                                .fillMaxSize()
                                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                                .background(color)
                                        ) {
                                            Icon(
                                                modifier = modifier
                                                    .padding(end = 16.dp)
                                                    .align(Alignment.CenterEnd),
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete"
                                            )
                                            Icon(
                                                modifier = modifier
                                                    .padding(start = 16.dp)
                                                    .align(Alignment.CenterStart),
                                                imageVector = Icons.Default.Create,
                                                contentDescription = "Create"
                                            )
                                        }
                                    }
                                ) {
                                    CustomCardView(
                                        title = item.title,
                                        description = item.description,
                                        isComplete = item.isComplete
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        taskState = remember {
            mutableStateOf(
                TaskState(
                    taskList = listOf(Task(title = "Title", description = "Description"))
                )
            )
        },
        currentWeatherState = remember {
            mutableStateOf(
                CurrentWeatherState(
                    currentWeatherResponse = CurrentWeatherResponse(
                        location = Location(name = "Johannesburg"),
                        current = Current(
                            temperatureCelsius = 20.0,
                            condition = Condition(
                                text = "Sunny",
                                icon = "//cdn.weatherapi.com/weather/64x64/day/113.png"
                            )
                        ),
                        forecast = Forecast(
                            forecastDayList = listOf(
                                ForecastDay(
                                    day = Day(
                                        maxTemperatureCelsius = 25.0,
                                        minTemperatureCelsius = 12.0
                                    ),
                                    astro = Astro(
                                        sunrise = "06:00",
                                        sunset = "17:00"
                                    )
                                )
                            )
                        )
                    )
                )
            )
        },
        tabItemList = listOf(
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
        ),
        snackbarHostState = SnackbarHostState(),
        onCreate = {},
        onGetDeviceLocation = {},
        onDeleteTask = {},
        onCompleteTask = {},
        onUndoCompletedTask = {},
        onTaskTabClicked = {},
        onNavigateToMenuScreenClicked = {},
        onNavigateToTaskScreenClicked = {}
    )
}