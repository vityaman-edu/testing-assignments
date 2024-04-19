package ru.vityaman.math.real.mapping.combination

import ru.vityaman.math.real.mapping.Mapping

class Division(
    private val left: Mapping,
    private val right: Mapping,
) : Mapping {
    override fun invoke(x: Double): Double =
        left(x) / right(x)
}

operator fun Mapping.div(rhs: Mapping): Mapping =
    Division(this, rhs)
