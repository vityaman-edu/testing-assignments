package ru.vityaman.effect.sound

import ru.vityaman.scene.StaticActor

class Buzzing : StaticActor(), Sound {
    override val state: String
        get() = "Жужжание"
}