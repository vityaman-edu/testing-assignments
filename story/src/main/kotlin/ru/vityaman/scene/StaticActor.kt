package ru.vityaman.scene

abstract class StaticActor : Actor {
    override fun resume() {
        // Do nothing
    }

    override val isExhausted: Boolean
        get() = false
}