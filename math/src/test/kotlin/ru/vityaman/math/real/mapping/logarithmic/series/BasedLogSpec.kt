package ru.vityaman.math.real.mapping.logarithmic.series

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import ru.vityaman.math.real.mapping.logarithmic.adapter.BasedLog
import kotlin.Double.Companion.MAX_VALUE
import kotlin.math.log

class BasedLogSpec : StringSpec({
    val eps = 0.001
    val ln = NaturalLogSeries(48)

    "Works as well as in standard library" {
        checkAll(Arb.double(0.0 + eps, MAX_VALUE), Arb.double(1.0 + eps, MAX_VALUE)) { x, base ->
            BasedLog(base, ln)(x) shouldBe (log(x, base) plusOrMinus eps)
        }
    }
})
