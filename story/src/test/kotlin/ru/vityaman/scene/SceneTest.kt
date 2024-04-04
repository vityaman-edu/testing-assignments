package ru.vityaman.scene

import io.kotest.common.runBlocking
import org.junit.jupiter.api.Test
import ru.vityaman.effect.light.Brightness
import ru.vityaman.effect.light.JustLight
import ru.vityaman.entity.misc.Star
import ru.vityaman.entity.person.Arthur
import ru.vityaman.entity.person.Ford
import ru.vityaman.entity.spaceship.that.ThatEngine
import ru.vityaman.entity.spaceship.that.ThatSpaceship
import ru.vityaman.entity.spaceship.that.ThatSpeed
import ru.vityaman.location.Space

class SceneTest {
    @Test
    fun `That Story`(): Unit = runBlocking {
        PrintingPerformance(
            { println(it) },
            LimitedActor(
                limit = 8,
                Scene(
                    Space(), listOf(
                        NamedActor(
                            "Звезды",
                            ActorGroup(
                                listOf(
                                    NamedActor("Солнце", Star(JustLight(Brightness(666)))),
                                    NamedActor("Тусклик", Star(JustLight(Brightness(100))))
                                )
                            )
                        ),
                        ThatSpaceship(ThatEngine(), ThatSpeed(), setOf(Arthur(), Ford()))
                    )
                )
            )
        ).run()
    }
}