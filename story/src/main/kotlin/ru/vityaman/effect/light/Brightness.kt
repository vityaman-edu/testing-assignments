package ru.vityaman.effect.light

class Brightness(private val level: Int) : Comparable<Brightness> {
    override fun toString(): String =
        "Яркость $level уровня"

    override fun compareTo(other: Brightness): Int {
        return this.level.compareTo(other.level)
    }
}
