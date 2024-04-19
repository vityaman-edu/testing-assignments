package ru.vityaman.math.example

import ru.vityaman.math.real.mapping.Mapping
import ru.vityaman.math.real.mapping.logarithmic.NaturalLog
import ru.vityaman.math.real.mapping.logarithmic.adapter.BasedLog
import ru.vityaman.math.real.mapping.logarithmic.series.NaturalLogSeries
import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Sec
import ru.vityaman.math.real.mapping.trigonometric.Sin
import ru.vityaman.math.real.mapping.trigonometric.adapter.CosFromSin
import ru.vityaman.math.real.mapping.trigonometric.adapter.FixedSinSeries
import ru.vityaman.math.real.mapping.trigonometric.adapter.SecFromCos
import ru.vityaman.math.real.mapping.trigonometric.series.SinSeries
import kotlin.math.pow

class ExampleMapping(
    private val sin: Sin = FixedSinSeries(SinSeries(limit = 16)),
    private val cos: Cos = CosFromSin(sin),
    private val sec: Sec = SecFromCos(cos),
    private val ln: NaturalLog = NaturalLogSeries(limit = 48),
    private val log2: BasedLog = BasedLog(2.0, ln),
    private val log3: BasedLog = BasedLog(3.0, ln),
    private val log5: BasedLog = BasedLog(5.0, ln),
    private val log10: BasedLog = BasedLog(10.0, ln),
) : Mapping {
    // x <= 0 : (((((sin(x) * sec(x)) / sec(x)) + cos(x)) * ((sec(x) / sec(x)) - (sin(x) * cos(x)))) ^ 2)
    // x > 0 : (((((log_2(x) + log_2(x)) - log_3(x)) ^ 2) + (ln(x) + log_10(x))) / ((ln(x) ^ 2) - (log_5(x) - log_2(x))))
    override fun invoke(x: Double): Double = if (x <= 0) {
        (((((sin(x) * sec(x)) / sec(x)) + cos(x)) * ((sec(x) / sec(x)) - (sin(x) * cos(x)))).pow(2))
    } else {
        (((((log2(x) + log2(x)) - log3(x)).pow(2)) + (ln(x) + log10(x))) / ((ln(x).pow(2)) - (log5(x) - log2(x))))
    }

}