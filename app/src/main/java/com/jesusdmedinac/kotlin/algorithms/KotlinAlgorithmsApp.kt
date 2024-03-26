package com.jesusdmedinac.kotlin.algorithms

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun KotlinAlgorithmsApp() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            var initialList by remember {
                mutableStateOf(emptyList<Int>())
            }
            if (initialList.isEmpty()) {
                CircularProgressIndicator()
            } else {
                InsertionSort(initialList)
                SelectionSort(initialList.map { it * 10 })
                BubbleSort(initialList.map { it * 100 })
            }
            LaunchedEffect(Unit) {
                launch {
                    val maxNumber = 10
                    val list = mutableListOf<Int>()
                    repeat((1..maxNumber).count()) {
                        var random: Int
                        do {
                            random = Random.nextInt(1, maxNumber + 1)
                        } while (random in list)
                        list.add(random)
                    }
                    initialList = list
                }
            }
        }
    }
}

@Composable
@Preview
fun KotlinAlgorithmsAppPreview() {
    KotlinAlgorithmsApp()
}