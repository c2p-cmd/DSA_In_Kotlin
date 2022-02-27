package algorithms.shortest.path

import non.linear.graphs.WeightedGraph
import java.util.LinkedList
import java.util.Stack

object NegativeWeightedGraphCompanion {
    data class DistanceInfo(var distance: Double = 1000000.00, var lastVertex: Int = -1)

    @JvmStatic
    private fun buildDistanceTable(graph: WeightedGraph, source: Int): Map<Int, DistanceInfo> =
        mutableMapOf<Int, DistanceInfo>().apply {
            repeat(graph.numVertices) {
                this[it] = DistanceInfo()
            }

            // set up distance table of specified source
            this[source]?.let {
                it.distance = 0.0
                it.lastVertex = source
            }

            // creating queue
            val queue = LinkedList<Int>()

            // relaxing all edges numVertices-1 times
            repeat(graph.numVertices-1) {

                // add every vertex to the queue, so we're sure to access all edges
                queue.addAll( 0 until graph.numVertices )

                // keep track of the edges visited, so we visit each edge just once
                val visitedEdges = HashSet<String>()
                while (queue.isNotEmpty()) {
                    val currentVertex = queue.pollFirst()

                    for (neighbour in graph.getAdjacentVertices(currentVertex).second) {
                        val edge = "$currentVertex$neighbour"

                        // do not visit edges more than once in each iteration
                        if (edge in visitedEdges)
                            continue
                        visitedEdges.add(edge)

                        val distance = this[currentVertex]?.distance?.plus(graph.getEdgeWeight(currentVertex, neighbour))

                        // if we find a new shortest path to neighbour update distance table
                        if (distance != null) {
                            if (distance < this[neighbour]!!.distance) {
                                this[neighbour]?.distance = distance
                                this[neighbour]?.lastVertex = currentVertex
                            }
                        }
                    }
                }
            }

            // check for negative cycles
            queue.addAll( 0 until graph.numVertices )

            // relaxing all edges one last time to check if negative cycle exists
            while (queue.isNotEmpty()) {
                val currentVertex = queue.pollFirst()
                for (neighbour in graph.getAdjacentVertices(currentVertex).second) {
                    val distance = this[currentVertex]!!.distance + graph.getEdgeWeight(currentVertex, neighbour)
                    if (this[neighbour]!!.distance > distance)
                        throw IllegalArgumentException("The graph has negative cycle.")
                }
            }
        }.toMap()


    @JvmStatic
    fun shortestPath(graph: WeightedGraph, source: Int, destination: Int): String {
        val distanceTable = buildDistanceTable(graph, source)

        val stack = Stack<Int>().apply {
            push(destination)
        }

        var previousVertex = distanceTable[destination]!!.lastVertex
        while (previousVertex != -1 && previousVertex != source) {
            stack.push(previousVertex)
            previousVertex = distanceTable[previousVertex]!!.lastVertex
        }

        return if (previousVertex == -1) {
            "There is no path from node: $source to node: $destination"
        } else buildString {
            append("Smallest path is $source")
            while (stack.isNotEmpty()) {
                append(" -> ${stack.pop()}")
            }
            append(" Bellman Ford DONE!")
        }
    }
}