package non.linear.graphs

private const val DEFAULT_MATRIX_VALUE = Double.MIN_VALUE

data class WeightedAdjacencyMatrixGraph
@JvmOverloads constructor(
    override val numVertices: Int,
    override val graphType: GraphType = GraphType.DIRECTED
) : WeightedGraph(numVertices, graphType) {

    private val validVertexRange = 0 until numVertices
    private val matrixGraph = Array(numVertices) { DoubleArray(numVertices) { DEFAULT_MATRIX_VALUE } }

    override fun isEdgePresent(v1: Int, v2: Int): Boolean =
        try {
            matrixGraph[v1][v2] != DEFAULT_MATRIX_VALUE
        } catch (_: Exception) {
            false
        }

    override fun addEdge(v1: Int, v2: Int, weight: Double) {
        require(v1 in validVertexRange && v2 in validVertexRange) { "Vertex not in range." }

        matrixGraph[v1][v2] = weight
        if (graphType == GraphType.UNDIRECTED)
            matrixGraph[v2][v1] = weight
    }

    override fun addEdge(v1: Int, v2: Int): Unit =
        addEdge(v1, v2, 1.0)

    override fun getEdgeWeight(v1: Int, v2: Int): Double {
        require(v1 in validVertexRange && v2 in validVertexRange) { "Vertex not in Range." }

        return matrixGraph[v1][v2]
    }

    override fun removeEdge(v1: Int, v2: Int): Boolean {
        require(v1 in validVertexRange && v2 in validVertexRange) { "Vertex not in range." }

        if (isEdgePresent(v1, v2)) {
            matrixGraph[v1][v2] = DEFAULT_MATRIX_VALUE
            if (graphType == GraphType.UNDIRECTED)
                matrixGraph[v2][v1] = DEFAULT_MATRIX_VALUE
            return true
        }
        return false
    }

    override fun getAdjacentVertices(v: Int): Pair<Int, List<Int>> =
        v to ArrayList<Int>().apply {
            require(v in validVertexRange) { "Vertex isn't valid." }

            repeat(numVertices) {
                if (isEdgePresent(v, it))
                    add(it)
            }

            // always return vertices in ascending order
            sort()
        }.toList()

    private fun HashMap<Int, List<Int>>.put(pair: Pair<Int, List<Int>>) =
        put(pair.first, pair.second)

    override val allVertices: Map<Int, List<Int>>
        get() = HashMap<Int, List<Int>>().apply {
            repeat(numVertices) { vertex ->
                this.put(getAdjacentVertices(vertex))
            }
        }.toMap()

    override fun getIndegree(v: Int): Int {
        require(v in validVertexRange) {
            "Invalid Vertex!"
        }

        return matrixGraph.count { it[v] != DEFAULT_MATRIX_VALUE }
    }

    override val indegreesOfAll: Map<Int, Int>
        get() = mutableMapOf<Int, Int>().apply {
            repeat(numVertices) { vertex ->
                this[vertex] = getIndegree(vertex)
            }
        }

    override fun toString(): String =
        buildString {
            append("\n\t")
            repeat(numVertices) { append("$it\t\t") }
            append("\n")

            repeat(numVertices) { i ->
                append("$i |\t")
                repeat(numVertices) { j ->
                    if (matrixGraph[i][j] == DEFAULT_MATRIX_VALUE)
                        append("0.00\t")
                    else
                        append("${matrixGraph[i][j]}\t")
                }
                append("\n")
            }
        }
}