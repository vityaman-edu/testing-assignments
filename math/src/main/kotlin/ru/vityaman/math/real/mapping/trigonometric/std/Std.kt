package ru.vityaman.math.real.mapping.trigonometric.std

import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Sin
import kotlin.math.cos
import kotlin.math.sin

class StdSin : Sin {
    override fun invoke(x: Double): Double = sin(x)
}

class StdCos : Cos {
    override fun invoke(x: Double): Double = cos(x)
}
