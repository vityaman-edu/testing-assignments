package ru.vityaman.math

import kotlin.math.pow
import kotlin.math.sign

class SinSeries constructor(private val limit: Int) {
    companion object {
        private const val LIMIT_MAX: Int = 32
        private val limitRange = (1 until LIMIT_MAX)
    }

    init {
        require(limit in limitRange) {
            "limit must be in range $limitRange, but $limit was given"
        }
    }

    operator fun invoke(x: Double): Double =
        (0 until limit).sumOf { n ->
            sign(-1.0).pow(n) * (x.pow(2 * n + 1) / factorial(2L * n + 1))
        }

    private fun factorial(n: Long): Long =
        if (n in setOf(0L, 1L)) {
            1
        } else {
            n * factorial(n - 1)
        }
}
