import kotlin.properties.Delegates

class Reactor<T> {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    interface Subscription {
        fun cancel()
    }

    inner class InputCell<T>(value: T) : Cell<T>(value) {
        override fun computeValue() = value
    }

    inner class ComputeCell<T>(private vararg val sources: Cell<T>, val function: (List<T>) -> T)
        : Cell<T>(function(sources.map { it.computeValue() })) {
        init {
            for (cell in sources) {
                cell.addCallback { value = computeValue() }
            }
        }

        override fun computeValue() = function(sources.map { it.computeValue() })
    }

    abstract class Cell<T>(initial: T) {

        var value: T by Delegates.observable(initial) { _, oldValue, newValue ->
            if (oldValue != newValue) {
                callbacks.values.forEach { callback -> callback(newValue) }
            }
        }

        abstract fun computeValue(): T

        val callbacks = mutableMapOf<Subscription, (T) -> Unit>()

        fun addCallback(callback: (T) -> Unit) = object : Subscription {
            override fun cancel() {
                callbacks.remove(this)
            }
        }.also { callbacks[it] = callback }
    }
}

