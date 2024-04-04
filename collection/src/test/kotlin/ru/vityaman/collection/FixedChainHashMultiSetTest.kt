package ru.vityaman.collection

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

data class XorHashedString(private val content: String) {
    override fun hashCode(): Int {
        var code = 0
        for (symbol in content) {
            code = code xor symbol.code
            code = code shl 16
        }
        return code
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as XorHashedString
        return content == other.content
    }
}

typealias Set = FixedChainHashMultiSet<XorHashedString>

class FixedChainHashMultiSetTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 100, 1_000, 10_000])
    fun `Creatable with positive buckets count`(bucketsCount: Int) {
        assertDoesNotThrow { Set(bucketsCount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -1_000, -10_000])
    fun `Can't create with non-positive buckets count`(bucketsCount: Int) {
        assertThrows<IllegalArgumentException> {
            Set(bucketsCount)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА"])
    fun `Insertion adds an element`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        assertFalse(set.contains(value))
        set.insert(value)
        assertTrue(set.contains(value))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА"])
    fun `Removal removes an element`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        set.insert(value)
        set.remove(value)
        assertFalse(set.contains(value))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА"])
    fun `Duplicates are supported`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        set.insert(value)
        set.insert(value)
        assertTrue(set.contains(value))
        set.remove(value)
        assertTrue(set.contains(value))
        set.remove(value)
        assertFalse(set.contains(value))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА"])
    fun `Removal unknown element does nothing`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        assertFalse(set.contains(value))
        set.remove(value)
        assertFalse(set.contains(value))
    }

    @Test
    fun `Sample usage`() {
        val a = XorHashedString("a")
        val abc = XorHashedString("abc")
        val test = XorHashedString("test")

        val set = Set(5)

        set.insert(a)
        set.insert(abc)
        set.insert(abc)
        set.insert(test)

        assertTrue(set.contains(a))
        assertTrue(set.contains(abc))
        assertTrue(set.contains(test))

        set.remove(test)

        assertTrue(set.contains(a))
        assertTrue(set.contains(abc))
        assertFalse(set.contains(test))

        set.remove(abc)

        assertTrue(set.contains(a))
        assertTrue(set.contains(abc))
        assertFalse(set.contains(test))

        set.remove(abc)

        assertTrue(set.contains(a))
        assertFalse(set.contains(abc))
        assertFalse(set.contains(test))
    }

    @Test
    fun `Collisions are resolved using chaining`() {
        val a = XorHashedString("a")
        val abc = XorHashedString("abc")
        val test = XorHashedString("test")

        val set = Set(3)

        assertIterableEquals(
            set.buckets.asList(), listOf<List<String>>(
                emptyList(), emptyList(), emptyList(),
            )
        )

        set.insert(a)
        set.insert(abc)
        set.insert(test)
        set.insert(abc)

        assertIterableEquals(
            set.buckets.asList(),
            listOf(listOf(abc, abc), listOf(a), listOf(test)),
        )

        set.remove(test)

        assertIterableEquals(
            set.buckets.asList(),
            listOf(listOf(abc, abc), listOf(a), listOf()),
        )

        set.remove(abc)

        assertIterableEquals(
            set.buckets.asList(),
            listOf(listOf(abc), listOf(a), listOf()),
        )

        set.remove(abc)

        assertIterableEquals(
            set.buckets.asList(),
            listOf(listOf(), listOf(a), listOf()),
        )
    }
}
