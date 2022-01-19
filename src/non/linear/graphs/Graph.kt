package non.linear.graphs

import java.util.*
import kotlin.text.StringBuilder

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

object GraphTraversals {
    @JvmStatic
    private fun depthFirstTraversalOf(
        graph: Graph,
        visitedVertices: BooleanArray,
        currentVertex: Int,
        resultString: StringBuilder = StringBuilder()
    ): String {
        if (visitedVertices[currentVertex])
            return resultString.toString()

        visitedVertices[currentVertex] = true

        val adjacentVertices = graph.getAdjacentVertices(currentVertex)
        for (vertex in adjacentVertices.second) {
            depthFirstTraversalOf(graph, visitedVertices, vertex, resultString)
        }

        return resultString.append("->$currentVertex ").toString()
    }

    @JvmStatic
    fun depthFirstTraversal(graph: Graph) = depthFirstTraversalOf(graph, BooleanArray(graph.numVertices), 0)
}