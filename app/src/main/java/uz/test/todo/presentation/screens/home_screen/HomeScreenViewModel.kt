package uz.test.todo.presentation.screens.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.test.todo.domain.use_case.AddTaskUseCase
import uz.test.todo.domain.use_case.DeleteTaskByIdUseCase
import uz.test.todo.domain.use_case.GetAllTasksUseCase
import uz.test.todo.presentation.mapper.toDomain
import uz.test.todo.presentation.mapper.toUi
import uz.test.todo.presentation.models.TaskModelUi
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskByIdUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

    init {
        getAllTasks()
    }

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase.invoke().collect { result ->
                result.onSuccess { tasks ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            taskList = tasks.map { it.toUi() },
                            error = null
                        )
                    }
                }
                result.onFailure { error ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            error = error.message ?: "Unknown error"
                        )
                    }
                }
            }
        }
    }

    fun addTask(task: TaskModelUi) {
        viewModelScope.launch {
            Log.d("TAGTODO", "addTask: called")
            addTaskUseCase(task.toDomain())
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(id)
        }
    }

    fun changeDialogState(show: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = currentState.dialogState.copy(
                    show = show
                )
            )
        }
    }

    fun changeTitle(newText: String) {
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = currentState.dialogState.copy(
                    title = newText
                )
            )
        }
    }

    fun changeDescription(newText: String) {
        _uiState.update { currentState ->
            currentState.copy(
                dialogState = currentState.dialogState.copy(
                    description = newText
                )
            )
        }
    }

}