package non.linear.graphs

import non.linear.graphs.GraphNode as Node

data class AdjacencySetGraph
@JvmOverloads constructor(
    private val numVertices: Int,
    private val graphType: Graph.GraphType = Graph.GraphType.DIRECTED,
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
        if (graphType == Graph.GraphType.UNDIRECTED)
            vertexList[v2].addEdge(v1)
    }

    override fun removeEdge(v1: Int, v2: Int): Boolean {
        assert(v1 in validVertexRange && v2 in validVertexRange) {
            "The Vertex isn't valid!"
        }

        if (graphType == Graph.GraphType.UNDIRECTED) {
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

    override fun getAllVertices(): Map<Int, List<Int>> = HashMap<Int, List<Int>>().apply {
        repeat(numVertices) {
            this[it] = getAdjacentVertices(it).second
        }
    }

    override fun toString(): String = buildString {
        vertexList.forEach {
            append("$it\n")
        }
    }

    override fun getNumVertices(): Int = numVertices
}