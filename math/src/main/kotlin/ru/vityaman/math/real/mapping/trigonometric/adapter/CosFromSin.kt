package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Sin
import kotlin.math.PI

class CosFromSin(private val sin: Sin) : Cos {
    override fun invoke(x: Double): Double =
        sin(PI / 2 - x)
}
