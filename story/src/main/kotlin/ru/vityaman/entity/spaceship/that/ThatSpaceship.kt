package ru.vityaman.entity.spaceship.that

import ru.vityaman.entity.person.Person
import ru.vityaman.entity.spaceship.Engine
import ru.vityaman.entity.spaceship.Spaceship
import ru.vityaman.entity.spaceship.Speed

class ThatSpaceship(
    override val engine: Engine,
    override val speed: Speed,
    private val team: Set<Person>,
) : Spaceship {
    init {
        require(team.isNotEmpty())
    }

    override val state: String
        get() = "Космический корабль, летящий со скоростью ${speed.description}" +
                ", управляемый следующими штацкими: " +
                team.joinToString(", ") { it.state } + ", " +
                "а в нем ${engine.state}"

    override fun resume() {
        engine.resume()
        team.forEach { it.resume() }
    }

    override val isExhausted: Boolean
        get() = engine.isExhausted && team.all { it.isExhausted }
}
