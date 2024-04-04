package ru.vityaman.collection

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class FixedChainHashMultiSetTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 100, 1_000, 10_000])
    fun `Creatable with positive buckets count`(bucketsCount: Int) {
        assertDoesNotThrow { FixedChainHashMultiSet<Any>(bucketsCount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -1_000, -10_000])
    fun `Can't create with non-positive buckets count`(bucketsCount: Int) {
        assertThrows<IllegalArgumentException> {
            FixedChainHashMultiSet<Any>(bucketsCount)
        }
    }
}
