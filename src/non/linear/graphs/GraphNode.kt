package non.linear.graphs

import java.util.*

data class GraphNode(
    val vertexNumber: Int
) {
    private val adjacencySet: MutableSet<Int> = HashSet()

    fun addEdge(vertexNumber: Int) = adjacencySet.add(vertexNumber)

    fun removeEdge(vertexNumber: Int): Boolean = adjacencySet.remove(vertexNumber)

    fun isEdgePresent(vertexNumber: Int) = vertexNumber in adjacencySet

    fun getAdjacentVertices(): List<Int> = ArrayList(adjacencySet).apply(Collections::sort)

    override fun toString(): String =
        "$vertexNumber -> $adjacencySet"
}

data class GraphListNode
@JvmOverloads constructor(
    val vertexId: Int,
    val graphNodes: List<Int> = LinkedList()
) {
    override fun toString(): String =
        "$vertexId -> $graphNodes"

}