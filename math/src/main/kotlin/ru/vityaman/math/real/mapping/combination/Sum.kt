package ru.vityaman.math.real.mapping.combination

import ru.vityaman.math.real.mapping.Mapping

class Sum(
    private val left: Mapping,
    private val right: Mapping,
) : Mapping {
    override fun invoke(x: Double): Double =
        left(x) + right(x)
}

operator fun Mapping.plus(rhs: Mapping): Mapping =
    Sum(this, rhs)
