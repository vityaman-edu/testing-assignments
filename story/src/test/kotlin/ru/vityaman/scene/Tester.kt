package ru.vityaman.scene

class Tester(private val name: String) : StaticActor() {
    override val state: String
        get() = "Тестировщик $name"
}
