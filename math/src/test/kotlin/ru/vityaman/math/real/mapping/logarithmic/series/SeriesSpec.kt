package ru.vityaman.math.real.mapping.logarithmic.series

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import kotlin.math.ln

class SeriesSpec : StringSpec({
    "NaturalLogSeries converges from 0 to 2" {
        checkAll(Arb.double(0.0, 2.0), Arb.int(32, 63)) { x, n ->
            NaturalLogSeries(n)(x) shouldBe (ln(x) plusOrMinus 0.001)
        }
    }
})
