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
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА", "\'test\'", "\uD83D\uDE03", "हिन्दी", "\u0000", "\u0BBD"])
    fun `Insertion adds an element`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        assertFalse(set.contains(value))
        set.insert(value)
        assertTrue(set.contains(value))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА", "\'test\'", "\uD83D\uDE03", "हिन्दी", "\u0000", "\u0BBD"])
    fun `Removal removes an element`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        set.insert(value)
        set.remove(value)
        assertFalse(set.contains(value))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА", "\'test\'", "\uD83D\uDE03", "हिन्दी", "\u0000", "\u0BBD"])
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
    @ValueSource(strings = ["", "a", "test", " ", "\n", "Б", "АБАСАБА", "\'test\'", "\uD83D\uDE03", "हिन्दी", "\u0000", "\u0BBD"])
    fun `Removal unknown element does nothing`(value: String) {
        val value = XorHashedString(value)

        val set = Set(10)
        assertFalse(set.contains(value))
        set.remove(value)
        assertFalse(set.contains(value))
    }

    @Test
    fun `Stores long strings`() {
        val strings = sequence {
            yield(1_000)
            yield(10_000)
            yield(100_000)
        }.map { XorHashedString(randomStringWithSize(it)) }.toList()

        val set = Set(12)

        for (string in strings) {
            set.insert(string)
        }

        for (string in strings) {
            assertTrue(set.contains(string))
        }
    }

    @Test
    fun `Resolves collisions of distinct string`() {
        val set = FixedChainHashMultiSet<ConstHashedString>(3)

        val test = ConstHashedString("test")
        val mouse = ConstHashedString("mouse")
        val joke = ConstHashedString("joke")
        val unknown = ConstHashedString("unknown")

        set.insert(test)
        set.insert(mouse)
        set.insert(test)
        set.insert(joke)

        assertIterableEquals(
            listOf(listOf(test, mouse, test, joke), listOf(), listOf()),
            set.buckets.asList(),
        )

        assertTrue(set.contains(test))
        assertTrue(set.contains(mouse))
        assertTrue(set.contains(joke))
        assertFalse(set.contains(unknown))

        set.remove(mouse)

        assertIterableEquals(
            listOf(listOf(test, test, joke), listOf(), listOf()),
            set.buckets.asList(),
        )

        set.remove(unknown)

        assertIterableEquals(
            listOf(listOf(test, test, joke), listOf(), listOf()),
            set.buckets.asList(),
        )
    }

    @Test
    fun `Respects equals`() {
        val left = java.lang.String("")
        val right = java.lang.String("")

        val set = FixedChainHashMultiSet<java.lang.String>(11)
        set.insert(left)
        set.insert(right)

        assertTrue(set.contains(left))
        assertTrue(set.contains(right))

        set.remove(left)
        set.remove(right)

        assertFalse(set.contains(left))
        assertFalse(set.contains(right))
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

    private fun randomStringWithSize(size: Int): String {
        val random = ThreadLocalRandom.current()
        val alphabet = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return random
            .ints(size.toLong(), 0, alphabet.size)
            .asSequence()
            .map(alphabet::get)
            .joinToString("")
    }

    private fun randomString(maxSize: Int): String {
        val size = randomInt(maxSize)
        return randomStringWithSize(size)
    }
}
