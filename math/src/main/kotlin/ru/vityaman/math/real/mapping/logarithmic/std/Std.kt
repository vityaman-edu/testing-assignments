package ru.vityaman.math.real.mapping.logarithmic.std

import ru.vityaman.math.real.mapping.logarithmic.NaturalLog
import kotlin.math.ln

class StdLn : NaturalLog {
    override fun invoke(x: Double): Double = ln(x)
}
