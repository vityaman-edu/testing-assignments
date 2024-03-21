package ru.vityaman.math

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SinSeriesTest {
    private val epsilon = 0.001

    @Test
    fun `Approximating the value of sin(3)`() {
        val x = 3.0
        assertEquals(+3.00000, SinSeries(1)(x), epsilon)
        assertEquals(-1.50000, SinSeries(2)(x), epsilon)
        assertEquals(+0.52500, SinSeries(3)(x), epsilon)
        assertEquals(+0.09107, SinSeries(4)(x), epsilon)
        assertEquals(+0.14531, SinSeries(5)(x), epsilon)
        assertEquals(+0.14087, SinSeries(6)(x), epsilon)
    }

    @Test
    fun `Approximating the value of sin(10)`() {
        val x = 10.0
        assertEquals(+0010.000000, SinSeries(1)(x), epsilon)
        assertEquals(-0156.666666, SinSeries(2)(x), epsilon)
        assertEquals(+0676.666666, SinSeries(3)(x), epsilon)
        assertEquals(-1307.460317, SinSeries(4)(x), epsilon)
        assertEquals(+1448.272000, SinSeries(5)(x), epsilon)
        assertEquals(-1056.939000, SinSeries(6)(x), epsilon)
        assertEquals(+0548.966000, SinSeries(7)(x), epsilon)
        assertEquals(-0215.751000, SinSeries(8)(x), epsilon)
    }
}
