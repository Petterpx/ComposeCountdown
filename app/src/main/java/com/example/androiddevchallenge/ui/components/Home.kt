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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author petterp
 * @Date 2021/3/5-6:16 下午
 * @Email ShiyihuiCloud@163.com
 * @Function Main interface
 */

@Composable
fun Home() {
    Column(Modifier.wrapContentHeight(align = Alignment.CenterVertically)) {
        val isTimeStartState = mutableStateOf(false)
        val isResetState = mutableStateOf(true)
        Countdown(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .size(350.dp),
            isTimeStartState, isResetState
        )
        TimeContainer(
            isTimeStartState, isResetState,
            Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Time() {
    Home()
}
