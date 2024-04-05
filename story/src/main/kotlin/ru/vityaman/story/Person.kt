package ru.vityaman.story

class Person(private val name: String) {
    fun eat(food: Food, item: String) {
        Story.tellThat("$name захотел скушать $item")
        food.take(item)
        Story.tellThat("$name насытился $item")
    }

    override fun toString(): String = name

}