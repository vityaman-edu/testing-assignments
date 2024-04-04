package ru.vityaman.math.real.mapping

fun interface Mapping {
    operator fun invoke(x: Double): Double
}
