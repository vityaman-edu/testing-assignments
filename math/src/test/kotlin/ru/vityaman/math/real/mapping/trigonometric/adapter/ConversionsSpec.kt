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

class ConversionsSpec : StringSpec({
    "SinFromCos works as well as SinSeries" {
        checkAll(Arb.double(-8 * PI, +8 * PI), Arb.int(8, 16)) { x, n ->
            SinFromCos(FixedCosSeries(CosSeries(n)))(x) shouldBe (FixedSinSeries(SinSeries(n))(x) plusOrMinus 0.001)
        }
    }

    "CosFromSin works as well as CosSeries" {
        checkAll(Arb.double(-8 * PI, +8 * PI), Arb.int(8, 16)) { x, n ->
            CosFromSin(FixedSinSeries(SinSeries(n)))(x) shouldBe (FixedCosSeries(CosSeries(n))(x) plusOrMinus 0.001)
        }
    }

    "SecFromCos works as well as standard" {
        checkAll(Arb.double(-8 * PI, +8 * PI), Arb.int(8, 16)) { x, n ->
            SecFromCos(FixedCosSeries(CosSeries(n)))(x) shouldBe (1 / cos(x) plusOrMinus 0.001)
        }
    }
})