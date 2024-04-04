package ru.vityaman.effect.sound

import ru.vityaman.scene.StaticActor

class Whistle(private val degree: Degree) : StaticActor(), Sound {
    enum class Degree(val description: String) {
        THIN("Тонкий"),
    }

    override val state: String
        get() = "${degree.description} свист"
}