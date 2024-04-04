package ru.vityaman.scene

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

class PrintingPerformance(
    private val output: (String) -> Unit,
    private val actor: Actor,
) : Performance {
    override suspend fun run() {
        while (!actor.isExhausted) {
            withContext(Dispatchers.IO) {
                output(actor.state)
            }
            actor.resume()
            yield()
        }
    }
}