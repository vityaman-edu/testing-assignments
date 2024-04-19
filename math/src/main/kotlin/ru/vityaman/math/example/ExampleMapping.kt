package ru.vityaman.math.example

import ru.vityaman.math.real.mapping.Mapping
import ru.vityaman.math.real.mapping.logarithmic.NaturalLog
import ru.vityaman.math.real.mapping.logarithmic.adapter.BasedLog
import ru.vityaman.math.real.mapping.logarithmic.series.NaturalLogSeries
import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.Cot
import ru.vityaman.math.real.mapping.trigonometric.Csc
import ru.vityaman.math.real.mapping.trigonometric.Sec
import ru.vityaman.math.real.mapping.trigonometric.Sin
import ru.vityaman.math.real.mapping.trigonometric.adapter.CosFromSin
import ru.vityaman.math.real.mapping.trigonometric.adapter.CotFormula
import ru.vityaman.math.real.mapping.trigonometric.adapter.CscFormula
import ru.vityaman.math.real.mapping.trigonometric.adapter.FixedSinSeries
import ru.vityaman.math.real.mapping.trigonometric.adapter.SecFromCos
import ru.vityaman.math.real.mapping.trigonometric.series.SinSeries
import kotlin.math.pow

class ExampleMapping(
    val sin: Sin = FixedSinSeries(SinSeries(limit = 10)),
    val cos: Cos = CosFromSin(sin),
    val sec: Sec = SecFromCos(cos),
    val cot: Cot = CotFormula(cos, sin),
    val csc: Csc = CscFormula(sin),
    val ln: NaturalLog = NaturalLogSeries(limit = 63),
    val log2: BasedLog = BasedLog(2.0, ln),
    val log5: BasedLog = BasedLog(5.0, ln),
    val log10: BasedLog = BasedLog(10.0, ln),
) : Mapping {
    // x <= 0 : (((((cot(x) + sec(x)) / sec(x)) - sec(x)) * (((sin(x) ^ 3) + sec(x)) + (sin(x) + csc(x)))) ^ 3)
    // x > 0 : (((((log_5(x) ^ 2) ^ 2) - log_2(x)) - log_2(x)) + ((log_5(x) ^ 3) / (ln(x) / ((log_2(x) ^ 3) + (log_10(x) - log_5(x))))))
    override fun invoke(x: Double): Double = if (x <= 0) {
        (((((cot(x) + sec(x)) / sec(x)) - sec(x)) * (((sin(x).pow(3)) + sec(x)) + (sin(x) + csc(x)))).pow(3))
    } else {
        (((((log5(x).pow(2)).pow(2)) - log2(x)) - log2(x)) + ((log5(x).pow(3)) / (ln(x) / ((log2(x).pow(3)) + (log10(x) - log5(x))))))
    }
}
