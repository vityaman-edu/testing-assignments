package ru.vityaman.entity.spaceship.that

import ru.vityaman.effect.sound.Buzzing
import ru.vityaman.effect.sound.Roar
import ru.vityaman.effect.sound.Sound
import ru.vityaman.effect.sound.Whistle
import ru.vityaman.entity.spaceship.Engine
import ru.vityaman.scene.SwitchingActor

class ThatEngine : Engine {
    private val sequence = SwitchingActor(
        sequence<Sound> {
            repeat(1) {
                yield(Buzzing())
            }
            repeat(3) {
                yield(Whistle(Whistle.Degree.THIN))
            }
            repeat(1_000_000_000) {
                yield(Roar())
            }
        }
    )

    override val sound: Sound
        get() = sequence.current ?: throw IllegalStateException()

    override val state: String
        get() = "Двигатель раздает ${sound.state}"

    override fun resume() {
        sequence.resume()
    }

    override val isExhausted: Boolean
        get() = sequence.isExhausted
}