package ru.vityaman.math.real.mapping.logarithmic.series

import ru.vityaman.math.real.mapping.Mapping
import kotlin.math.pow

class NaturalLogSeries(private val limit: Int) : Mapping {
    companion object {
        private const val LIMIT_MAX: Int = 64
        private val limitRange = (1 until LIMIT_MAX)
    }

    init {
        require(limit in limitRange) {
            "limit must be in range ${limitRange}, but $limit was given"
        }
    }

    override operator fun invoke(x: Double): Double =
        when (x) {
            -0.0 -> Double.NaN
            Double.POSITIVE_INFINITY -> Double.POSITIVE_INFINITY
            else -> 1 / (x / (x - 1) - (1 until limit).sumOf { k ->
                2.0.pow(-k) * x.pow(2.0.pow(-k)) / (1 + x.pow(2.0.pow(-k)))
            })
        }
}
