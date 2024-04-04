package ru.vityaman.scene

import io.kotest.common.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.vityaman.entity.person.Ford

class LimitedActorTest {
    @Test
    fun `Makes limit steps when nested actor is infinite`(): Unit = runBlocking {
        val lines = ArrayList<String>()
        PrintingPerformance(
            { lines.add(it) },
            LimitedActor(limit = 6, Ford())
        ).run()
        assertEquals(6, lines.size)
    }

    @Test
    fun `Makes less than limit steps when nested makes less`(): Unit = runBlocking {
        val lines = ArrayList<String>()
        PrintingPerformance(
            { lines.add(it) },
            LimitedActor(limit = 6, LimitedActor(limit = 2, Ford()))
        ).run()
        assertEquals(2, lines.size)
    }

    @Test
    fun `Limit must be natural number`(): Unit = runBlocking {
        assertThrows<IllegalArgumentException> {
            LimitedActor(limit = -6, Ford())
        }
    }
}