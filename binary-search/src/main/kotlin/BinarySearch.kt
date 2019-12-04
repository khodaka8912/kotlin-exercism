object BinarySearch {
    fun search(list: List<Int>, item: Int): Int {
        var begin = 0
        var end = list.size - 1
        while (begin <= end) {
            val mid = (begin + end) / 2
            when {
                list[mid] == item -> return mid
                list[mid] < item -> begin = mid + 1
                else -> end = mid - 1
            }
        }
        throw NoSuchElementException("$item is not contained in the list")
    }
}
