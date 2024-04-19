package ru.vityaman.math.real.mapping.logarithmic.adapter

import ru.vityaman.math.real.mapping.Mapping
import ru.vityaman.math.real.mapping.logarithmic.NaturalLog

class BasedLog(private val base: Double, private val ln: NaturalLog) : Mapping {
    override fun invoke(x: Double): Double =
        ln(x) / ln(base)
}
