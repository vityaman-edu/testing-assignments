package ru.vityaman.math.real.mapping.logarithmic.series

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import kotlin.Double.Companion.MAX_VALUE
import kotlin.Double.Companion.NEGATIVE_INFINITY
import kotlin.math.ln

class NaturalLogSeriesSpec : StringSpec({
    val eps = 0.001

    "Converges from 0 to 2" {
        checkAll(Arb.double(0.0 + eps, 2.0), Arb.int(32, 63)) { x, n ->
            NaturalLogSeries(n)(x) shouldBe (ln(x) plusOrMinus eps)
        }
    }

    "Converges everywhere" {
        checkAll(Arb.double(0.0 + eps, MAX_VALUE), Arb.int(32, 63)) { x, n ->
            NaturalLogSeries(n)(x) shouldBe (ln(x) plusOrMinus eps)
        }
    }

    "Is undefined on negative" {
        checkAll(Arb.double(NEGATIVE_INFINITY, 0.0 - eps), Arb.int(32, 63)) { x, n ->
            NaturalLogSeries(n)(x) shouldBe Double.NaN
        }
    }
})
