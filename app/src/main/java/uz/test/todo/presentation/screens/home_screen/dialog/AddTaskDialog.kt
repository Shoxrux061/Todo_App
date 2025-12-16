package uz.test.todo.presentation.screens.home_screen.dialog

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import uz.test.todo.presentation.models.TaskModelUi
import uz.test.todo.presentation.screens.home_screen.HomeScreenViewModel

@Composable
fun AddTaskDialog(
    dialogState: DialogState,
    viewModel: HomeScreenViewModel,
    navController: NavHostController
) {

    Dialog(
        onDismissRequest = {
            viewModel.changeDialogState(false)
        },
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = dialogState.title,
                    onValueChange = {
                        viewModel.changeTitle(it)
                    }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = dialogState.description,
                    onValueChange = {
                        viewModel.changeDescription(it)
                    }
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (dialogState.title.isNotEmpty() && dialogState.description.isNotEmpty()) {
                            viewModel.addTask(
                                TaskModelUi(
                                    title = dialogState.title,
                                    description = dialogState.description
                                )
                            )
                            viewModel.changeDialogState(false)
                        } else {
                            Toast.makeText(
                                navController.context,
                                "Title or description can't be empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        text = "Add"
                    )
                }
            }
        }
    }
}