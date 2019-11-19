enum class Relationship {

    EQUAL, SUBLIST, SUPERLIST, UNEQUAL

}

fun <T> List<T>.relationshipTo(other: List<T>) = when {
        size == other.size && this == other -> Relationship.EQUAL
        size > other.size && isSuperlist(other) -> Relationship.SUPERLIST
        size < other.size && isSublist(other) -> Relationship.SUBLIST
        else ->  Relationship.UNEQUAL
    }

private fun <T> List<T>.isSuperlist(other: List<T>): Boolean {
    for (i in 0..size-other.size) {
        if (subList(i, other.size + i) == other) {
            return true
        }
    }
    return false
}

private fun <T> List<T>.isSublist(other: List<T>) = other.isSuperlist(this)