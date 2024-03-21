package ru.vityaman.math

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import kotlin.math.PI
import kotlin.math.sin

class SinSeriesSpec : StringSpec({
    "Converges from -pi to pi" {
        checkAll(Arb.double(-PI, PI), Arb.int(8, 16)) { x, n ->
            SinSeries(n)(x) shouldBe (sin(x) plusOrMinus 0.001)
        }
    }
})
