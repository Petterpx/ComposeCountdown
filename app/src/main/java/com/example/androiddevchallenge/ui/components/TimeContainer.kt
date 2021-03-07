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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author petterp
 * @Date 2021/3/5-6:16 下午
 * @Email ShiyihuiCloud@163.com
 * @Function TimeContainer Simple
 */

@Composable
fun TimeContainer(
    isStartState: MutableState<Boolean>,
    isResetState: MutableState<Boolean>,
    modifier: Modifier
) {
    Row(modifier.padding(top = 40.dp)) {
        val btnModifier = Modifier.padding(10.dp)
        Button(
            modifier = btnModifier,
            onClick = {
                if (!isStartState.value) {
                    isResetState.value = false
                    isStartState.value = true
                } else {
                    isStartState.value = false
                }
            }
        ) {
            Text(text = if (isStartState.value) "Stop" else "Play")
        }
        Spacer(Modifier.width(30.dp))
        Button(
            modifier = btnModifier,
            onClick = {
                isResetState.value = true
                isStartState.value = false
            }
        ) {
            Text(text = "Reset")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContainer() {
    TimeContainer(mutableStateOf(false), mutableStateOf(false), Modifier)
}
