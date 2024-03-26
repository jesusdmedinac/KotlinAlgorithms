package com.jesusdmedinac.kotlin.algorithms

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun InsertionSort(initialList: List<Int> = emptyList()) {
    var tempUnsortedList by remember {
        mutableStateOf(initialList.toList())
    }
    var tempSortedList by remember {
        mutableStateOf(emptyList<Int>())
    }
    var sortingNumber by remember {
        mutableStateOf(0)
    }
    var operations by remember {
        mutableStateOf(0)
    }

    Text(text = "1.1 Insertion Sort", style = MaterialTheme.typography.titleLarge)
    Text(text = "Operations: $operations", style = MaterialTheme.typography.titleMedium)
    LazyRow {
        itemsIndexed(
            listOf(tempSortedList, tempUnsortedList).flatten(),
            key = { _, item -> item }) { _, number ->
            val modifier = when {
                number in tempSortedList && number == sortingNumber -> Modifier
                    .border(2.dp, Color.Red)
                    .background(Color.Red)
                number in tempUnsortedList -> Modifier.border(2.dp, Color.White)
                else -> Modifier.border(2.dp, Color.Black)
            }
            RectTextNumber(
                number,
                modifier = modifier
            )
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            for (sortingNumberInList in initialList) {
                sortingNumber = sortingNumberInList
                if (tempSortedList.isEmpty()) {
                    tempSortedList += sortingNumber
                } else {
                    val lastIndex = tempSortedList.size - 1
                    var index = lastIndex
                    do {
                        val sortingUpNumberInTempList = tempSortedList[index]
                        val sortingDownNumberInTempList =
                            tempSortedList.getOrElse(index - 1) { -1 }
                        when {
                            sortingNumber >= sortingUpNumberInTempList -> tempSortedList =
                                tempSortedList
                                    .toMutableList()
                                    .apply { add(sortingNumber) }
                                    .also { index = -1 }

                            sortingNumber in sortingDownNumberInTempList..<sortingUpNumberInTempList ->
                                tempSortedList = tempSortedList
                                    .toMutableList()
                                    .apply { add(index, sortingNumber) }
                                    .also { index = -1 }
                        }
                        index--
                        operations++
                    } while (index >= 0)
                }
                if (tempUnsortedList.isNotEmpty())
                    tempUnsortedList = tempUnsortedList
                        .toMutableList()
                        .apply { removeAt(0) }
                delay(10_000L / initialList.size)
                sortingNumber = -1
                operations++
            }
            delay(3000)
            tempUnsortedList = initialList.toMutableList()
            tempSortedList = mutableListOf()
            operations = 0
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.RectTextNumber(number: Int, modifier: Modifier = Modifier) {
    Text(
        text = "$number",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .animateItemPlacement(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    visibilityThreshold = IntOffset.VisibilityThreshold,
                    dampingRatio = Spring.DampingRatioHighBouncy
                )
            )
            .clip(RectangleShape)
            .then(modifier)
            .padding(4.dp)
    )
}

@Preview
@Composable
fun InsertionSortPreview() {
    Column {
        InsertionSort()
    }
}