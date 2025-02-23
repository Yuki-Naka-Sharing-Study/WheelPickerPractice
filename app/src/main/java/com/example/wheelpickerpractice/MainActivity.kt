package com.example.wheelpickerpractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
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
        Text(text = "選択された年: $selectedYear", style = MaterialTheme.typography.titleMedium)
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
    var selectedYear by remember { mutableStateOf(startYear) }

    WheelDatePicker(
        startDate = LocalDate.of(startYear, 1, 1),
        minDate = LocalDate.of(minYear, 1, 1),
        maxDate = LocalDate.of(maxYear, 12, 31),
        size = DpSize(100.dp, 150.dp),
        rowCount = 5,
        textStyle = MaterialTheme.typography.bodyLarge,
        selectorProperties = WheelPickerDefaults.selectorProperties(
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFd3d3d3),
            border = BorderStroke(1.dp, Color.Cyan)
        )
    ) { snappedDate ->
        selectedYear = snappedDate.year
        onYearSelected(selectedYear)
    }
}
