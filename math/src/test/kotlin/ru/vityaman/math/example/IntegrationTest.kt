package ru.vityaman.math.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class IntegrationTest {
    val eps = 0.001
    val f = ExampleMapping()

    @ParameterizedTest
    @CsvFileSource(resources = ["/points.csv"], numLinesToSkip = 1)
    fun `Big-Bang on chosen points`(x: Double, expected: Double) {
        val actual = f(x)
        assertEquals(expected, actual, eps)
    }
}
