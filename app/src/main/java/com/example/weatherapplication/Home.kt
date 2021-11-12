package com.example.weatherapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapplication.ui.theme.WeatherApplicationTheme

@Composable
fun Home(){
    WeatherApplicationTheme(darkTheme=false){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        )
        {
            Row (
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(
                    horizontalAlignment = Alignment.Start) {
                    text()
                    Text("Сменить город")
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Row () {
                        Text("º   ", fontSize = 18.sp)
                        CustomToggleGroup()
                    }
                    CreateToggleButton()
                }
            }
            Column(Modifier.padding(top=167.dp, start=46.dp,end=60.dp)){
                DisplayCurrentWeather("Облачно", -10)
            }
            Row (Modifier.padding(top=480.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column (verticalArrangement = Arrangement.SpaceBetween){
                    CreateWeatherProperty("Ветер", "5 м/с, западный")
                    CreateWeatherProperty("Влажность", "60%")
                }
                Column (verticalArrangement = Arrangement.SpaceBetween){
                    CreateWeatherProperty("Давление", "752 мм рт. ст.")
                    CreateWeatherProperty("Вероятность дождя", "10%")
                }
            }
        }


    }
}

@Composable
fun DisplayCurrentWeather(weather: String, weatherDegrees: Int){
    Column(
        Modifier
        .size(269.dp, 240.dp)){
        Row(
            Modifier
            .size(269.dp, 180.dp)) {
            Image(imageVector = ImageVector.vectorResource(R.drawable.ic_cloud),
                contentDescription = null,
                Modifier.size(135.dp, 200.dp))
            Text(weatherDegrees.toString())
        }
        Text(weather)
    }
}

@Composable
fun CreateWeatherProperty(key: String, value: String){
    Column {
        Text(text = key)
        Text(
            text = value,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CreateToggleButton() {
    val checked = remember {
        mutableStateOf(false)
    }
    IconToggleButton(
        checked = checked.value,
        onCheckedChange = { checked.value = it },
        Modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceAround){
            Icon(painterResource(R.drawable.ic_location), "location")
            Text(
                "Мое местоположение",
                Modifier
                    .padding(5.dp)
                    .toggleable(value = checked.value, onValueChange = { checked.value = it }),
                fontSize = 18.sp,
                color =
                if (checked.value) {
                    Color.Red
                }
                else {
                    Color.Blue
                }
            )
        }
    }
}



@Composable
fun text() {
    Text(stringResource(R.string.city), style = TextStyle(fontSize = 22.sp))
}

@Composable
fun img() {
    Image(
        imageVector = ImageVector.vectorResource(R.drawable.ic_storm),
        contentDescription = "Android",
        modifier = Modifier.size(50.dp, 50.dp)
    )
}


@Composable
fun CustomToggleGroup() {
    val options = listOf(
        "C",
        "F",
    )

    var selectedOption by remember {
        mutableStateOf("")
    }

    val onSelectionChange = { text: String ->
        selectedOption = text
    }

    Row (modifier = Modifier
        .clip(
            RoundedCornerShape(size = 12.dp)
        ))
    {
        options.forEach { text ->
            Column(
                modifier = Modifier
                    .background(color = Color.Green)
                    .padding(
                        all = 0.dp,
                    ),

                ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    color = Color.White,
                    modifier = Modifier
                        .clickable {
                            onSelectionChange(text)
                        }
                        .background(
                            if (text == selectedOption) {
                                Color.Magenta
                            } else {
                                Color.LightGray
                            }
                        )
                        .padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        ),
                )
            }
        }
    }
}