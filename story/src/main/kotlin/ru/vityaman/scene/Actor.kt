package ru.vityaman.scene

interface Actor {
    val state: String
    fun resume()
    val isExhausted: Boolean
}