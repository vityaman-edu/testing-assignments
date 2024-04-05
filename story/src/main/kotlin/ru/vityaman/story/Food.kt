package ru.vityaman.story

class Food(items: Collection<String>) : Cargo {
    private val items = ArrayList<String>(items)

    override val description: String
        get() = "Провизия: ${items.joinToString()}"

    fun take(item: String) {
        val removed = items.remove(item)
        if (!removed) {
            throw IllegalStateException("А вот нет у нас $item!")
        }
        Story.tellThat("Кто-то стащил $item")
    }

    fun showFirst(): String =
        items.firstOrNull() ?: throw IllegalStateException("Еды больше нет ;(")

}