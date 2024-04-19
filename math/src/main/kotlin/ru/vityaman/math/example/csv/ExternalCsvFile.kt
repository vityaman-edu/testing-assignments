package ru.vityaman.math.example.csv

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

class ExternalCsvFile(private val filename: String) : CsvFile {
    override fun batch(write: CsvWriter.() -> Unit) {
        csvWriter().open(filename) {
            write(object : CsvWriter {
                override fun writeln(vararg values: Double) {
                    val (first, second) = values
                    writeRow(first, second)
                }
            })
        }
    }
}
