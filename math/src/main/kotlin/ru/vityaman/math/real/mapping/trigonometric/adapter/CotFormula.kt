package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Cot
import ru.vityaman.math.real.mapping.trigonometric.Sin

class CotFormula(
    private val cos: Cos,
    private val sin: Sin,
) : Cot {
    override fun invoke(x: Double): Double =
        cos(x) / sin(x)
}
