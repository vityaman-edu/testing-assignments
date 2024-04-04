package ru.vityaman.scene

import ru.vityaman.location.Location

class Scene(
    private val location: Location,
    actors: Collection<Actor>
) : Actor {
    private val actors = ActorGroup(actors)

    override val state: String
        get() = "Находясь в ${location.description} есть ${actors.state}"

    override fun resume() = actors.resume()

    override val isExhausted: Boolean get() = actors.isExhausted
}