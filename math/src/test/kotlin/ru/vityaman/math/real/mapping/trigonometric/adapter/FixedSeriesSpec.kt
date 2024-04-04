package ru.vityaman.math.real.mapping.trigonometric.adapter

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import ru.vityaman.math.real.mapping.trigonometric.series.CosSeries
import ru.vityaman.math.real.mapping.trigonometric.series.SinSeries
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class FixedSeriesSpec : StringSpec({
    "FixedSinSeries works as well as standard" {
        checkAll(Arb.double(-8 * PI, +8 * PI), Arb.int(8, 16)) { x, n ->
            FixedSinSeries(SinSeries(n))(x) shouldBe (sin(x) plusOrMinus 0.001)
        }
    }
    "FixedCosSeries works as well as standard" {
        checkAll(Arb.double(-8 * PI, +8 * PI), Arb.int(8, 16)) { x, n ->
            FixedCosSeries(CosSeries(n))(x) shouldBe (cos(x) plusOrMinus 0.001)
        }
    }
})