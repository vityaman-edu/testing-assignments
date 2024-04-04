package ru.vityaman.effect.light

import ru.vityaman.scene.StaticActor

class JustLight(override val brightness: Brightness) : StaticActor(), Light {
    override val state: String
        get() = "Свет, у которого $brightness"
}