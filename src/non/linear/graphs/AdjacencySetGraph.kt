package non.linear.graphs

import non.linear.graphs.GraphNode as Node

class AdjacencySetGraph
@JvmOverloads constructor(
    override val numVertices: Int,
    override val graphType: GraphType = GraphType.DIRECTED,
) : Graph {
    private val vertexList: List<Node> = ArrayList<Node>().apply {
        repeat(numVertices) { add(Node(it)) }
    }
    private val validVertexRange = 0 until numVertices

    override fun isEdgePresent(v1: Int, v2: Int): Boolean = vertexList[v1].isEdgePresent(v2)

    override fun addEdge(v1: Int, v2: Int) {
        assert(v1 in validVertexRange && v2 in validVertexRange) {
            "The Vertex isn't valid!"
        }

        if (isEdgePresent(v1, v2))
            return
        vertexList[v1].addEdge(v2)
        if (graphType == GraphType.UNDIRECTED)
            vertexList[v2].addEdge(v1)
    }

    override fun removeEdge(v1: Int, v2: Int): Boolean {
        assert(v1 in validVertexRange && v2 in validVertexRange) {
            "The Vertex isn't valid!"
        }

        if (graphType == GraphType.UNDIRECTED) {
            vertexList[v2].removeEdge(v1)
        }
        return vertexList[v1].removeEdge(v2)
    }

    override fun getAdjacentVertices(v: Int): Pair<Int, List<Int>> {
        assert(v in validVertexRange) {
            "The Vertex isn't valid!"
        }

        return v to vertexList[v].getAdjacentVertices()
    }

    override val allVertices: Map<Int, List<Int>>
        get() = HashMap<Int, List<Int>>().apply {
            repeat(numVertices) {
                this[it] = getAdjacentVertices(it).second
            }
        }

    override fun getIndegree(v: Int): Int {
        require(v in validVertexRange) { "Invalid vertex" }

        return allVertices.count { vertexPair ->
            v in vertexPair.value
        }
    }

    override val indegreesOfAll: Map<Int, Int>
        get() = mutableMapOf<Int, Int>().apply {
            repeat(numVertices) { vertex ->
                this[vertex] = getIndegree(vertex)
            }
        }

    override fun toString(): String = buildString {
        vertexList.forEach {
            append("$it\n")
        }
    }
}