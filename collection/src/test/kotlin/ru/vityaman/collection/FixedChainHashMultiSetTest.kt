package ru.vityaman.collection

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.abs
import kotlin.streams.asSequence

typealias Set = FixedChainHashMultiSet<XorHashedString>

class FixedChainHashMultiSetTest {
    // TODO: check that set uses equals not reference compare, new String
    // TODO: check very long string
    // TODO: check different string with same hash
    // TODO: string with ', strange symbols, invalid unicode

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
            set.buckets.asList(), listOf<List<XorHashedString>>(
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

    @Test
    fun `XorHash keys distribution`() {
        val strings = (1..1000).map { randomString(maxSize = 100) }

        val bucketsCount = 37
        val set = Set(bucketsCount)
        for (string in strings) {
            set.insert(XorHashedString(string))
        }

        val distribution = set.buckets.map { it.size }
        assertTrue(distribution.max() < 2 * strings.size / bucketsCount)
    }

    @Test
    fun `Works as well as reference solution`() {
        val silly = ListMultiSet<XorHashedString>()
        val smart = FixedChainHashMultiSet<XorHashedString>(37)

        for (i in 1..1_000) {
            val action = randomInt(3)
            val string = XorHashedString(randomString(5))
            when (action) {
                0 -> assertEquals(silly.contains(string), smart.contains(string))
                1 -> assertEquals(silly.insert(string), smart.insert(string))
                2 -> assertEquals(silly.remove(string), smart.remove(string))
            }
        }
    }

    private fun randomInt(limit: Int): Int {
        return abs(ThreadLocalRandom.current().nextInt()) % limit
    }

    private fun randomString(maxSize: Int): String {
        val random = ThreadLocalRandom.current()
        val alphabet = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val size = randomInt(maxSize).toLong()
        return random
            .ints(size, 0, alphabet.size)
            .asSequence()
            .map(alphabet::get)
            .joinToString("")
    }
}
