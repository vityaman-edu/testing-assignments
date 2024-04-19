package ru.vityaman.math.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.arbitrary.filter
import io.kotest.property.checkAll
import ru.vityaman.math.real.mapping.logarithmic.std.StdLn
import ru.vityaman.math.real.mapping.trigonometric.std.StdCos
import ru.vityaman.math.real.mapping.trigonometric.std.StdSin

class ExampleSpec : StringSpec({
    val eps = 0.01

    val actual = ExampleMapping()

    val expected = ExampleMapping(
        sin = StdSin(),
        cos = StdCos(),
        ln = StdLn(),
    )

    "Custom dependencies work as well as stdlib from -1000 to 0" {
        checkAll(Arb.double(-1000.0, 0.0 - eps)) { x ->
            actual(x).shouldBeWithinPercentageOf(expected(x), eps)
        }
    }

    "Custom dependencies work as well as stdlib from 0 to 1000" {
        checkAll(Arb.double(0.0 + eps, 1000.0).filter { it != 1.0 }) { x ->
            actual(x).shouldBeWithinPercentageOf(expected(x), eps)
        }
    }
})
