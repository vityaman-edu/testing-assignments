package ru.vityaman.collection

data class ConstHashedString(private val content: String) {
    override fun hashCode(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ConstHashedString
        return content == other.content
    }
}