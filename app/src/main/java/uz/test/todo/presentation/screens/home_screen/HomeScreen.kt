package uz.test.todo.presentation.screens.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavHostController
import uz.test.todo.R
import uz.test.todo.presentation.models.TaskModelUi
import uz.test.todo.presentation.screens.home_screen.dialog.AddTaskDialog
import uz.test.todo.presentation.ui.components.SwipeToDelete

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, navController: NavHostController) {

    val uiState = viewModel.uiState.collectAsState()

    val dialogState = uiState.value.dialogState

    val tasks = uiState.value.taskList
    val error = uiState.value.error

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
                title = {
                    Text(
                        text = "TODO APP",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold
                        )

                    )
                },
                modifier = Modifier.fillMaxWidth(),
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )

            if (tasks.isNotEmpty()) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    item {
                        Spacer(Modifier.height(20.dp))
                    }
                    items(
                        items = tasks,
                        key = { it.id }
                    ) { task ->
                        SwipeToDelete(
                            onDelete = {
                                viewModel.deleteTask(task.id)
                            }
                        ) {
                            TaskItem(
                                task = task,
                                onClick = {
                                    // TODO
                                },
                                onEdit = {
                                    // TODO
                                },
                                onChangeStatus = {
                                    viewModel.toggleIsCompleted(task.id)
                                },
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }

            } else if (error != null) {
                Text(
                    error,
                    color = Color.Red
                )
            } else {
                IfEmptyPlaceholder(
                    title = "Nothing to do"
                )
            }
        }

        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(bottom = 20.dp, end = 20.dp)
                .size(48.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                viewModel.changeDialogState(true)
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_add),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        if (uiState.value.dialogState.show) {
            AddTaskDialog(
                dialogState = dialogState,
                viewModel = viewModel,
                navController = navController
            )
        }

    }
}

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    onChangeStatus: (Boolean) -> Unit,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    task: TaskModelUi,
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onTertiary),
        elevation = CardDefaults.cardElevation(4.dp)

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Text(
                    text = task.description,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Spacer(Modifier.weight(1f))

            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = {
                    onChangeStatus(it)
                }
            )
        }
    }
}

@Composable
fun IfEmptyPlaceholder(
    title: String
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )

    }

}