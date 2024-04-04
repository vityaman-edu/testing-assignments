package ru.vityaman.entity.spaceship

import ru.vityaman.scene.Actor

interface Spaceship : Actor {
    val engine: Engine
    val speed: Speed
}