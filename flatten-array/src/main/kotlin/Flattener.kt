class Flattener {
    companion object {

        /**
         * Flatten any level nested list to a single list with all values except nil/null.
         *
         * @param list a list to flatten
         * @return a single flatten list with all values except nil/null.
         */
        fun flatten(list: List<*>): List<Any> {
            var result = list.filterNotNull()
            while (result.any { it is Collection<*> }) {
                result = mutableListOf<Any>().apply {
                    for (value in result) {
                        if (value is Collection<*>) {
                            addAll(value.filterNotNull())
                        } else {
                            add(value)
                        }
                    }
                }
            }
            return result
        }
    }
}