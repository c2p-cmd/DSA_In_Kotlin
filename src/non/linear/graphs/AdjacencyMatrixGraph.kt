package non.linear.graphs

import java.util.ArrayList
import java.util.HashMap

// Default = Directed Graph
data class AdjacencyMatrixGraph
@JvmOverloads constructor(
    private val numVertices: Int,
    private val graphType: Graph.GraphType = Graph.GraphType.DIRECTED
) : Graph {

    private val validVertexRange = 0 until numVertices
    private val adjacencyMatrix = Array(numVertices) { BooleanArray(numVertices) { false } }

    override fun isEdgePresent(v1: Int, v2: Int): Boolean =
        adjacencyMatrix[v1][v2]

    override fun addEdge(v1: Int, v2: Int) {
        assert(
            v1 in validVertexRange && v2 in validVertexRange
        ) { "Vertex isn't Valid!" }

        if (isEdgePresent(v1, v2))
            return

        adjacencyMatrix[v1][v2] = true
        if (graphType == Graph.GraphType.UNDIRECTED)
            adjacencyMatrix[v2][v1] = true
    }

    override fun removeEdge(v1: Int, v2: Int): Boolean {
        assert(
            v1 in validVertexRange && v2 in validVertexRange
        ) { return false }

        return if (isEdgePresent(v1, v2)) {
            adjacencyMatrix[v1][v2] = false
            if (graphType == Graph.GraphType.UNDIRECTED)
                adjacencyMatrix[v2][v1] = false
            true
        } else {
            false
        }
    }

    override fun getAdjacentVertices(v: Int): Pair<Int, List<Int>> =
        v to ArrayList<Int>().apply {
            assert(v in validVertexRange) {
                "Vertex isn't valid."
            }

            repeat(numVertices) {
                if (adjacencyMatrix[v][it])
                    add(it)
            }

            // always return vertices in ascending order
            sort()
        }

    override fun getAllVertices() = HashMap<Int, List<Int>>().apply {
        repeat(numVertices) {
            this[it] = getAdjacentVertices(it).second
        }
    }

    override fun toString(): String = buildString {
        append("\n\t")
        repeat(numVertices) { append("$it\t\t") }
        append("\n")

        for (i in validVertexRange) {
            append("$i |\t")
            for (j in validVertexRange) {
                append("${adjacencyMatrix[i][j]}\t")
            }
            append("\n")
        }
    }

    override fun getNumVertices(): Int = numVertices
}