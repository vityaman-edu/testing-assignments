package ru.vityaman.scene

class ActorGroup(private val actors: Collection<Actor>) : Actor {
    override val state: String
        get() = actors.filterNot { it.isExhausted }.joinToString(", ") { it.state }

    override fun resume() {
        actors.filterNot { it.isExhausted }.forEach { it.resume() }
    }

    override val isExhausted: Boolean
        get() = actors.all { it.isExhausted }
}
