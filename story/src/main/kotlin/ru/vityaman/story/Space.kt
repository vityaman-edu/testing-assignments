package ru.vityaman.story

class Space(
    private val name: String,
    private val stars: Set<Star>
) {
    val description: String
        get() = "Вселенная $name" +
                if (stars.size > 1) {
                    ", так много в ней звезд: ${stars.joinToString()}"
                } else {
                    stars.joinToString()
                }
}