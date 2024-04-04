package ru.vityaman.entity.person

import ru.vityaman.scene.StaticActor

class Arthur : StaticActor(), Person {
    override val name: String
        get() = "Артур"
}
