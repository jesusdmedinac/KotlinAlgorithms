package com.jesusdmedinac.kotlin.algorithms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SelectionSort(initialList: List<Int> = emptyList()) {
    var sortedList by remember {
        mutableStateOf(emptyList<Int>())
    }
    var unsortedList by remember {
        mutableStateOf(initialList)
    }
    var pointingNumber by remember {
        mutableStateOf(-1)
    }
    var minNumber by remember {
        mutableStateOf(-1)
    }
    var minNumberIndex by remember {
        mutableStateOf(-1)
    }
    var operations by remember {
        mutableStateOf(0)
    }

    Text(text = "1.2 Selection Sort", style = MaterialTheme.typography.titleLarge)
    Text(text = "Operations: $operations", style = MaterialTheme.typography.titleMedium)
    LazyRow {
        itemsIndexed(
            listOf(sortedList, unsortedList).flatten(),
            key = { _, item -> item }) { _, number ->
            val modifier = when {
                minNumber == number -> Modifier
                    .background(Color.Red)

                number in sortedList -> Modifier
                    .border(2.dp, Color.Black)

                else -> Modifier
            }
            RectTextNumber(
                number,
                modifier = modifier
            )
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            repeat(initialList.size) {
                unsortedList.forEachIndexed { index, number ->
                    pointingNumber = index
                    when {
                        minNumber == -1 -> {
                            minNumber = number
                            minNumberIndex = index
                        }

                        number <= minNumber -> {
                            minNumber = number
                            minNumberIndex = index
                        }
                    }
                    operations++
                }
                delay(10_000L / initialList.size)
                sortedList += minNumber
                unsortedList = unsortedList
                    .toMutableList()
                    .apply {
                        if (isNotEmpty())
                            removeAt(minNumberIndex)
                    }
                pointingNumber = -1
                minNumber = -1
                operations++
            }
            delay(3000)
            sortedList = emptyList()
            unsortedList = initialList
            operations = 0
        }
    }
}

@Preview
@Composable
fun SelectionSortPreview() {
    Column {
        SelectionSort()
    }
}