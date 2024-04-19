package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Sec

class SecFromCos(private val cos: Cos) : Sec {
    override fun invoke(x: Double): Double =
        1.0 / cos(x)
}
