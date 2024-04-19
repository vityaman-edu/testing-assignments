package ru.vityaman.math.example

import ru.vityaman.math.example.csv.ExternalCsvFile

enum class ModuleName {
    SIN,
    COS,
    COT,
    SEC,
    CSC,
    LN,
    LOG5,
    EXAMPLE,
}

fun dump(module: ModuleName, step: Double) {
    val example = ExampleMapping()

    val mapping = when (module) {
        ModuleName.SIN -> example.sin
        ModuleName.COS -> example.cos
        ModuleName.COT -> example.cot
        ModuleName.SEC -> example.sec
        ModuleName.CSC -> example.csc
        ModuleName.LN -> example.ln
        ModuleName.LOG5 -> example.log5
        ModuleName.EXAMPLE -> example
    }

    ExternalCsvFile("test.csv").batch {
        for (x in -10.0..10.0 step step) {
            writeln(x, mapping(x))
        }
    }
}

fun main() {
    dump(ModuleName.CSC, step = 0.05)
}
