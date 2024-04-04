package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Sin
import kotlin.math.PI

class SinFromCos(private val cos: Cos) : Sin {
    override fun invoke(x: Double): Double =
        cos(PI / 2 - x)
}