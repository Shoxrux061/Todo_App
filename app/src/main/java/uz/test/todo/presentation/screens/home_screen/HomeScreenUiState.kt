package uz.test.todo.presentation.screens.home_screen

import uz.test.todo.presentation.models.TaskModelUi
import uz.test.todo.presentation.screens.home_screen.dialog.DialogState

data class HomeScreenUiState(

    val taskList: List<TaskModelUi> = emptyList(),
    val error: String? = null,
    val dialogState: DialogState = DialogState()
)
