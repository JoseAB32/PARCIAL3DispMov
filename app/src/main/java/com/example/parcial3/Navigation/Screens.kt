package com.example.parcial3.Navigation

sealed class Screens (val route: String) {
    object PlanesScreen : Screens("planes")
    object FormEnvioSimScreen : Screens("formEnvioSim")
}