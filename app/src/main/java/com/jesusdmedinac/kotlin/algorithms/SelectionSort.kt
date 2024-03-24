package com.jesusdmedinac.kotlin.algorithms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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

@Composable
fun ColumnScope.SelectionSort() {
    val initialList = listOf(8, 5, 2, 6, 9, 3, 1, 4, 0, 7)
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
    Text(text = "1.2 Selection Sort")
    LazyRow {
        itemsIndexed(sortedList, key = { _, item -> item }) { _, number ->
            RectTextNumber(
                number,
                modifier = Modifier
                    .background(Color.Yellow)
            )
        }
        itemsIndexed(unsortedList, key = { _, item -> item }) { index, number ->
            RectTextNumber(
                number,
                modifier = Modifier
                    .run {
                        if (pointingNumber == index)
                            border(2.dp, Color.Black)
                        else this
                    }
                    .run {
                        if (minNumber == number)
                            background(Color.Red)
                        else this
                    }
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
                    delay(1000)
                }
                sortedList += minNumber
                unsortedList = unsortedList
                    .toMutableList()
                    .apply {
                        if (isNotEmpty())
                            removeAt(minNumberIndex)
                    }
                pointingNumber = -1
                minNumber = -1
            }
            delay(3000)
            sortedList = emptyList()
            unsortedList = initialList
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