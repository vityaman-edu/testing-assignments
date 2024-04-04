package ru.vityaman.entity.person

import ru.vityaman.scene.Actor

interface Person : Actor {
    val name: String

    override val state: String
        get() = name
}
