package linear.queue

data class Queue<T>(
    private val maxSize: Int = 10
) : MyQueue<T> {
    private var currentSize = -1
    private val storage = ArrayList<T>(maxSize)

    override fun isEmpty(): Boolean =
        this.currentSize == -1

    override fun isFull(): Boolean =
        this.currentSize == maxSize-1

    override fun enqueue(element: T): Boolean {
        if (isFull) return false
        this.currentSize++
        this.storage.add(element)
        return true
    }

    override fun dequeue(): T =
        this.storage.removeAt(this.currentSize--)

    override fun peek(): T =
        this.storage[this.currentSize]

    override fun toString(): String =
        buildString {
            this@buildString.append(
                this@Queue.storage
            )
        }
}