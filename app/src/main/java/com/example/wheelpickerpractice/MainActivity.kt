package com.example.wheelpickerpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.WheelTextPicker
import com.example.wheelpickerpractice.ui.theme.WheelPickerPracticeTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WheelPickerPracticeTheme {
                YearPickerExample()
            }
        }
    }
}

@Composable
fun YearPickerExample() {
    var selectedYear by remember { mutableIntStateOf(LocalDate.now().year) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "選択された年: $selectedYear",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        YearPicker(
            startYear = selectedYear,
            onYearSelected = { year ->
                selectedYear = year
                // 必要に応じて他の処理をここに追加
            }
        )
    }
}

@Composable
fun YearPicker(
    startYear: Int = LocalDate.now().year,
    minYear: Int = 1900,
    maxYear: Int = LocalDate.now().year,
    onYearSelected: (Int) -> Unit
) {
    val years = (minYear..maxYear).toList()
    val startIndex = years.indexOf(startYear)

    WheelTextPicker(
        texts = years.map { it.toString() },
        startIndex = startIndex,
        rowCount = 3,
        onScrollFinished = { snappedIndex ->
            onYearSelected(years[snappedIndex])
            snappedIndex
        },
        modifier = Modifier.size(200.dp, 200.dp)
    )
}
