package ru.vityaman.math.real.mapping.trigonometric.series

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class SeriesSpec : StringSpec({
    "SinSeries converges from -pi to pi" {
        checkAll(Arb.double(-PI, PI), Arb.int(8, 16)) { x, n ->
            SinSeries(n)(x) shouldBe (sin(x) plusOrMinus 0.001)
        }
    }
    "CosSeries converges from -pi to pi" {
        checkAll(Arb.double(-PI, PI), Arb.int(8, 16)) { x, n ->
            CosSeries(n)(x) shouldBe (cos(x) plusOrMinus 0.001)
        }
    }
})
