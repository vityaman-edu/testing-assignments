package ru.vityaman.story

class Spaceship(
    private val space: Space,
    private val team: Set<Person>,
    private val food: Food
) {
    private var speed: Int = 0

    init {
        Story.tellThat("Корабль $speedDescription")
    }

    private val speedDescription: String
        get() = when (speed) {
            0 -> "Стоит"
            1 -> "Еле-еле"
            2 -> "Разгоняется"
            3 -> "Пошел-пошел"
            4 -> "Летит"
            else -> "Как конфети летит"
        }

    val soundDescription: String
        get() = when (speed) {
            0 -> "Тихий"
            1 -> "Жужжит"
            in 2..3 -> "Свистит"
            else -> "Ревет"
        }

    fun speedUp() {
        speed += 1
        Story.tellThat("Корабль ускорился, летит $speedDescription, а мотор $soundDescription")
    }

    fun haveLunch() {
        Story.tellThat("Время обеда")

        team.forEach { it.eat(food, food.showFirst()) }
    }

    fun lookAway() {
        if (team.isEmpty()) {
            throw IllegalStateException("Некому смотреть")
        }
        Story.tellThat("${team.joinToString()} посмотрели в окно, а там: ${space.description}")
    }
}