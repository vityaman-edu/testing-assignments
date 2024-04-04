package ru.vityaman.math.combinatorics

fun factorial(n: Long): Long =
    if (n in setOf(0L, 1L)) {
        1
    } else {
        n * factorial(n - 1)
    }
