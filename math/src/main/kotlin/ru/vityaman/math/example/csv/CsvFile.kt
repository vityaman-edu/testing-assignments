package ru.vityaman.math.example.csv

interface CsvWriter {
    fun writeln(vararg values: Double)
}

interface CsvFile {
    fun batch(write: CsvWriter.() -> Unit)
}
