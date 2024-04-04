package ru.vityaman.entity.person

import ru.vityaman.scene.StaticActor

class Ford : StaticActor(), Person {
    override val name: String
        get() = "Форд"
}