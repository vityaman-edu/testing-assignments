package ru.vityaman.scene

import io.kotest.common.runBlocking
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test

class SwitchingActorTest {
    @Test
    fun `Behave like multiple switching one by one actors`(): Unit = runBlocking {
        val lines = ArrayList<String>()
        PrintingPerformance(
            { lines.add(it) },
            Scene(Polygon(), listOf(
                SwitchingActor(
                    sequence {
                        yield(Tester("Вася"))
                        yield(Tester("Вася"))
                        yield(Tester("Петя"))
                        yield(Tester("Рома"))
                        yield(Tester("умер"))
                    }
                )
            ))
        ).run()
        assertIterableEquals(
            listOf(
                "Находясь в Полигон есть Тестировщик Вася",
                "Находясь в Полигон есть Тестировщик Вася",
                "Находясь в Полигон есть Тестировщик Петя",
                "Находясь в Полигон есть Тестировщик Рома",
                "Находясь в Полигон есть Тестировщик умер",
            ),
            lines
        )
    }
}