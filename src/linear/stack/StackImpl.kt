package linear.stack

import kotlin.collections.ArrayList

data class Stack<T>(
    val maxSize: Int = 15
) : MyStack<T> {
    private var top = -1
    private val storage = ArrayList<T>(maxSize)

    override fun isFull(): Boolean =
        top+1 == maxSize

    override fun isEmpty(): Boolean =
        top == -1

    override fun push(element: T): Boolean {
        if (isFull) return false
        storage.add(element)
        top++
        return true
    }

    override fun pop(): T =
        this.storage.removeAt(top--)

    override fun peek(): T =
        this.storage[top]

    override fun toString(): String =
        buildString {
            this@buildString.append(
                this@Stack.storage
            )
        }
}