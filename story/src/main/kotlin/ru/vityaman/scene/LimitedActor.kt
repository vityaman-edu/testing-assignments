package ru.vityaman.scene

class LimitedActor(
    private val limit: Int,
    private val actor: Actor
) : Actor {
    private var step: Int = 0

    init {
        require(0 < limit) { "limit must be positive, got $limit" }
    }

    override val state: String
        get() = actor.state

    override fun resume() {
        actor.resume()
        step += 1
    }

    override val isExhausted: Boolean
        get() = actor.isExhausted || limit <= step
}