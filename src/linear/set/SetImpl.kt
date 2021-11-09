package linear.set

import java.lang.IllegalArgumentException
import java.util.NoSuchElementException

data class Set(
    val maxSize: Int = 15
) : MySet {
    private var size = -1
    private val storage = arrayOfNulls<Number>(maxSize)

    private fun isValidIndex(index: Int): Boolean =
        (index in (0 until size))

    override fun getSize(): Int? =
        if (this.isEmpty) null
        else this.size

    override fun contains(element: Number): Boolean =
        element in storage

    override fun isEmpty(): Boolean =
        this.size == -1

    override fun isFull(): Boolean =
        this.size == maxSize

    override fun add(element: Number) {
        if (isFull)
            throw IllegalArgumentException("The set is full.")
        if (isEmpty)
            this.size = 0
        if (!this.contains(element))
            this.storage[size++] = element
    }

    override fun remove(element: Number?): Number? {
        var value: Number? = null
        if ((element != null) && this.contains(element)) {
            repeat(this.size) { i ->
                if (storage[i] == element) {
                    value = storage[i]
                    storage[i] = null
                }
            }
            this.size--
        }
        return value
    }

    override fun removeAt(index: Int): Number? {
        if (isValidIndex(index))
            return this.remove(this.storage[index])
        return null
    }

    override fun toString(): String = buildString {
        append("[ ")
        for (s in storage) {
            if (s != null)
                append("$s ")
        }
        append("]")
    }

    operator fun get(i: Int): Number {
        if (isEmpty)
            throw IllegalAccessError("Set is empty.")
        if (!isValidIndex(i))
            throw IllegalAccessError("Invalid Index.")
        if (storage[i] == null)
            throw NoSuchElementException("Element doesn't exit")
        return storage[i]!!
    }
}