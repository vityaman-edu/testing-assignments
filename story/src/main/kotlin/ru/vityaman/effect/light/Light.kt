package ru.vityaman.effect.light

import ru.vityaman.scene.Actor

interface Light : Actor {
    val brightness: Brightness
}
