package ru.vityaman.scene

class NamedActor(
    private val name: String,
    private val origin: Actor
) : Actor {
    override val state: String
        get() = "$name - ${origin.state}"

    override fun resume() {
        origin.resume()
    }

    override val isExhausted: Boolean
        get() = origin.isExhausted
}