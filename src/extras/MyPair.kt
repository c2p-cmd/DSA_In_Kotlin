package extras

data class MyPair<A, B> (
    val firstElement: A,
    val secondElement: B
) {
    override fun toString(): String {
        return "Pair{" +
                "" + firstElement +
                ", " + secondElement +
                '}'
    }
}

fun String.toPair(): MyPair<Int, Int> {
    val (first, second) = this.split(",")
    try {
        first.toInt()
        second.toInt()
    } catch (e: Exception) {
        println(e.message)
    }
    return MyPair(first.toInt(), second.toInt())
}