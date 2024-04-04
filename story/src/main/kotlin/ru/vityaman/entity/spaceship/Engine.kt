package ru.vityaman.entity.spaceship

import ru.vityaman.effect.sound.Sound
import ru.vityaman.scene.Actor

interface Engine : Actor {
    val sound: Sound
}