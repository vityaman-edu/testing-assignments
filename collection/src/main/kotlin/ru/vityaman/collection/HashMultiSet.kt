package ru.vityaman.collection

interface HashMultiSet<K> {
    operator fun contains(key: K): Boolean
    fun insert(key: K)
    fun remove(key: K)
}
