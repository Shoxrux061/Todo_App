package uz.test.todo.data.mapper

import uz.test.todo.data.local.room.TaskEntity
import uz.test.todo.domain.model.tasks.TaskModel


fun TaskEntity.toDomain(): TaskModel {

    return TaskModel(
        id = taskId,
        title = title,
        description = description,
        isCompleted = isCompleted
    )

}

fun TaskModel.toData(): TaskEntity {
    return TaskEntity(
        taskId = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}