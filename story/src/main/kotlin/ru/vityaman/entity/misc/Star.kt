package ru.vityaman.entity.misc

import ru.vityaman.effect.light.Light
import ru.vityaman.scene.Actor

class Star(private val light: Light) : Actor {
    override val state: String
        get() = "Точка, излучающая ${light.state}"

    override fun resume() = light.resume()

    override val isExhausted: Boolean
        get() = false
}
