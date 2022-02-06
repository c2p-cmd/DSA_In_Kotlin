package algorithms.shortest.path

import non.linear.graphs.Graph
import java.util.*

private fun UnweightedGraph.DistanceInfo.isNotValidDistance() = this.distance == -1

object UnweightedGraph {
    data class DistanceInfo(var distance: Int = -1, var lastVertex: Int = -1)

    private fun buildDistanceTable(graph: Graph, source: Int): Map<Int, DistanceInfo> =
        HashMap<Int, DistanceInfo>().apply {
            // set all vertices to default values
            repeat(graph.numVertices) { i ->
                put(i, DistanceInfo())
            }
        }.also { distanceTable ->
            // setting source 0 dist & setting itself as lastVertex
            distanceTable[source] = DistanceInfo(0, source)

            // setting up the queue to handle BFS in the graph && adding source as first element
            val queue: Queue<Int> = LinkedList<Int>().apply { add(source) }

            while (queue.isNotEmpty()) {
                val currentVertex = queue.poll()

                for (vertexNeighbour in graph.getAdjacentVertices(currentVertex).second) {
                    distanceTable[vertexNeighbour]?.
                    takeIf(DistanceInfo::isNotValidDistance)?.
                    let { distInfo ->
                        distInfo.distance = 1 + distanceTable[currentVertex]!!.distance
                        distInfo.lastVertex = currentVertex

                        // enqueue neighbour only if it has other adjacent vertices
                        if (graph.getAdjacentVertices(1).second.isNotEmpty())
                            queue.add(vertexNeighbour)
                    }
                }
            }
        }

    @JvmStatic
    fun shortestPath(graph: Graph, source: Int, destination: Int): String {
        // distance table to keep track of distances
        val distanceTable = buildDistanceTable(graph, source)

        // stack to backtrack our steps
        val stack = Stack<Int>().apply {
            push(destination)
        }

        var previousVertex = distanceTable[destination]?.lastVertex
        while (previousVertex != -1 && previousVertex != source) {
            stack.push(previousVertex)
            previousVertex = distanceTable[previousVertex]!!.lastVertex
        }
        println("DistanceTable = $distanceTable")
        return if (previousVertex == -1) {
            "There is no path from vertex: $source to $destination"
        } else buildString {
            append("Smallest path is $source")
            while (stack.isNotEmpty()) {
                append("->${stack.pop()}")
            }
            append(" Shortest Path Unweighted DONE!!\n")
        }
    }
}