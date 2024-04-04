package ru.vityaman.effect.sound

import ru.vityaman.scene.StaticActor

class Roar : StaticActor(), Sound {
    override val state: String
        get() = "Рев"
}