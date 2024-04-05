package ru.vityaman.story

class Story private constructor() {
    companion object {
        private val instance: Story by lazy {
            Story()
        }

        fun subscribe(observer: (String) -> Unit) {
            instance.subscribe(observer)
        }

        fun tellThat(action: String) {
            instance.tellThat(action)
        }

        fun reset() {
            instance.observers.clear()
        }
    }

    private val observers = mutableListOf<(String) -> Unit>()

    fun subscribe(observer: (String) -> Unit) {
        observers.add(observer)
    }

    fun tellThat(action: String) {
        observers.forEach { it(action) }
    }
}