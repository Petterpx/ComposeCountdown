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

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/**
 * Composable with animation can be displayed in segments
 */
@Composable
fun AnimatedCircle(
    duration: Int,
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier,
    progressState: MutableState<Float>,
) {

    val currentState = remember {
        MutableTransitionState(AnimatedCircleProgress.START)
            .apply { targetState = AnimatedCircleProgress.END }
    }

    val segments = mutableListOf<Float>()

    proportions.forEachIndexed { index, _ ->
        segments.add(
            proportions.filterIndexed { itemIndex, _ ->
                itemIndex <= index
            }.sum()
        )
    }

    val stroke = with(LocalDensity.current) { Stroke(10.dp.toPx()) }
    val transition = updateTransition(currentState)
    val angleOffset by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = duration * 1000,
                easing = LinearEasing
            )
        }
    ) { progress ->
        if (progress == AnimatedCircleProgress.START) {
            0f
        } else {
            1f
        }
    }

    // 计算这次将分几段来画
    val segmentSum = segments.filterSegment(angleOffset)

    progressState.value = angleOffset
    // 当前扫过角度
    val angleOffsetX = angleOffset * 360
    Canvas(modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.width - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        // 开始绘制
        (0..segmentSum).forEach {
            val startAngle = if (it == 0) 0f else segments[it - 1] * 360f
            val sweepAngle = angleOffsetX - startAngle
            drawArc(
                color = colors[it],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
        }
    }
}

fun List<Float>.filterSegment(angleOffset: Float): Int {
    forEachIndexed { index, fl ->
        if (angleOffset <= fl) {
            return index
        }
    }
    return 0
}

// 目的是保证类型安全
private enum class AnimatedCircleProgress { START, END }
