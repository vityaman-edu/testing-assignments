package ru.vityaman.collection

class ListMultiSet<K> : HashMultiSet<K> {
    private val list = ArrayList<K>()

    override fun contains(key: K): Boolean =
        list.contains(key)

    override fun insert(key: K) =
        list.add(key).let { }

    override fun remove(key: K) =
        list.remove(key).let { }
}