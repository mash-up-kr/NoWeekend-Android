package team.noweekend.core.common.android.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import team.noweekend.core.common.android.mvi.Intent
import team.noweekend.core.common.android.mvi.SideEffect
import team.noweekend.core.common.android.mvi.UiState
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class MVIViewModel<I : Intent, SE : SideEffect, S : UiState>(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialState: S by lazy { createInitialState(savedStateHandle) }
    protected abstract fun createInitialState(savedStateHandle: SavedStateHandle): S

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val currentState: S
        get() = _uiState.value

    private val _sideEffect: Channel<SideEffect> =
        Channel(onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val sideEffect: Flow<SideEffect> = _sideEffect.receiveAsFlow()

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleClientException(throwable)
    }

    override fun onCleared() {
        super.onCleared()
        _sideEffect.close()
    }

    fun intent(intent: Intent) = launch {
        handleIntent(intent)
    }

    protected abstract fun handleClientException(throwable: Throwable)

    protected abstract suspend fun handleIntent(intent: Intent)

    protected open fun navigateBack() = launch {
        postSideEffect(SideEffect.NavigateToHistoryBack)
    }

    protected suspend fun postSideEffect(sideEffect: SideEffect) {
        _sideEffect.send(sideEffect)
    }

    protected fun reduce(action: S.() -> S) {
        _uiState.update { currentState.action() }
    }

    protected inline fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        crossinline action: suspend CoroutineScope.() -> Unit,
    ) = viewModelScope.launch(
        context = context + coroutineExceptionHandler,
        start = start
    ) {
        action()
    }
}