package algorithms.shortest.path

import java.util.PriorityQueue
import java.util.Stack
import non.linear.graphs.WeightedGraph as WGraph

private const val INF = Int.MAX_VALUE

object WeightedGraphCompanion {
    // tells use the distance info for distance table
    private class DistanceInfo(var distance: Int = INF, var lastVertex: Int = -1)

    // VertexInfo class for storing weight of a graph
    class VertexInfo(val vertexId: Int, val distance: Int)

    // Comparator class for VertexInfoDistance
    class DistanceComparator: Comparator<VertexInfo> {
        override fun compare(
            v1: VertexInfo, v2: VertexInfo
        ): Int = v1.distance.compareTo(v2.distance)
    }

    private fun buildDistanceTable(graph: WGraph, source: Int): Map<Int, DistanceInfo> =
        HashMap<Int, DistanceInfo>().apply {
            repeat(graph.numVertices) { v ->
                put(v, DistanceInfo())
            }

            // setting first element
            set(source, DistanceInfo(0, source))
        }.also { distanceTable ->
            val sourceVertexInfo = VertexInfo(source, 0)

            val priorityQueue: PriorityQueue<VertexInfo> = PriorityQueue(DistanceComparator()).
                apply { add(sourceVertexInfo) }

            val vertexInfoMap = mutableMapOf<Int, VertexInfo>().
                apply { this[source] = sourceVertexInfo }

            while (priorityQueue.isNotEmpty()) {
                val currentVertex = priorityQueue.poll().vertexId

                for (vertexNeighbour in graph.getAdjacentVertices(currentVertex).second) {
                    // new distance from source to neighbour
                    val distance = distanceTable[currentVertex]!!.distance +
                            graph.getEdgeWeight(currentVertex, vertexNeighbour)

                    /* if we find new shortest path to neighbour update
                       the distance and the last vertex. */
                    if (distanceTable[vertexNeighbour]!!.distance > distance) {
                        distanceTable[vertexNeighbour] = DistanceInfo(distance.toInt(), currentVertex)

                        // we have found a new short path to neighbour so remove
                        // the old vertex from queue
                        vertexInfoMap[vertexNeighbour]?.let { neighbourVertexInfo ->
                            priorityQueue.remove(neighbourVertexInfo)
                        }

                        // add neighbour back with a new updated distance
                        VertexInfo(vertexNeighbour, distance.toInt()).let { newNeighbourVertexInfo ->
                            priorityQueue.add(newNeighbourVertexInfo)
                            vertexInfoMap[vertexNeighbour] = newNeighbourVertexInfo
                        }
                    }
                }
            }
        }

    @JvmStatic
    fun shortestPath(graph: WGraph, source: Int, destination: Int): String {
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

        return if (previousVertex == -1) {
            "There is no path from vertex: $source to $destination"
        } else buildString {
            append("Smallest path is $source")
            while (stack.isNotEmpty()) {
                append("->${stack.pop()}")
            }
            append(" Dijkstra DONE!!\n")
        }
    }
}