package ru.vityaman.collection

import kotlin.math.abs

data class XorHashedString(private val content: String) {
    override fun hashCode(): Int {
        var code = 0
        for (symbol in content) {
            code = code xor symbol.code
            code = code shl 16
        }
        return abs(code)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as XorHashedString
        return content == other.content
    }
}
