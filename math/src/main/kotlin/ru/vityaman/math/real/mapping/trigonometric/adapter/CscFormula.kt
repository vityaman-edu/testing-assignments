package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Csc
import ru.vityaman.math.real.mapping.trigonometric.Sin

class CscFormula(private val sin: Sin) : Csc {
    override fun invoke(x: Double): Double =
        1 / sin(x)
}
