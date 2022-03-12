package algorithms.minimal.spanning.tree

import non.linear.graphs.WeightedGraph
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap
import kotlin.collections.HashSet

const val INF = Int.MAX_VALUE

object PrimsAlgorithm {
    // tells use the distance info for distance table
    private class DistanceInfo(var distance: Int = INF, var lastVertex: Int = -1)

    // VertexInfo class for storing weight of a graph
    private class VertexInfo(val vertexId: Int, val distance: Int)

    private class ComparatorClass : Comparator<VertexInfo> {
        override fun compare(v1: VertexInfo, v2: VertexInfo): Int = v1.distance.compareTo(v2.distance)
    }

    @JvmStatic
    fun spanningTree(graph: WeightedGraph, source: Int): String {
        val distanceTable = HashMap<Int, DistanceInfo>()
        val queue: PriorityQueue<VertexInfo> = PriorityQueue(ComparatorClass())

        repeat(graph.numVertices) {
            distanceTable[it] = DistanceInfo()
        }

        distanceTable[source]?.let {
            it.distance = 0
            it.lastVertex = source
        }

        val vertexInfoMap = HashMap<Int, VertexInfo>()

        val sourceVertexInfo = VertexInfo(source, 0)
        queue.add(sourceVertexInfo)
        vertexInfoMap[source] = sourceVertexInfo

        val spanningTree = HashSet<String>()
        val visitedVertices = HashSet<Int>()

        while (queue.isNotEmpty()) {
            val vertexInfo = queue.poll()
            val currentVertex = vertexInfo.vertexId

            // do not re-visit vertices which are already part of the tree
            if (currentVertex in visitedVertices)
                continue

            visitedVertices.add(currentVertex)

            // if the current vertex is a source we don't have an edge yet.
            if (currentVertex != source) {
                val edge = currentVertex.toString() + distanceTable[currentVertex]?.lastVertex?.toString()
                if (edge !in spanningTree)
                    spanningTree.add(edge)
            }

            for (neighbour in graph.getAdjacentVertices(currentVertex).second) {
                val distance = graph.getEdgeWeight(currentVertex, neighbour).toInt()

                // if we find a new shortest path to the neighbour update
                // the distance and the last vertex
                if (distanceTable[neighbour]?.distance!! > distance) {
                    distanceTable[neighbour]?.distance = distance
                    distanceTable[neighbour]?.lastVertex = currentVertex

                    var neighbourVertexInfo = vertexInfoMap[neighbour]
                    if (neighbourVertexInfo != null) {
                        queue.remove(neighbourVertexInfo)
                    }

                    neighbourVertexInfo = VertexInfo(neighbour, distance)
                    vertexInfoMap[neighbour] = neighbourVertexInfo
                    queue.add(neighbourVertexInfo)
                }
            }
        }

        return buildString {
            for(edge in spanningTree) {
                append(edge).append("-> ")
            }
            append('\n')
        }
    }
}

object KruskalAlgorithm {
    // class which represents and edge is an undirected weighted graph
    private class EdgeInfo(val vertex1: Int, val vertex2: Int, val weight: Double) {
        override fun toString(): String = "$vertex1$vertex2"
    }

    private class EdgeComparator : Comparator<EdgeInfo> {
        override fun compare(e1: EdgeInfo, e2: EdgeInfo): Int = e1.weight.compareTo(e2.weight)
    }

    private fun hasCycle(edgeMap: Map<Int, MutableSet<Int>>) : Boolean {
        for (sourceVertex in edgeMap.keys) {
            val queue = LinkedList<Int>().apply {
                add(sourceVertex)
            }

            val visitedVertices = HashSet<Int>()
            while (queue.isNotEmpty()) {
                val currentVertex = queue.pollFirst()
                if (currentVertex in visitedVertices)
                    return true

                visitedVertices.add(currentVertex)
                queue.addAll(edgeMap[currentVertex]!!)
            }
        }

        return false
    }

    @JvmStatic
    fun spanningTree(graph: WeightedGraph): String {
        // priority queue to store and retrieve the edges on the basis of their weights
        val queue = PriorityQueue(EdgeComparator())

        // add all the edges to the priority queue
        repeat(graph.numVertices) { i ->
            for (neighbour in graph.getAdjacentVertices(i).second) {
                queue.add(
                    EdgeInfo(i, neighbour, graph.getEdgeWeight(i, neighbour))
                )
            }
        }

        val visitedVertices: HashSet<Int> = HashSet()
        val spanningTree: HashSet<EdgeInfo> = HashSet()
        val edgeMap = HashMap<Int, HashSet<Int>>().apply {
            repeat(graph.numVertices) { this[it] = HashSet() }
        }

        while (queue.isNotEmpty() && spanningTree.size < graph.numVertices - 1) {
            val currentEdge = queue.poll()

            // add the bew edge to the edge map and see if it ends up with a cycle
            // If yes then discard this edge and get the next edge from the priority queue
            edgeMap[currentEdge.vertex1]?.add(currentEdge.vertex2)
            if (hasCycle(edgeMap)) {
                edgeMap[currentEdge.vertex1]?.remove(currentEdge.vertex2)
                continue
            }

            spanningTree.add(currentEdge)

            // add both vertices to the visited list, the set will ensure
            // that only one copy of the vertex exists
            visitedVertices.addAll( arrayOf(currentEdge.vertex1, currentEdge.vertex2) )
        }

        // check whether all vertices have been covered with the spanning tree
        return if (visitedVertices.size != graph.numVertices) {
            "Minimum Spanning Tree isn't possible."
        } else
            buildString {
                append("Minimum spanning tree using Kruskal's Algorithm.\n")
                spanningTree.forEach {
                    append(it).append("-> ")
                }
                append('\n')
            }
    }
}
