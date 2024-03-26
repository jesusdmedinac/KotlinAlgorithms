package com.jesusdmedinac.kotlin.algorithms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
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
import java.util.Collections
import kotlin.random.Random

@Composable
fun BubbleSort(initialList: List<Int> = emptyList()) {
    var unsortedList by remember {
        mutableStateOf(initialList)
    }
    var sortedList by remember {
        mutableStateOf(emptyList<Int>())
    }
    var sortingPair by remember {
        mutableStateOf(0 to 0)
    }
    var operations by remember {
        mutableStateOf(0)
    }
    Text(text = "1.3 Bubble Sort", style = MaterialTheme.typography.titleLarge)
    Text(text = "Operations: $operations", style = MaterialTheme.typography.titleMedium)
    LazyRow {
        itemsIndexed(listOf(unsortedList, sortedList).flatten(), key = { _, item -> item }) { index, number ->
            val modifier = when {
                number in unsortedList && (index == sortingPair.first || index == sortingPair.second) -> Modifier
                    .background(Color.Red)
                number in sortedList -> Modifier
                    .border(2.dp, Color.Black)
                else -> Modifier
            }
            RectTextNumber(
                number = number,
                modifier = modifier
            )
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            do {
                for (index in 0 until unsortedList.size - 1) {
                    sortingPair = index to index + 1
                    if (unsortedList[sortingPair.first] >
                        unsortedList[sortingPair.second]
                    ) {
                        Collections.swap(unsortedList, sortingPair.first, sortingPair.second)
                    }
                    operations++
                }
                sortedList = sortedList
                    .toMutableList()
                    .apply { if (unsortedList.isNotEmpty()) add(0, unsortedList.last()) }
                unsortedList = unsortedList
                    .toMutableList()
                    .apply { if (unsortedList.isNotEmpty()) removeLast() }
                delay(10_000L / initialList.size)
                operations++
            } while (sortedList.size < initialList.size)
            delay(3000)
            sortingPair = 0 to 0
            sortedList = emptyList()
            unsortedList = initialList
            operations = 0
        }
    }
}

@Preview
@Composable
fun BubbleSortPreview() {
    Column {
        BubbleSort()
    }
}