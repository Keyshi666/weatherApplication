package com.example.weatherapplication

sealed class Routes(val route: String) {
    object Home : Routes("home")
}
