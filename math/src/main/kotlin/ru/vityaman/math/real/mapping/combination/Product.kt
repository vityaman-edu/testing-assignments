package ru.vityaman.math.real.mapping.combination

import ru.vityaman.math.real.mapping.Mapping

class Product(
    private val left: Mapping,
    private val right: Mapping,
) : Mapping {
    override fun invoke(x: Double): Double =
        left(x) * right(x)
}

operator fun Mapping.times(rhs: Mapping): Mapping =
    Product(this, rhs)
