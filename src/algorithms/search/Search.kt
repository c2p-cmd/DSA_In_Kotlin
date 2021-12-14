package algorithms.search

fun recursiveBinarySearch(
    arrayToSearch: IntArray, elementToSearch: Int, low: Int, high: Int
): Int {
    return if (low > high)
        -1
    else (low + (high-low) / 2).let { mid ->
        return when {
            (arrayToSearch[mid] == elementToSearch) ->
                mid
            (arrayToSearch[mid] < elementToSearch) ->
                recursiveBinarySearch(arrayToSearch, elementToSearch, (mid+1), high)
            else ->
                recursiveBinarySearch(arrayToSearch, elementToSearch, low, (mid-1))
        }
    }
}

fun binarySearch(
    arrayToSearch: IntArray, element: Int
): Int {
    var low = 0
    var high = arrayToSearch.size - 1
    while (low <= high) (low + (high - low) / 2).
        let { mid ->
            when {
                (arrayToSearch[mid] == element) -> return mid
                (arrayToSearch[mid] < element) -> low = mid + 1
                else -> high = mid - 1
            }
        }
    return -1
}

fun <T> linearSearch(
    collection: Array<T>, element: T
) : Int {
    for (i in collection.indices) {
        if (collection[i] == element)
            return i
    }
    return -1
}

fun <T> sentinelSearch(
    collection: Array<T>,
    elementToFind: T
) : Int {
    collection[collection.size - 1] = elementToFind
    for (i in collection.indices) {
        if (collection[i] == elementToFind)
            return i
    }
    return -1
}