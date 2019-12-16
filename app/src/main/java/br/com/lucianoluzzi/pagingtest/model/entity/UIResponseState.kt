package br.com.lucianoluzzi.pagingtest.model.entity

sealed class UIResponseState {
    object Loading: UIResponseState()
    data class Error(val errorMessage: String): UIResponseState()
    data class Success<T>(val content: T): UIResponseState() {
        inline fun <reified T> isInstanceOf(instance: Any?): Boolean {
            return instance is T
        }
    }
}