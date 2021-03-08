/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @Author petterp
 * @Date 2021/3/4-11:12 下午
 * @Email ShiyihuiCloud@163.com
 * @Function Countdown Simple
 */

@Composable
fun Countdown(modifier: Modifier, isState: MutableState<Boolean>, reSetState: MutableState<Boolean>) {
    Box(modifier = modifier) {
        val expanded = mutableStateOf(0f)

        if (reSetState.value) {
            Text(
                text = "10s countdown",
                modifier = Modifier
                    .background(Color.White)
                    .align(Alignment.Center)
            )
        } else {
            if (isState.value) {
                reSetState.value = false
                AnimatedCircle(
                    duration = 10,
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.Center)
                        .padding(20.dp)
                        .fillMaxSize(),
                    proportions = listOf(0.5f, 0.3f, 0.2f),
                    colors = listOf(Color.Green, Color.Gray, Color.Red),
                    progressState = expanded
                )
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        text = "schedule",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = String.format("%.1f", expanded.value*10),
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    if (expanded.value == 1f) {
                        isState.value = false
                        reSetState.value = true
                    }
                }
            } else {
                Text(
                    text = "Sample pause",
                    modifier = Modifier
                        .background(Color.White)
                        .align(Alignment.Center)
                )
            }
        }
    }
}
