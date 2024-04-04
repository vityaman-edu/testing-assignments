package ru.vityaman.scene

class SwitchingActor<T : Actor>(sequence: Sequence<T>) : Actor {
    private val iterator: Iterator<T> = sequence.iterator()

    var current: T? = null

    init {
        resume()
    }

    override val state: String
        get() = current?.state ?: throw IllegalStateException()

    override fun resume() {
        current = if (iterator.hasNext()) {
            iterator.next()
        } else {
            null
        }
    }

    override val isExhausted: Boolean
        get() = current == null
}