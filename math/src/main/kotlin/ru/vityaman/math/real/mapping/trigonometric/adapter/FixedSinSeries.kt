package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Sin
import ru.vityaman.math.real.mapping.trigonometric.series.SinSeries

class FixedSinSeries(sin: SinSeries) : Sin {
    private val sin = Pi2PiAdapted(sin)

    override fun invoke(x: Double): Double = sin(x)
}