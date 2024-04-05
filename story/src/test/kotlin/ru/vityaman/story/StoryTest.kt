package ru.vityaman.story

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StoryTest {
    private val lines = mutableListOf<String>()

    @BeforeEach
    fun beforeEach() {
        Story.subscribe { lines.add(it) }
    }

    @AfterEach
    fun afterEach() {
        Story.reset()
    }

    @Test
    fun `Like a given text`() {
        val stars = setOf(Star(), Star(), Star())
        val space = Space("Черная пустота", setOf(*stars.toTypedArray(), Star(), Star()))
        val team = setOf(Person("Артур"), Person("Форд"))
        val food = Food(listOf("Хлеб", "Молоко", "Банан"))
        val spaceship = Spaceship(space, team, food)

        for (i in 1..7) {
            spaceship.speedUp()
        }

        spaceship.lookAway()

        spaceship.haveLunch()
        spaceship.speedUp()

        stars.forEach {
            it.increaseBrightness()
            it.increaseBrightness()
            it.increaseBrightness()
        }

        spaceship.lookAway()

        assertIterableEquals(
            listOf(
                "Корабль Стоит",
                "Корабль ускорился, летит Еле-еле, а мотор Жужжит",
                "Корабль ускорился, летит Разгоняется, а мотор Свистит",
                "Корабль ускорился, летит Пошел-пошел, а мотор Свистит",
                "Корабль ускорился, летит Летит, а мотор Ревет",
                "Корабль ускорился, летит Как конфети летит, а мотор Ревет",
                "Корабль ускорился, летит Как конфети летит, а мотор Ревет",
                "Корабль ускорился, летит Как конфети летит, а мотор Ревет",
                "Артур, Форд посмотрели в окно, а там: Вселенная Черная пустота, так много в ней звезд: Горит Никак, Горит Никак, Горит Никак, Горит Никак, Горит Никак",
                "Время обеда",
                "Артур захотел скушать Хлеб",
                "Кто-то стащил Хлеб",
                "Артур насытился Хлеб",
                "Форд захотел скушать Молоко",
                "Кто-то стащил Молоко",
                "Форд насытился Молоко",
                "Корабль ускорился, летит Как конфети летит, а мотор Ревет",
                "Артур, Форд посмотрели в окно, а там: Вселенная Черная пустота, так много в ней звезд: Горит Сияет, Горит Сияет, Горит Сияет, Горит Никак, Горит Никак",
            ),
            lines
        )

        spaceship.speedUp()
        assertEquals("Ревет", spaceship.soundDescription)
        spaceship.speedUp()
        assertEquals("Ревет", spaceship.soundDescription)
        spaceship.speedUp()
        assertEquals("Ревет", spaceship.soundDescription)

        assertThrows<IllegalStateException> {
            spaceship.haveLunch()
        }
    }

    @Test
    fun `Star brightness`() {
        val star = Star()
        assertEquals("Горит Никак", star.description)
        star.increaseBrightness()
        assertEquals("Горит Тускло", star.description)
        star.increaseBrightness()
        assertEquals("Горит Ярко", star.description)
        star.increaseBrightness()
        assertEquals("Горит Сияет", star.description)
        star.increaseBrightness()
        assertEquals("Горит Ааа, глазааа", star.description)
        assertThrows<IllegalStateException> {
            star.increaseBrightness()
        }
    }

    @Test
    fun `Spaceship lunch ok`() {
        val spaceship = Spaceship(Space("a", emptySet()), emptySet(), Food(emptyList()))
        spaceship.haveLunch()
    }

    @Test
    fun `Spaceship lunch failed`() {
        val spaceship = Spaceship(Space("a", emptySet()), setOf(Person("sorry")), Food(emptyList()))
        assertThrows<IllegalStateException> {
            spaceship.haveLunch()
        }
    }

    @Test
    fun `Spaceship lunch failed after one`() {
        val spaceship = Spaceship(Space("a", emptySet()), setOf(Person("sorry")), Food(listOf("bread")))
        spaceship.haveLunch()
        assertThrows<IllegalStateException> {
            spaceship.haveLunch()
        }
    }

    @Test
    fun `Lunch sequence`() {
        val spaceship = Spaceship(
            Space("a", emptySet()),
            setOf(Person("sorry")),
            Food(listOf("bread", "milk"))
        )
        spaceship.haveLunch()
        spaceship.haveLunch()
        assertThrows<IllegalStateException>("Еды больше нет") {
            spaceship.haveLunch()
        }

        assertIterableEquals(
            listOf(
                "Корабль Стоит",
                "Время обеда",
                "sorry захотел скушать bread",
                "Кто-то стащил bread",
                "sorry насытился bread",
                "Время обеда",
                "sorry захотел скушать milk",
                "Кто-то стащил milk",
                "sorry насытился milk",
                "Время обеда",
            ),
            lines,
        )
    }

    @Test
    fun `Star minimum`() {
        val star = Star()
        assertThrows<IllegalStateException>("Темнее уж никак") {
            star.decreaseBrightness()
        }
    }

    @Test
    fun `Empty spaceship look away`() {
        assertThrows<IllegalStateException>("Некому смотреть") {
            val spaceship = Spaceship(Space("a", emptySet()), setOf(), Food(listOf()))
            spaceship.lookAway()
        }
    }

    @Test
    fun `Food take`() {
        val food = Food(listOf("a", "b", "c"))
        food.take("b")
        assertThrows<IllegalStateException> { food.take("d") }
        food.take(food.showFirst())
        food.take(food.showFirst())
        assertThrows<IllegalStateException> { food.showFirst() }
    }
}