package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.trigonometric.Cos
import ru.vityaman.math.real.mapping.trigonometric.series.CosSeries

class FixedCosSeries(cos: CosSeries) : Cos {
    private val cos = Pi2PiAdapted(cos)

    override fun invoke(x: Double): Double = cos(x)
}