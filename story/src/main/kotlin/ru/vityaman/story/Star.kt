package ru.vityaman.story

class Star {
    private var brightness: Int = 0

    val description: String
        get() = "Горит " + when (brightness) {
            0 -> "Никак"
            1 -> "Тускло"
            2 -> "Ярко"
            3 -> "Сияет"
            4 -> "Ааа, глазааа"
            else -> throw IllegalStateException()
        }

    override fun toString(): String = description

    fun increaseBrightness() {
        if (brightness == 4) {
            throw IllegalStateException("Ярче некуда")
        }
        brightness += 1
    }

    fun decreaseBrightness() {
        if (brightness == 0) {
            throw IllegalStateException("Темнее уж никак")
        }
        brightness -= 1
    }
}