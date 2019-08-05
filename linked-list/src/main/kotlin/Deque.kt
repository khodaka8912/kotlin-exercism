/**
 * This class is not thread safe.
 */
class Deque<E> {

    private var first: Node<E>? = null
    private var last: Node<E>? = null

    fun push(element: E) {
        val tmp = last
        if (tmp == null) {
            last = Node(element, null, null)
            first = last
        } else {
            last = Node(element, tmp, null)
            tmp.next = last
        }
    }

    fun pop(): E {
        val tmp = last ?: throw NoSuchElementException()
        last = last?.prev
        last?.next = null
        return tmp.element
    }

    fun shift(): E {
        val tmp = first ?: throw NoSuchElementException()
        first = first?.next
        first?.prev = null
        return tmp.element
    }

    fun unshift(element: E) {
        val tmp = first
        if (tmp == null) {
            last = Node(element, null, null)
            first = last
        } else {
            first = Node(element, null, tmp)
            tmp.prev = first
        }

    }

    class Node<E>(val element: E, var prev: Node<E>?, var next: Node<E>?)
}