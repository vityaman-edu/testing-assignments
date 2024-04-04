package ru.vityaman.collection

import java.util.LinkedList

@Deprecated("Unsafe for production")
class FixedChainHashMultiSet<K>(bucketsCount: Int) : HashMultiSet<K> {
    internal val buckets = Array<MutableList<K>>(run {
        require(0 < bucketsCount) { "buckets count must be positive" }
        bucketsCount
    }) { LinkedList() }

    override fun contains(key: K): Boolean =
        buckets[index(key)].contains(key)

    override fun insert(key: K) =
        buckets[index(key)].addLast(key)

    override fun remove(key: K) =
        buckets[index(key)].remove(key).let { }

    internal fun index(key: K): Int =
        key.hashCode() % buckets.size
}
