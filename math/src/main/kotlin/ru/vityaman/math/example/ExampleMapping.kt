package ru.vityaman.math.example

import ru.vityaman.math.real.mapping.Mapping
import ru.vityaman.math.real.mapping.logarithmic.adapter.BasedLog
import ru.vityaman.math.real.mapping.logarithmic.series.NaturalLogSeries
import ru.vityaman.math.real.mapping.trigonometric.adapter.CosFromSin
import ru.vityaman.math.real.mapping.trigonometric.adapter.FixedSinSeries
import ru.vityaman.math.real.mapping.trigonometric.adapter.SecFromCos
import ru.vityaman.math.real.mapping.trigonometric.series.SinSeries
import kotlin.math.pow

class ExampleMapping : Mapping {
    private val sin = FixedSinSeries(SinSeries(limit = 16))
    private val cos = CosFromSin(sin)
    private val sec = SecFromCos(cos)
    private val ln = NaturalLogSeries(limit = 48)
    private val log_2 = BasedLog(2.0, ln)
    private val log_3 = BasedLog(3.0, ln)
    private val log_5 = BasedLog(5.0, ln)
    private val log_10 = BasedLog(10.0, ln)

    // x <= 0 : (((((sin(x) * sec(x)) / sec(x)) + cos(x)) * ((sec(x) / sec(x)) - (sin(x) * cos(x)))) ^ 2)
    // x > 0 : (((((log_2(x) + log_2(x)) - log_3(x)) ^ 2) + (ln(x) + log_10(x))) / ((ln(x) ^ 2) - (log_5(x) - log_2(x))))
    override fun invoke(x: Double): Double = if (x <= 0) {
        (((((sin(x) * sec(x)) / sec(x)) + cos(x)) * ((sec(x) / sec(x)) - (sin(x) * cos(x)))).pow(2))
    } else {
        (((((log_2(x) + log_2(x)) - log_3(x)).pow(2)) + (ln(x) + log_10(x))) / ((ln(x).pow(2)) - (log_5(x) - log_2(x))))
    }

}