fun <E> List<E>.customAppend(list: List<E>): List<E> {
    val appended = mutableListOf<E>()
    appended.addAll(this)
    appended.addAll(list)
    return appended
}

fun <E> List<List<E>>.customConcat(): List<E> {
    val concatenated = mutableListOf<E>()
    forEach { concatenated.addAll(it) }
    return concatenated
}

fun <E> List<E>.customFilter(predicate: (E) -> Boolean): List<E> {
    val filtered = mutableListOf<E>()
    forEach {
        if (predicate(it)) {
            filtered.add(it)
        }
    }
    return filtered
}

val List<*>.customSize: Int
    get() {
        var count = 0
        forEach { _ ->
            count++
        }
        return count
    }

fun <T, R> List<T>.customMap(function: (T) -> R): List<R> {
    val mapped = mutableListOf<R>()
    forEach { mapped.add(function(it)) }
    return mapped
}

fun <T, E> List<E>.customFoldLeft(initial: T, function: (T, E) -> T): T {
    var result = initial
    forEach { result = function(result, it) }
    return result
}

fun <T, E> List<E>.customFoldRight(initial: T, function: (E, T) -> T): T {
    var result = initial
    val ite = listIterator(customSize)
    while (ite.hasPrevious()) {
        result = function(ite.previous(), result)
    }
    return result
}

fun <E> List<E>.customReverse(): List<E> {
    val reversed = mutableListOf<E>()
    val ite = listIterator(customSize)
    while (ite.hasPrevious()) {
        reversed.add(ite.previous())
    }
    return reversed
}