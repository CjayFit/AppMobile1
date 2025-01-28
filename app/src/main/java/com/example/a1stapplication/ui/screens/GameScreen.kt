package com.example.a1stapplication.ui.screens

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a1stapplication.R
import com.example.a1stapplication.core.Constant


@Composable
fun GameScreen(viewModel: GameScreeViewModel = viewModel()) {
    val uiSate = viewModel.uiState.collectAsStateWithLifecycle().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = uiSate.userTry.toString(), fontSize = 56.sp)
            Column {
                Button(onClick = {
                    viewModel.increment()
                }) { Text(text = "+", fontSize = 20.sp, )}
                Button(onClick = {
                    viewModel.decrement()
                }) { Text(text = "-", fontSize = 20.sp) }
            }
        }
        if (uiSate.status == GameStatus.WIN) {
            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = {
                    viewModel.restart()
                }
            ) {
                Text(text = stringResource(R.string.restart), fontSize = 32.sp)

            }

        } else{

            Button(
                modifier = Modifier.fillMaxWidth(0.5f),
                onClick = {
                    viewModel.validate()
                }
            ) {
                Text(text = stringResource(R.string.msg_try), fontSize = 32.sp)

            }
        }
        Text(text = when(uiSate.status){
            GameStatus.NEW_GAME -> stringResource(R.string.good_luck)
            GameStatus.TOO_HIGH -> stringResource(R.string.too_high)
            GameStatus.TOO_LOW -> stringResource(R.string.too_low)
            GameStatus.WIN -> stringResource(R.string.you_won)
        }, fontSize = 32.sp)
    }
}