package ru.vityaman.scene

import io.kotest.common.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.vityaman.effect.light.Brightness
import ru.vityaman.effect.light.JustLight
import ru.vityaman.entity.misc.Star

class NamedActorTest {
    @Test
    fun `Appends a name describing actor state`(): Unit = runBlocking {
        val star = Star(JustLight(Brightness(1)))
        assertEquals("Точка, излучающая Свет, у которого Яркость 1 уровня", star.state)
        assertEquals("Солнце - Точка, излучающая Свет, у которого Яркость 1 уровня", NamedActor("Солнце", star).state)
    }
}