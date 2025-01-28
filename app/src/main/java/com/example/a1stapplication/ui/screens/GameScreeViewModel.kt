package com.example.a1stapplication.ui.screens

import androidx.lifecycle.ViewModel
import com.example.a1stapplication.core.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameScreeViewModel : ViewModel() {

    private var _answer = (Constant.MIN_TRY..Constant.MAX_TRY).random()
    private val _uiState = MutableStateFlow(GameUiSate())
    val uiState: StateFlow<GameUiSate> = _uiState.asStateFlow()
    fun increment(){
        if (uiState.value.userTry >= Constant.MAX_TRY)
            return
        _uiState.update {
            _uiState.value.copy(userTry = it.userTry+1 )
        }
    }
    fun decrement(){
        if (uiState.value.userTry <= Constant.MIN_TRY)
            return
        _uiState.update {
            _uiState.value.copy(userTry = it.userTry-1 )
        }
    }
    fun validate() {
        val newStatus : GameStatus = if(uiState.value.userTry == _answer) {
            GameStatus.WIN
        } else if (uiState.value.userTry > _answer) {
            GameStatus.TOO_HIGH
        }else{
            GameStatus.TOO_LOW
        }
        _uiState.update{
            _uiState.value.copy(status = newStatus)
        }
    }

    fun restart() {
        _answer = (Constant.MIN_TRY..Constant.MAX_TRY).random()
        _uiState.update {
            GameUiSate()
        }
    }
}