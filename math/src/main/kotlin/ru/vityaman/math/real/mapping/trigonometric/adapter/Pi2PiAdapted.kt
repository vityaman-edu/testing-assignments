package ru.vityaman.math.real.mapping.trigonometric.adapter

import ru.vityaman.math.real.mapping.Mapping
import kotlin.math.PI
import kotlin.math.ceil
import kotlin.math.floor

class Pi2PiAdapted(private val nested: Mapping) : Mapping {
    override fun invoke(x: Double): Double =
        nested(converted(x))

    private fun converted(x: Double): Double {
        val lower = (-PI - x) / (2 * PI)
        val upper = (+PI - x) / (2 * PI)
        assert(ceil(lower) == floor(upper))
        val shift = ceil(lower)
        return x + 2 * PI * shift
    }
}
