package com.example.a1stapplication.ui.screens

import com.example.a1stapplication.core.Constant

enum class GameStatus{
    NEW_GAME,
    TOO_HIGH,
    TOO_LOW,
    WIN
}

data class GameUiSate(
    val userTry:Int = Constant.DEFAULT_TRY,
    val status: GameStatus = GameStatus.NEW_GAME
)
