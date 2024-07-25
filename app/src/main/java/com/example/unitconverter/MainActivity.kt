package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var input by remember { mutableStateOf("") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iSelected by remember { mutableStateOf("Meters") }
    var oSelected by remember { mutableStateOf("Meters") }
    var iConversionFactor by remember { mutableStateOf(1.0) }
    var oConversionFactor by remember { mutableStateOf(1.0) }
    var result by remember { mutableStateOf(0.0) }

    fun convertUnits(){
        val inputValDouble = input.toDoubleOrNull() ?:0.0
        result = ((inputValDouble * iConversionFactor * 100.0) / oConversionFactor) / 100.0
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = input, onValueChange = {
            input = it
            convertUnits()
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            Box{
                Button(onClick = { iExpanded = true }) {
                    Text(text = iSelected)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        iSelected = "Meters"
                        iConversionFactor = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iSelected = "Centimeters"
                        iConversionFactor = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Kilometers") }, onClick = {
                        iSelected = "Kilometers"
                        iConversionFactor = 1000.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        iSelected = "Millimeters"
                        iConversionFactor = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick = { oExpanded = true }) {
                    Text(text = oSelected)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        oSelected = "Meters"
                        oConversionFactor = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oSelected = "Centimeters"
                        oConversionFactor = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Kilometers") }, onClick = {
                        oSelected = "Kilometers"
                        oConversionFactor = 1000.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        oSelected = "Millimeters"
                        oConversionFactor = 0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Results: $result $oSelected ", style = MaterialTheme.typography.headlineLarge)


    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}