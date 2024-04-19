package ru.vityaman.math.real.mapping.combination

import ru.vityaman.math.real.mapping.Mapping
import kotlin.math.pow

class Powered(private val mapping: Mapping, private val power: Int) : Mapping {
    override fun invoke(x: Double): Double =
        mapping(x).pow(power)
}

fun Mapping.pow(power: Int): Powered =
    Powered(this, power)
