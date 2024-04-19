package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.Mapping
import ru.vityaman.math.real.mapping.trigonometric.Cos

class SecFromCos(private val cos: Cos) : Mapping {
    override fun invoke(x: Double): Double =
        1.0 / cos(x)
}
