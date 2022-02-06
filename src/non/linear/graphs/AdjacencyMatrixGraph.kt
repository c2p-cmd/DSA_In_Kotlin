package non.linear.graphs

// Default = Directed Graph
data class AdjacencyMatrixGraph
@JvmOverloads constructor(
    override val numVertices: Int,
    override val graphType: GraphType = GraphType.DIRECTED,
) : Graph {

    private val validVertexRange = 0 until numVertices
    private val adjacencyMatrix = Array(numVertices) { BooleanArray(numVertices) { false } }

    override fun isEdgePresent(v1: Int, v2: Int): Boolean =
        adjacencyMatrix[v1][v2]

    override fun addEdge(v1: Int, v2: Int) {
        require(
            v1 in validVertexRange && v2 in validVertexRange
        ) { "Vertex isn't Valid!" }

        if (isEdgePresent(v1, v2))
            return

        adjacencyMatrix[v1][v2] = true
        if (graphType == GraphType.UNDIRECTED)
            adjacencyMatrix[v2][v1] = true
    }

    override fun removeEdge(v1: Int, v2: Int): Boolean {
        require(
            v1 in validVertexRange && v2 in validVertexRange
        ) { return false }

        return if (isEdgePresent(v1, v2)) {
            adjacencyMatrix[v1][v2] = false
            if (graphType == GraphType.UNDIRECTED)
                adjacencyMatrix[v2][v1] = false
            true
        } else {
            false
        }
    }

    override fun getAdjacentVertices(v: Int): Pair<Int, List<Int>> =
        v to ArrayList<Int>().apply {
            require(v in validVertexRange) {
                "Vertex isn't valid."
            }

            repeat(numVertices) {
                if (adjacencyMatrix[v][it])
                    add(it)
            }

            // always return vertices in ascending order
            sort()
        }.toList()

    override val allVertices: Map<Int, List<Int>>
        get() = HashMap<Int, List<Int>>().apply {
            repeat(numVertices) { vertex ->
                this.put(getAdjacentVertices(vertex))
            }
        }

    private fun HashMap<Int, List<Int>>.put(pair: Pair<Int, List<Int>>) =
        put(pair.first, pair.second)

    override fun getIndegree(v: Int): Int {
        require(v in validVertexRange) {
            "Invalid Vertex!"
        }

        return adjacencyMatrix.count { it[v] }
    }

    override val indegreesOfAll: Map<Int, Int>
            get() = mutableMapOf<Int, Int>().apply {
                repeat(numVertices) { vertex ->
                    this[vertex] = getIndegree(vertex)
                }
            }.toMap()

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
}