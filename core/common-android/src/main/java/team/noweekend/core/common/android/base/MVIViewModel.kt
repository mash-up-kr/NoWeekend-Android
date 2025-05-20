package team.noweekend.core.common.android.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
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

/**
 * MVI 아키텍처에 기반한 [ViewModel]입니다.
 *
 * 스크린에서 사용되는 [Intent], [SideEffect], [UiState]를 정의합니다.
 */
abstract class MVIViewModel<I : Intent, SE : SideEffect, S : UiState>(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialState: S by lazy { createInitialState(savedStateHandle) }
    protected abstract fun createInitialState(savedStateHandle: SavedStateHandle): S

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    protected val currentState: S
        get() = _uiState.value

    private val _sideEffect: Channel<SE> =
        Channel(onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val sideEffect: Flow<SE> = _sideEffect.receiveAsFlow()

    protected val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            handleClientException(throwable)
        }

    override fun onCleared() {
        super.onCleared()
        _sideEffect.close()
    }

    /**
     * 사용자의 행위를 전달합니다.
     *
     * 정의된 Intent를 ViewModel에 전달하고, 해당 Intent를 통해 UiState의 갱신([reduce])
     * 또는 사이드 이펙트를 실행([postSideEffect]) 시킬 수 있습니다.
     *
     * @param intent 전달할 Intent 객체
     */
    fun intent(intent: I): Job = execute {
        handleIntent(intent)
    }

    /**
     * [Exception] 발생 시 [coroutineExceptionHandler]에 의해 동작할 예외처리를 정의합니다.
     *
     * @param throwable [coroutineExceptionHandler]에 의해 catch된 예외
     */
    protected abstract fun handleClientException(throwable: Throwable)

    /**
     * [MVIViewModel]을 상속하는 ViewModel에서는 해당 메서드를 구현하여 수신한 Intent의 처리 동작을 정의할 수 있습니다.
     *
     * @param intent 수신한 Intent 객체
     */
    protected abstract suspend fun handleIntent(intent: I)

    /**
     * 앱 내 상태 변경이 아닌 추적, 탐색 등과 같은 작업을 사이드 이펙트를 통해 처리합니다.
     *
     * [sideEffect]의 처리는 Route 별로 정의되는 별도 SideEffectHandler를 통해 처리합니다.
     *
     * @param sideEffect 전달할 SideEffect 객체
     */
    protected suspend fun postSideEffect(sideEffect: SE) {
        _sideEffect.send(sideEffect)
    }

    /**
     * 현재 상태 ([currentState]와 특정 [action]과 함께 새로운 상태를 생성하여 방출합니다.
     *
     * @param action [currentState]를 변경할 함수
     */
    protected fun reduce(action: S.() -> S) {
        _uiState.update { currentState.action() }
    }

    /**
     * [coroutineExceptionHandler]를 적용한 [viewModelScope]를 제공합니다.
     *
     * 안전한 호출을 위해 상위 코루틴 스코프 생성 시 [launch]대신 사용 할 것을 권장합니다
     *
     * @param action 호출 coroutine
     */
    protected inline fun execute(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job = viewModelScope.launch(
        context = context + coroutineExceptionHandler,
        start = start
    ) {
        action()
    }
}
