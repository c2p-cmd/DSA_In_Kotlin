package non.linear.graphs

import java.util.Queue
import java.util.ArrayList
import java.util.HashSet
import java.util.Collections
import java.util.LinkedList

enum class GraphType {
    DIRECTED, UNDIRECTED
}

interface Graph {
    val numVertices: Int

    val graphType: GraphType

    fun isEdgePresent(v1: Int, v2: Int): Boolean

    fun addEdge(v1: Int, v2: Int)

    fun removeEdge(v1: Int, v2: Int): Boolean

    fun getAdjacentVertices(v: Int): Pair<Int, List<Int>>

    val allVertices: Map<Int, List<Int>>

    fun getIndegree(v: Int): Int

    val indegreesOfAll: Map<Int, Int>
}

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

    @JvmStatic
    private fun breadthFirstTraversalOf(
        graph: Graph,
        visitedVertices: BooleanArray
    ): String = buildString {
        val queue: Queue<Int> = LinkedList()
        queue.add(0)

        while (!queue.isEmpty()) {
            val vertex = queue.remove()

            if (visitedVertices[vertex])
                continue

            append("$vertex->")
            visitedVertices[vertex] = true

            for (v in graph.getAdjacentVertices(vertex).second) {
                if (!visitedVertices[v])
                    queue.add(v)
            }
        }

        /*try {
            repeat(graph.numVertices) {
                breadthFirstTraversalOf(graph, visitedVertices, it)
            }
        } catch (_: StackOverflowError) {
            return@buildString
        }*/

        append("\n")
    }

    @JvmStatic
    fun breadthFirstTraversal(graph: Graph) = breadthFirstTraversalOf(graph, BooleanArray(graph.numVertices))
}