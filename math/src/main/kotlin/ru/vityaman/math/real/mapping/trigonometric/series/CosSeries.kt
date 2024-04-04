package ru.vityaman.math.real.mapping.trigonometric.series

import ru.vityaman.math.combinatorics.factorial
import ru.vityaman.math.real.mapping.Mapping
import kotlin.math.pow
import kotlin.math.sign

class CosSeries(private val limit: Int) : Mapping {
    companion object {
        private const val LIMIT_MAX: Int = 32
        private val limitRange = (1 until LIMIT_MAX)
    }

    init {
        require(limit in limitRange) {
            "limit must be in range $limitRange, but $limit was given"
        }
    }

    override operator fun invoke(x: Double): Double =
        (0 until limit).sumOf { n ->
            sign(-1.0).pow(n) * (x.pow(2 * n) / factorial(2L * n))
        }
}
