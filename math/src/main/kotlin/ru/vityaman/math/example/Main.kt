package ru.vityaman.math.example

import ru.vityaman.math.example.csv.ExternalCsvFile

fun dump(step: Double) {
    val example = ExampleMapping()
    ExternalCsvFile("test.csv").batch {
        for (x in -10.0..10.0 step step) {
            writeln(x, example(x))
        }
    }
}

fun main() {
    dump(step = 0.05)
}
