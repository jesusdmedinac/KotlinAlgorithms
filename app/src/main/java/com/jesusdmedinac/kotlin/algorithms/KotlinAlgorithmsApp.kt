package com.jesusdmedinac.kotlin.algorithms

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

@Composable
fun KotlinAlgorithmsApp() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            InsertionSort()
        }
    }
}

@Composable
fun InsertionSort() {
    val initialList = listOf(6, 5, 3, 1, 8, 7, 2, 4)
    var tempUnsortedList by remember {
        mutableStateOf(initialList.toList())
    }
    var tempSortedList by remember {
        mutableStateOf(emptyList<Int>())
    }
    var sortingNumber by remember {
        mutableStateOf(0)
    }

    Text(text = "1.1 Insertion Sort")
    Text(text = "Sorting Number: $sortingNumber")
    LazyRow {
        items(tempSortedList, key = { it }) { number ->
            RectTextNumber(sortingNumber, number)
        }
        items(tempUnsortedList, key = { it }) { number ->
            RectTextNumber(sortingNumber, number)
        }
    }
    LaunchedEffect(Unit) {
        while (true) {
            /*
             * While step 1 - sortingList = [6, 5, 3, 1, 8, 7, 2, 4]
             */
            tempUnsortedList = initialList.toMutableList()
            /*
             * While step 1 - tempSortedList = []
             */
            tempSortedList = mutableListOf()
            /**
             * For step 1 - sortingNumberInList = 6 in [6, 5, 3, 1, 8, 7, 2, 4]
             * For step 2 - sortingNumberInList = 5 in [6, 5, 3, 1, 8, 7, 2, 4]
             * For step 3 - sortingNumberInList = 3 in [6, 5, 3, 1, 8, 7, 2, 4]
             */
            for (sortingNumberInList in initialList) {
                /**
                 * For step 1 - sortingNumber = 6
                 * For step 2 - sortingNumber = 5
                 * For step 3 - sortingNumber = 3
                 */
                sortingNumber = sortingNumberInList
                /**
                 * For step 1 - tempSortedList.isEmpty() = true
                 * For step 2 - tempSortedList.isEmpty() = false
                 * For step 3 - tempSortedList.isEmpty() = false
                 */
                if (tempSortedList.isEmpty()) {
                    /**
                     * For step 1 - tempSortedList = [6]
                     */
                    tempSortedList += sortingNumber
                } else {
                    /**
                     * For step 2 - For step 1 - index = 0 in tempSortedList.size - 1 = 0 downTo 0
                     * For step 3 - For step 1 - index = 1 in tempSortedList.size - 1 = 0 downTo 0
                     * For step 3 - For step 2 - index = 0 in tempSortedList.size - 1 = 0 downTo 0
                     */
                    val lastIndex = tempSortedList.size - 1
                    var index = lastIndex
                    do {
                        /**
                         * For step 2 - For step 1 - sortingUpNumberInTempList = 6
                         * For step 3 - For step 1 - sortingUpNumberInTempList = 6
                         * For step 3 - For step 2 - sortingUpNumberInTempList = 5
                         */
                        val sortingUpNumberInTempList = tempSortedList[index]

                        /**
                         * For step 2 - For step 1 - sortingDownNumberInTempList = -1
                         * For step 3 - For step 1 - sortingDownNumberInTempList = 5
                         * For step 3 - For step 2 - sortingDownNumberInTempList = -1
                         */
                        val sortingDownNumberInTempList =
                            tempSortedList.getOrElse(index - 1) { -1 }
                        when {
                            /**
                             * For step 2 - For step 1 - sortingNumber = 5 in sortingDownNumberInTempList = -1 to sortingUpNumberInTempList = 6 = true
                             * For step 3 - For step 1 - sortingNumber = 3 in sortingDownNumberInTempList = 5 to sortingUpNumberInTempList = 6 = false
                             * For step 3 - For step 2 - sortingNumber = 3 in sortingDownNumberInTempList = -1 to sortingUpNumberInTempList = 5 = true
                             */
                            sortingNumber >= sortingUpNumberInTempList -> tempSortedList =
                                tempSortedList
                                    .toMutableList()
                                    .apply { add(sortingNumber) }
                                    .also { index = -1 }

                            sortingNumber in sortingDownNumberInTempList..<sortingUpNumberInTempList ->
                                /**
                                 * For step 2 - For step 1 - tempSortedList = [5, 6]
                                 * For step 3 - For step 2 - tempSortedList = [3, 5, 6]
                                 */
                                tempSortedList = tempSortedList
                                    .toMutableList()
                                    .apply { add(index, sortingNumber) }
                                    .also { index = -1 }
                        }
                        /**
                         * For step 2 - For step 1 - ended
                         * For step 3 - For step 1 - ended
                         * For step 3 - For step 2 - ended
                         */
                        index--
                    } while (index >= 0)
                }
                /**
                 * For step 1 - tempUnsortedList = [5, 3, 1, 8, 7, 2, 4]
                 * For step 2 - tempUnsortedList = [3, 1, 8, 7, 2, 4]
                 * For step 3 - tempUnsortedList = [1, 8, 7, 2, 4]
                 */
                if (tempUnsortedList.isNotEmpty())
                    tempUnsortedList = tempUnsortedList
                        .toMutableList()
                        .apply { removeAt(0) }
                /**
                 * For step 1 - ended
                 * For step 2 - ended
                 * For step 3 - ended
                 */
                delay(1000)
            }
            sortingNumber = -1
            /**
             * While step 1 ended
             */
            delay(3000)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LazyItemScope.RectTextNumber(sortingNumber: Int, number: Int) {
    val borderColor = when {
        sortingNumber == number -> Color.Red
        else -> Color.Black
    }
    Text(
        text = "$number",
        modifier = Modifier
            .animateItemPlacement(
                animationSpec = spring(
                    stiffness = Spring.StiffnessHigh,
                    visibilityThreshold = IntOffset.VisibilityThreshold,
                    dampingRatio = Spring.DampingRatioHighBouncy
                )
            )
            .clip(RectangleShape)
            .border(2.dp, borderColor)
            .padding(4.dp)
    )
}

@Composable
@Preview
fun KotlinAlgorithmsAppPreview() {
    KotlinAlgorithmsApp()
}