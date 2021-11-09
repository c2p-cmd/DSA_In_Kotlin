package algorithms.search

fun <T> linearSearch(
    collection: Array<T>, element: T
) : Int? {
    for (i in collection.indices) {
        if (i == element)
            return i
    }
    return null
}

fun <T> recursiveBinarySearch(
    collection: Array<T>,
    elementToFind: T,
    low: Int = 0,
    high: Int = (collection.size-1)
) : Int? {
    val mid: Int = low + (high - low) / 2

    val currentElement = collection[mid].toString()
    val element = elementToFind.toString()

    println("low = $low, high = $high, mid = $mid")
    if (high >= low) {
        if (currentElement == elementToFind)
            return mid

        return if (currentElement > element)
            recursiveBinarySearch(collection, elementToFind, low, mid-1)
        else
            recursiveBinarySearch(collection, elementToFind, mid+1, high)
    }
    TODO("Array Out Of Bounds")
}

fun <T> sentinelSearch(
    collection: Array<T>,
    elementToFind: T
) : Int? {
    collection[collection.size - 1] = elementToFind
    for (i in collection.indices) {
        if (collection[i] == elementToFind)
            return i
    }
    return null
}