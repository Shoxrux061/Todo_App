package uz.test.todo.core.utils

suspend fun <T> safeDatabaseCall(
    block: suspend () -> T
): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Exception) {
        Result.failure(e)
    }
}